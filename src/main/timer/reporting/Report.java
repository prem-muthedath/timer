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

  public void view(Class<?> testClass) {
    this.viewTitle(testClass.getSimpleName());
    this.viewData();
    this.viewFooter();
  }

  void viewTitle(String testClass) {}

  void viewData() {
    this.results.report(this);
  }

  void viewFooter() {}

  int[] sizes() {
    return this.results.sortedSizes();
  }

  String[] methods() {
    return this.results.sortedMethods();
  }

  protected abstract void viewBySize(int[] sizes, double[][] timings);
  protected abstract void viewByMethod(String[] methods, double[][] timings);

}


