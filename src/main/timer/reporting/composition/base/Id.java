package timer.reporting.composition.base;

import timer.reporting.base.Component;

public abstract class Id {
	public boolean equals(Object object) {
		if(object==null) return false;
		if(!getClass().equals(object.getClass())) return false;
		return this.name().equals(((Id) object).name()) && this.surname().equals(((Id) object).surname());		
	}

	protected abstract Cell name();
	protected abstract Cell surname();	

	public int hashCode() {
		return 41*(41+name().hashCode())+surname().hashCode();
	}

	public Row toRow(Component component)  {
		return new Row(new Component[] {name(), component, surname()});
	}
}