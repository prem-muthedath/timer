package timer.reporting.ordering;

import timer.reporting.base.Field;
import timer.reporting.base.Format;

public class Size implements Field, Comparable<Size> {
	private int value;

	public Size(int value) {
		this.value=value;
	} 

	public String format(Format format) {
		return format.size(value);
	}

	public int compareTo(Size another)  {
		return this.value-another.value;
	}	
}
