package timer.output.types;

import timer.output.base.Format;
import timer.output.base.TableBuilder;
import timer.output.base.Cell;
import timer.output.base.RowId;

public class TextFormat extends Format {
	private static final String FIRST_COLUMN_HEADER=String.format("%-25s", "\n");
	private static final String LAST_COLUMN_HEADER="\n";

	public TextFormat() {
		super(new TableBuilder(new RowId(FIRST_COLUMN_HEADER, LAST_COLUMN_HEADER)));
	}

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

	public void print()  {
		table().print(this);
	}	
}
