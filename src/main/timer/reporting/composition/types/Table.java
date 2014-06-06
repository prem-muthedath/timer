package timer.reporting.composition.types;

import timer.reporting.composition.base.Row;

import timer.reporting.base.Component;

public class Table extends Row {
	public Table(Component first)  {
		super(new Component[]{first});
	}

	public void add(Component component) {
		validate(component);
		super.add(component);
	}	

	private void validate(Component component) {
		if(size() > 0) validate(all()/size(), component.all());
	}

	private void validate(int expected, int actual) {
		if(expected==actual) return;
		throw new RuntimeException("Invalid Table Row -> TOTAL CELLS EXPECTED:  "+expected+"  TOTAL CELLS FOUND: "+actual);		
	}	
}