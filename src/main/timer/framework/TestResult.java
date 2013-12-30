package timer.framework;

public class TestResult {
	private TimingTest test;
	private double timing;

	public TestResult(TimingTest test, double timing) {
		this.test=test;
		this.timing=timing;
	}

	public void export(TestResultExport export)  {
		export.timing(timing);
		test.export(export);		
	}
}
