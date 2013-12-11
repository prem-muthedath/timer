package timer;

import java.lang.reflect.Constructor;

public class CollectionTimer {
	static final int MINIMUM_SIZE=1;			
	private static final int MAXIMUM_SIZE= 100000;
	static final int ONE_SECOND= 1000000000;	

	private TimingTests tests;

	public CollectionTimer(TimingTests tests) {
		this.tests=tests;
	}

	public void report() throws Exception { 
		Timings timings;
		for (int size= MINIMUM_SIZE; size <= MAXIMUM_SIZE; size*= 10) {
			timings=new Timings();
			testsInstance(size).run(timings);
			new CollectionSize(size).print(timings);
		}
	}

	private TimingTests testsInstance(int size) throws Exception {
		Constructor<?> constructor= tests.getClass().getConstructor(new Class[]{int.class});
		return (TimingTests) constructor.newInstance(new Object[]{size});
	}
}
