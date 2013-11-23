package timer;

import java.lang.reflect.Method;

public class TimedMethod {
	private Method method;
	private Object instance;

	public TimedMethod(Method method, Object instance) {
		this.method=method;
		this.instance=instance;
	}

	public long timeFor(int iterations) throws Exception {
 		long start = System.nanoTime();
 		for (int i = 0; i < iterations; i++)
 			method.invoke(instance, new Object[0]);
 		return System.nanoTime() - start;
 	}
}
