package timer.reporting;

import java.util.TreeMap;
import java.util.List;
import java.util.ArrayList;

/** Represents timings of all timing test methods for a certain collection size. 
 *  The timings are ordered by test method names.
 *
 *  java 1.7 API reference
 *  https://docs.oracle.com/en/java/javase/17/docs/api/
 *
 *  author: Prem Muthedath
 */
class MethodTimings extends ParameterTimings {
  /* for TreeMap, see java 1.7 oracle docs @ https://tinyurl.com/54nmsmtk */
  private TreeMap<String, Double> methodTimings;

  public MethodTimings() {
    this.methodTimings = new TreeMap<String, Double>();
  }

  void add(String method, double timing) {
    this.methodTimings.put(method, Double.valueOf(timing));
  }

  /* https://www3.ntu.edu.sg/home/ehchua/programming/java/JavaGeneric.html */
  java.util.Collection<Double> timingValues() {
    return this.methodTimings.values();
  }

  String[] methods() {
    String[] methods = new String [this.size()];
    List<String> keys = new ArrayList<String>(this.methodTimings.keySet());
    for (int i=0; i < keys.size(); i++)
      methods[i] = keys.get(i);
    return methods;
  }

  int size() {
    return this.methodTimings.size();
  }
}




