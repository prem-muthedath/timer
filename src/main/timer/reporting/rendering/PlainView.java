package timer.reporting.rendering;

import timer.reporting.base.View;

import timer.reporting.composition.base.Document;

public class PlainView extends View {
	public PlainView(Document document) {
		super(document);
	}

	public void renderHeading(String heading) {
		renderText(heading);
	}

	public void renderText(String text) {
		System.out.print(text);
	}	
}