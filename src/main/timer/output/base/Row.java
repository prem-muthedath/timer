package timer.output.base;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

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

	public void print(Format format)  {
		for(Component each : components)
			each.print(format);	
	}

	public int size() {
		int size=0;
		for (Component each : components)
			size+=each.all()==0  ?  0 : 1;
		return size;
	}

	public int all() {
		int size=0;
		for (Component each : components)
			size+=each.all();
		return size;
	}

	public String toString() {
		StringBuffer result=new StringBuffer("");
		for (Component each : components)
			result.append(each.toString());
		return result.toString();
	}	
}