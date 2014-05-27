package timer.output.types;

import timer.output.base.Format;
import timer.output.base.Cell;
import timer.output.base.RowId;
import timer.output.base.Tag;
import timer.output.base.TableBuilder;
import timer.output.base.TitledComponent;
import timer.output.base.Row;
import timer.output.base.Component;
import timer.output.base.View;

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
			new TitledComponent(new Tag(column), 
				new TitledComponent(new Tag("timing"), new Cell(content))
			)
		);
	}		

	public void render(View view)  {
		view.render(document());
	}	
		
	public Component document()  {
		String declaration="<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>\n";
		return new Row(
			new Component[] {
				new Cell(declaration), 
				new TitledComponent(new Tag("timings"), table())
			});
	}	
}