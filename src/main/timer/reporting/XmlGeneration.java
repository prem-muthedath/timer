package timer.reporting;

/** This class, having only inner classes, has code to model and generate XML.
 *
 *  This class acts as a wrapper or namespace for its inner classes. It does not 
 *  have any state or behavior of its own. All the inner classes within this 
 *  class are responsible for modeling and generating XML.
 *
 *  Why is this class created or needed in the first place?
 *  Previously, all the inner classes in this class used to reside in 
 *  `timer.reporting.XmlReport`, but having them there caused the class to 
 *  become bloated and blurred its key responsibility, which is to direct the 
 *  organization and building of an appropriate XML report from the data.  
 *  Therefore, this wrapper class was created to separate and organize the inner 
 *  classes, allowing for better code organization and maintaining the single 
 *  responsibility principle. This wrapper acts much like a module in Haskell.
 *
 *  Usage:
 *  To use this class, create an instance of XmlGeneration and access its inner 
 *  classes to model and generate the XML structures.
 *
 *  Example:
 *  XmlGeneration xmlGen = new XmlGeneration();
 *  xmlGen.new Timing(5.0).toXml();
 *
 *  1.7 API reference
 *  https://docs.oracle.com/en/java/javase/17/docs/api/
 *
 *  XML terminology:
 *    https://nlp.stanford.edu/IR-book/html/htmledition/basic-xml-concepts-1.html
 *    https://docs.oracle.com/en/cloud/saas/marketing/responsys-develop/RPL/XMLTerminology.htm
 *    https://sbnwiki.astro.umd.edu/wiki/Anatomy_of_the_XML_Prolog
 *
 *  author: Prem Muthedath
 */
class XmlGeneration {
  /* +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ */
  /* +++++++++ core inner classes for XML modeling and generation ++++++++++ */
  /* +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ */
  abstract class XmlNode {
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
  class XmlLeafNode extends XmlNode {
    private String value;

    XmlLeafNode(Tag tag, String value) {
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
  class XmlElementNode extends XmlNode {
    private Tag tag;
    private XmlNode[] children;

    XmlElementNode(Tag tag, XmlNode[] children) {
      super(tag);
      this.children = children;
    }

    XmlElementNode(Tag tag, XmlNode child) {
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
  class Tag {
    private TagAttribute[] attributes;
    private String name;

    Tag(String name, TagAttribute[] attributes) {
      this.name = name.trim();
      this.attributes = attributes;
    }

    Tag(String name, TagAttribute attribute) {
      this.name = name.trim();
      this.attributes = new TagAttribute[] { attribute };
    }

    Tag(String name) {
      this(name, new TagAttribute("", ""));
    }

    Tag(String name, String attrName, String attrValue) {
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
  /* ++++++++ helper inner classes for XML node creation from data +++++++++ */
  /* +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ */
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
  class Size extends TimingParameter {
    private int value;

    Size(int size) {
      this.value = size;
    }

    Tag tag() {
      String formattedValue = String.format("%s", this.value);
      return new Tag("size", "value", formattedValue);
    }
  }

  /* +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ */
  class Method extends TimingParameter {
    private String name;

    Method(String name) {
      this.name = name;
    }

    Tag tag() {
      String formattedName = String.format("%s", this.name);
      return new Tag("method", "name", formattedName);
    }
  }

  /* +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ */
  class Timing {
    private double value;

    Timing(double timing) {
      this.value = timing;
    }

    XmlLeafNode toXml() {
      return new XmlLeafNode(new Tag("timing"), String.format("%.2f", this.value));
    }
  }

  /* +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ */

}
