package timer.output.base;

import java.util.List;
import java.util.ArrayList;

public class TableBuilder {
	private List<Id> columns=new ArrayList<Id>();
	private Rows rows=new Rows();

	public TableBuilder(Id firstColumn) {
		columns.add(firstColumn);
	}

	public void add(Id column, Id row, Component cell)  {
		int rowCount=rows.add(row, cell);
		if(repeatColumn(rowCount)) return;
		columns.add(column);
	}

	private boolean repeatColumn(int rowCount) {
		return rowCount > 1;
	}

	public Table table() {	
		Table table=new Table(header());
		rows.fill(table);
		return table;
	}

	public Component header() {
		Row header=new Row();
		for(int i=1; i < columns.size(); i++)
			columns.get(i).addTo(header);
		return new TitledComponent(columns.get(0), header);
	}
}