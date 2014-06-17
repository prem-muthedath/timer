package timer.reporting.composition.types;

import timer.reporting.composition.base.Report;
import timer.reporting.composition.base.Row;

public class TextReport extends Report {
	public String method(String method) {
		return String.format("%-25s", method);		
	}	

	public String timing(double timing) {
		return String.format("%-25.2f", timing);		
	}

	public String size(int size) {
		return String.format("%-25s", "size="+size);		
	}

	public void add(String column, String row, String content) {
		add(new RowId(column), new RowId(row, "\n"), new TextCell(content));
	}

	protected Row contents()  {
		final String firstColumn=String.format("%-25s", "\n");
		final String lastColumn="\n";
		return table(new RowId(firstColumn, lastColumn));
	}	
}
