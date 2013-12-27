package timer;

public class Timing {
	private String method;
	private double time;

	public Timing(String method, double time) {
		this.method=method;
		this.time=time;
	}

	public Test export(int size, Order order) {
		return order.test(size, method, time);
	}
}
