package timer.framework;

import java.lang.reflect.Constructor;

public class CollectionTimer {
	private static final int MAXIMUM_SIZE= 100000;
	static final int ONE_SECOND= 1000000000;	

	private TimingTests tests;

	public CollectionTimer(TimingTests tests) {
		this.tests=tests;
	}

	public void report(Timings timings) throws Exception { 
		for (int size= 1; size <= MAXIMUM_SIZE; size*= 10)
			testsInstance(size).run(timings);
	}

	private TimingTests testsInstance(int size) throws Exception {
		Constructor<?> constructor= tests.getClass().getConstructor(new Class[]{int.class});
		return (TimingTests) constructor.newInstance(new Object[]{size});
	}
}
