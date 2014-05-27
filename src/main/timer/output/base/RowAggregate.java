package timer.output.base;

import java.util.Iterator;

abstract class RowAggregate {
	private Iterator<Component> iterator;

	RowAggregate(Iterator<Component> iterator) {
		this.iterator=iterator;
	}

	void process() {
		while(iterator.hasNext())
			process(iterator.next());
	}

	abstract void process(Component child);
	abstract public int total();
	abstract public String toString();
}