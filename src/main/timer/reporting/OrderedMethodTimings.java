package timer.reporting;

import java.util.TreeMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

/** Represents set of timings of all timing test methods for a certain 
 *  collection size. Reports timings in ascending order of test method names.
 *
 *  java 1.7 API reference
 *  https://docs.oracle.com/en/java/javase/17/docs/api/
 *
 *  author: Prem Muthedath
 */
class OrderedMethodTimings extends OrderedParameterTimings {
  /* for TreeMap, see java 1.7 oracle docs @ https://tinyurl.com/54nmsmtk */
  private TreeMap<String, Double> methodTimings;

  public OrderedMethodTimings() {
    this.methodTimings = new TreeMap<String, Double>();
  }

  void add(String method, double timing) {
    this.methodTimings.put(method, Double.valueOf(timing));
  }

  /* https://www3.ntu.edu.sg/home/ehchua/programming/java/JavaGeneric.html */
  java.util.Collection<Double> rawValues() {
    /* `TreeMap` returns values in ascending order of the keys, which here means 
     * in ascending order of test method names.
     */
    return this.methodTimings.values();
  }

  String[] methods() {
    /* `TreeMap` returns keys in ascending order. which here means in ascending 
     * order of test method names.
     */
    List<String> keys = new ArrayList<String>(this.methodTimings.keySet());
    /* see /u/ waxwing @ https://tinyurl.com/5a3dj2ck (so) */
    return Arrays.copyOf(keys.toArray(), keys.size(), String[].class);
  }

  int size() {
    return this.methodTimings.size();
  }
}




