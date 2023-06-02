package timer.reporting;

import java.util.TreeMap;
import java.util.List;
import java.util.ArrayList;

/** Represents timing tests results ordered by collection size for reports.
 *  java 1.7 API reference
 *  https://docs.oracle.com/en/java/javase/17/docs/api/
 *
 *  author: Prem Muthedath
 */
public class SizeOrderedResults extends OrderedResults {
  /* for TreeMap, see java 1.7 oracle docs @ https://tinyurl.com/54nmsmtk */
  private TreeMap<Integer, MethodTimings> results;

  public SizeOrderedResults() {
    this.results = new TreeMap<Integer, MethodTimings>();
  }

  protected void add(int size, String method, double timing) {
    Integer key = Integer.valueOf(size);
    MethodTimings val = this.results.containsKey(key) ? this.results.get(key) : new MethodTimings();
    val.add(method, timing);
    this.results.put(key, val);
  }

  /* https://www3.ntu.edu.sg/home/ehchua/programming/java/JavaGeneric.html */
  void report(Report report) {
    /* timings grouped by collection size, which is why it is a 2d array. */
    double[][] timings = new double [this.size()] [];
    List<MethodTimings> methodTimings = new ArrayList<MethodTimings>(this.results.values());
    for (int i=0; i < methodTimings.size(); i++)
      timings[i] = methodTimings.get(i).values();
    report.viewBySize(this.sortedSizes(), timings);
  }

  /* https://www3.ntu.edu.sg/home/ehchua/programming/java/JavaGeneric.html */
  int[] sortedSizes() {
    int[] sizes = new int [this.size()];
    List<Integer> keys = new ArrayList<Integer>(this.results.keySet());
    for (int i=0; i < keys.size(); i++)
      sizes[i] = keys.get(i).intValue();
    return sizes;
  }

  String[] sortedMethods() {
    Integer first = this.results.firstKey();
    return this.results.get(first).methods();
  }

  private int size() {
    return this.results.size();
  }
}
