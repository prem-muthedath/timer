package timer;

import java.util.Map;
import java.util.TreeMap;
import java.util.Collection;

class Format {
	private Map<String, String>timings=new TreeMap<String, String>();

	public void print(int size, Timings timings) {
		timings.export(this);
		if(size==CollectionTimer.MINIMUM_SIZE) printHeader();
		printValues(size);
	}

	public void add(String methodName, double time) {
		timings.put(String.format("%-25s\t", methodName), String.format("%-25.2f\t", time));
	}

	private void printHeader()  {
		print(String.format("%-10s\t", "size"), timings.keySet());
	}

	private void printValues(int size)  {
		print(String.format("%-10d\t", size), timings.values());
	}	

	private void print(String size, Collection<String> data) {
		System.out.print(size);
		for (String each : data) 
			System.out.print(each);
		System.out.println();
	}
}
