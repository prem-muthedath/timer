package timer.output.base;

public class Tag  {
	private String name;

	public Tag(String name) {
		this.name=name.trim();
	}

	public Node titledNode(NodeFactory factory)  {
		return factory.titledNode(startTag(), endTag());
	}

	private String startTag() {
		return "<"+name+">"+"\n";
	}

	private String endTag() {
		return "</"+goodName()+">"+"\n";
	}

	private String goodName() {
		return nameWithoutAttributes();		
	}

	private String nameWithoutAttributes() {
		return name.indexOf(" ") > 0  ?  name.substring(0, name.indexOf(" "))  :  name;		
	}
}
