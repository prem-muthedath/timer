package timer.framework;

import java.lang.reflect.Method;
	
public abstract class TimingTests {
	public void run(Report report) throws Exception {
		for (Method each : this.getClass().getMethods()) {
			if(skip(each)) continue;
			report.run(each, copy());
		}
	}

	private boolean skip(Method method) throws Exception {
		Class methodClass=method.getDeclaringClass();
		return (methodClass.equals(Object.class) || methodClass.equals(TimingTests.class));
	}

	/** 
	 * copy() needed for accurate method timing; else, if, for a given size, 
	 * we use the same instance for all methods, JVM caches the instance across methods, 
	 * resulting in lower reported method timings, especially for later methods and large sizes. 
	 */
	protected abstract TimingTests copy(); 
	protected abstract int size();

	double timing(IterationsTime iterationsTime) throws Exception {
		return iterationsTime.timing(overhead());
	}

	private TimingTest overhead() throws Exception {
		Method method=getClass().getMethod("nothing", new Class[0]);
		return new TimingTest(method, this);
	}

	public void nothing() {}
}
