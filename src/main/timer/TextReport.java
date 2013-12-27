package timer;

public class TextReport extends Report {
	private StringBuffer header=new StringBuffer();

	public TextReport(Layout layout) {
		super(layout);
	}	

	public TestGroup methodGroup(int size, String method, double timing) {
		addHeader(size(size));
		return new TestGroup(method(method), timing(timing), "");
	}	

	private void addHeader(String aHeader) {
		if(header.indexOf(aHeader) < 0) 
			header.append(aHeader);
	}

	protected String size(int size) {
		return String.format("%-25s", "size="+size);		
	}

	protected String method(String method) {
		return String.format("%-25s", method);		
	}	

	private String timing(double timing) {
		return String.format("%-25.2f", timing);		
	}

	public TestGroup sizeGroup(int size, String method, double timing) {
		addHeader(method(method));
		return new TestGroup(size(size), timing(timing), "");
	}

	public String header() {
		String margin=String.format("%-25s", "");
		return "\n"+margin+header;
	}	
}