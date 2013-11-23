package timer;

public class MethodTimerResults {
	private int iterations=0;
	private long totalTime=0L;

	public MethodTimerResults(int iterations, long totalTime) {
		this.iterations=iterations;
		this.totalTime=totalTime;
	}

	public double methodTime(TimedMethod overhead) throws Exception { 
		long overheadTime=overhead.timeFor(iterations);
		return (double) (totalTime - overheadTime) / (double) iterations;
	}
}
