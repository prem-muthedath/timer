package timer.reporting.base;

import java.util.Comparator;

public abstract class TestResult implements Comparable<TestResult>, Comparator<TestResult> {
	protected int size;
	protected String method;
	protected double timing;

	public TestResult (int size, String method, double timing) {
		this.size=size;
		this.method=method;
		this.timing=timing;
	}

	public void exportBySize(Contents contents) {
		contents.add(sizeTitle(), sizeContent());
	}

	public void exportByMethod(Contents contents) {
		contents.add(methodTitle(), methodContent());
	}

	protected abstract Title sizeTitle();
	protected abstract Content sizeContent();	
	protected abstract Title methodTitle();
	protected abstract Content methodContent();	

	public int compareTo(TestResult another)  {
		return size-another.size==0  ?  method.compareTo(another.method)  :  size-another.size;
	}

	public int compare(TestResult one, TestResult another)  {
		return one.method.compareTo(another.method)==0  ?  one.size-another.size  :  one.method.compareTo(another.method);				
	}	
}
