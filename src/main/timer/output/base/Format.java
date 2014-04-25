package timer.output.base;

public abstract class Format {
	protected TableBuilder builder;
	protected NodeFactory factory;

	public Format(NodeFactory factory, TableBuilder builder) {
		this.factory=factory;
		this.builder=builder;
	}

	public void addBySize(int size, String method, double timing) {
		add(method(method), size(size), timing(timing));
	}

	public void addByMethod(int size, String method, double timing)  {
		add(size(size), method(method), timing(timing));
	}

	protected abstract String method(String method);
	protected abstract String timing(double timing);
	protected abstract String size(int size);
	
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

