package timer.reporting.base;

public abstract class Format {
	private TableBuilder builder=new TableBuilder();

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

	protected void add(Id column, Id row, Component cell) {
		builder.add(column, row, cell);
	}

	public void render(View view) {
		view.render(document());
	}

	protected abstract Component document();

	protected Component table(Id firstRow) {
		return builder.table(firstRow);
	}
}