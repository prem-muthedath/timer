package timer.output.formatting.rows;

import java.util.Iterator;

import timer.output.base.Component;

abstract class RowAggregate {
	private Iterator<Component> row;

	RowAggregate(Iterator<Component> row) {
		this.row=row;
	}

	void process() {
		while(row.hasNext())
			process(row.next());
	}

	abstract void process(Component child);
	public abstract int total();
	public abstract String toString();

	static int size(final Iterator<Component> row)	{
		return new RowCounts(row) {
			void process(Component child) {
				add(child.all()==0 ? 0 : 1);
			}
		}.total();
	}

	static int all(final Iterator<Component> row)	{
		return new RowCounts(row) {
			void process(Component child) {
				add(child.all());
			}
		}.total();
	}

	static String toString(final Iterator<Component> row)	{
		return new RowContent(row) {
			void process(Component child) {
				add(child.toString());
			}
		}.toString();
	}	
}