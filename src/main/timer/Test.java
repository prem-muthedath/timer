package timer;

import java.util.Comparator;

public class Test {
	private int size;
	private String method;
	private double timing;

	public enum Order {BY_METHOD, BY_SIZE}

	public Test(int size, String method, double timing) {
		this.size=size;
		this.method=method;
		this.timing=timing;
	}

	public void group(Report report)  {
		report.groupTest(size, method, timing);
	}

	public static Comparator<Test> comparator(Test.Order order)  {
		if(order==Test.Order.BY_METHOD) {
			return new Comparator<Test>() {
				public int compare(Test one, Test another)  {
					return one.method.compareTo(another.method)==0  ?  one.size-another.size  :  one.method.compareTo(another.method);				
				}	
			};
		}	
		return new Comparator<Test>() {
			public int compare(Test one, Test another)  {
				return one.size-another.size==0  ?  one.method.compareTo(another.method)  :  one.size-another.size;
			}	
		};
	}

	public int hashCode() {
		return size+method.hashCode();
	}

	public boolean equals(Object another)  {
		if(another instanceof Test) {
			Test that=(Test) another;
			return that.method.equals(this.method) && that.size==this.size;
		}
		return false;
	}	
}
