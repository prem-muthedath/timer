package timer.framework;

import java.util.List;
import java.util.ArrayList;

import timer.output.base.Format;

public abstract class Report {
	protected List<TestResult> results=new ArrayList<TestResult>();

	public void run(TimingTest test) throws Exception  {
		results.add(test.run());
	}

	private void add(TestResult result)	{
		results.add(result);
	}

	public void print(Format format) {
		sort();  
		for (TestResult each : results)
			export(each, format);
		format.print();
	}

	protected abstract void sort();
	protected abstract void export(TestResult result, Format format);
}
