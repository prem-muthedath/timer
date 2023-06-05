package timer.reporting;

/* java 1.7 API reference
 * https://docs.oracle.com/en/java/javase/17/docs/api/
 *
 * author: Prem Muthedath
 */
public class XmlReport extends Report {
  private final String prolog="<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>";
  private Tag root;

  public XmlReport(OrderedResults results) {
    super(results);
  }

  void viewTitle(String testClass) {
    this.setRoot(new Tag("timing-tests", new TagAttribute("class", testClass)));
    System.out.println(this.prolog);
  }

  private void setRoot(Tag root) {
    this.root=root;
  }

  protected void viewBySize(int[] sizes, double[][] timings) {
    Xml[] result = new Xml [sizes.length];
    for (int i=0; i < sizes.length; i++)
      result[i] = this.sizeElements(sizes[i], timings[i]);
    this.print(result);
  }

  private Xml sizeElements(int size, double[] timings) {
    String[] methods = super.methods();
    Xml[] result = new Xml [timings.length];
    for (int i=0; i < timings.length; i++)
      result[i] = this.methodElement(methods[i], timingElement(timings[i]));
    return this.sizeElement(size, result);
  }

  private void print(Xml[] allData) {
    Tag dataRoot = new Tag("method-timings", new TagAttribute("units", "nanoseconds"));
    Xml xml = new XmlElements(root, new XmlElements(dataRoot, allData));
    System.out.println(xml);
  }

  protected void viewByMethod(String[] methods, double[][] timings) {
    Xml[] result = new Xml [methods.length];
    for (int i=0; i < methods.length; i++)
      result[i] = this.methodElements(methods[i], timings[i]);
    this.print(result);
  }

  private Xml methodElements(String method, double[] timings) {
    int[] sizes = super.sizes();
    Xml[] result = new Xml [timings.length];
    for (int i=0; i < timings.length; i++)
      result[i] = this.sizeElement(sizes[i], timingElement(timings[i]));
    return this.methodElement(method, result);
  }

  private XmlElements sizeElement(int size, Xml[] xmls) {
    Tag tag = new Tag("size", new TagAttribute("value", String.format("%s", size)));
    return new XmlElements(tag, xmls);
  }

  private XmlElements sizeElement(int size, Xml xml) {
    return this.sizeElement(size, new Xml[] { xml });
  }

  private XmlElements methodElement(String method, Xml[] xmls) {
    Tag tag = new Tag("method", new TagAttribute("name", String.format("%s", method)));
    return new XmlElements(tag, xmls);
  }

  private XmlElements methodElement(String method, Xml xml) {
    return this.methodElement(method, new Xml[] { xml });
  }

  private XmlElement timingElement(double timing) {
    return new XmlElement(new Tag("timing"), String.format("%.2f", timing));
  }

  /* +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ */
  /* +++++++++++++++++ inner claases for XML generation ++++++++++++++++++++ */
  /* +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ */
  private abstract class Xml {
    private Tag tag;

    private Xml(Tag tag) {
      this.tag = tag;
    }

    public String toString() {
      return this.tag.toString(this);
    }

    abstract String toString(String open, String close);
  }

  /* +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ */
  private class XmlElement extends Xml {
    private String value;

    private XmlElement(Tag tag, String value) {
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
  private class XmlElements extends Xml {
    private Tag tag;
    private Xml[] children;

    private XmlElements(Tag tag, Xml[] children) {
      super(tag);
      this.children = children;
    }

    private XmlElements(Tag tag, Xml child) {
      super(tag);
      this.children = new Xml[] { child };
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
      if (children[0] instanceof XmlElements) return "\n";
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

    private String open() {
      if (name == "")
        throw new IllegalArgumentException("tag name can not be empty");
      String attributesString = "";
      for (int i=0; i < this.attributes.length; i++)
        attributesString += this.attributes[i].toString();
      return "<" + name + attributesString + ">";
    }

    private String close() {
      if (name == "")
        throw new IllegalArgumentException("tag name can not be empty");
      return "</" + name + ">";
    }

    private String toString(Xml xml) {
      return xml.toString(open(), close());
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
