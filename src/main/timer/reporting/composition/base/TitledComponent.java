package timer.reporting.composition.base;

import timer.reporting.base.Component;
import timer.reporting.base.View;

public class TitledComponent implements Component {
	private Component component;

	public TitledComponent(Id title, Component component) {
		this.component=title.toRow(component);
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