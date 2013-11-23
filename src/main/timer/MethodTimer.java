package timer;

public class MethodTimer {
	private TimedMethod method;  
	private TimedMethod overhead;

	public MethodTimer(TimedMethod method, TimedMethod overhead) {
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
