package timer;

import java.util.Map;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.TreeMap;

/*  Prem added this import. */
import timer.framework.TimingTests;

/** Timing tests for HashMap vs LinkedHashMap vs TreeMap comparison.
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
public class Maps extends TimingTests { /* Prem added this inheritance */
  /* Kent Beck's code. */
  private Map<String, String> hashMap;
  private Map<String, String> linkedHashMap;
  private Map<String, String> treeMap;
  private String probe;

  public Maps(int size) {
    hashMap = new HashMap<String, String>(size);
    linkedHashMap = new LinkedHashMap<String, String>(size);
    treeMap = new TreeMap<String, String>();
    for (int i= 0; i < size; i++) {
      String element= String.format("a%d", i);
      hashMap.put(element, element);
      linkedHashMap.put(element, element);
      treeMap.put(element, element);
    }
    probe= String.format("a%d", size / 2);
  }

  /* timing test for hash map contains method, written by Kent Beck. */
  public void hashMapContains() {
    hashMap.containsKey(probe);
  }

  /* timing test for hash map modification, written by Kent Beck.
   * NOTE: the code ensures that the collection is unchanged by this test.  this 
   * is very important to avoid dependencies between tests.
   */
  public void hashMapModification() {
    hashMap.put("b", "b");
    hashMap.remove("b");
  }

  /* timing test for tree map contains method, written by Kent Beck. */
  public void treeMapContains() {
    treeMap.containsKey(probe);
  }

  /* timing test for tree map modification, written by Kent Beck.
   * NOTE: the code ensures that the collection is unchanged by this test.  this 
   * is very important to avoid dependencies between tests.
   */
  public void treeMapModification() {
    treeMap.put("b", "b");
    treeMap.remove("b");
  }

  /* timing test for linked hash map contains method, written by Kent Beck. */
  public void linkedHashMapContains() {
    linkedHashMap.containsKey(probe);
  }

  /* timing test for linked hash map modification, written by Kent Beck.
   * NOTE: the code ensures that the collection is unchanged by this test.  this 
   * is very important to avoid dependencies between tests.
   */
  public void linkedHashMapModification() {
    linkedHashMap.put("b", "b");
    linkedHashMap.remove("b");
  }

  /* return the collection size associated with this instance.
   * any class encapsulating a set of timing tests must implement this method.
   * Prem added this method to run the timing tests in his framework.
   */
  protected int size()  {
    return treeMap.size();
  }

  /* a new instance of this class having the same size as the current instance.
   * any class encapsulating a set of timing tests must implement this method.
   * Prem added this method to run the timing tests in his framework.
   */
  protected TimingTests copy() {
    return new Maps(size());
  }
}

