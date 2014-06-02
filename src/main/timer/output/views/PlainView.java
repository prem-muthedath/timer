package timer.output.views;

import timer.output.base.View;
import timer.output.base.Format;

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