package timer.reporting.types;

import timer.reporting.base.TestResult;
import timer.reporting.base.Content;
import timer.reporting.base.Title;

public class TextTestResult extends TestResult {
	public TextTestResult(int size, String method, double timing) {
		super(size, method, timing);
	}

	public Content methodContent() {
		return new Content(method(), timing(), "");
	}	

	private String method() {
		return String.format("%-25s", method);		
	}	

	private String timing() {
		return String.format("%-25.2f", timing);		
	}

	protected Content sizeContent() {
		return new Content(size(), timing(), "");
	}

	private String size() {
		return String.format("%-25s", "size="+size);		
	}

	protected Title sizeTitle() {
		return new Title(new Content(margin(), method(), ""), new Content());
	}	

	protected Title methodTitle() {
		return new Title(new Content(margin(), size(), ""), new Content());
	}	

	private String margin() {
		return "\n"+String.format("%-25s", "");
	}	
}