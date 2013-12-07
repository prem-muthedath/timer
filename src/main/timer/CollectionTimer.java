package timer;

import java.lang.reflect.Constructor;

public class CollectionTimer {
	private static final int MAXIMUM_SIZE= 100000;
	static final int ONE_SECOND= 1000000000;	

	private TimingTests tests;

	public CollectionTimer(TimingTests tests) {
		this.tests=tests;
	}

	public void report() throws Exception { 
		Timings timings=new Timings();
		for (int size= 1; size <= MAXIMUM_SIZE; size*= 10) {
			createInstance(size).run(timings);
			timings.print(size);
			System.out.println();
		}
	}

	private TimingTests createInstance(int size) throws Exception {
		Constructor<?> constructor= tests.getClass().getConstructor(new Class[]{int.class});
		return (TimingTests) constructor.newInstance(new Object[]{size});
	}
}
