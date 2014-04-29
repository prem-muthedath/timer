package timer.output.base;

public class TitledNode extends Node  {
	private Node footer;
	private Node header;

	public TitledNode(Node header, Node footer) {
		this.header=header;
		this.footer=footer;
	}

	public void print(Format format)  {
		header.print(format);
		super.print(format);
		footer.print(format);
	}

	public boolean namedAs(Node node) {
		if(node instanceof TitledNode)
			return this.header.namedAs(((TitledNode) node).header) && this.footer.namedAs(((TitledNode) node).footer);
		return false;
	}

	public int meAndDescendants() {
		return header.meAndDescendants()+super.meAndDescendants()+footer.meAndDescendants();
	}
}
