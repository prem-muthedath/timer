package timer;

public class MethodTimer {
	private TestMethod test;  
	private TestMethod overhead;

	public MethodTimer(TestMethod test, TestMethod overhead) {
		this.test= test;
		this.overhead=overhead;
	}

	public double time() throws Exception {		
		int iterations=1;
		long totalTime=0L;
		while (true) {
			totalTime=test.timeFor(iterations);
			if(totalTime > MethodsTimer.ONE_SECOND) break;
			iterations*=2;
		}
		return average(iterations, totalTime);
	}

	private double average(int iterations, long totalTime) throws Exception { 
		long overheadTime=overhead.timeFor(iterations);
		return (double) (totalTime - overheadTime) / (double) iterations;
	}
}
