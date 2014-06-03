package timer.reporting.formatting;

import timer.reporting.base.Component;
import timer.reporting.base.View;

public class HeaderCell extends Cell {
	public HeaderCell(String content) {
		super(content);
	}

	public HeaderCell(Component component) {
		super(component);
	}

	public void render(View view) {
		view.renderHeading(toString());
	}
}