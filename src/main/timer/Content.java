package timer;

import java.util.List;
import java.util.ArrayList;

public class Content  {
	private List<TestGroup> groups=new ArrayList<TestGroup>();
	private StringBuffer headers=new StringBuffer();

	public void add(String headerMargin, String header, TestGroup group)  {
		add(headerMargin, header);
		add(group);
	}

	private void add(String headerMargin, String header)  {
		if(headers.length()==0)
			headers.append(headerMargin);
		if(headers.indexOf(header) < 0) 
			headers.append(header);
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

	public void print()  {
		System.out.println(headers);
		for (TestGroup each : groups)
			System.out.println(each);
	}
}
