package timer.reporting;

import java.util.Comparator;

/* java 1.7 API reference
 * https://docs.oracle.com/en/java/javase/17/docs/api/
 *
 * author: Prem Muthedath
 */
public class MethodOrderedResults extends OrderedResults {
  protected int sort(String oneSize, String anotherSize, String oneMethod, String anotherMethod) {
    // see /u/ paul mckenzie @ https://tinyurl.com/3fjxenyf (so)
    return oneMethod.compareTo(anotherMethod) == 0  ?
      Integer.valueOf(oneSize).compareTo(Integer.valueOf(anotherSize)) :
      oneMethod.compareTo(anotherMethod);
  }

  void report(Report report) {
    report.byMethod(this.sortedMethods(), this.timings());
  }

  protected double[][] allTimings() {
    int methodCount = this.sortedMethods().length;
    int step = super.count()/methodCount;
    double[][] timings = new double [methodCount] [];
    for (int i = 0; i < methodCount; i ++) {
      int from = i*step, to = from + step;
      timings[i] = super.timingsSlice(from, to);
    }
    return timings;
  }
}
