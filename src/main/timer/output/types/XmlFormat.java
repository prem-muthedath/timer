package timer.output.types;

import timer.output.base.Format;
import timer.output.base.TableBuilder;
import timer.output.base.Cell;
import timer.output.base.Id;
import timer.output.base.Row;
import timer.output.base.Tag;
import timer.output.base.Component;

public class XmlFormat extends Format {
	private static final String FIRST_COLUMN_HEADER="";
	private static final String LAST_COLUMN_HEADER="";

	public XmlFormat() {
		super(new TableBuilder(new Id(FIRST_COLUMN_HEADER, LAST_COLUMN_HEADER)));
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
		add(new Id(""), new Id(new Tag(row)), xmlContent(column, content));
	}

	private Cell xmlContent(String column, String content) {
		Row contentRow=new Row(new Id(new Tag("timing")), Cell.textCell(content));
		return Cell.textCell(new Row(new Id(new Tag(column)), contentRow).toString());
	}

	public void print()  {
		String declaration="<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>\n";
		new Row(new Component[] {  								// Row(Component[])
			Cell.textCell(declaration),
			new Row(new Id(new Tag("timings")), table())    // Row(Id, Component)
		}).print(this);
	}	
}
