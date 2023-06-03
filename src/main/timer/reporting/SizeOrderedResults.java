package timer.reporting;

import java.util.TreeMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

/** Timing tests results in ascending order of collection sizes for reporting.
 *  For a collection size, reports timings in ascending order of method names.
 *
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
    /* `TreeMap` returns values in ascending order of keys, the sizes. */
    List<MethodTimings> methodTimings = new ArrayList<MethodTimings>(this.results.values());
    for (int i=0; i < methodTimings.size(); i++)
      timings[i] = methodTimings.get(i).orderedByMethod();
    report.viewBySize(this.sortedSizes(), timings);
  }

  /* https://www3.ntu.edu.sg/home/ehchua/programming/java/JavaGeneric.html */
  int[] sortedSizes() {
    int[] sizes = new int [this.size()];
    /* `TreeMap` returns keys, the collection sizes, in ascending order. */
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

  /** Represents set of timings of all timing test methods for a certain 
   *  collection size. Reports timings in ascending order of test method names.
   *
   *  java 1.7 API reference
   *  https://docs.oracle.com/en/java/javase/17/docs/api/
   *
   *  author: Prem Muthedath
   */
  private class MethodTimings {
    /* for TreeMap, see java 1.7 oracle docs @ https://tinyurl.com/54nmsmtk */
    private TreeMap<String, Double> methodTimings;

    private MethodTimings() {
      this.methodTimings = new TreeMap<String, Double>();
    }

    private void add(String method, double timing) {
      this.methodTimings.put(method, Double.valueOf(timing));
    }

    /* report method timings in ascending order of the test method names.
     * https://www3.ntu.edu.sg/home/ehchua/programming/java/JavaGeneric.html
     */
    private double[] orderedByMethod() {
      double[] timings = new double [this.size()];
      /* `TreeMap` returns values in ascending order of keys, the methods.  */
      List <Double> vals = new ArrayList<Double>(this.methodTimings.values());
      for (int i=0; i < vals.size(); i++)
        timings[i] = vals.get(i).doubleValue();
      return timings;
    }

    private String[] methods() {
      /* `TreeMap` returns keys, the test method names, in ascending order. */
      List<String> keys = new ArrayList<String>(this.methodTimings.keySet());
      /* see /u/ waxwing @ https://tinyurl.com/5a3dj2ck (so) */
      return Arrays.copyOf(keys.toArray(), keys.size(), String[].class);
    }

    private int size() {
      return this.methodTimings.size();
    }
  }
}
