package timer.reporting.composition.types;

import timer.reporting.composition.base.Id;
import timer.reporting.composition.base.Cell;

public class RowId extends Id {
	private String header;
	private String footer;

	public RowId(String header, String footer) {
		this.header=header;
		this.footer=footer;
	}

	public RowId(String header) {
		this(header, null);
	}

	public RowId() {
		this(null, null);
	}

	protected Cell name() {
		return component(header); 
	}

	protected Cell surname() {
		return component(footer); 
	}

	private Cell component(String title) {
		return title==null ?  new NullCell()  :  new HeaderCell(title);
	}
}
