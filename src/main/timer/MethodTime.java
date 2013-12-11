package timer;

public class MethodTime implements Comparable, Formatable {
	private String name;
	private double time;

	public MethodTime(String name, double time) {
		this.name=name;
		this.time=time;
	}

	public String header(Format format) {
		return format.bigHeader(name);
	}

	public String value(Format format) {
		return format.value(time);
	}

	public int compareTo(Object another) {
		if(!(another instanceof MethodTime)) 
			throw new ClassCastException("MethodTime expected");
		MethodTime anotherMethodTime=(MethodTime) another;
		return this.name.compareTo(anotherMethodTime.name);
	}
}