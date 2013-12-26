package timer;

public class Report {
	private Content content=new Content();
	private StringBuffer headers=new StringBuffer();

	public void addTests(int size, Timing[] timings) {	
		content.addTests(size, timings);
	}

	public void print() {
		content.print(this, Test.Order.BY_SIZE);
	}

	public void groupTest(int size, String method, double timing) {
		addHeader(method);
		content.addGroup(format(size(size)), format(timing));
	}

	protected void addHeader(String header)  {
		if(headers.indexOf(header) < 0) 
			headers.append(format(header));
	}

	protected String format(String data)  {
		return String.format("%-25s", data);
	}

	protected String format(double data)  {
		return String.format("%-25.2f", data);
	}

	protected String size(int size) {
		return "size="+size;
	}

	public void print(String[] contents)	{
		System.out.println(format("")+headers);
		for (String each : contents)
			System.out.println(each);
	}
}
