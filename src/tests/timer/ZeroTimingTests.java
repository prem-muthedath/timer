package timer;

import java.util.List;
import java.util.ArrayList;

/*  Prem added this import. */
import timer.framework.TimingTests;

/** This class encapsulates no (zero) timing tests.  It is used to test if the 
 *  framework handles a timing tests class with 0 timing tests gracefully.
 *
 *  NOTE:
 *    1. To encapsulate a set of timing tests in a class, your class should 
 *       first extend the `timer.framework.TimingTests` class. See below code.
 *    2. Your class must have a single public constructor that accepts a single 
 *       argument: the collection size for the timing tests as an `int`.
 *    3. Each public method in any class that encapsulates a set of timing tests 
 *       is a timing test method. This class, by definition, has no timing 
 *       tests, so it has no public methods at all.
 *    4. All timing tests have to be `public` methods taking no arguments and 
 *       having the return type as `void`. They should also be not throwing any  
 *       exceptions explicitly in their signatures.
 *    5. Any timing test that modifies a collection or the state of your timing 
 *       tests class in any way must restore the objects back to their original 
 *       states.  This is very important to avoid dependencies between tests.
 *    6. Finally, you should implement the abstract methods `copy()` and 
 *       `size()` defined in `timer.framework.TimingTests` class; see the 
 *       implementations below for a sample.
 *
 *  Terminology:
 *    Note that `timing test` is same as `timing test method` or `test method`.  
 *    All these terms refer to a public method in this class, or in any other 
 *    class that encapsulates a set of timing tests, that will be run and timed 
 *    by the `Timer` framework. The `Timer` frammwork, in fact, selects and runs 
 *    and times all public methods, the timing tests, in any class that 
 *    encapsulates a set of timing tests.
 *
 *  java 1.7 API reference
 *  https://docs.oracle.com/en/java/javase/17/docs/api/
 *
 *  author: Prem Muthedath
 */
public class ZeroTimingTests extends TimingTests { /* Prem added this inheritance */
  /* Kent Beck's code. */
  private List<Integer> numbers;
  private int probe;

  public ZeroTimingTests(int size) {
    numbers= new ArrayList<Integer>();
    for (int i= 0; i < size; i++)
      numbers.add(i);
    probe= size / 2;
  }

  /* return the collection size associated with this instance.
   * any class encapsulating a set of timing tests must implement this method.
   * Prem added this method to run the timing tests in his framework.
   */
  protected int size()  {
    return numbers.size();
  }

  /* a new instance of this class having the same size as the current instance.
   * any class encapsulating a set of timing tests must implement this method.
   * Prem added this method to run the timing tests in his framework.
   */
  protected TimingTests copy() {
    return new ZeroTimingTests(size());
  }
}
