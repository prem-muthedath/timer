package timer.framework;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public abstract class Layout {
	private List<Test> tests=new ArrayList<Test>();

	public void addTests(int size, Timing[] timings)  {
		for (Timing each : timings)
			each.export(size, this);
	}
	
	public abstract void addTest(int size, String method, double timing);

	protected void add(Test test) {
		tests.add(test);
	}

	public void print(Report report) {
		Content content=new Content(report);
		Collections.sort(tests);
		for (Test each : tests)
			each.export(content);
		content.print(this);
	}

	public void print(Report report, TestGroup[] groups)  {
		System.out.println(report.header());
		for (TestGroup each : groups)
			System.out.println(each);
	}
}
