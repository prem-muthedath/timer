package timer.output.base;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class Row implements Component {
	private List<Component> contents=new ArrayList<Component>();
	private static final int ID_OFFSET=1;

	public Row() {
		this(new Id());
	}

	public Row(Id id, Component component) {
		this(id);
		add(component);
	}

	public Row(Id id) {
		id.addTo(this);
	}

	public Row(Component[] components) {
		this();
		add(components);
	}

	public Row add(Component[] components) {
		this.contents.addAll(index(), Arrays.asList(components));
		return this;
	}	

	private int index() {
		return contents.size() <= ID_OFFSET  ?  contents.size()  : contents.size()-ID_OFFSET;		
	}	

	public Row add(Component component) {
		return add(new Component[] {component});
	}

	public void print(Format format)  {
		for(Component each : contents)
			each.print(format);	
	}

	public int size() {
		int size=0;
		for (Component each : contents)
			size+=each.all()==0  ?  0 : 1;
		return size;
	}

	public int all() {
		int size=0;
		for (Component each : contents)
			size+=each.all();
		return size;
	}

	public String toString() {
		StringBuffer result=new StringBuffer("");
		for (Component each : contents)
			result.append(each.toString());
		return result.toString();
	}	
}
