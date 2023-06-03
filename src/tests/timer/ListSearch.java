package timer;

import java.util.ArrayList;
import java.util.List;

/*  Prem added this import. */
import timer.framework.TimingTests;

/** Timing tests encapsulated for list search.
 *
 *  NOTE:
 *    1. To encapsulate a set of timing tests in a class, your class should 
 *       first extend the `timer.framework.TimingTests` class. See below code.
 *    2. Each public method in this class, or in any other class that 
 *       encapsulates a set of timing tests, is a timing test method.
 *    3. All timing tests have to be `public` methods taking no arguments and 
 *       having the return type as `void`. They should also be not throwing any  
 *       exceptions explicitly in their signatures.
 *    4. Any timing test that modifies a collection or the state of your timing 
 *       tests class in any way must restore the objects back to their original 
 *       states.  This is very important to avoid dependencies between tests.
 *    5. Finally, you should implement the abstract methods `copy()` and 
 *       `size()` defined in `timer.framework.TimingTests` class; see the 
 *       implementations below for a sample.
 *
 *  Terminology:
 *    Note that `timing test` is same as `timing test method` or `test method`.  
 *    All these terms refer to a public method in this class, or in any other 
 *    class that encapsulates a set of timing tests, that will be run and timed 
 *    by the `Timer` framework. The `Timer` frammwork, in fact, selects and runs 
 *    and times all public methods, the timing tests, in this class, or in any 
 *    other class that encapsulates a set of timing tests.
 *
 *  original source of this code: Kent Beck's 'Implementation Patterns' book.
 *  Prem slightly modified Beck's code to suit his design here.
 *
 *  java 1.7 API reference
 *  https://docs.oracle.com/en/java/javase/17/docs/api/
 *
 *  author: Kent Beck, with comments and slight modifications by Prem Muthedath
 */
public class ListSearch extends TimingTests { /* Prem added this inheritance */
  /* Kent Beck's code. */
  private List<Integer> numbers;
  private int probe;

  public ListSearch(int size) {
    numbers= new ArrayList<Integer>();
    for (int i= 0; i < size; i++)
      numbers.add(i);
    probe= size / 2;
  }

  /* timing test method for list search. This method comes from Kent Beck. */
  public void search() {
    numbers.contains(probe);
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
    return new ListSearch(size());
  }
}
