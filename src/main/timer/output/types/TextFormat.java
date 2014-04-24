package timer.output.types;

import timer.output.base.Format;

public class TextFormat extends Format {
	public void addBySize(int size, String method, double timing) {
		add(method(method), size(size), timing(timing));
	}

	public void addByMethod(int size, String method, double timing)  {
		add(size(size), method(method), timing(timing));
	}

	private String method(String method) {
		return String.format("%-25s", method);		
	}	

	private String timing(double timing) {
		return String.format("%-25.2f", timing);		
	}

	private String size(int size) {
		return String.format("%-25s", "size="+size);		
	}

	public void print() {
		printContent(rowSeperator());						
		printContent(String.format("%-25s", ""));
		super.print();
	}

	protected String rowSeperator() {
		return "\n";
	}

	protected void printHeader(String header) {
		printContent(header);
	}

	protected void printContent(String content) {
		System.out.print(content);
	}
}
