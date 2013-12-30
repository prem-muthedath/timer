package timer.framework;

import timer.reporting.base.TestResult;

public class TestTiming {
	private TimingTest test;
	private double timing;

	public TestTiming(TimingTest test, double timing) {
		this.test=test;
		this.timing=timing;
	}

	public void export(TestResult result)  {
		result.timing(timing);
		test.export(result);		
	}
}
