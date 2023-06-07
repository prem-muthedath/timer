package timer.reporting;

/* java 1.7 API reference
 * https://docs.oracle.com/en/java/javase/17/docs/api/
 *
 * author: Prem Muthedath
 */
public class TextReport extends Report {
  public TextReport(OrderedResults results) {
    super(results);
  }

  void viewTitle(String testClass) {
    super.print(TextFormat.title("********** " + testClass + " Class Method Timings (nanoseconds):"));
  }

  protected void viewBySize(int[] sizes, double[][] timings) {
    this.printMethodHeadersRow();
    for (int i=0; i < sizes.length; i++)
      this.printDataRow(TextFormat.sizeLabel(sizes[i]), timings[i]);
  }

  private void printMethodHeadersRow() {
    String [] methods = super.methods();
    String header = TextFormat.leftMargin();
    for (int i=0; i < methods.length; i++)
      header += TextFormat.methodLabel(methods[i]);
    super.print(header);
  }

  protected void viewByMethod(String[] methods, double[][] timings) {
    this.printSizeHeadersRow();
    for (int i=0; i < methods.length; i++)
      this.printDataRow(TextFormat.methodLabel(methods[i]), timings[i]);
  }

  private void printSizeHeadersRow() {
    int sizes[] = super.sizes();
    String header = TextFormat.leftMargin();
    for (int i=0; i < sizes.length; i++)
      header += TextFormat.sizeLabel(sizes[i]);
    super.print(header);
  }

  private void printDataRow(String label, double[] timings) {
    String row = label;
    for (int i=0; i < timings.length; i++)
      row += TextFormat.timingValue(timings[i]);
    super.print(row);
  }

}

