package timer.output.base;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class RowBuilder {
	private Id id;	
	private List<Component> contents=new ArrayList<Component>();

	public RowBuilder() {
		this(new RowId());
	}

	public RowBuilder(Id id, Component component) {
		this(id);
		add(component);
	}

	public RowBuilder(Id id) {
		this.id=id;
	}

	public RowBuilder add(Component[] components) {
		this.contents.addAll(Arrays.asList(components));
		return this;
	}	

	public RowBuilder add(Component component) {
		return add(new Component[] {component});
	}

	public RowBuilder add(RowBuilder builder) {
		return add(builder.row());
	}

	public Row row(Id some)  {
		return some.toRow(row());
	}

	public Row row()  {
		return id.toRow(new Row(contents.toArray(new Component[0])));
	}
}