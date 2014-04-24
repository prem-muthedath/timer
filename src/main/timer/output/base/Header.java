package timer.output.base;

public class Header extends Content {
	public Header(String content) {
		super(content);
	}

	public void print(Format format) {
		format.printHeader(content());
	}

	public boolean equals(Object another)  {
		return another instanceof Header ? ((Header) another).content().equals(content()) : false;
	}	
}
