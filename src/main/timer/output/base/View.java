package timer.output.base;

import timer.framework.Field;

public abstract class View  {
	private Format format;

	public View(Format format) {
		this.format=format;
	}

	public void add(Field[] fields) {
		format.add(fields);
	}

	public void render() {
		format.render(this);
	}

	public void render(Component component) {
		component.render(this);
	}

	public abstract void renderHeading(String heading);
	public abstract void renderText(String text);
}