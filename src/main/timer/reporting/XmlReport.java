package timer.reporting;

import timer.reporting.XmlGeneration.Tag;
import timer.reporting.XmlGeneration.XmlNode;
import timer.reporting.XmlGeneration.XmlElementNode;
import timer.reporting.XmlGeneration.Size;
import timer.reporting.XmlGeneration.Method;

/**
 * This class generates an XML report from the supplied data.
 *
 * This object directs the overall organization and building of the appropriate
 * XML report from the data, delegating the responsibility of modeling and
 * generating the actual XML to the inner classes within the wrapper class
 * `XmlGeneration`.
 *
 * Depending on the type of `OrderedResults` object passed to the `XmlReport`
 * during its creation, the generated XML report will be organized either by
 * collection size or by the timing test method name.
 *
 * The generated XML report is printed to the console.
 *
 *  java 1.7 API reference
 *  https://docs.oracle.com/en/java/javase/17/docs/api/
 *
 *  XML terminology:
 *    https://nlp.stanford.edu/IR-book/html/htmledition/basic-xml-concepts-1.html
 *    https://docs.oracle.com/en/cloud/saas/marketing/responsys-develop/RPL/XMLTerminology.htm
 *    https://sbnwiki.astro.umd.edu/wiki/Anatomy_of_the_XML_Prolog
 *
 *  author: Prem Muthedath
 */
public class XmlReport extends Report {
  private Tag root;
  private final String prolog="<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>";

  public XmlReport(OrderedResults results) {
    super(results);
  }

  void viewTitle(String testClass) {
    this.setRoot(this.xmlGen().new Tag("timing-tests", "class", testClass));
    System.out.println(this.prolog);
  }

  private void setRoot(Tag root) {
    this.root=root;
  }

  protected void viewBySize(int[] sizes, double[][] timings) {
    XmlElementNode[] nodes = new XmlElementNode [sizes.length];
    for (int i=0; i < sizes.length; i++)
      nodes[i] = this.sizeElementNode(sizes[i], timings[i]);
    this.print(this.xmlDocument(nodes));
  }

  private XmlElementNode sizeElementNode(int size, double[] timings) {
    String[] methods = super.methods();
    XmlElementNode[] nodes = new XmlElementNode [timings.length];
    for (int i=0; i < timings.length; i++)
      nodes[i] = this.xmlGen().new Method(methods[i]).toXml(timings[i]);
    return this.xmlGen().new Size(size).toXml(nodes);
  }

  private XmlNode xmlDocument(XmlNode[] nodes) {
    Tag dataRoot = this.xmlGen().new Tag("method-timings", "units", "nanoseconds");
    XmlNode xmlDoc = this.xmlGen().new XmlElementNode(root,
        this.xmlGen().new XmlElementNode(dataRoot, nodes));
    return xmlDoc;
  }

  private void print(XmlNode document) {
    System.out.println(document);
  }

  protected void viewByMethod(String[] methods, double[][] timings) {
    XmlElementNode[] nodes = new XmlElementNode [methods.length];
    for (int i=0; i < methods.length; i++)
      nodes[i] = this.methodElementNode(methods[i], timings[i]);
    this.print(this.xmlDocument(nodes));
  }

  private XmlElementNode methodElementNode(String method, double[] timings) {
    int[] sizes = super.sizes();
    XmlElementNode[] nodes = new XmlElementNode [timings.length];
    for (int i=0; i < timings.length; i++)
      nodes[i] = this.xmlGen().new Size(sizes[i]).toXml(timings[i]);
    return this.xmlGen().new Method(method).toXml(nodes);
  }

  private XmlGeneration xmlGen() {
    return new XmlGeneration();
  }

}
