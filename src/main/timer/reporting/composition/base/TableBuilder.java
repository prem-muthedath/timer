package timer.reporting.composition.base;

import timer.reporting.composition.types.Table;

import java.util.Map;
import java.util.LinkedHashMap;

public class TableBuilder {
	private Row columns=new Row();
	private Map<Id, Row> rows=new LinkedHashMap<Id, Row>();

	void add(Id column, Id row, Cell cell)  {
		row(row).add(cell);            					
		if(repeatColumn()) return;
		addColumn(column);
	}

	private Row row(Id row) {  
		return rows.containsKey(row) ?  rows.get(row)  :  create(row);    // Flyweight pattern used here.
	}

	private Row create(Id row) {
		rows.put(row, new Row());  				
		return rows.get(row);		
	}

	private boolean repeatColumn() {
		return rows.size() > 1;
	}

	private void addColumn(Id column) {
		columns.add(new TitledComponent(column, new Row()));
	}

	Table table(Id firstRow) {	
		Table table=new Table(new TitledComponent(firstRow, columns));
		for(Id each : rows.keySet())
			table.add(new TitledComponent(each, rows.get(each)));  
		return table;
	}	
}