package timer.reporting.composition.base;

import timer.reporting.base.Content;
import timer.reporting.base.View;
import timer.reporting.base.Field;

public abstract class Document implements Content {
	private TableBuilder builder=new TableBuilder();

	public void add(Field[] fields) {
		String[] contents=new String[fields.length];		
		for(int i=0; i < fields.length; i++)
			contents[i]=fields[i].format(this);
		add(contents[0], contents[1], contents[2]);
	}

	protected abstract void add(String column, String row, String content);

	protected void add(Id column, Id row, Cell cell) {
		builder.add(column, row, cell);
	}

	public void render(View view) {
		view.render(contents());
	}

	protected abstract Row contents();

	protected Row table(Id firstRow) {
		return builder.table(firstRow);
	}
}