package timer.reporting;

import java.util.TreeMap;
import java.util.List;
import java.util.ArrayList;

/** Represents timings for a range of collection sizes associated with a certain 
 *  timing test method. The timings are ordred by collection size.
 *
 *  java 1.7 API reference
 *  https://docs.oracle.com/en/java/javase/17/docs/api/
 *
 *  author: Prem Muthedath
 */
class SizeTimings extends ParameterTimings {
  /* for TreeMap, see java 1.7 oracle docs @ https://tinyurl.com/54nmsmtk */
  private TreeMap<Integer, Double> sizeTimings;

  public SizeTimings() {
    this.sizeTimings = new TreeMap<Integer, Double>();
  }

  void add(int size, double timing) {
    this.sizeTimings.put(Integer.valueOf(size), Double.valueOf(timing));
  }

  /* https://www3.ntu.edu.sg/home/ehchua/programming/java/JavaGeneric.html */
  java.util.Collection<Double> timingValues() {
    return this.sizeTimings.values();
  }

  int[] sizes() {
    int[] sizes = new int [this.size()];
    List<Integer> keys = new ArrayList<Integer>(this.sizeTimings.keySet());
    for (int i=0; i < keys.size(); i++)
      sizes[i] = keys.get(i).intValue();
    return sizes;
  }

  int size() {
    return this.sizeTimings.size();
  }
}




