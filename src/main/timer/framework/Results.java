package timer.framework;

import java.lang.reflect.Method;

/** this abstract class represents results of timing tests.
 *
 *  the `Results` object collects results of timing tests, including method 
 *  timings, method names, and collection sizes used in timing tests runs.
 *
 *  subclasses decide how they want to record the results of a timing test.
 *
 *  java 1.7 API reference
 *  https://docs.oracle.com/en/java/javase/17/docs/api/
 *
 *  author: Prem Muthedath
 */
public abstract class Results {
  /* run a timing test and collect its results.
   *
   * Note:
   *  It looks strange that a `Results` object has a `run()` method to run a 
   *  timing test. how come we didn't put this code in `run()` method of the 
   *  `TimingTests` object? well, we could have, but that would have violated 
   *  the `Law of Demeter`, which bars having more than one dot per line.  Many 
   *  people scoff at such stuff, but I find the law very useful. Supoose we had 
   *  the code in `TimingTests` class, then it would look like this:
   *
   *    results.add(size(),
   *                method.getName(),
   *                new TimingTest(method, copy()).timing());
   *
   *  now we have 2 dots per line in the above code, so i moved this code over 
   *  to `Results` object, where we got the dots per line reduced to 1.
   */
  void run(Method method, TimingTests instance) throws Exception {
    add(instance.size(), method.getName(), new TimingTest(method, instance).timing());
  }

  /* record the results of a timing test run.
   * subclasses decide how they want to record the results of a timing test.
   */
  protected abstract void add(int size, String method, double timing);
}

