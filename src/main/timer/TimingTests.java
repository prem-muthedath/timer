package timer;

import java.lang.reflect.Method;

public abstract class TimingTests {
	private int iterations;
	private long totalTime;	

	public void run(Timings timings) throws Exception {
		for (Method each : this.getClass().getMethods()) 
			timings.run(each, this);
	}

	double time(Method method) throws Exception {
		TimingTest test=new TimingTest(method, this);
		iterations=1;
		totalTime=0L;
		while (true) {
			totalTime=test.timeFor(iterations);
			if(totalTime > CollectionPerformance.ONE_SECOND) break;
			iterations*=2;
		}
		return average(overhead());
	}

	private long overhead() throws Exception {
		Method method=getClass().getMethod("nothing", new Class[0]);
		return new TimingTest(method, this).timeFor(iterations);
	}

	public void nothing() {}

	private double average(long overheadTime) throws Exception { 
		return (double) (totalTime - overheadTime) / (double) iterations;
	}	
}
