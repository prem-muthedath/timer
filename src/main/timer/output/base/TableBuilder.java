package timer.output.base;

import java.util.ArrayList;
import java.util.List;

public class TableBuilder {
	private List<Node> rows=new ArrayList<Node>();

	public TableBuilder(Node headers) {
		this.rows.add(headers);
	}

	public void add(Node column, Node row, Node cell)  {
		fetch(row).add(cell);
		if(contentRows().size()==1)
			headers().add(column);
	}

	private Node fetch(Node row) {
		for(Node each : contentRows())
			if(each.namedAs(row)) return each;
		rows.add(row);
		return row;	
	}

	private List<Node> contentRows() {
		return rows.size()  >  1  ?  rows.subList(1, rows.size()) : new ArrayList<Node>();
	}	

	private Node headers() {
		return rows.get(0);
	}

	public Node table(NodeFactory factory) {
		validate(new SizeValidator(headers().meAndDescendants()));				
		return factory.node().add(rows.toArray(new Node[rows.size()]));
	}

	private void validate(SizeValidator validator) {
		for(Node each : contentRows())
			each.validate(validator);
	}	
}
