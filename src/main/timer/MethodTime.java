package timer;

public class MethodTime {
	private String name;
	private double time;

	public MethodTime(String name, double time) {
		this.name=name;
		this.time=time;
	}

	public void export(Format format) {
		format.add(name, time);
	}
}