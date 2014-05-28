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
	public abstract int total();
	public abstract String toString();

	static int size(final Iterator<Component> iterator)	{
		return new RowCounts(iterator) {
			void process(Component child) {
				add(child.all()==0 ? 0 : 1);
			}
		}.total();
	}

	static int all(final Iterator<Component> iterator)	{
		return new RowCounts(iterator) {
			void process(Component child) {
				add(child.all());
			}
		}.total();
	}

	static String toString(final Iterator<Component> iterator)	{
		return new RowContent(iterator) {
			void process(Component child) {
				add(child.toString());
			}
		}.toString();
	}	
}