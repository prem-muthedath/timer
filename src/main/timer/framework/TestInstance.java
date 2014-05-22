package timer.framework;

import java.util.Comparator;

import timer.output.base.Format;

public class TestInstance implements Comparable<TestInstance>, Comparator<TestInstance> {
	private Size size;
	private Method method;

	public TestInstance(Size size, Method method) {
		this.size=size;
		this.method=method;				
	}

	public void exportBySize(Timing timing, Format format) {
		format.add(new Field[]{method, size, timing});
	}

	public void exportByMethod(Timing timing, Format format) {
		format.add(new Field[]{size, method, timing});
	}

	public int compareTo(TestInstance another)  {
		return size.compareTo(another.size)==0  ?  method.compareTo(another.method)  :  size.compareTo(another.size);
	}

	public int compare(TestInstance one, TestInstance another)  {
		return one.method.compareTo(another.method)==0  ?  one.size.compareTo(another.size)  :  one.method.compareTo(another.method);				
	}	
}
