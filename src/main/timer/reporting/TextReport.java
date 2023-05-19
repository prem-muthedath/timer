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

  public void view() {
    this.printTitle();
    super.view();
  }

  private void printTitle() {
    System.out.println(TextFormat.title("********** Test Timings (nanoseconds):"));
  }

  protected void bySize(int[] sizes, double[][] timings) {
    this.printMethodHeadersRow();
    for (int i=0; i < sizes.length; i++)
      this.printDataRow(TextFormat.sizeLabel(sizes[i]), timings[i]);
  }

  private void printMethodHeadersRow() {
    String [] methods = super.methods();
    String header = TextFormat.leftMargin();
    for (int i=0; i < methods.length; i++)
      header += TextFormat.methodLabel(methods[i]);
    System.out.println(header);
  }

  protected void byMethod(String[] methods, double[][] timings) {
    this.printSizeHeadersRow();
    for (int i=0; i < methods.length; i++)
      this.printDataRow(TextFormat.methodLabel(methods[i]), timings[i]);
  }

  private void printSizeHeadersRow() {
    int sizes[] = super.sizes();
    String header = TextFormat.leftMargin();
    for (int i=0; i < sizes.length; i++)
      header += TextFormat.sizeLabel(sizes[i]);
    System.out.println(header);
  }

  private void printDataRow(String label, double[] timings) {
    String row = label;
    for (int i=0; i < timings.length; i++)
      row += TextFormat.timingValue(timings[i]);
    System.out.println(row);
  }

}

