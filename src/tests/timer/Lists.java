package timer;

import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;

/*  Prem added this import. */
import timer.framework.TimingTests;

/** Timing tests encapsulated for ArrayList vs LinkedList comparison.
 *
 *  NNOTE:
 *    1. To encapsulate a set of timing tests in a class, your class should 
 *       first extend the `timer.framework.TimingTests` class. See below code.
 *    2. Each public method in this class, or in any other class that 
 *       encapsulates a set of timing tests, is a timing test method.
 *    3. All timing tests have to be `public` methods taking no arguments and 
 *       having return type as `void`. They should also be not throwing any  
 *       exceptions explicitly in their signatures.
 *    4. Any timing test that modifies a collection in any way must restore that 
 *       collection back to its original state.  This is very important to avoid 
 *       dependencies between tests.
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
public class Lists extends TimingTests { /* Prem added this inheritance */
  /* Kent Beck's code. */
  private List<String> arrayList;
  private List<String> linkedList;
  private final int size;

  public Lists(int size) {
    this.size = size;
    arrayList = new ArrayList<String>(size);
    linkedList = new LinkedList<String>();
    for (int i=0; i < size; i++) {
      String element = String.format("a%d", i);
      arrayList.add(element);
      linkedList.add(element);
    }
  }

  /* timing test for array list modification, written by Kent Beck.
   * NOTE: the code ensures that the collection is unchanged by this test.  this 
   * is very important to avoid dependencies between tests.
   */
  public void arrayListModification() {
    arrayList.add(0, "b");
    arrayList.remove(0);
  }

  /* timing test for linked list modification, written by Kent Beck.
   * NOTE: the code ensures that the collection is unchanged by this test.  this 
   * is very important to avoid dependencies between tests.
   */
  public void linkedListModification() {
    linkedList.add(0, "b");
    linkedList.remove(0);
  }

  /* timing test for array list access, written by Kent Beck. */
  public void arrayListAccess() {
    arrayList.get(size / 2);
  }

  /* timing test for linked list access, written by Kent Beck. */
  public void linkedListAccess() {
    linkedList.get(size / 2);
  }

  /* return the collection size associated with this instance.
   * any class encapsulating a set of timing tests must implement this method.
   * Prem added this method to run the timing tests in his framework.
   */
  protected int size()  {
    return arrayList.size();
  }

  /* a new instance of this class having the same size as the current instance.
   * any class encapsulating a set of timing tests must implement this method.
   * Prem added this method to run the timing tests in his framework.
   */
  protected TimingTests copy() {
    return new Lists(size());
  }
}

