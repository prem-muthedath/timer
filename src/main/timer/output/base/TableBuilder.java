package timer.output.base;

import java.util.Map;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.ArrayList;

public class TableBuilder {
	private RowBuilder columns;
	private Map<Id, RowBuilder> rows=new LinkedHashMap<Id, RowBuilder>();

	public TableBuilder(Id firstColumn) {
		columns=new RowBuilder(firstColumn); 			// RowBuilder(Id)
	}

	public void add(Id column, Id row, Component cell)  {
		row(row).add(cell);            					// RowBuilder.add(Component)
		if(repeatColumn()) return;
		columns.add(new RowBuilder(column));  		    // RowBuilder.add(new RowBuilder(Id))
	}

	private RowBuilder row(Id row) {  // Flyweight pattern
		return rows.containsKey(row) ?  rows.get(row)  :  create(row);
	}

	private RowBuilder create(Id row) {
		rows.put(row, new RowBuilder());  				// RowBuilder()
		return rows.get(row);		
	}

	private boolean repeatColumn() {
		return rows.size() > 1;
	}

	public Component table() {		
		List<Component> table=new ArrayList<Component>();
		table.add(columns.row());							// RowBuilder.row()
		for(Id each : rows.keySet())
			add(table, each.toRow(rows.get(each)));         // id.toRow(RowBuilder)
		return new Row(table.toArray(new Component[0])); 	// Row(Component[])
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
