package timer.framework;

import timer.output.base.Format;

public class Timing implements Field {
	private double value;

	public Timing(double value) {
		this.value=value;
	} 

	public void addTo(Format format) {
		format.addTiming(value);
	}
}
