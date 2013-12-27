package timer;

public class Timing {
	private String method;
	private double time;

	public Timing(String method, double time) {
		this.method=method;
		this.time=time;
	}

	public void export(int size, Report report) {
		report.addTest(size, method, time);
	}
}
