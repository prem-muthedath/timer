package timer.output.base;

public class Cell implements Component {
	private String content;

	public Cell(String content) {
		this.content=content;
	}

	public Cell(Component component) {
		this.content=component.toString();
	}

	public void render(View view) {
		view.renderText(content);
	}

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
}