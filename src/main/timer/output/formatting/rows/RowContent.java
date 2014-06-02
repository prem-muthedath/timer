package timer.output.formatting.rows;

import java.util.Iterator;

import timer.output.base.Component;

abstract class RowContent extends RowAggregate  {
	private StringBuffer content=new StringBuffer("");

	RowContent(Iterator<Component> row) {
		super(row);
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