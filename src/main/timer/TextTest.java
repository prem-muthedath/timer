package timer;

public class TextTest  extends Test {
	public TextTest(int size, String method, double timing) {
		super(size, method, timing);
	}	

	protected String headerMargin() {
		return String.format("%-25s", "");
	}

	protected String sizeHeader() {
		return String.format("%-25s", "size="+size);		
	}

	protected TestGroup methodGroup() {
		return new TestGroup(methodHeader(), timing(), "");
	}	

	protected String methodHeader() {
		return String.format("%-25s", method);		
	}	

	protected TestGroup sizeGroup() {
		return new TestGroup(sizeHeader(), timing(), "");
	}

	private String timing() {
		return String.format("%-25.2f", timing);		
	}
}