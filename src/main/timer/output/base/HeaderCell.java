package timer.output.base;

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