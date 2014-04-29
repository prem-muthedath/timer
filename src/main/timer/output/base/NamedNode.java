package timer.output.base;

public class NamedNode extends Node {
	private String name;

	public NamedNode(String name)  {
		this.name=name;
	}

	public void print(Format format) {
		printName(format);
		super.print(format);
	}

	protected void printName(Format format) {
		format.printHeading(name);
	}

	public boolean namedAs(Node node) {
		if(node instanceof NamedNode)
			return this.name.equals(((NamedNode)node).name);
		return false;
	}

	public int meAndDescendants() {
		return 1+super.meAndDescendants();
	}	
}
