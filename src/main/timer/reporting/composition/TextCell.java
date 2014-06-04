package timer.reporting.composition;

import timer.reporting.composition.base.Cell;

import timer.reporting.base.Component;
import timer.reporting.base.View;

public class TextCell extends Cell {
	public TextCell(String content) {
		super(content);
	}

	public TextCell(Component component) {
		super(component);
	}

	public void render(View view) {
		view.renderText(toString());
	}
}