package timer.output.formatting.rows;

import java.util.Iterator;

import timer.output.base.Component;

abstract class RowCounts extends RowAggregate {
	private int counts=0;

	RowCounts(Iterator<Component> row) {
		super(row);
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

