package timer.framework;

public abstract class Report {
	private Layout layout;

	public Report(Layout layout)  {
		this.layout=layout;
	}

	public void addTests(int size, Timing[] timings) {	
		layout.addTests(size, timings);
	}

	public void print() {
		layout.print(this);
	}

	public abstract TestGroup methodGroup(int size, String method, double timing);
	public abstract TestGroup sizeGroup(int size, String method, double timing);
	public abstract String header();
}