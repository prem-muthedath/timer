package timer.output.base;

import java.util.Map;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class Rows {
	private Map<Id, Row> rows=new LinkedHashMap<Id, Row>();

	public int add(Id row, Component cell)  {
		row(row).add(cell);            					
		return rows.size();
	}

	private Row row(Id row) {  
		return rows.containsKey(row) ?  rows.get(row)  :  create(row);
	}

	private Row create(Id row) {
		rows.put(row, new Row());  				
		return rows.get(row);		
	}

	public void fill(Table table) {		
		List<Component> content=new ArrayList<Component>();
		for(Id each : rows.keySet())
			content.add(new TitledComponent(each, rows.get(each)));         
		table.add(content.toArray(new Component[0])); 
	}	
}