package timer.results.base;

import timer.reporting.base.Format;

public class Timing implements Field {
	private double value;

	Timing(double value) {
		this.value=value;
	} 

	public String format(Format format) {
		return format.timing(value);
	}
}
