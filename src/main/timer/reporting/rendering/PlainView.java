package timer.reporting.rendering;

import timer.reporting.base.View;
import timer.reporting.base.Content;

public class PlainView extends View {
	public PlainView(Content content) {
		super(content);
	}

	public void renderHeading(String heading) {
		renderText(heading);
	}

	public void renderText(String text) {
		System.out.print(text);
	}	
}