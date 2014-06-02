package timer.output.ordering;

import timer.output.base.Field;
import timer.output.base.Format;

public class Method implements Field, Comparable<Method> {
	private String name;

	public Method(String name) {
		this.name=name;
	} 

	public String format(Format format) {
		return format.method(name);
	}

	public int compareTo(Method another)  {
		return this.name.compareTo(another.name);
	}		
}
