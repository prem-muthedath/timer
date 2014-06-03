package timer.reporting.formatting;

import timer.reporting.base.Format;
import timer.reporting.base.Component;

public class TextFormat extends Format {
	private static final String FIRST_COLUMN=String.format("%-25s", "\n");
	private static final String LAST_COLUMN="\n";

	public String method(String method) {
		return String.format("%-25s", method);		
	}	

	public String timing(double timing) {
		return String.format("%-25.2f", timing);		
	}

	public String size(int size) {
		return String.format("%-25s", "size="+size);		
	}

	protected void add(String column, String row, String content) {
		add(new RowId(column), new RowId(row, "\n"), new Cell(content));
	}

	public Component document()  {
		return table(new RowId(FIRST_COLUMN, LAST_COLUMN));
	}	
}
