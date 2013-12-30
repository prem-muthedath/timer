package timer.framework;

import java.util.List;
import java.util.ArrayList;

public class Timings {
	private List<TestResult> results=new ArrayList<TestResult>();

	public void run(TimingTest test) throws Exception {
		results.add(test.run());
	}	

	public TestResult[] export() {
		return results.toArray(new TestResult[results.size()]);
	}
}
