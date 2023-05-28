package timer.framework;

import java.lang.reflect.Method;

/** `TimingTest` object represents a single timing test among a set of timing 
 *  tests defined for a specific colection size.  Since a set of timing tests is 
 *  represented by a class, a single timing test is represented by an object 
 *  that binds together a method, the timing test or the method to be timed, and 
 *  the class instance (i.e., the class instance for the set of timing tests for 
 *  a collection size) associated with that method.
 *
 *  a `TimingTest` object times its method, the timing test, and then works with 
 *  other objects to compute the final method timing.
 *
 *  the original idea and parts of source code come from Kent Beck's 
 *  'Implementation Patterns' book, but although Beck certainly came up with the 
 *  overall algorithm used here, he did not encapsulate it in the way it has 
 *  been done here. indeed, the design here departs considerably from Beck's.  
 *  Prem came up with this class as part of his refactoring of Beck's code.
 *
 *  java 1.7 API reference
 *  https://docs.oracle.com/en/java/javase/17/docs/api/
 *
 *  author: Prem Muthedath (with parts of source code and ideas from Kent Beck)
 */
class TimingTest {
  private Method method;
  private TimingTests instance;

  TimingTest(Method method, TimingTests instance) {
    this.method = method;         /* the timing test, the method to be timed */
    this.instance = instance;     /* the associated timing tests instance */
  }

  /* time (in nanoseconds) this timing test,
   * Prem wrote this method and designed the object interactions here.
   */
  double timing() throws Exception {
    return this.instance.timing(this.run());
  }

  /* run the timing test, the method to be timed, enough number of times to get 
   * an accurate value of its timing, and capture the run details in an object.
   *
   * procedural time calculation algorithm from Kent Beck. Prem, however, 
   * designed the objects and their interactions here.
   */
  private TestRun run() throws Exception {
    int iterations = 1;
    long totalTime = 0L;
    while (true) {
      totalTime = this.totalTime(iterations);
      if(totalTime > CollectionTimer.ONE_SECOND) break;
      iterations *= 2;
    }
    return new TestRun(iterations, totalTime);  /* this is Prem's idea */
  }

  /* compute the total time, in nanoseconds, for executing the timing method 
   * repeatedly for a specified number of iterations.
   *
   * Prem wrote this code, because this code was missing in Beck's book.
   *
   * as Kent Beck has suggested in his book (see `Implementation Patterns`), we 
   * use the same class instance for iterative time calculations below.  this 
   * technique of caching the instance for these calculations allows us to run 
   * this timing test, which would otherwise take impossibly long times.
   */
  long totalTime(int iterations) throws Exception {
    long start = System.nanoTime();
    for (int i = 0; i < iterations; i++)
      this.method.invoke(this.instance, new Object[0]);
    return System.nanoTime() - start;
  }
 }
