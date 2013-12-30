package timer.reporting.base;

public class Content  {
	private String header;
	private String data;
	private String footer;

	public Content(String header, String data, String footer)  {
		this.header=header;
		this.data=data;
		this.footer=footer;
	}

	public Content addUnique(Content another)  {
		return data.indexOf(another.data) >=0   ?  this  :  add(another);
	}

	public Content add(Content another)  {
		String newData=this.data+another.data;
		return this.equals(another) ?  new Content(header, newData, footer) :  this;
	}

	public String toString() {
		return header+data+footer;
	}

	public int hashCode() {
		return header.hashCode()+footer.hashCode();
	}

	public boolean equals(Object another)  {
		if(another instanceof Content) {
			Content that=(Content) another;
			return that.header.equals(this.header) && that.footer.equals(this.footer);
		}
		return false;
	}			
}
