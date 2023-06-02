package timer.reporting;

import java.util.List;
import java.util.ArrayList;

import timer.framework.Results;

/** Represents timing tests results ordered in some way.
 *  This class has all common code that subclasses use.
 *
 *  java 1.7 API reference
 *  https://docs.oracle.com/en/java/javase/17/docs/api/
 *
 *  author: Prem Muthedath
 */
public abstract class OrderedResults extends Results {
  /* report results */
  abstract void report(Report report);

  /* unique sorted sizes. */
  abstract int[] sortedSizes();

  /* unique sorted methods. */
  abstract String[] sortedMethods();

  /* all recorded timings, grouped by values of ordering parameter (i.e., 
   * collection size or method name). this grouping is why you have a 
   * 2-dimensional array as result.
   */
  double[][] timings() {
    double[][] timings = new double [this.size()] [];
    List <ParameterTimings> vals = new ArrayList<ParameterTimings>(this.timingValues());
    for (int i=0; i < vals.size(); i++)
      timings[i] = vals.get(i).timings();
    return timings;
  }

  /* how many timing test results do we have? */
  abstract int size();

  /* https://www3.ntu.edu.sg/home/ehchua/programming/java/JavaGeneric.html */
  abstract java.util.Collection<? extends ParameterTimings> timingValues();
}
