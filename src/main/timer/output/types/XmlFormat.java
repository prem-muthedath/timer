package timer.output.types;

import timer.output.base.Format;
import timer.output.base.NodeFactory;
import timer.output.base.TableBuilder;
import timer.output.base.Node;

public class XmlFormat extends Format {
	private static final String FIRST_COLUMN_HEADER="";
	private static final String LAST_COLUMN_HEADER="";

	public XmlFormat(NodeFactory factory) {
		super(factory, new TableBuilder(factory.titledNode(FIRST_COLUMN_HEADER, LAST_COLUMN_HEADER)));
	}

	public String method(String method) {
		return String.format("method name=\"%s\"", method);		
	}	

	public String timing(double timing) {
		return String.format("%.2f\n", timing);		
	}

	public String size(int size) {
		return String.format("size value=\"%s\"", size);		
	}

	protected void add(String column, String row, String content) {
		Node xmlContent=xmlContent(column, content);
		add(column(xmlContent), factory.tagNode(row), xmlContent);
	}

	private Node xmlContent(String column, String content) {
		Node xml=factory.tagNode("timing", content);
		return factory.tagNode(column).add(xml);
	}

	private Node column(Node content)  {
		return factory.node(content.meAndDescendants()-1);
	}

	public void print()  {
		String declaration="<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>\n";
		factory.node(declaration).
			add(factory.tagNode("timings").
				add(table())).print(this);
	}	
}
