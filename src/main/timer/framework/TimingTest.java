package timer.framework;

import java.lang.reflect.Method;

/* java 1.7 API reference
 * https://docs.oracle.com/en/java/javase/17/docs/api/
 *
 * the original idea and source of this code come from Kent Beck's 
 * 'Implementation Patterns' book, but although Beck certainly came up with the 
 * overall algorithm used here, he did not encapsulate it in the way it has been 
 * done here. indeed, the design here departs considerably from Beck's.  Prem 
 * came up with this class as part of his refactoring of Beck's code.
 *
 * author: Prem Muthedath
 */
class TimingTest {
  private Method method;
  private TimingTests instance;

  TimingTest(Method method, TimingTests instance) {
    this.method = method;
    /* as Kent Beck has suggested in his book (see `Implementation Patterns`), 
     * we use the same instance for iterative time calculations below.  this 
     * technique of caching the instance for these calculations allows us to run 
     * this test, which would otherwise take impossibly long times.
     */
    this.instance = instance;
  }

  double timing() throws Exception {
    return this.instance.timing(this.run());
  }

  private TestRun run() throws Exception {
    /* procedural time calculation algorithm from Kent Beck.
     * Prem, however, designed the objects and their interactions here.
     */
    int iterations = 1;
    long totalTime = 0L;
    while (true) {
      totalTime = this.totalTime(iterations);
      if(totalTime > CollectionTimer.ONE_SECOND) break;
      iterations *= 2;
    }
    return new TestRun(iterations, totalTime);  /* this is Prem's idea */
  }

  long totalTime(int iterations) throws Exception {
    long start = System.nanoTime();
    for (int i = 0; i < iterations; i++)
      this.method.invoke(this.instance, new Object[0]);
    return System.nanoTime() - start;
  }
 }
