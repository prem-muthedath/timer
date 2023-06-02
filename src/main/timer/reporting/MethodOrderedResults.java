package timer.reporting;

import java.util.TreeMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

/** Represents timing tests results ordered by test method names for reporting.
 *  java 1.7 API reference
 *  https://docs.oracle.com/en/java/javase/17/docs/api/
 *
 *  author: Prem Muthedath
 */
public class MethodOrderedResults extends OrderedResults {
  /* for TreeMap, see java 1.7 oracle docs @ https://tinyurl.com/54nmsmtk */
  private TreeMap<String, OrderedSizeTimings> results;

  public MethodOrderedResults() {
    this.results = new TreeMap<String, OrderedSizeTimings>();
  }

  protected void add(int size, String method, double timing) {
    String key = method;
    OrderedSizeTimings val = this.results.containsKey(key) ? this.results.get(key) : new OrderedSizeTimings();
    val.add(size, timing);
    this.results.put(key, val);
  }

  /* https://www3.ntu.edu.sg/home/ehchua/programming/java/JavaGeneric.html */
  void report(Report report) {
    /* timings grouped by test method name, which is why it is a 2d array. */
    double[][] timings = new double [this.size()] [];
    /* `TreeMap` returns values in ascending order of the keys, which here means 
     * in ascending order of test method names.
     */
    List<OrderedSizeTimings> sizeTimings = new ArrayList<OrderedSizeTimings>(this.results.values());
    for (int i=0; i < sizeTimings.size(); i++)
      timings[i] = sizeTimings.get(i).values();
    report.viewByMethod(this.sortedMethods(), timings);
  }

  /* https://www3.ntu.edu.sg/home/ehchua/programming/java/JavaGeneric.html */
  String[] sortedMethods() {
    /* `TreeMap` returns keys in ascending order, which here means in ascending 
     * order of timing test method names.
     */
    List<String> keys = new ArrayList<String>(this.results.keySet());
    /* see /u/ waxwing @ https://tinyurl.com/5a3dj2ck (so) */
    return Arrays.copyOf(keys.toArray(), keys.size(), String[].class);
  }

  int[] sortedSizes() {
    String first = this.results.firstKey();
    return this.results.get(first).sizes();
  }

  private int size() {
    return this.results.size();
  }
}
