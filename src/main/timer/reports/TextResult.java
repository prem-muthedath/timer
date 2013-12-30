package timer.reports;

import timer.framework.TestResultExport;
import timer.framework.TestGroup;
import timer.framework.Title;

public class TextResult extends TestResultExport {
	public TestGroup methodGroup() {
		return new TestGroup(method(), timing(), "");
	}	

	private String method() {
		return String.format("%-25s", method);		
	}	

	private String timing() {
		return String.format("%-25.2f", timing);		
	}

	protected TestGroup sizeGroup() {
		return new TestGroup(size(), timing(), "");
	}

	private String size() {
		return String.format("%-25s", "size="+size);		
	}

	protected void sizeHeader(Title title) {
		title.add(new TestGroup(margin(), method(), ""));
	}	

	protected void methodHeader(Title title) {
		title.add(new TestGroup(margin(), size(), ""));
	}	

	private String margin() {
		return "\n"+String.format("%-25s", "");
	}	
}