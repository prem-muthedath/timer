package timer.output.base;

public abstract class Id {
	public boolean equals(Object object) {
		if(object==null) return false;
		if(!getClass().equals(object.getClass())) return false;
		return this.header().equals(((Id) object).header()) && this.footer().equals(((Id) object).footer());		
	}

	protected abstract Component header();
	protected abstract Component footer();	

	protected Component component(String title) {
		return new HeaderCell(title);
	}

	public int hashCode() {
		return 41*(41+header().hashCode())+footer().hashCode();
	}

	public Row toRow(RowBuilder builder)  {
		return builder.row(this);
	}

	public Row toRow(Row row)  {
		return new Row(new Component[] {header(), row, footer()});
	}
}