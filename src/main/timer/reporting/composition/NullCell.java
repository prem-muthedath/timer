package timer.reporting.composition;

import timer.reporting.composition.base.Cell;

import timer.reporting.base.View;

public class NullCell extends Cell {
	public NullCell(String content) {
		super(content);
	}

	public NullCell() {
		this("");
	}

	public void render(View view) {}

	public int all() {
		return 0;
	}
}