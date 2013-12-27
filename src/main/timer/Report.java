package timer;

import java.util.List;
import java.util.ArrayList;

public abstract class Report {
	protected List<Test> tests=new ArrayList<Test>();
	private Format format;

	public Report(Format format)  {
		this.format=format;
	}

	public void addTests(int size, Timing[] timings) {	
		for (Timing each : timings)
			each.export(size, this);
	}

	public void addTest(int size, String method, double timing) {
		tests.add(format.test(size, method, timing));
	}	

	public void print() {
		Content content=new Content();
		sort();
		for (Test each : tests)
			export(each, content);
		content.print();
	}

	protected abstract void sort();
	protected abstract void export(Test test, Content content);
}
