package timer;

public class Timing {
	private String method;
	private double time;

	public Timing(String method, double time) {
		this.method=method;
		this.time=time;
	}

	public void export(int size, Content content) {
		content.addTest(size, method, time);
	}
}
