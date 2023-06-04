package timer.reporting;

/* java 1.7 API reference
 * https://docs.oracle.com/en/java/javase/17/docs/api/
 *
 * author: Prem Muthedath
 */
public class XmlReport extends Report {
  /* define couple of string constants. */
  private final String TESTS_LABEL="timing-tests";
  private final String TIMINGS_LABEL="method-timings";

  public XmlReport(OrderedResults results) {
    super(results);
  }

  void viewTitle(String testClass) {
    System.out.println("<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>");
    System.out.println(this.attrTag(this.TESTS_LABEL, "class", testClass));
    System.out.println(this.attrTag(this.TIMINGS_LABEL, "units", "nanoseconds"));
  }

  void viewFooter() {
    System.out.println(this.closeTag(this.TIMINGS_LABEL));
    System.out.println(this.closeTag(this.TESTS_LABEL));
  }

  protected void viewBySize(int[] sizes, double[][] timings) {
    for (int i=0; i < sizes.length; i++)
      System.out.println(this.sizeElements(sizes[i], timings[i]));
  }

  private String sizeElements(int size, double[] timings) {
    String[] methods = super.methods();
    String result = "\n";
    for (int i=0; i < timings.length; i++)
      result += this.methodElement(methods[i], timingElement(timings[i])) + "\n";
    return this.sizeElement(size, result);
  }

  protected void viewByMethod(String[] methods, double[][] timings) {
    for (int i=0; i < methods.length; i++)
      System.out.println(this.methodElements(methods[i], timings[i]));
  }

  private String methodElements(String method, double[] timings) {
    int[] sizes = super.sizes();
    String result = "\n";
    for (int i=0; i < timings.length; i++)
      result += this.sizeElement(sizes[i], timingElement(timings[i])) + "\n";
    return this.methodElement(method, result);
  }

  private String sizeElement(int size, String value) {
    String sizeTag = "<" + String.format("size value=\"%s\"", size) + ">";
    return sizeTag + value + "</size>";
  }

  private String methodElement(String method, String value) {
    String methodTag = "<" + String.format("method name=\"%s\"", method) + ">";
    return methodTag + value + "</method>";
  }

  private String timingElement(double timing) {
    return "<timing>" + String.format("%.2f", timing) + "</timing>";
  }

  private String attrTag(String name, String attrName, String attrValue) {
    return "<" + name + " " + attrName + "=\"" + attrValue + "\">";
  }

  private String closeTag(String name) {
    return "</" + name + ">";
  }

}
