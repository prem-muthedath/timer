package timer;

import java.util.Map;
import java.util.TreeMap;
import java.util.List;
import java.util.ArrayList;

public class Timings  {
	private TreeMap<SortKey, String> timings=new TreeMap<SortKey, String>();
	private Report report;

	public Timings(Report report) {
		this.report=report;
	}

	public void add(SortKey key, String timing)  {
		timings.put(key, timing);
	}

	public void print()  {
		List<StringBuffer> rows=new ArrayList<StringBuffer>();
		StringBuffer timingGroup=new StringBuffer();		
		SortKey previous=null;
		for (SortKey each : timings.keySet()) {
			String rowPrefix=each.value(report, previous);
			timingGroup=timingGroup(rowPrefix, timingGroup).append(timings.get(each));
			if(!rows.contains(timingGroup)) 
				rows.add(timingGroup);			
			previous=each;
		}
		report.print(header(), rows.toArray(new StringBuffer[rows.size()]));
	}

	private StringBuffer timingGroup(String rowPrefix, StringBuffer timingGroup)  {
		if(rowPrefix.isEmpty()) return timingGroup;
		return new StringBuffer(rowPrefix);
	}

	private String header()  {
		StringBuffer header=new StringBuffer();
		for (SortKey each : timings.keySet())
			each.header(report, header);
		return header.toString();
	}
}


/*  BELOW, PREVIOUS COMPLICATED VERSION OF print()  -- I LISTED IT HERE SO THAT WE CAN SEE HOW WE REFACTORED THIS METHOD IN THE ABOVE CLASS.
	THIS SIMPLIFICATION HAPPENED WHEN WE INTRODUCED timingGroup, A StringBuffer(), IN THE ABOVE CLASS.

	public void print(String firstHeader)  {
		List<String> data=new ArrayList<String>();	
		SortKey[] keys=timings.keySet().toArray(new SortKey[timings.size()]);
		for (int i=1; i < keys.length; i++) {
			if(keys[i].value(report, keys[i-1]).isEmpty())
				data.set(data.size()-1, data.get(data.size()-1)+timings.get(each));
			else
				data.add(each.value(report, previous)+timings.get(each));
			previous=each;
		}
		report.print(data.toArray(new String[data.size()]));
	}
*/