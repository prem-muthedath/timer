package timer.reporting;

/* java 1.7 API reference
 * https://docs.oracle.com/en/java/javase/17/docs/api/
 * XML terminology:
 *  https://nlp.stanford.edu/IR-book/html/htmledition/basic-xml-concepts-1.html
 *  https://docs.oracle.com/en/cloud/saas/marketing/responsys-develop/RPL/XMLTerminology.htm
 * author: Prem Muthedath
 */
public class XmlReport extends Report {
  private Tag root;
  private final String prolog="<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>";

  public XmlReport(OrderedResults results) {
    super(results);
  }

  void viewTitle(String testClass) {
    this.setRoot(new Tag("timing-tests", "class", testClass));
    System.out.println(this.prolog);
  }

  private void setRoot(Tag root) {
    this.root=root;
  }

  protected void viewBySize(int[] sizes, double[][] timings) {
    XmlElementNode[] nodes = new XmlElementNode [sizes.length];
    for (int i=0; i < sizes.length; i++)
      nodes[i] = this.sizeElementNode(sizes[i], timings[i]);
    this.print(nodes);
  }

  private XmlElementNode sizeElementNode(int size, double[] timings) {
    String[] methods = super.methods();
    XmlElementNode[] nodes = new XmlElementNode [timings.length];
    for (int i=0; i < timings.length; i++)
      nodes[i] = new Method(methods[i]).toXml(timings[i]);
    return new Size(size).toXml(nodes);
  }

  private void print(XmlNode[] nodes) {
    Tag dataRoot = new Tag("method-timings", "units", "nanoseconds");
    XmlNode xml = new XmlElementNode(root, new XmlElementNode(dataRoot, nodes));
    System.out.println(xml);
  }

  protected void viewByMethod(String[] methods, double[][] timings) {
    XmlElementNode[] nodes = new XmlElementNode [methods.length];
    for (int i=0; i < methods.length; i++)
      nodes[i] = this.methodElementNode(methods[i], timings[i]);
    this.print(nodes);
  }

  private XmlElementNode methodElementNode(String method, double[] timings) {
    int[] sizes = super.sizes();
    XmlElementNode[] nodes = new XmlElementNode [timings.length];
    for (int i=0; i < timings.length; i++)
      nodes[i] = new Size(sizes[i]).toXml(timings[i]);
    return new Method(method).toXml(nodes);
  }

  /* +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ */
  /* +++++++++++++ helper inner classes for XML generation +++++++++++++++++ */
  /* +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ */
  /* XML terminology:
   *  https://nlp.stanford.edu/IR-book/html/htmledition/basic-xml-concepts-1.html
   *  https://docs.oracle.com/en/cloud/saas/marketing/responsys-develop/RPL/XMLTerminology.htm
   */
  private abstract class TimingParameter {
    XmlElementNode toXml(XmlNode[] children) {
      return new XmlElementNode(this.tag(), children);
    }

    XmlElementNode toXml(XmlNode child) {
      return this.toXml(new XmlNode[] { child });
    }

    XmlElementNode toXml(double timing) {
      return this.toXml(new Timing(timing).toXml());
    }

    abstract Tag tag();
  }

  /* +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ */
  private class Size extends TimingParameter {
    private int value;

    private Size(int size) {
      this.value = size;
    }

    Tag tag() {
      String formattedValue = String.format("%s", this.value);
      return new Tag("size", "value", formattedValue);
    }
  }

  /* +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ */
  private class Method extends TimingParameter {
    private String name;

    private Method(String name) {
      this.name = name;
    }

    Tag tag() {
      String formattedName = String.format("%s", this.name);
      return new Tag("method", "name", formattedName);
    }
  }

  /* +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ */
  private class Timing {
    private double value;

    private Timing(double timing) {
      this.value = timing;
    }

    private XmlLeafNode toXml() {
      return new XmlLeafNode(new Tag("timing"), String.format("%.2f", this.value));
    }
  }

  /* +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ */
  /* ++++++++++++++ core inner classes for XML generation ++++++++++++++++++ */
  /* +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ */
  /* XML terminology:
   *  https://nlp.stanford.edu/IR-book/html/htmledition/basic-xml-concepts-1.html
   *  https://docs.oracle.com/en/cloud/saas/marketing/responsys-develop/RPL/XMLTerminology.htm
   */
  private abstract class XmlNode {
    private Tag tag;

    private XmlNode(Tag tag) {
      this.tag = tag;
    }

    public String toString() {
      return this.tag.toString(this);
    }

    abstract String toString(String open, String close);
  }

  /* +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ */
  private class XmlLeafNode extends XmlNode {
    private String value;

    private XmlLeafNode(Tag tag, String value) {
      super(tag);
      /* NOTE: see /u/ ernest_k @ https://tinyurl.com/4jk52rea (so)
       * instead of `trim()`, which trims everything, including "\n", you could 
       * use the code below to trim just whitespace (leading & trailing):
       *
       *    this.value = value.replaceAll("(^ +)|( +$)", "");
       */
      this.value = value.trim();
    }

    String toString(String open, String close) {
      return open + this.value + close;
    }
  }

  /* +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ */
  private class XmlElementNode extends XmlNode {
    private Tag tag;
    private XmlNode[] children;

    private XmlElementNode(Tag tag, XmlNode[] children) {
      super(tag);
      this.children = children;
    }

    private XmlElementNode(Tag tag, XmlNode child) {
      super(tag);
      this.children = new XmlNode[] { child };
    }

    String toString(String open, String close) {
      String result=adfix();
      for (int i=0; i < children.length; i++)
        result+=children[i].toString() + adfix();
      return open + result + close;
    }

    private String adfix() {
      int size = this.children.length;
      if (size > 1) return "\n";
      if (size == 0) return "";
      if (children[0] instanceof XmlElementNode) return "\n";
      return "";
    }
  }

  /* +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ */
  private class Tag {
    private TagAttribute[] attributes;
    private String name;

    private Tag(String name, TagAttribute[] attributes) {
      this.name = name.trim();
      this.attributes = attributes;
    }

    private Tag(String name, TagAttribute attribute) {
      this.name = name.trim();
      this.attributes = new TagAttribute[] { attribute };
    }

    private Tag(String name) {
      this(name, new TagAttribute("", ""));
    }

    private Tag(String name, String attrName, String attrValue) {
      this(name, new TagAttribute(attrName, attrValue));
    }

    private String open() {
      this.validate();
      String attributesString = "";
      for (int i=0; i < this.attributes.length; i++)
        attributesString += this.attributes[i].toString();
      return "<" + name + attributesString + ">";
    }

    private String close() {
      this.validate();
      return "</" + name + ">";
    }

    private String toString(XmlNode node) {
      return node.toString(open(), close());
    }

    private void validate() {
      if (name == "")
        throw new IllegalArgumentException("tag name can not be empty");
    }
  }

  /* +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ */
  private class TagAttribute {
    private String name;
    private String value;

    private TagAttribute(String name, String value) {
      this.name = name.trim();
      this.value = value.trim();
    }

    public String toString() {
      if (name == "") return "";
      return " " + name + "=\"" + value + "\"";
    }
  }

  /* +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ */

}
