package timer.output.types;

import timer.output.base.Format;
import timer.output.base.NodeFactory;
import timer.output.base.TableBuilder;
import timer.output.base.Node;


public class TextFormat extends Format {
	private static final String FIRST_COLUMN_HEADER=String.format("%-25s", "\n");
	private static final String LAST_COLUMN_HEADER="\n";

	public TextFormat(NodeFactory factory) {
		super(factory, new TableBuilder(factory.titledNode(FIRST_COLUMN_HEADER, LAST_COLUMN_HEADER)));
	}

	public void addMethod(String method) {
		add(String.format("%-25s", method));		
	}	

	public void addTiming(double timing) {
		add(String.format("%-25.2f", timing));		
	}

	public void addSize(int size) {
		add(String.format("%-25s", "size="+size));		
	}

	protected void add(String column, String row, String content) {
		add(factory.node(column), factory.titledNode(row, "\n"), factory.textNode(content));
	}
}
