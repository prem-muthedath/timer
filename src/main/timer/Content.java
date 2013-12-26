package timer;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class Content  {
	private List<Test> tests=new ArrayList<Test>();
	private TestGroups groups=new TestGroups();

	public void addTests(int size, Timing[] timings) {	
		for (Timing each : timings)
			each.export(size, this);
	}

	public void addTest(int size, String method, double timing)  {
		tests.add(new Test(size, method, timing));
	}

	public void print(Report report, Test.Order order) {
		Collections.sort(tests, Test.comparator(order));
		for (Test each : tests)
			each.group(report);
		groups.print(report);
	}

	public void addGroup(String group, String timing)  {
		groups.add(group, timing);
	}	
}
