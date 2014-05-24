package timer.output.base;

public class Tag extends Id {
	private String name;

	public Tag(String name) {
		this.name=name.trim();
	}

	private String startTag() {
		return "<"+goodName()+">";
	}

	private String goodName() {
		return name.replace('>', '\0').replace('<', '\0').replace('/', '\0');
	}

	private String endTag() {
		return "</"+nameWithoutAttributes()+">";
	}

	private String nameWithoutAttributes() {
		return goodName().indexOf(" ") > 0  ?  goodName().substring(0, goodName().indexOf(" "))  :  goodName();		
	}

	protected Component header() {
		return component(startTag()); 
	}

	protected Component footer() {
		return component(endTag()); 
	}
}
