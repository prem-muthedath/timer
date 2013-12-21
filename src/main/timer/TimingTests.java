package timer;

import java.util.List;
import java.util.ArrayList;
import java.lang.reflect.Method;

 public abstract class TimingTests {
	public void run(Report report) throws Exception {
		List<Timing> timings=new ArrayList<Timing>();
		for (Method each : this.getClass().getMethods()) {
			if(skip(each)) continue;
			timings.add(new TimingTest(each, this).run());
		}
		report.add(size(), timings.toArray(new Timing[timings.size()]));
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
}
