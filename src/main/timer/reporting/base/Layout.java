package timer.reporting.base;

import java.util.List;
import java.util.ArrayList;

public class Layout {
	private List<Content> contents=new ArrayList<Content>();
	private Title title=new Title();

	void add(Title aTitle, Content content) {
		title.append(aTitle);
		add(content);
	}	

	private void add(Content content) {
		for(int i=0; i < contents.size(); i++)
			contents.set(i, contents.get(i).add(content));
		if(!contents.contains(content))
			contents.add(content);
	}

	void print()  {
		title.print(this);
	}

	void print(Content header, Content footer)  {
		System.out.println(header);
		for (Content each : contents)
			System.out.println(each);
		System.out.println(footer);		
	}
}