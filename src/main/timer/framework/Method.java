package timer.framework;

import timer.output.base.Format;

public class Method implements Field, Comparable<Method> {
	private String name;

	public Method(java.lang.reflect.Method method) {
		this.name=method.getName();
	} 

	public String format(Format format) {
		return format.method(name);
	}

	public int compareTo(Method another)  {
		return this.name.compareTo(another.name);
	}		
}
