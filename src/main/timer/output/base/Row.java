package timer.output.base;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;

public class Row implements Component {
	private List<Component> components=new ArrayList<Component>();

	public Row() {}

	public Row(Component[] components) {
		add(components);
	}

	public void add(Component component) {
		add(new Component[]{component});
	}	

	public void add(Component[] components) {
		this.components.addAll(Arrays.asList(components));
	}	

	public void render(View view)  {
		for(Component each : components)
			each.render(view);	
	}

	public int size() {
		return RowCounts.size(iterator()).total();
	}

	private Iterator<Component> iterator() {	
		return Collections.unmodifiableCollection(components).iterator();
	}
	
	public int all() {
		return RowCounts.all(iterator()).total();
	}

	public String toString() {
		return new RowContent(iterator()).toString();
	}	
}