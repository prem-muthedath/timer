package timer.reporting.ordering;

import java.util.Comparator;

import timer.reporting.base.Field;
import timer.reporting.base.View;

public class TestInstance implements Comparable<TestInstance>, Comparator<TestInstance> {
	private Size size;
	private Method method;

	public TestInstance(Size size, Method method) {
		this.size=size;
		this.method=method;				
	}

	public void exportBySize(Timing timing, View view) {
		view.add(new Field[]{method, size, timing});
	}

	public void exportByMethod(Timing timing, View view) {
		view.add(new Field[]{size, method, timing});
	}

	public int compareTo(TestInstance another)  {
		return size.compareTo(another.size)==0  ?  method.compareTo(another.method)  :  size.compareTo(another.size);
	}

	public int compare(TestInstance one, TestInstance another)  {
		return one.method.compareTo(another.method)==0  ?  one.size.compareTo(another.size)  :  one.method.compareTo(another.method);				
	}	
}
