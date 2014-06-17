package timer.reporting.composition.base;

import timer.reporting.base.Format;
import timer.reporting.base.View;

public abstract class Report implements Format {
	private TableBuilder builder=new TableBuilder();

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