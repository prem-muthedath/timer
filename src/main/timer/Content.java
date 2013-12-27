package timer;

import java.util.List;
import java.util.ArrayList;

public class Content  {
	private List<TestGroup> groups=new ArrayList<TestGroup>();
	private Report report;

	public Content(Report report)  {
		this.report=report;
	}

	public void addMethodGroup(int size, String method, double timing)  {
		add(report.methodGroup(size, method, timing));
	}

	public void addSizeGroup(int size, String method, double timing)  {
		add(report.sizeGroup(size, method, timing));
	}

	private void add(TestGroup group) {
		for(int i=0; i < groups.size(); i++)
			groups.set(i, sum(groups.get(i), group));
		if(!groups.contains(group))
			groups.add(group);
	}

	private TestGroup sum(TestGroup addend, TestGroup augend) {
		return addend.add(augend)==null  ?  addend  :  addend.add(augend);
	}

	public void print() {
		report.print(groups.toArray(new TestGroup[groups.size()]));
	}
}
