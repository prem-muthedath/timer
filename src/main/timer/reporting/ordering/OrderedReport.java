package timer.reporting.ordering;

import timer.framework.Report;

import timer.reporting.base.View;

import java.util.Map;
import java.util.HashMap;
import java.util.TreeMap;
import java.util.Comparator;

public abstract class OrderedReport extends Report {
	private Map<TestInstance, Timing> timings=new HashMap<TestInstance, Timing>();

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

	Map<TestInstance, Timing> sizeSort() {
		return new TreeMap<TestInstance, Timing>(timings);
	}

	Map<TestInstance, Timing> methodSort() {
		Map<TestInstance, Timing> sorted=new TreeMap<TestInstance, Timing>(comparator());
		sorted.putAll(timings);
		return sorted;
	}
	
	private TestInstance comparator() {
		return timings.isEmpty()  ?   null  :  timings.keySet().toArray(new TestInstance[0])[0];
	}

	abstract Map<TestInstance, Timing> sort();
	abstract void export(TestInstance testInstance, Timing timing, View view);
}