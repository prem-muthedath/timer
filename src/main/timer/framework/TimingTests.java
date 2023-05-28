package timer.framework;

import java.lang.reflect.Method;

/** this abstract class, a key abstraction in the timer framework, represents a 
 *  set of timing tests for a given collection size. being abstract, this class 
 *  has no timing tests (i.e., public methods to be timed), but it provides the 
 *  basic framework to run a set of timing tests and record their timings. 
 *  subclasses with concrete set of timing tests can use this framework code to 
 *  run their timing tests.
 *
 *  the original idea and parts of source code come from Kent Beck's 
 *  'Implementation Patterns' book, but although Beck certainly came up with the 
 *  overall timer framework idea and algorithm, he did not encapsulate it in the 
 *  way it has been done here. indeed, the design here departs considerably from 
 *  Beck's.  Prem created this class as part of his refactoring of Beck's code.
 *
 *  java 1.7 API reference
 *  https://docs.oracle.com/en/java/javase/17/docs/api/
 *
 *  author: Prem Muthedath (with parts of source code and ideas from Kent Beck)
 */
public abstract class TimingTests {
  /* run all timing tests & record the results (i.e., method timings, etc).
   * timing tests: all public methods in the invoking instance's class.
   */
  void run(Results results) throws Exception { /* collecting parameter */
    for (Method each : this.getClass().getMethods()) {
      if(this.skip(each)) continue;
      this.copy().run(each, results);
    }
  }

  /* return true if the method is not defined in invoking instance's class. */
  private boolean skip(Method method) throws Exception {
    Class<? extends TimingTests> thisClass = this.getClass();
    Class<?> methodClass = method.getDeclaringClass();
    return !methodClass.equals(thisClass);
  }

  /* create a new timing tests instance having the same collection size and type 
   * as the invoking instance.
   *
   * copy() needed for accurate method timing.
   * For a given collection size, when we time all methods using the SAME 
   * instance of TimingTests, the JVM caches the instance across methods when it 
   * executes method.invoke(instance, new Object[0]).  For methods whose timings 
   * INCREASE with collection size, timings become dependent on execution order.  
   * That is, if method A uses the SAME collection as method B, timings reported 
   * when method A is executed BEFORE method B is DIFFERENT from the timings 
   * reported when method A is executed AFTER method B.  The errors get big for 
   * LARGE collection sizes (>= 1000) and LONG method execution times (>= 200 
   * ns).  With copy(), we supply a new instance for each method, thus avoiding 
   * the caching problem.
   */
  protected abstract TimingTests copy();

  /* return collection size associated with the invoking instance. */
  protected abstract int size();

  /* run a timing test, a method in the invoking instance's class. */
  private void run(Method method, Results results) throws Exception {
    results.run(method, this);
  }

  /* compute method timing from a completed timing test run. */
  double timing(TestRun run) throws Exception {
    return run.timing(this.overhead());
  }

  /* represent dynamic method invocation overhead with a timing test object.  
   * the basic idea of overhead and it's calculation come from Kent Beck.  Prem, 
   * however, designed the objects and their interactions here.
   */
  private TimingTest overhead() throws Exception {
    Method method = this.getClass().getMethod("nothing", new Class[0]);
    return new TimingTest(method, this.copy());
  }

  /* an empty method representing an overhead timing test.
   * the idea of using an empty method for overhead comes from Kent Beck.
   */
  public final void nothing() {}
}
