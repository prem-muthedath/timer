package timer.output.formatting;

import timer.output.base.Component;
import timer.output.base.View;

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