package timer.output.base;

import java.util.List;
import java.util.ArrayList;

import timer.framework.Field;

public abstract class Format {
	private TableBuilder builder;
	protected NodeFactory factory;

	public Format(NodeFactory factory, TableBuilder builder) {
		this.factory=factory;
		this.builder=builder;
	}

	public void add(Field[] fields) {
		String[] contents=new String[fields.length];		
		for(int i=0; i < fields.length; i++)
			contents[i]=fields[i].format(this);
		add(contents[0], contents[1], contents[2]);
	}

	public abstract String size(int size);
	public abstract String method(String method);
	public abstract String timing(double timing);
	protected abstract void add(String column, String row, String content);

	protected void add(Node column, Node row, Node content) {
		builder.add(column, row, content);
	}

	public void print()  {
		table().print(this);
	}

	protected Node table() {
		return builder.table(factory);
	}

	protected void printHeading(String heading) {
		printText(heading);
	}

	protected void printText(String text) {
		System.out.print(text);
	}
}

