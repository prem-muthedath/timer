package timer.reporting.composition.base;

import timer.reporting.base.Component;
import timer.reporting.base.View;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;

public class Row implements Component {
	private List<Component> components=new ArrayList<Component>();

	public Row() {}

	public Row(Component[] components) {
		this.components.addAll(Arrays.asList(components));
	}

	public void add(Component component) {
		this.components.add(component);
	}	

	public void render(View view)  {
		for(Component each : components)
			each.render(view);	
	}

	public int size() {
		return RowAggregate.size(row());
	}

	private Iterator<Component> row() {	
		return Collections.unmodifiableCollection(components).iterator();
	}
	
	public int all() {
		return RowAggregate.all(row());
	}

	public String toString() {
		return RowAggregate.toString(row());
	}	
}