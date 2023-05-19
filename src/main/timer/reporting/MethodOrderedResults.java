package timer.reporting;

import java.util.Comparator;

/* java 1.7 API reference
 * https://docs.oracle.com/en/java/javase/17/docs/api/
 *
 * author: Prem Muthedath
 */
public class MethodOrderedResults extends OrderedResults {
  protected int sort(String oneSize, String anotherSize, String oneMethod, String anotherMethod) {
    /* see /u/ paul mckenzie @ https://tinyurl.com/3fjxenyf (so) */
    return oneMethod.compareTo(anotherMethod) == 0  ?
      Integer.valueOf(oneSize).compareTo(Integer.valueOf(anotherSize)) :
      oneMethod.compareTo(anotherMethod);
  }

  void report(Report report) {
    report.byMethod(super.sortedMethods(), super.timings());
  }

  protected double[][] allTimings() {
    int methodCount = super.sortedMethods().length;
    return super.timingsSlices(methodCount);
  }
}
