package timer.output.base;

public class Content {
	private String content="";

	public Content(String content) {
		this.content=content;
	}

	protected String content() {
		return content;
	}

	public void print(Format format) {
		format.printContent(content());
	}

	public boolean equals(Object another)  {
		return another instanceof Content ? ((Content) another).content.equals(content) : false;
	}
}
