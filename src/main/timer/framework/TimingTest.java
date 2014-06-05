package timer.framework;

import java.lang.reflect.Method;

public class TimingTest {
	private Method method;
	private TimingTests instance;

	TimingTest(Method method, TimingTests instance) {
		this.method=method;
		this.instance=instance;
	}

	double timing() throws Exception {
		return instance.timing(run());
	}

	private TestRun run() throws Exception {
		int iterations=1;
		long totalTime=0L;
		while (true) {
			totalTime=totalTime(iterations);
			if(totalTime > CollectionTimer.ONE_SECOND) break;
			iterations*=2;
		}
		return new TestRun(iterations, totalTime);		
	}

	long totalTime(int iterations) throws Exception {
 		long start = System.nanoTime();
 		for (int i = 0; i < iterations; i++)
 			method.invoke(instance, new Object[0]);
 		return System.nanoTime() - start;
 	}
 }
