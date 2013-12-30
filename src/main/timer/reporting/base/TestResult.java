package timer.reporting.base;

import java.util.Comparator;

public abstract class TestResult implements Comparable<TestResult>, Comparator<TestResult> {
	protected int size;
	protected String method;
	protected double timing;

	public void timing(double timing) {
		this.timing=timing;
	}

	public void method(String method)  {
		this.method=method;
	}

	public void size(int size)  {
		this.size=size;
	}

	public void exportBySize(Report report, Title title) {
		sizeHeader(title);
		report.add(sizeContent());
	}

	public void exportByMethod(Report report,  Title title) {
		methodHeader(title);
		report.add(methodContent());
	}

	protected abstract Content sizeContent();
	protected abstract Content methodContent();
	protected abstract void sizeHeader(Title title);
	protected abstract void methodHeader(Title title);

	public int compare(TestResult one, TestResult another)  {
		return one.method.compareTo(another.method)==0  ?  one.size-another.size  :  one.method.compareTo(another.method);				
	}	

	public int compareTo(TestResult another)  {
		return size-another.size==0  ?  method.compareTo(another.method)  :  size-another.size;
	}	
}
