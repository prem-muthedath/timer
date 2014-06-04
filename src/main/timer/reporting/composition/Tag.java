package timer.reporting.composition;

import timer.reporting.composition.base.Id;
import timer.reporting.composition.base.Cell;

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

	protected Cell name() {
		return new TextCell(startTag()); 
	}

	protected Cell surname() {
		return new TextCell(endTag()); 
	}
}
