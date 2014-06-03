package timer.reporting.formatting.layouts;

import timer.reporting.base.Component;

public class Table extends Row {
	public Table(Component first)  {
		add(first);
	}

	public void add(Component[] components) {
		for(Component each : components)
			validate(each);
		super.add(components);
	}	

	public void add(Component component) {
		validate(component);
		super.add(component);
	}	

	public void validate(Component component) {
		if(size() > 0) validate(all()/size(), component.all());
	}

	private void validate(int expected, int actual) {
		if(expected==actual) return;
		throw new RuntimeException("Invalid Row Size: EXPECTED:  "+expected+"  FOUND: "+actual);		
	}	
}