package timer.framework;

import java.lang.reflect.Method;
import java.util.Arrays;

import timer.reporting.base.TestResult;

 public abstract class TimingTests {
	public void run(Timings timings) throws Exception {
		for (Method each : this.getClass().getMethods()) {
			if(skip(each)) continue;
			timings.run(new TimingTest(each, this));
		}
	}

	private boolean skip(Method method) throws Exception {
		Class methodClass=method.getDeclaringClass();
		return (methodClass.equals(Object.class) || methodClass.equals(TimingTests.class));
	}

	protected abstract int size();

	double timing(IterationsTime iterationsTime) throws Exception {
		return iterationsTime.timing(overhead());
	}

	private TimingTest overhead() throws Exception {
		Method method=getClass().getMethod("nothing", new Class[0]);
		return new TimingTest(method, this);
	}

	public void nothing() {}

 	public void export(TestResult result)  {
		result.size(size());
	}		
}
