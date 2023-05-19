package timer.reporting;

import java.util.Comparator;

/* java 1.7 API reference
 * https://docs.oracle.com/en/java/javase/17/docs/api/
 *
 * author: Prem Muthedath
 */
public class SizeOrderedResults extends OrderedResults {
  protected int sort(String oneSize, String anotherSize, String oneMethod, String anotherMethod) {
    /* see /u/ paul mckenzie @ https://tinyurl.com/3fjxenyf (so) */
    return Integer.valueOf(oneSize).compareTo(Integer.valueOf(anotherSize)) == 0 ?
      oneMethod.compareTo(anotherMethod) :
      Integer.valueOf(oneSize).compareTo(Integer.valueOf(anotherSize));
  }

  void report(Report report) {
    report.bySize(super.sortedSizes(), super.timings());
  }

  protected double[][] allTimingSlices() {
    int sizeCount = super.sortedSizes().length;
    return super.allTimingSlices(sizeCount);
  }
}
