package timer.reporting;

import java.util.TreeMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

/** Timing tests results in ascending order of test method names for reporting.
 *  For a test method, reports timings in ascending order of collection sizes.
 *
 *  java 1.7 API reference
 *  https://docs.oracle.com/en/java/javase/17/docs/api/
 *
 *  author: Prem Muthedath
 */
public class MethodOrderedResults extends OrderedResults {
  /* for TreeMap, see java 1.7 oracle docs @ https://tinyurl.com/54nmsmtk */
  private TreeMap<String, SizeTimings> results;

  public MethodOrderedResults() {
    this.results = new TreeMap<String, SizeTimings>();
  }

  protected void add(int size, String method, double timing) {
    String key = method;
    SizeTimings val = this.results.containsKey(key) ? this.results.get(key) : new SizeTimings();
    val.add(size, timing);
    this.results.put(key, val);
  }

  /* https://www3.ntu.edu.sg/home/ehchua/programming/java/JavaGeneric.html */
  void report(Report report) {
    /* timings grouped by test method name, which is why it is a 2d array. */
    double[][] timings = new double [this.size()] [];
    /* `TreeMap` returns values in ascending order of keys, the methods. */
    List<SizeTimings> sizeTimings = new ArrayList<SizeTimings>(this.results.values());
    for (int i=0; i < sizeTimings.size(); i++)
      timings[i] = sizeTimings.get(i).orderedBySize();
    report.viewByMethod(this.sortedMethods(), timings);
  }

  /* https://www3.ntu.edu.sg/home/ehchua/programming/java/JavaGeneric.html */
  String[] sortedMethods() {
    /* `TreeMap` returns keys, the test method mames, in ascending order. */
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

  /** Represents set of timings for a range of collection sizes associated with 
   *  a certain test method. Reports timings in ascending order of sizes.
   *
   *  java 1.7 API reference
   *  https://docs.oracle.com/en/java/javase/17/docs/api/
   *
   *  author: Prem Muthedath
   */
  private class SizeTimings {
    /* for TreeMap, see java 1.7 oracle docs @ https://tinyurl.com/54nmsmtk */
    private TreeMap<Integer, Double> sizeTimings;

    private SizeTimings() {
      this.sizeTimings = new TreeMap<Integer, Double>();
    }

    private void add(int size, double timing) {
      this.sizeTimings.put(Integer.valueOf(size), Double.valueOf(timing));
    }

    /* report method timings in ascending order of the collection sizes.  
     * https://www3.ntu.edu.sg/home/ehchua/programming/java/JavaGeneric.html
     */
    private double[] orderedBySize() {
      double[] timings = new double [this.size()];
      /* `TreeMap` returns values in ascending order of keys, the sizes.  */
      List <Double> vals = new ArrayList<Double>(this.sizeTimings.values());
      for (int i=0; i < vals.size(); i++)
        timings[i] = vals.get(i).doubleValue();
      return timings;
    }

    private int[] sizes() {
      int[] sizes = new int [this.size()];
      /* `TreeMap` returns keys, the collection sizes, in ascending order. */
      List<Integer> keys = new ArrayList<Integer>(this.sizeTimings.keySet());
      for (int i=0; i < keys.size(); i++)
        sizes[i] = keys.get(i).intValue();
      return sizes;
    }

    private int size() {
      return this.sizeTimings.size();
    }
  }
}
