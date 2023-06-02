package timer.reporting;

import java.util.TreeMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

/** Represents timing tests results ordered by method name.
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

  void report(Report report) {
    report.viewByMethod(this.sortedMethods(), super.timings());
  }

  String[] sortedMethods() {
    List<String> keys = new ArrayList<String>(this.results.keySet());
    /* see /u/ waxwing @ https://tinyurl.com/5a3dj2ck (so) */
    return Arrays.copyOf(keys.toArray(), keys.size(), String[].class);
  }

  /* https://www3.ntu.edu.sg/home/ehchua/programming/java/JavaGeneric.html */
  java.util.Collection<SizeTimings> parameterTimings() {
    return this.results.values();
  }

  int[] sortedSizes() {
    String first = this.results.firstKey();
    return this.results.get(first).sizes();
  }

  int size() {
    return this.results.size();
  }

}
