package timer.output.types;

import timer.output.base.Format;
import timer.output.base.Cell;
import timer.output.base.RowId;
import timer.output.base.RowBuilder;
import timer.output.base.Tag;
import timer.output.base.TableBuilder;

public class XmlFormat extends Format {
	private static final String FIRST_COLUMN_HEADER="";
	private static final String LAST_COLUMN_HEADER="";

	public XmlFormat() {
		super(new TableBuilder(new RowId(FIRST_COLUMN_HEADER, LAST_COLUMN_HEADER)));
	}

	public String method(String method) {
		return String.format("method name=\"%s\"", method);		
	}	

	public String timing(double timing) {
		return String.format("%.2f\n", timing);		
	}

	public String size(int size) {
		return String.format("size value=\"%s\"", size);		
	}

	protected void add(String column, String row, String content) {
		add(new RowId(""), new Tag(row), cell(column, content));
	}

	private Cell cell(String column, String content) {
		return new Cell(
			new RowBuilder(new Tag(column))    				// RowBuilder(Tag)
				.add(new RowBuilder(
						new Tag("timing"), 
						new Cell(content)
					) 										// RowBuilder.add(new RowBuilder(Tag, Component))
				)  									
				.row()
			);
	}

	public void print()  {
		String declaration="<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>\n";
		new RowBuilder()
			.add(new Cell(declaration))
			.add(new RowBuilder(
					new Tag("timings"), 
					table()
				)   									// RowBuilder.add(new RowBuilder(Tag, Component))
			)  
			.row()
			.print(this);
	}	
}