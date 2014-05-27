package timer.output.base;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

abstract class RowCounts extends RowAggregate {
	private List<Integer> counts=new ArrayList<Integer>();

	RowCounts(Iterator<Component> iterator) {
		super(iterator);
	}

	public int total() {		
		process();
		int total=0;
		for(Integer each : counts)
			total+=each.intValue();
		return total;
	}

	void add(int count) {
		counts.add(new Integer(count));
	}	

	public String toString() {
		return new Integer(total()).toString();
	}

	static RowAggregate size(final Iterator<Component> iterator)	{
		return new RowCounts(iterator) {
			void process(Component child) {
				add(child.all()==0 ? 0 : 1);
			}
		};
	}

	static RowAggregate all(final Iterator<Component> iterator)	{
		return new RowCounts(iterator) {
			void process(Component child) {
				add(child.all());
			}
		};
	}
}

