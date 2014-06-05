package timer.reporting.ordering;

import timer.framework.Report;

import timer.reporting.base.View;

import java.util.Map;
import java.util.HashMap;

public abstract class OrderedReport extends Report {
	protected Map<TestInstance, Timing> timings=new HashMap<TestInstance, Timing>();

	protected void add(int size, String method, double timing)	{
		timings.put(testInstance(size, method), new Timing(timing));
	}

	private TestInstance testInstance(int size, String method) {
		return new TestInstance(new Size(size), new Method(method));
	}

	public void render(View view) {
		Map<TestInstance, Timing> sorted=sort();
		for(TestInstance each : sorted.keySet())
			export(each, sorted.get(each), view);
		view.render();
	}

	abstract Map<TestInstance, Timing> sort();
	abstract void export(TestInstance testInstance, Timing timing, View view);
}