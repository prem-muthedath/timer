package timer.output.base;

public class TitledComponent implements Component {
	private Component component;

	public TitledComponent(Id id, Component component) {
		this.component=id.toRow(component);
	}

	public void render(View view)  {
		component.render(view);
	}

	public int size() {
		return component.size();
	}

	public int all() {
		return component.all();
	}

	public String toString() {
		return component.toString();
	}	
}