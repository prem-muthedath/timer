package timer.output.base;

import timer.output.formatting.HeaderCell;
import timer.output.formatting.rows.Row;

public abstract class Id {
	public boolean equals(Object object) {
		if(object==null) return false;
		if(!getClass().equals(object.getClass())) return false;
		return this.name().equals(((Id) object).name()) && this.surname().equals(((Id) object).surname());		
	}

	protected abstract Component name();
	protected abstract Component surname();	

	protected Component component(String title) {
		return new HeaderCell(title);
	}


	public int hashCode() {
		return 41*(41+name().hashCode())+surname().hashCode();
	}

	public void addTo(Row row)  {
		row.add(new Component[] {name(), surname()});
	}

	public Row toRow(Component component)  {
		return new Row(new Component[] {name(), component, surname()});
	}
}