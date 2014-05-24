package timer.output.base;

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

	protected Component header() {
		return component(header); 
	}

	protected Component footer() {
		return component(footer); 
	}

	protected Component component(String title) {
		return title==null ?  new NullCell()  :  super.component(title);
	}
}
