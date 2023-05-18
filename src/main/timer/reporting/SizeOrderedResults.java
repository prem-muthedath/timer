package timer.reporting;

import java.util.Comparator;

/* java 1.7 API reference
 * https://docs.oracle.com/en/java/javase/17/docs/api/
 *
 * author: Prem Muthedath
 */
public class SizeOrderedResults extends OrderedResults {
  protected int sort(String oneSize, String anotherSize, String oneMethod, String anotherMethod) {
    // see /u/ paul mckenzie @ https://tinyurl.com/3fjxenyf (so)
    return Integer.valueOf(oneSize).compareTo(Integer.valueOf(anotherSize)) == 0 ?
      oneMethod.compareTo(anotherMethod) :
      Integer.valueOf(oneSize).compareTo(Integer.valueOf(anotherSize));
  }

  void report(Report report) {
    report.bySize(this.sortedSizes(), this.timings());
  }

  protected double[][] allTimings() {
    int sizeCount = this.sortedSizes().length;
    int step = super.count()/sizeCount;
    double[][] timings = new double [sizeCount] [];
    for (int i = 0; i < sizeCount; i ++) {
      int from = i*step, to = from + step;
      timings[i] = super.timingsSlice(from, to);
    }
    return timings;
  }
}
