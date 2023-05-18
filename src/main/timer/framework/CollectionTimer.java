package timer.framework;

import java.lang.reflect.Constructor;

/* java 1.7 API reference
 * https://docs.oracle.com/en/java/javase/17/docs/api/
 *
 * original idea, algorithm from Kent beck's 'Implementation Patterns' book.
 * Prem created this class as part of his refactoring of Beck's code.
 *
 * author: Prem Muthedath
 */
public class CollectionTimer {
  private static final int MAXIMUM_SIZE= 100000;
  static final int ONE_SECOND= 1000000000;

  private Class<? extends TimingTests> timingTests;

  public CollectionTimer(Class<? extends TimingTests> timingTests) {
    this.timingTests=timingTests;
  }

  public void run(Results results) throws Exception {
    /* this code comes from Kent Beck */
    for (int size= 1; size <= MAXIMUM_SIZE; size*= 10)
      tests(size).run(results);
  }

  private TimingTests tests(int size) throws Exception {
    /* this code comes from Kent Beck */
    Constructor<? extends TimingTests> constructor= timingTests.getConstructor(new Class[]{int.class});
    return (TimingTests) constructor.newInstance(new Object[]{size});
  }
}
