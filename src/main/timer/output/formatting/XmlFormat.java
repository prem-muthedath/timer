package timer.output.formatting;

import timer.output.base.Format;
import timer.output.base.Component;

import timer.output.formatting.rows.TitledComponent;
import timer.output.formatting.rows.Row;

public class XmlFormat extends Format {
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
		add(new RowId(), new Tag(row), cell(column, content));
	}

	private Cell cell(String column, String content) {
		return new Cell(
			new TitledComponent(new Tag(column), 
				new TitledComponent(new Tag("timing"), new Cell(content))
			)
		);
	}		

	protected Component document()  {
		String declaration="<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>\n";
		return new Row(
			new Component[] {
				new Cell(declaration), 
				new TitledComponent(new Tag("timings"), table(new RowId()))
			});
	}	
}