package timer;

import java.util.ArrayList;
import java.util.List;

import timer.framework.TimingTests;

/* java 1.7 API reference
 * https://docs.oracle.com/en/java/javase/17/docs/api/
 *
 * original source of this code: Kent Beck's 'Implementation Patterns' book.
 * Prem slightly modified Beck's code to suit his design here.
 *
 * author: Kent Beck, with slight modifications by Prem Muthedath
 */
public class ListSearch extends TimingTests {
  private List<Integer> numbers;
  private int probe;

  public ListSearch(int size) {
    numbers= new ArrayList<Integer>();
    for (int i= 0; i < size; i++)
      numbers.add(i);
    probe= size / 2;
  }

  public void search() {
    numbers.contains(probe);
  }

  protected int size()  {
    return numbers.size();
  }

  protected TimingTests copy() {
    return new ListSearch(size());
  }
}
