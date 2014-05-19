package timer.framework;

import timer.output.base.Format;

public class Name implements Field, Comparable<Name> {
	private String value;

	public Name(String value) {
		this.value=value;
	} 

	public void addTo(Format format) {
		format.addMethod(value);
	}

	public int compareTo(Name another)  {
		return this.value.compareTo(another.value);
	}		
}
