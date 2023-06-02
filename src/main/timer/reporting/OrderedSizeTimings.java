package timer.reporting;

import java.util.TreeMap;
import java.util.List;
import java.util.ArrayList;

/** Represents set of timings for a range of collection sizes associated with a 
 *  certain test method. Reports timings in ascending order of collection sizes.
 *
 *  java 1.7 API reference
 *  https://docs.oracle.com/en/java/javase/17/docs/api/
 *
 *  author: Prem Muthedath
 */
class OrderedSizeTimings extends OrderedParameterTimings {
  /* for TreeMap, see java 1.7 oracle docs @ https://tinyurl.com/54nmsmtk */
  private TreeMap<Integer, Double> sizeTimings;

  public OrderedSizeTimings() {
    this.sizeTimings = new TreeMap<Integer, Double>();
  }

  void add(int size, double timing) {
    this.sizeTimings.put(Integer.valueOf(size), Double.valueOf(timing));
  }

  /* https://www3.ntu.edu.sg/home/ehchua/programming/java/JavaGeneric.html */
  java.util.Collection<Double> rawValues() {
    /* `TreeMap` returns values in ascending order of the keys, which here means 
     * in ascending order of collection sizes.
     */
    return this.sizeTimings.values();
  }

  int[] sizes() {
    int[] sizes = new int [this.size()];
    /* `TreeMap` returns keys in ascending order. which here means in ascending 
     * order of collection sizes.
     */
    List<Integer> keys = new ArrayList<Integer>(this.sizeTimings.keySet());
    for (int i=0; i < keys.size(); i++)
      sizes[i] = keys.get(i).intValue();
    return sizes;
  }

  int size() {
    return this.sizeTimings.size();
  }
}




