package timer.reporting;

import java.util.List;
import java.util.ArrayList;
import java.util.Collection;

/** Represents set of timings associated with a range of values of a single 
 *  parameter (such as collection size or timing test method name).
 *
 *  This class has all common code that subclasses use.
 *
 *  java 1.7 API reference
 *  https://docs.oracle.com/en/java/javase/17/docs/api/
 *
 *  author: Prem Muthedath
 */
abstract class ParameterTimings {
  abstract int size();
  /* https://www3.ntu.edu.sg/home/ehchua/programming/java/JavaGeneric.html */
  abstract Collection<Double> timingValues();

  double[] timings() {
    double[] timings = new double [this.size()];
    List <Double> vals = new ArrayList<Double>(this.timingValues());
    for (int i=0; i < vals.size(); i++)
      timings[i] = vals.get(i).doubleValue();
    return timings;
  }
}
