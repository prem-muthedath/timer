import java.util.ArrayList;
import java.util.HashSet;
import java.util.Collection;
import java.util.Iterator;

/* Kent Beck's code listed in his "Implementation Patterns" book.
 *
 * See instructions at the top of `notes/kent-beck-timer/MethodsTimer.java` file 
 * on how to compile and run Beck's timer.
 * */
public class SetVsArrayList {
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
