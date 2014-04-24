package timer.framework;

import java.lang.reflect.Method;
	
public abstract class TimingTests {
	public void run(Report report) throws Exception {
		for (Method each : this.getClass().getMethods()) {
			if(skip(each)) continue;
			new TimingTest(each, copy()).run(report);
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
	 * For methods whose timings INCREASE with collection size, timings become dependent on execution order.
	 * That is, if method A uses the SAME collection as method B, timings reported when method A is executed 
	 * BEFORE method B is DIFFERENT from the timings reported when method A is executed AFTER method B.
	 * The errors get big for LARGE collection sizes (>= 1000) and LONG method execution times (>= 200 ns). 
	 * With copy(), we supply a new instance for each method, thus avoiding the caching problem.
	 */
	protected abstract TimingTests copy(); 
	protected abstract int size();

	TestResult testResult(Method method, IterationsTime iterationsTime) throws Exception {
		return new TestResult(size(), method.getName(), iterationsTime.timing(overhead()));
	}

	private TimingTest overhead() throws Exception {
		Method method=getClass().getMethod("nothing", new Class[0]);
		return new TimingTest(method, this);
	}

	public void nothing() {}
}
