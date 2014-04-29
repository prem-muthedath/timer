package timer.output.base;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

public abstract class Node {
	private List<Node> children=new ArrayList<Node>();

	public Node add(Node child) {
		children.add(child);
		return this;
	}

	public Node add(Node[] children) {
		this.children.addAll(Arrays.asList(children));
		return this;
	}

	public void print(Format format)  {
		for(Node each : children)
			each.print(format);		
	}

	public int children() {
		return children.size();
	}

	public int meAndDescendants() {
		int size=0;
		for (Node each : children)
			size+=each.meAndDescendants();
		return size;
	}

	public abstract boolean namedAs(Node node);

	public void validate(SizeValidator validator) {
		validator.validate(this);
	}
}
