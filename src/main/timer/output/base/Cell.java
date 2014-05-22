package timer.output.base;

public abstract class Cell implements Component {
	private String content;

	public Cell(String content) {
		this.content=content;
	}

	public abstract void print(Format format);

	public int size() {
		return all();
	}

	public int all() {
		return 1;
	}

	public String toString() {
		return content;
	}

	public boolean equals(Object object) {
		if(object==null) return false;
		if(!getClass().equals(object.getClass())) return false;
		return content.equals(((Cell) object).content);
	}

	public int hashCode() {
		return content.hashCode();
	}

	public static Cell textCell(final String content) {
		return new Cell(content) {
			public void print(Format format) {
				format.printText(content);
			}
		};
	}

	public static Cell headerCell(final String content) {
		return new Cell(content) {
			public void print(Format format) {
				format.printHeading(content);
			}
		};
	}

	public static Cell nullCell() {
		return nullCell("");
	}

	public static Cell nullCell(final String content) {
		return new Cell(content) {
			public void print(Format format) {}

			public int all() {
				return 0;
			}
		};
	}
}
