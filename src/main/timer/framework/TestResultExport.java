package timer.framework;

import java.util.Comparator;

public abstract class TestResultExport implements Comparable<TestResultExport>, Comparator<TestResultExport> {
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
		report.add(sizeGroup());
	}

	public void exportByMethod(Report report,  Title title) {
		methodHeader(title);
		report.add(methodGroup());
	}

	protected abstract TestGroup sizeGroup();
	protected abstract TestGroup methodGroup();
	protected abstract void sizeHeader(Title title);
	protected abstract void methodHeader(Title title);

	public int compare(TestResultExport one, TestResultExport another)  {
		return one.method.compareTo(another.method)==0  ?  one.size-another.size  :  one.method.compareTo(another.method);				
	}	

	public int compareTo(TestResultExport another)  {
		return size-another.size==0  ?  method.compareTo(another.method)  :  size-another.size;
	}	
}
