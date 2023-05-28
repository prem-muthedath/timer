package timer.framework;

import java.lang.reflect.Constructor;

/** `CollectionTimer`, as the name implies, runs a set of timing tests, 
 *  encapsulated in a class, over a range of collection sizes.
 *
 *  original idea and parts of source code for this class come from Kent Beck's 
 *  'Implementation Patterns' book. But this class itself is absent in Beck's 
 *  design. Prem created this class as part of his refactoring of Beck's code.
 *
 *  java 1.7 API reference
 *  https://docs.oracle.com/en/java/javase/17/docs/api/
 *
 *  author: Prem Muthedath (with parts of source code and ideas from Kent Beck)
 */
public class CollectionTimer {
  /* define some useful constants. this is Kent Beck's code. */
  private static final int MAXIMUM_SIZE = 100000;
  static final int ONE_SECOND = 1000000000;

  private Class<? extends TimingTests> timingTests;

  public CollectionTimer(Class<? extends TimingTests> timingTests) {
    this.timingTests = timingTests;   /* set of timing tests to be timed */
  }

  /* run timing tests over a range of collection sizes & record the results. */
  public void run(Results results) throws Exception {
    /* basic idea comes from Beck, but he doesn't implement it this way. */
    for (int size = 1; size <= MAXIMUM_SIZE; size *= 10)
      this.tests(size).run(results);  /* collecting parameter pattern */
  }

  /* create an instance of `TimingTests` class for a given collection size. */
  private TimingTests tests(int size) throws Exception {
    /* this code comes from Kent Beck. */
    Constructor<? extends TimingTests> constructor = this.timingTests.getConstructor(new Class[]{int.class});
    return (TimingTests) constructor.newInstance(new Object[]{size});
  }
}
