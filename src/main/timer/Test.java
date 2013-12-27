package timer;

import java.util.Comparator;

public abstract class Test implements Comparable<Test> {
	protected int size;
	protected String method;
	protected double timing;

	public Test(int size, String method, double timing) {
		this.size=size;
		this.method=method;
		this.timing=timing;
	}

	public void export(Content content)  {
		content.add(headerMargin(), sizeHeader(), methodGroup());
	}

	public void exportBySize(Content content)  {
		content.add(headerMargin(), methodHeader(), sizeGroup());
	}

	protected abstract String headerMargin();
	protected abstract String sizeHeader();
	protected abstract TestGroup methodGroup();
	protected abstract String methodHeader();	
	protected abstract TestGroup sizeGroup();

	public int compareTo(Test another)  {
		return method.compareTo(another.method)==0  ?  size-another.size  :  method.compareTo(another.method);				
	}

	public static Comparator<Test> sizeComparator()  {
		return new Comparator<Test>() {
			public int compare(Test one, Test another)  {
				return one.size-another.size==0  ?  one.method.compareTo(another.method)  :  one.size-another.size;
			}	
		};
	}
}	