package timer.framework;

import java.util.Comparator;

import timer.output.base.Format;

public class TestTiming implements Comparable<TestTiming>, Comparator<TestTiming> {
	private int size;
	private String method;
	private double timing;

	public TestTiming(int size, String method, double timing) {
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

	public int compareTo(TestTiming another)  {
		return size-another.size==0  ?  method.compareTo(another.method)  :  size-another.size;
	}

	public int compare(TestTiming one, TestTiming another)  {
		return one.method.compareTo(another.method)==0  ?  one.size-another.size  :  one.method.compareTo(another.method);				
	}	
}
