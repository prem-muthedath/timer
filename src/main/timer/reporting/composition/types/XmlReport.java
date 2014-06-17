package timer.reporting.composition.types;

import timer.reporting.composition.base.Report;
import timer.reporting.composition.base.TitledComponent;
import timer.reporting.composition.base.Row;

import timer.reporting.base.Component;

public class XmlReport extends Report {
	public String method(String method) {
		return String.format("method name=\"%s\"", method);		
	}	

	public String timing(double timing) {
		return String.format("%.2f\n", timing);		
	}

	public String size(int size) {
		return String.format("size value=\"%s\"", size);		
	}

	public void add(String column, String row, String content) {
		add(new RowId(), new Tag(row), cell(column, content));
	}

	private TextCell cell(String column, String content) {
		return new TextCell(
			new TitledComponent(new Tag(column), 
				new TitledComponent(new Tag("timing"), new TextCell(content))
			)
		);
	}		

	protected Row contents()  {
		final String declaration="<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>\n";
		return new Row(
			new Component[] {
				new TextCell(declaration), 
				new TitledComponent(new Tag("timings"), table(new RowId()))
			});
	}	
}