package timer.framework;

import timer.output.base.Format;

public class Size implements Field, Comparable<Size> {
	private int value;

	public Size(int value) {
		this.value=value;
	} 

	public void addTo(Format format) {
		format.addSize(value);
	}

	public int compareTo(Size another)  {
		return this.value-another.value;
	}	
}
