import java.util.ArrayList;
import java.util.List;

/* Kent Beck's code listed in his "Implementation Patterns" book.
 *
 * See instructions given at the top of `notes/kent-beck/MethodsTimer.java` file 
 * on how to compile and run Beck's timer.
 * */
public class ListSearch {
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
}
