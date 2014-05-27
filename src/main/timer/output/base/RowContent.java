package timer.output.base;

import java.util.Iterator;

class RowContent extends RowAggregate  {
	private StringBuffer content=new StringBuffer("");

	RowContent(Iterator<Component> iterator) {
		super(iterator);
	}

	void process(Component child) {
		content.append(child.toString());
	}

	public int total() {
		return toString().length();
	}	

	public String toString() {
		process();
		return content.toString();
	}
}