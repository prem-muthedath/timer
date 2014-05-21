package timer.framework;

import java.util.Map;
import java.util.HashMap;

import timer.output.base.Format;

public abstract class Report {
	protected Map<TestInstance, Timing> timings=new HashMap<TestInstance, Timing>();

	public void run(TestInstance testInstance, TimingTest test) throws Exception  {
		timings.put(testInstance, test.run());
	}

	public void print(Format format) {
		Map<TestInstance, Timing> sorted=sort();
		for(TestInstance each : sorted.keySet())
			export(each, sorted.get(each), format);
		format.print();
	}

	protected abstract Map<TestInstance, Timing> sort();
	protected abstract void export(TestInstance testInstance, Timing timing, Format format);
}
