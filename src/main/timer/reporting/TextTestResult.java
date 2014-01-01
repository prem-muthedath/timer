package timer.reporting;

import timer.reporting.base.TestResult;
import timer.reporting.base.Content;

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

	protected Content sizeHeader() {
		return new Content(margin(), method(), "");
	}	

	protected Content methodHeader() {
		return new Content(margin(), size(), "");
	}	

	private String margin() {
		return "\n"+String.format("%-25s", "");
	}	
}