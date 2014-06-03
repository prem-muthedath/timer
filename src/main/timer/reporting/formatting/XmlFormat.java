package timer.reporting.formatting;

import timer.reporting.base.Format;
import timer.reporting.base.Component;

import timer.reporting.formatting.layouts.TitledComponent;
import timer.reporting.formatting.layouts.Row;

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