package timer.reporting;

import java.util.List;
import java.util.ArrayList;
import java.util.Collection;

/** Represents set of method timings associated with a range of values of a 
 *  single parameter (such as collection size or timing test method name).  
 *  Subclasses report timings in ascending order of the parameter values.
 *
 *  This class has all common code that subclasses use.
 *
 *  java 1.7 API reference
 *  https://docs.oracle.com/en/java/javase/17/docs/api/
 *
 *  author: Prem Muthedath
 */
abstract class OrderedParameterTimings {
  abstract int size();
  /* https://www3.ntu.edu.sg/home/ehchua/programming/java/JavaGeneric.html */
  abstract Collection<Double> rawValues();

  /* timings reported in ascending order of the parameter values. */
  double[] values() {
    double[] timings = new double [this.size()];
    List <Double> vals = new ArrayList<Double>(this.rawValues());
    for (int i=0; i < vals.size(); i++)
      timings[i] = vals.get(i).doubleValue();
    return timings;
  }
}
