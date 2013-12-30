package timer.framework;

import java.util.List;
import java.util.ArrayList;

public abstract class Report {
	private List<TestGroup> groups=new ArrayList<TestGroup>();
	
	public void print(Format format, Timings timings) {
		format.print(this, timings);
	}

	public void print(TestResultExport[] exports) {
		sort(exports);  
		Title title=new Title();
		for (TestResultExport each : exports)
			export(each, title);
		print(title);
	}

	protected abstract void sort(TestResultExport[] exports);
	protected abstract void export(TestResultExport export, Title title);

	public void add(TestGroup group) {
		for(int i=0; i < groups.size(); i++)
			groups.set(i, groups.get(i).add(group));
		if(!groups.contains(group))
			groups.add(group);
	}

	private void print(Title title)  {
		System.out.println(title);
		for (TestGroup each : groups)
			System.out.println(each);
	}
}
