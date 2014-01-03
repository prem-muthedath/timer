package timer.framework;

import java.lang.reflect.Constructor;

public class CollectionTimer {
	private static final int MAXIMUM_SIZE= 100000;
	static final int ONE_SECOND= 1000000000;	

	private Class<?> timingTests;

	public CollectionTimer(Class<?> timingTests) {
		this.timingTests=timingTests;
	}

	public void report(Report report) throws Exception { 
		for (int size= 1; size <= MAXIMUM_SIZE; size*= 10)
			testsInstance(size).run(report);
		report.print();
	}

	private TimingTests testsInstance(int size) throws Exception {
		Constructor<?> constructor= timingTests.getConstructor(new Class[]{int.class});
		return (TimingTests) constructor.newInstance(new Object[]{size});
	}
}
