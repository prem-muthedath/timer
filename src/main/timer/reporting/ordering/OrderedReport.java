package timer.reporting.ordering;

import timer.framework.Report;

import timer.reporting.base.View;

import java.util.Map;
import java.util.TreeMap;
import java.util.Comparator;

abstract class OrderedReport extends Report {
	private Map<TestInstance, Timing> timings;

	OrderedReport(Comparator<TestInstance> comparator) {
		this.timings=new TreeMap<TestInstance, Timing>(comparator);	
	}

	OrderedReport() {
		this.timings=new TreeMap<TestInstance, Timing>();	
	}

	protected void add(int size, String method, double timing)	{
		timings.put(testInstance(size, method), new Timing(timing));
	}

	private TestInstance testInstance(int size, String method) {
		return new TestInstance(new Size(size), new Method(method));
	}

	public void render(View view) {
		for(TestInstance each : timings.keySet())
			export(each, timings.get(each), view);
		view.render();
	}

	abstract void export(TestInstance testInstance, Timing timing, View view);
}