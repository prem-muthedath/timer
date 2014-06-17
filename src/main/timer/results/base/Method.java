package timer.results.base;

import timer.reporting.base.Format;

public class Method implements Field, Comparable<Method> {
	private String name;

	Method(String name) {
		this.name=name;
	} 

	public String format(Format format) {
		return format.method(name);
	}

	public int compareTo(Method another)  {
		return this.name.compareTo(another.name);
	}		
}
