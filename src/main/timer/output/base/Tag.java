package timer.output.base;

public class Tag {
	private String name;

	public Tag(String name) {
		this.name=name.trim();
	}

	public String startTag() {
		return "<"+goodName()+">";
	}

	private String goodName() {
		return name.replace('>', '\0').replace('<', '\0').replace('/', '\0');
	}

	public String endTag() {
		return "</"+nameWithoutAttributes()+">";
	}

	private String nameWithoutAttributes() {
		return goodName().indexOf(" ") > 0  ?  goodName().substring(0, goodName().indexOf(" "))  :  goodName();		
	}
}
