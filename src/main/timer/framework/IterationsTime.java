package timer.framework;

 public class IterationsTime  {
 	private int iterations;
 	private long totalTime;

 	IterationsTime(int iterations, long totalTime)  {
 		this.iterations=iterations;
 		this.totalTime=totalTime;
 	}

 	double timing(TimingTest overhead)  throws Exception {
		long overheadTime=overhead.totalTime(iterations);
		return (double) (totalTime - overheadTime) / (double) iterations; 		
 	}
 }	
