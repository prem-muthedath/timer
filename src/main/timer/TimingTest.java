package timer;

import java.lang.reflect.Method;

public class TimingTest {
	private Method method;
	private TimingTests instance;

	public TimingTest(Method method, TimingTests instance) {
		this.method=method;
		this.instance=instance;
	}

	public void run(Timings timings)  throws Exception {
		if(skip()) return;
		timings.run(this);
	}

	private boolean skip() throws Exception {
		Class methodClass=method.getDeclaringClass();
		return (methodClass.equals(Object.class) || methodClass.equals(TimingTests.class));
	}

	public MethodTime time() throws Exception {
		return new MethodTime(method.getName(), instance.time(this));
	}

	public long timeFor(int iterations) throws Exception {
 		long start = System.nanoTime();
 		for (int i = 0; i < iterations; i++)
 			method.invoke(instance, new Object[0]);
 		return System.nanoTime() - start;
 	}
}
