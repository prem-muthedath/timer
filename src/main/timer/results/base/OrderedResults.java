package timer.results.base;

import timer.framework.Results;

import timer.reporting.base.View;
import timer.reporting.base.Format;

import java.util.Map;
import java.util.TreeMap;
import java.util.Comparator;

public abstract class OrderedResults extends Results {
	private Map<TestInstance, Timing> timings;

	public OrderedResults(Comparator<TestInstance> order) {
		this.timings=new TreeMap<TestInstance, Timing>(order);	
	}

	protected void add(int size, String method, double timing)	{
		timings.put(testInstance(size, method), new Timing(timing));
	}

	private TestInstance testInstance(int size, String method) {
		return new TestInstance(new Size(size), new Method(method));
	}

	public void render(View view, Format format) {
		for(TestInstance each : timings.keySet())
			export(each, timings.get(each), format);
		format.render(view);
	}

	protected abstract void export(TestInstance testInstance, Timing timing, Format format);
}