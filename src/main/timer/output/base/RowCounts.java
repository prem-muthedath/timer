package timer.output.base;

import java.util.Iterator;

abstract class RowCounts extends RowAggregate {
	private int counts=0;

	RowCounts(Iterator<Component> iterator) {
		super(iterator);
	}

	public int total() {		
		process();
		return counts;
	}

	void add(int count) {
		counts+=count;
	}	

	public String toString() {
		return new Integer(total()).toString();
	}
}

