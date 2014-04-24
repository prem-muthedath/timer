package timer.output.base;

import java.util.List;
import java.util.ArrayList;

public class Row  {
	private List<Content> contents=new ArrayList<Content>();

	void addUnique(Content content)  {
		if(contents.contains(content)) return;
		add(content);
	}

	void add(Content content)  {
		contents.add(content);
	}

	public void print(String header, Format format)  {
		format.printHeader(header);
		for (Content each : contents)
			each.print(format);
	}		
}
