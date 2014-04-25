package timer.output.base;

public class TitledNode extends Node  {
	private Node footer;

	public TitledNode(String header, Node footer) {
		super(header);
		this.footer=footer;
	}

	public void print(Format format)  {
		super.print(format);
		footer.print(format);
	}

	public boolean namedAs(Node node) {
		if(!super.namedAs(node)) return false;
		if(getClass().equals(node.getClass()))
			return this.footer.namedAs(((TitledNode) node).footer);
		return false;
	}

	public int children() {
		return super.children()+footer.children();
	}

	public int meAndDescendants() {
		return super.meAndDescendants()+footer.meAndDescendants();
	}
}
