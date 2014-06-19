package timer.results.base;

import timer.reporting.base.Format;

import java.util.Comparator;

public class TestInstance {
	private Size size;
	private Method method;

	TestInstance(Size size, Method method) {
		this.size=size;
		this.method=method;				
	}

	public void exportBySize(Timing timing, Format format) {
		new TestResultExport(size, method, timing).dumpTo(format);
	}

	public void exportByMethod(Timing timing, Format format) {
		new TestResultExport(method, size, timing).dumpTo(format);
	}

	public static Comparator<TestInstance> sizeOrder() {
		return new Comparator<TestInstance>() {
			public int compare(TestInstance one, TestInstance another)  {
				return one.size.compareTo(another.size)==0  ?  one.method.compareTo(another.method)  :  one.size.compareTo(another.size);
			}
		};
	}
	
	public static Comparator<TestInstance> methodOrder() {
		return new Comparator<TestInstance>() {
			public int compare(TestInstance one, TestInstance another)  {
				return one.method.compareTo(another.method)==0  ?  one.size.compareTo(another.size)  :  one.method.compareTo(another.method);				
			}
		};
	}
}