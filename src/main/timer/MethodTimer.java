package timer;

public class MethodTimer {
	private TestMethod method;  
	private TestMethod overhead;

	public MethodTimer(TestMethod method, TestMethod overhead) {
		this.method= method;
		this.overhead=overhead;
	}

	public double time() throws Exception {
		return compute().methodTime(overhead);
	}

	private MethodTimerResults compute() throws Exception {
		int iterations=1;
		long totalTime=0L;
		while (true) {
			totalTime=method.timeFor(iterations);
			if(totalTime > MethodsTimer.ONE_SECOND) break;
			iterations*=2;
		}
		return new MethodTimerResults(iterations, totalTime);
	}
}
