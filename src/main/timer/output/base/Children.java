package timer.output.base;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

public class Children {
	private List<Node> children=new ArrayList<Node>();

	public void add(Node child) {
		children.add(child);
	}

	public void add(Node[] children) {
		this.children.addAll(Arrays.asList(children));
	}

	public void print(Format format)  {
		for(Node each : children)
			each.print(format);		
	}

	public int count() {
		return children.size();
	}

	public int descendants() {
		int size=0;
		for (Node each : children)
			size+=each.meAndDescendants();
		return size;
	}
}
