package timer.reporting.base;

import java.util.List;
import java.util.ArrayList;

import timer.framework.Timings;

public abstract class Report {
	private List<Content> contents=new ArrayList<Content>();
	
	public void print(Format format, Timings timings) {
		format.print(this, timings);
	}

	public void print(TestResult[] results) {
		sort(results);  
		Title title=new Title();
		for (TestResult each : results)
			export(each, title);
		print(title);
	}

	protected abstract void sort(TestResult[] results);
	protected abstract void export(TestResult result, Title title);

	public void add(Content content) {
		for(int i=0; i < contents.size(); i++)
			contents.set(i, contents.get(i).add(content));
		if(!contents.contains(content))
			contents.add(content);
	}

	private void print(Title title)  {
		System.out.println(title);
		for (Content each : contents)
			System.out.println(each);
	}
}
