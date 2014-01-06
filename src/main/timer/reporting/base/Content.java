package timer.reporting.base;

public class Content  {
	private String header;
	private String content;
	private String footer;

	public Content() {
		this("", "", "");
	}
	
	public Content(String header, String content, String footer)  {
		this.header=header;
		this.content=content;
		this.footer=footer;
	}

	Content addUnique(Content another)  {
		return content.indexOf(another.content) >=0   ?  this  :  add(another);
	}

	Content add(Content another)  {
		String newContent=this.content+another.content;
		return this.equals(another) ?  new Content(header, newContent, footer) :  this;
	}

	public String toString() {
		return header+content+footer;
	}

	public int hashCode() {
		return header.hashCode()+footer.hashCode();
	}

	public boolean equals(Object another)  {
		return another instanceof Content ? ((Content) another).header.equals(header) && ((Content) another).footer.equals(footer) : false;
	}			
}
