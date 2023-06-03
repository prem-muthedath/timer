package timer;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Collection;
import java.util.Iterator;

/*  Prem added this import. */
import timer.framework.TimingTests;

/** Timing tests encapsulated for Set vs ArrayList comparison.
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
public class SetVsArrayList extends TimingTests {/* Prem added this inheritance */
  /* Kent Beck's code. */
  private Collection<String> set;
  private Collection<String> arrayList;

  private String probe;

  public SetVsArrayList(int size) {
    set= new HashSet<String>(size);
    arrayList= new ArrayList<String>(size);
    for (int i= 0; i < size; i++) {
      String element= String.format("a%d", i);
      set.add(element);
      arrayList.add(element);
    }
    probe= String.format("a%d", size / 2);
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
    return new SetVsArrayList(size());
  }

  /* timing test for set membership. This method comes from Kent Beck. */
  public void setMembership() {
    set.contains(probe);
  }

  /* timing test for array list membership; written by Kent Beck. */
  public void arrayListMembership() {
    arrayList.contains(probe);
  }

  /* timing test method for set iteration; written by Kent Beck. */
  public void setIteration() {
    Iterator<String> all= set.iterator();
    while (all.hasNext())
      all.next();
  }

  /* timing test method for array list iteration; written by Kent Beck. */
  public void arrayListIteration() {
    Iterator<String> all= arrayList.iterator();
    while (all.hasNext())
      all.next();
  }

  /* timing test for set modification; written by Kent Beck.
   * NOTE: this operation ensures that the original collection remains 
   * unchanged.  this is important to avoid dependencies between tests.
   */
  public void setModification() {
    set.add("b");
    set.remove("b");
  }

  /* timing test method for array list modification; written by Kent Beck.
   * NOTE: this operation ensures that the original collection remains 
   * unchanged.  this is important to avoid dependencies between tests.
   */
  public void arrayListModification() {
    arrayList.add("b");
    arrayList.remove("b");
  }
}
