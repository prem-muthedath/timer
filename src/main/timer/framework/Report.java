package timer.framework;

import java.util.List;
import java.util.ArrayList;

import timer.output.base.Format;

public abstract class Report {
	protected List<TestTiming> timings=new ArrayList<TestTiming>();

	public void run(TimingTest test) throws Exception  {
		timings.add(test.run());
	}

	public void print(Format format) {
		sort();  
		for (TestTiming each : timings)
			export(each, format);
		format.print();
	}

	protected abstract void sort();
	protected abstract void export(TestTiming timings, Format format);
}
