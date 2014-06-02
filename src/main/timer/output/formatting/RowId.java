package timer.output.formatting;

import timer.output.base.Id;
import timer.output.base.Component;

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

	protected Component name() {
		return component(header); 
	}

	protected Component surname() {
		return component(footer); 
	}

	protected Component component(String title) {
		return title==null ?  new NullCell()  :  super.component(title);
	}
}
