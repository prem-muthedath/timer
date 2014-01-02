package timer.reporting.base;

public class Title {
	private Content header;
	private Content footer;

	public Title() {
		this(new NullContent(), new NullContent());
	}

	public Title(Content header, Content footer) {
		this.header=header;
		this.footer=footer;
	}	

	public void append(Title title) {
		header=header.addUnique(title.header);
		footer=footer.addUnique(title.footer);
	}

	public void print(Contents contents) {
		contents.print(header, footer);
	}	
}