package timer.reporting.composition.types;

import timer.reporting.composition.base.Document;
import timer.reporting.composition.base.Row;

public class TextDocument extends Document {
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
		add(new RowId(column), new RowId(row, "\n"), new TextCell(content));
	}

	protected Row contents()  {
		final String FIRST_COLUMN=String.format("%-25s", "\n");
		final String LAST_COLUMN="\n";
		return table(new RowId(FIRST_COLUMN, LAST_COLUMN));
	}	
}
