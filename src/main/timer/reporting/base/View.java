package timer.reporting.base;

public abstract class View  {
	private Content content;

	public View(Content content) {
		this.content=content;
	}

	public void add(Field[] fields) {
		content.add(fields);
	}

	public void render() {
		content.render(this);
	}

	public void render(Component component) {
		component.render(this);
	}

	public abstract void renderHeading(String heading);
	public abstract void renderText(String text);
}