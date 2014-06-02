package timer.output.ordering;

import timer.output.base.Field;
import timer.output.base.Format;

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
