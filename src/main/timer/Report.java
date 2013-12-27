package timer;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public abstract class Report {
	protected List<Test> tests=new ArrayList<Test>();
	private Order order;

	public Report(Order order)  {
		this.order=order;
	}

	public void addTests(int size, Timing[] timings) {	
		tests.addAll(Arrays.asList(order.tests(size, timings)));
	}

	public void print() {
		Content content=new Content(this);
		Collections.sort(tests);
		for (Test each : tests)
			each.export(content);
		content.print();
	}

	protected abstract TestGroup methodGroup(int size, String method, double timing);
	protected abstract TestGroup sizeGroup(int size, String method, double timing);

	public void print(TestGroup[] groups)  {
		printHeader();
		for (TestGroup each : groups)
			System.out.println(each);
	}

	protected void printHeader() {
		System.out.println();
	}		
}
