package timer.output.base;

import java.util.Iterator;

abstract class RowContent extends RowAggregate  {
	private StringBuffer content=new StringBuffer("");

	RowContent(Iterator<Component> iterator) {
		super(iterator);
	}

	void add(String content) {
		this.content.append(content);
	}

	public int total() {
		return toString().length();
	}	

	public String toString() {
		process();
		return content.toString();
	}
}