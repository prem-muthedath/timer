package timer.framework;

import java.util.Comparator;

import timer.output.base.Format;

public class TestResult implements Comparable<TestResult>, Comparator<TestResult> {
	private int size;
	private String method;
	private double timing;

	public TestResult (int size, String method, double timing) {
		this.size=size;
		this.method=method;
		this.timing=timing;
	}

	public void exportBySize(Format format) {
		format.addBySize(size, method, timing);
	}

	public void exportByMethod(Format format) {
		format.addByMethod(size, method, timing);
	}

	public int compareTo(TestResult another)  {
		return size-another.size==0  ?  method.compareTo(another.method)  :  size-another.size;
	}

	public int compare(TestResult one, TestResult another)  {
		return one.method.compareTo(another.method)==0  ?  one.size-another.size  :  one.method.compareTo(another.method);				
	}	
}
