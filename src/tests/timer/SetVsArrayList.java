package timer;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Collection;
import java.util.Iterator;

import timer.framework.TimingTests;

/* java 1.7 API reference
 * https://docs.oracle.com/en/java/javase/17/docs/api/
 *
 * original source of this code: Kent beck's 'Implementation Patterns' book.
 * Prem modified Beck's code to suit his design here.
 *
 * author: Prem Muthedath
 */
public class SetVsArrayList extends TimingTests {
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

  protected int size()  {
    return arrayList.size();
  }

  protected TimingTests copy() {
    return new SetVsArrayList(size());
  }

  public void setMembership() {
    set.contains(probe);
  }

  public void arrayListMembership() {
    arrayList.contains(probe);
  }

  public void setIteration() {
    Iterator<String> all= set.iterator();
    while (all.hasNext())
      all.next();
  }

  public void arrayListIteration() {
    Iterator<String> all= arrayList.iterator();
    while (all.hasNext())
      all.next();
  }

  public void setModification() {
    set.add("b");
    set.remove("b");
  }

  public void arrayListModification() {
    arrayList.add("b");
    arrayList.remove("b");
  }
}
