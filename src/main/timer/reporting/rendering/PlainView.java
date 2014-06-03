package timer.reporting.rendering;

import timer.reporting.base.View;
import timer.reporting.base.Format;

public class PlainView extends View {
	public PlainView(Format format) {
		super(format);
	}

	public void renderHeading(String heading) {
		renderText(heading);
	}

	public void renderText(String text) {
		System.out.print(text);
	}	
}