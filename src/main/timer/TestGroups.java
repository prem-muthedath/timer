package timer;

import java.util.Map;
import java.util.LinkedHashMap;

public class TestGroups  {
	private Map<String, StringBuffer> groups=new LinkedHashMap<String, StringBuffer>();

	public void add(String group, String timing)  {
		get(group).append(timing);
	}

	public void print(Report report)  {
		int i=0;
		String[] data=new String[groups.size()];		
		for (String each : groups.keySet())
			data[i++]=each+groups.get(each).toString();
		report.print(data);
	}

	private StringBuffer get(String group)  {
		if(!groups.containsKey(group))
			groups.put(group, new StringBuffer());
		return groups.get(group);
	}
}
