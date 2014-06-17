package timer.reporting.base;

public abstract class View  {
	public void render(Component component) {
		component.render(this);
	}

	public abstract void renderHeading(String heading);
	public abstract void renderText(String text);
}