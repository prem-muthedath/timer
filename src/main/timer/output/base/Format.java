package timer.output.base;

import java.util.List;
import java.util.ArrayList;

import timer.framework.Field;

public abstract class Format {
	protected List<String> fields=new ArrayList<String>();	
	protected TableBuilder builder;
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


	protected abstract void add(String column, String row, String content);

	protected void add(Node column, Node row, Node content) {
		builder.add(column, row, content);
	}

	public void print()  {
		builder.table(factory).print(this);
	}

	protected void printHeading(String heading) {
		printText(heading);
	}

	protected void printText(String text) {
		System.out.print(text);
	}
}

