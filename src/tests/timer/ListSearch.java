package timer;

import java.util.ArrayList;
import java.util.List;

/*  Prem added this import. */
import timer.framework.TimingTests;

/** Timing tests encapsulated for list search.
 *
 *  Each public method in this class is a timing test method.
 *  All timing tests have to be public methods.
 *
 *  Terminology:
 *  Note that `timing test` is same as `timing test method` or `test method`.  
 *  All these terms refer to a public method in this class.
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

  /* Prem added this method to run the timing tests in his framework. */
  protected int size()  {
    return numbers.size();
  }

  /* Prem added this method to run the timing tests in his framework. */
  protected TimingTests copy() {
    return new ListSearch(size());
  }
}
