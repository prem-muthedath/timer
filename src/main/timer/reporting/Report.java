package timer.reporting;

/* java 1.7 API reference
 * https://docs.oracle.com/en/java/javase/17/docs/api/
 *
 * author: Prem Muthedath
 */
public abstract class Report {
  private OrderedResults results;

  public Report(OrderedResults results) {
    this.results = results;
  }

  public void view() {
    this.results.report(this);
  }

  int[] sizes() {
    return this.results.sortedSizes();
  }

  String[] methods() {
    return this.results.sortedMethods();
  }

  protected abstract void bySize(int[] sizes, double[][] timings);
  protected abstract void byMethod(String[] methods, double[][] timings);

}


