package timer.reporting;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import java.awt.GridLayout;

/* java 1.7 API reference
 * https://docs.oracle.com/en/java/javase/17/docs/api/
 *
 * author: Prem Muthedath
 */
public class SwingReport extends Report {
  private JFrame frame;
  public SwingReport(OrderedResults results) {
    super(results);
    this.frame = new JFrame();
  }

  void viewTitle(String testClass) {
    this.frame.setTitle(testClass + " Class Method Timings (nanoseconds)");
  }

  protected void viewBySize(int[] sizes, double[][] timings) {
    this.resetDisplay(sizes.length + 1);
    this.addMethodHeaders();
    for (int i=0; i < sizes.length; i++)
      addValues(TextFormat.sizeLabel(sizes[i]), timings[i]);
    this.viewFrame();
  }

  private void addMethodHeaders() {
    this.addHeader(TextFormat.leftMargin());
    String[] methods = super.methods();
    for (int i=0; i < methods.length; i++)
      this.addHeader(TextFormat.methodLabel(methods[i]));
  }

  protected void viewByMethod(String[] methods, double[][] timings) {
    this.resetDisplay(methods.length + 1);
    this.addSizeHeaders();
    for (int i=0; i < methods.length; i++)
      addValues(TextFormat.methodLabel(methods[i]), timings[i]);
    this.viewFrame();
  }

  private void addSizeHeaders() {
    this.addHeader(TextFormat.leftMargin());
    int[] sizes = super.sizes();
    for (int i=0; i < sizes.length; i++)
      this.addHeader(TextFormat.sizeLabel(sizes[i]));
  }

  private void addValues(String label, double[] timings) {
    this.addText(label);
    for (int i=0; i < timings.length; i++)
      this.addText(TextFormat.timingValue(timings[i]));
  }

  private void resetDisplay(int rowCount) {
    this.frame.getContentPane().removeAll();
    this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    /* what does it mean to have 0 for columns or rows in GridLayout 
     * constructor? here is an example from oracle documentation:
     *    GridLayout experimentLayout = new GridLayout(0,2);
     *    The constructor of the GridLayout class creates an instance that has 
     *    two columns and as many rows as necessary.
     *    https://docs.oracle.com/javase/tutorial/uiswing/layout/grid.html
     */
    this.frame.getContentPane().setLayout(new GridLayout(rowCount, 0));
  }

  private void addHeader(String heading) {
    this.frame.getContentPane().add(new JLabel(heading));
  }

  private void addText(String text) {
    this.frame.getContentPane().add(new JTextField(text));
  }

  private void viewFrame() {
    this.frame.pack();
    this.frame.setVisible(true);
  }

}
