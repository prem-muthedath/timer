package timer.reporting.ordering;

import timer.reporting.base.Field;
import timer.reporting.base.View;

import java.util.Comparator;

public class TestInstance {
	private Size size;
	private Method method;

	TestInstance(Size size, Method method) {
		this.size=size;
		this.method=method;				
	}

	void exportBySize(Timing timing, View view) {
		view.add(new Field[]{method, size, timing});
	}

	void exportByMethod(Timing timing, View view) {
		view.add(new Field[]{size, method, timing});
	}

	static Comparator<TestInstance> sizeComparator() {
		return new Comparator<TestInstance>() {
			public int compare(TestInstance one, TestInstance another)  {
				return one.size.compareTo(another.size)==0  ?  one.method.compareTo(another.method)  :  one.size.compareTo(another.size);
			}
		};
	}
	
	static Comparator<TestInstance> methodComparator() {
		return new Comparator<TestInstance>() {
			public int compare(TestInstance one, TestInstance another)  {
				return one.method.compareTo(another.method)==0  ?  one.size.compareTo(another.size)  :  one.method.compareTo(another.method);				
			}
		};
	}
}