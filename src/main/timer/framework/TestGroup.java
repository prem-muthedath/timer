package timer.framework;

public class TestGroup  {
	private String header;
	private String content;
	private String footer;

	public TestGroup(String header, String content, String footer)  {
		this.header=header;
		this.content=content;
		this.footer=footer;
	}

	public TestGroup addUnique(TestGroup another)  {
		return content.indexOf(another.content) >=0   ?  this  :  add(another);
	}

	public TestGroup add(TestGroup another)  {
		String newContent=this.content+another.content;
		return this.equals(another) ?  new TestGroup(header, newContent, footer) :  this;
	}

	public String toString() {
		return header+content+footer;
	}

	public int hashCode() {
		return header.hashCode()+footer.hashCode();
	}

	public boolean equals(Object another)  {
		if(another instanceof TestGroup) {
			TestGroup that=(TestGroup) another;
			return that.header.equals(this.header) && that.footer.equals(this.footer);
		}
		return false;
	}			
}
