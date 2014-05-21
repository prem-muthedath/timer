package timer.output.base;

import java.util.List;
import java.util.ArrayList;

import timer.framework.Field;

public abstract class Format {
	private List<String> fields=new ArrayList<String>();	
	private TableBuilder builder;
	protected NodeFactory factory;

	public Format(NodeFactory factory, TableBuilder builder) {
		this.factory=factory;
		this.builder=builder;
	}

	public void add(Field[] newFields) {
		fields.clear();
		for(Field each : newFields)
			each.addTo(this);
		add(fields.get(0), fields.get(1), fields.get(2));
	}


	public abstract void addSize(int size);
	public abstract void addMethod(String method);
	public abstract void addTiming(double timing);

	protected void add(String item) {
		fields.add(item);
	}
	
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

