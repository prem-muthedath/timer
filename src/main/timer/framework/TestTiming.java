package timer.framework;

import java.util.Comparator;

import timer.output.base.Format;

public class TestTiming implements Comparable<TestTiming>, Comparator<TestTiming> {
	private Size size;
	private Name name;
	private Timing timing;

	public TestTiming(Size size, Name name, Timing timing) {
		this.size=size;
		this.name=name;
		this.timing=timing;
	}

	public void exportBySize(Format format) {
		format.add(new Field[]{name, size, timing});
	}

	public void exportByMethod(Format format) {
		format.add(new Field[]{size, name, timing});
	}

	public int compareTo(TestTiming another)  {
		return size.compareTo(another.size)==0  ?  name.compareTo(another.name)  :  size.compareTo(another.size);
	}

	public int compare(TestTiming one, TestTiming another)  {
		return one.name.compareTo(another.name)==0  ?  one.size.compareTo(another.size)  :  one.name.compareTo(another.name);				
	}	
}
