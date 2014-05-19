package timer.framework;

 public class TestCalibration  {
 	private int iterations;
 	private long totalTime;

 	TestCalibration(int iterations, long totalTime)  {
 		this.iterations=iterations;
 		this.totalTime=totalTime;
 	}

 	Timing timing(TimingTest overhead)  throws Exception {
		long overheadTime=overhead.totalTime(iterations);
		return new Timing((double) (totalTime - overheadTime) / (double) iterations); 		
 	}
 }	