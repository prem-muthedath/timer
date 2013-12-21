package timer;

public class Report {
	private Timings timings=new Timings(this);

	public void add(int size, Timing[] timings) {	
		for (Timing each : timings)
			each.export(size, this);
	}

	public void addTiming(int size, String method, double timing)  {
		timings.add(key(size, method(method)), timing(timing));
	}

	protected SortKey key(int size, String method)  {
		return new SortKey(size, method);
	}

	private String method(String method)  {
		return String.format("%-25s\t", method);
	}

	private String timing(double timing)  {
		return String.format("%-25.2f\t", timing);
	}

	public String size(int size) {
		return String.format("%-15s\t", "size="+size);
	}

	public void print() {
		timings.print();
	}

	public void print(String header, StringBuffer[] data)	{
		String headerMargin=String.format("%-15s\t", "");		
		System.out.println(headerMargin+header);
		for (StringBuffer each : data)
			System.out.println(each);
	}
}
