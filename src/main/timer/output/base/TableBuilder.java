package timer.output.base;

import java.util.Map;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.ArrayList;

public class TableBuilder {
	private Row columns;
	private Map<Id, Row> rows=new LinkedHashMap<Id, Row>();

	public TableBuilder(Id firstColumn) {
		columns=new Row(firstColumn); // Row(Id)
	}

	public void add(Id column, Id row, Component cell)  {
		row(row).add(cell);            // Row.add(Component)
		if(repeatColumn()) return;
		columns.add(new Row(column));  // Row(Id), Row.add(Component)
	}

	private Row row(Id row) {  // Flyweight pattern
		return rows.containsKey(row) ?  rows.get(row)  :  create(row);
	}

	private Row create(Id row) {
		rows.put(row, new Row());  // Row()
		return rows.get(row);		
	}

	private boolean repeatColumn() {
		return rows.size() > 1;
	}

	public Component table() {		
		List<Component> table=new ArrayList<Component>();
		table.add(columns);
		for(Id each : rows.keySet())
			add(table, new Row(each, rows.get(each)));         // Row(Id, Component)
		return new Row().add(table.toArray(new Component[0])); // Row().add(Component[])
	}

	private void add(List<Component> table, Row row) {
		validate(table.get(0).all(), row.all());
		table.add(row);
	}

	private void validate(int expected, int actual) {
		if(expected==actual) return;
		throw new RuntimeException("Invalid Row Size: EXPECTED:  "+expected+"  FOUND: "+actual);		
	}
}
