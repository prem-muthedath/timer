package timer.output.formatting;

import timer.output.base.Id;
import timer.output.base.Component;

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

	protected Component name() {
		return component(startTag()); 
	}

	protected Component surname() {
		return component(endTag()); 
	}
}
