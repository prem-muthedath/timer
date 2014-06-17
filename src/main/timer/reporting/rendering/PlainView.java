package timer.reporting.rendering;

import timer.reporting.base.View;

public class PlainView extends View {
	public void renderHeading(String heading) {
		renderText(heading);
	}

	public void renderText(String text) {
		System.out.print(text);
	}	
}