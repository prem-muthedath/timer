package timer.reporting;

import java.util.TreeMap;
import java.util.List;
import java.util.ArrayList;

/** Represents timing tests results ordered by collection sizes for reporting.
 *  java 1.7 API reference
 *  https://docs.oracle.com/en/java/javase/17/docs/api/
 *
 *  author: Prem Muthedath
 */
public class SizeOrderedResults extends OrderedResults {
  /* for TreeMap, see java 1.7 oracle docs @ https://tinyurl.com/54nmsmtk */
  private TreeMap<Integer, OrderedMethodTimings> results;

  public SizeOrderedResults() {
    this.results = new TreeMap<Integer, OrderedMethodTimings>();
  }

  protected void add(int size, String method, double timing) {
    Integer key = Integer.valueOf(size);
    OrderedMethodTimings val = this.results.containsKey(key) ? this.results.get(key) : new OrderedMethodTimings();
    val.add(method, timing);
    this.results.put(key, val);
  }

  /* https://www3.ntu.edu.sg/home/ehchua/programming/java/JavaGeneric.html */
  void report(Report report) {
    /* timings grouped by collection size, which is why it is a 2d array. */
    double[][] timings = new double [this.size()] [];
    /* `TreeMap` returns values in ascending order of the keys, which here means 
     * in ascending order of collection sizes.
     */
    List<OrderedMethodTimings> methodTimings = new ArrayList<OrderedMethodTimings>(this.results.values());
    for (int i=0; i < methodTimings.size(); i++)
      timings[i] = methodTimings.get(i).values();
    report.viewBySize(this.sortedSizes(), timings);
  }

  /* https://www3.ntu.edu.sg/home/ehchua/programming/java/JavaGeneric.html */
  int[] sortedSizes() {
    int[] sizes = new int [this.size()];
    /* `TreeMap` returns keys in ascending order, which here means in ascending 
     * order of collection sizes.
     */
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
