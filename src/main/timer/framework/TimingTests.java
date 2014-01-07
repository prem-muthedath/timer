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
	 * copy() needed for accurate method timing.  
	 * For a given collection size, when we time all methods using the SAME instance of TimingTests,
	 * the JVM caches the instance across methods when it executes method.invoke(instance, new Object[0]).
	 * This results in lower reported method timings, especially if the method has HIGH execution times 
	 * (>= 200 ns) for large collection sizes (>= 1000) and uses the SAME collection used by an earlier method. 
	 * With copy(), we supply a new instance for each method, thus avoiding the caching problem.
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
