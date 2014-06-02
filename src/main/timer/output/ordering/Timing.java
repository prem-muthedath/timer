package timer.output.ordering;

import timer.output.base.Field;
import timer.output.base.Format;

public class Timing implements Field {
	private double value;

	public Timing(double value) {
		this.value=value;
	} 

	public String format(Format format) {
		return format.timing(value);
	}
}
