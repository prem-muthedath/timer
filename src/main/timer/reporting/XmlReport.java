package timer.reporting;

/* java 1.7 API reference
 * https://docs.oracle.com/en/java/javase/17/docs/api/
 *
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
    XmlNode[] results = new XmlNode [sizes.length];
    for (int i=0; i < sizes.length; i++)
      results[i] = this.sizeNode(sizes[i], timings[i]);
    this.print(results);
  }

  private XmlNode sizeNode(int size, double[] timings) {
    String[] methods = super.methods();
    XmlNode[] results = new XmlNode [timings.length];
    for (int i=0; i < timings.length; i++)
      results[i] = new MethodElement(methods[i]).toXml(timings[i]);
    return new SizeElement(size).toXml(results);
  }

  private void print(XmlComponent[] allData) {
    Tag dataRoot = new Tag("method-timings", "units", "nanoseconds");
    XmlComponent xml = new XmlNode(root, new XmlNode(dataRoot, allData));
    System.out.println(xml);
  }

  protected void viewByMethod(String[] methods, double[][] timings) {
    XmlNode[] results = new XmlNode [methods.length];
    for (int i=0; i < methods.length; i++)
      results[i] = this.methodNode(methods[i], timings[i]);
    this.print(results);
  }

  private XmlNode methodNode(String method, double[] timings) {
    int[] sizes = super.sizes();
    XmlNode[] results = new XmlNode [timings.length];
    for (int i=0; i < timings.length; i++)
      results[i] = new SizeElement(sizes[i]).toXml(timings[i]);
    return new MethodElement(method).toXml(results);
  }

  /* +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ */
  /* +++++++++++++ helper inner classes for XML generation +++++++++++++++++ */
  /* +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ */
  private abstract class Field {
    XmlNode toXml(XmlComponent[] components) {
      return new XmlNode(this.tag(), components);
    }

    XmlNode toXml(XmlComponent component) {
      return this.toXml(new XmlComponent[] { component });
    }

    XmlNode toXml(double timing) {
      return this.toXml(new TimingLeafElement(timing).toXml());
    }

    abstract Tag tag();
  }

  /* +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ */
  private class SizeElement extends Field {
    private int value;

    private SizeElement(int size) {
      this.value = size;
    }

    Tag tag() {
      String formattedValue = String.format("%s", this.value);
      return new Tag("size", "value", formattedValue);
    }
  }

  /* +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ */
  private class MethodElement extends Field {
    private String name;

    private MethodElement(String name) {
      this.name = name;
    }

    Tag tag() {
      String formattedName = String.format("%s", this.name);
      return new Tag("method", "name", formattedName);
    }
  }

  /* +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ */
  private class TimingLeafElement {
    private double value;

    private TimingLeafElement(double timing) {
      this.value = timing;
    }

    private XmlLeafNode toXml() {
      return new XmlLeafNode(new Tag("timing"), String.format("%.2f", this.value));
    }
  }

  /* +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ */
  /* ++++++++++++++ core inner classes for XML generation ++++++++++++++++++ */
  /* +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ */
  private abstract class XmlComponent {
    private Tag tag;

    private XmlComponent(Tag tag) {
      this.tag = tag;
    }

    public String toString() {
      return this.tag.toString(this);
    }

    abstract String toString(String open, String close);
  }

  /* +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ */
  private class XmlLeafNode extends XmlComponent {
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
  private class XmlNode extends XmlComponent {
    private Tag tag;
    private XmlComponent[] children;

    private XmlNode(Tag tag, XmlComponent[] children) {
      super(tag);
      this.children = children;
    }

    private XmlNode(Tag tag, XmlComponent child) {
      super(tag);
      this.children = new XmlComponent[] { child };
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
      if (children[0] instanceof XmlNode) return "\n";
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

    private String toString(XmlComponent component) {
      return component.toString(open(), close());
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
