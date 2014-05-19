package timer.output.base;

import java.util.Arrays;

public class NodeFactory  {
	public Node node() {
		return new NamedNode("") {
			protected void printName(Format format) {}
		};
	}

	public Node node(int children) {
		Node[] nodes=new Node[children];
		Arrays.fill(nodes, node());
		return node().add(nodes);
	}

	public Node node(String name) {
		return new NamedNode(name);
	}

	public Node textNode(final String name) {
		return new NamedNode(name) {
			protected void printName(Format format) {
				format.printText(name);
			}
		};
	}

	public Node titledNode(String header, String footer) {
		return new TitledNode(node(header), node(footer));
	}

	public Node tagNode(String tag) {
		return new Tag(tag).titledNode(this);
	}

	public Node tagNode(String tag, String content) {
		return tagNode(tag).add(textNode(content));
	}
}