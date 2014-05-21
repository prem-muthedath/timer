package timer.framework;

import java.lang.reflect.Method;

import java.util.Comparator;

import timer.output.base.Format;

public class TestInstance implements Comparable<TestInstance>, Comparator<TestInstance> {
	private Size size;
	private Name name;

	public TestInstance(Method test, TimingTests instance) {
		this.name=new Name(test.getName());
		this.size=new Size(instance.size());		
	}

	public void exportBySize(Timing timing, Format format) {
		format.add(new Field[]{name, size, timing});
	}

	public void exportByMethod(Timing timing, Format format) {
		format.add(new Field[]{size, name, timing});
	}

	public int compareTo(TestInstance another)  {
		return size.compareTo(another.size)==0  ?  name.compareTo(another.name)  :  size.compareTo(another.size);
	}

	public int compare(TestInstance one, TestInstance another)  {
		return one.name.compareTo(another.name)==0  ?  one.size.compareTo(another.size)  :  one.name.compareTo(another.name);				
	}	
}
