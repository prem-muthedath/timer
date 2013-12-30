package timer.framework;

import java.util.List;
import java.util.ArrayList;

public class Timings {
	private List<TestTiming> results=new ArrayList<TestTiming>();

	public void run(TimingTest test) throws Exception {
		results.add(test.run());
	}	

	public TestTiming[] export() {
		return results.toArray(new TestTiming[results.size()]);
	}
}
