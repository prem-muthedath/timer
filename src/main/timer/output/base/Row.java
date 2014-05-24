package timer.output.base;

public class Row implements Component {
	private Component[] components;

	public Row(Component[] components) {
		this.components=components;
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