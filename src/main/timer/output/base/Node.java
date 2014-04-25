package timer.output.base;

public class Node {
	private String name;
	private Children children=new Children();

	public Node(String name)  {
		this.name=name;
	}

	public Node add(Node child) {
		children.add(child);
		return this;
	}

	public Node add(Node[] children) {
		this.children.add(children);
		return this;
	}

	public void print(Format format) {
		printName(format);
		children.print(format);
	}

	protected void printName(Format format) {
		format.printHeading(name);
	}

	public boolean namedAs(Node node) {
		return this.name.equals(node.name);
	}

	public int children() {
		return children.count();
	}

	public int meAndDescendants() {
		return 1+children.descendants();
	}	

	public void validate(SizeValidator validator) {
		validator.validate(this);
	}
}
