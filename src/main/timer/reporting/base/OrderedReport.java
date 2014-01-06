package timer.reporting.base;

import timer.framework.Report;

import java.util.List;
import java.util.ArrayList;

import timer.reporting.types.Format;

public abstract class OrderedReport extends Report {
	protected List<TestResult> results=new ArrayList<TestResult>();
	private Format format;

	public OrderedReport(Format format)  {
		this.format=format;
	}	

	protected void add(int size, String method, double timing)	{
		results.add(format.testResult(size, method, timing));
	}

	public void print() {
		sort();  
		Contents contents=new Contents();
		for (TestResult each : results)
			export(each, contents);
		contents.print();
	}

	protected abstract void sort();
	protected abstract void export(TestResult result, Contents contents);
}