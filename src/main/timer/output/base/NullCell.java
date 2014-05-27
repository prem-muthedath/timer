package timer.output.base;

public class NullCell extends Cell {
	public NullCell(String content) {
		super(content);
	}

	public NullCell() {
		super("");
	}

	public void render(View view) {}

	public int all() {
		return 0;
	}
}