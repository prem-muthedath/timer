package timer.output.base;

public class Id {
	private Component header;
	private Component footer;

	public Id(Tag tag) {
		this(tag.startTag(), tag.endTag());
	}

	public Id(String header, String footer) {
		this(Cell.headerCell(header), Cell.headerCell(footer));
	}

	public Id(String header) {
		this(Cell.headerCell(header), Cell.nullCell());
	}

	public Id() {
		this(Cell.nullCell(), Cell.nullCell());
	}

	public Id(Component header, Component footer) {
		this.header=header;
		this.footer=footer;
	}

	public boolean equals(Object object) {
		if(object==null) return false;
		if(!getClass().equals(object.getClass())) return false;
		return this.header.equals(((Id) object).header) && this.footer.equals(((Id) object).footer);		
	}

	public int hashCode() {
		return 41*(41+header.hashCode())+footer.hashCode();
	}

	public void addTo(Row row)  {
		row.add(new Component[]{header, footer});
	}
}
