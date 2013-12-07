package timer;

import java.util.Map;
import java.util.TreeMap;
import java.lang.reflect.Method;

public class Timings  {
	private Map<String, String> methodsTime=new TreeMap<String, String>();

	public void run(Method method, TimingTests tests) throws Exception {
		if(skip(method)) return;
		add(method, tests.time(method));
	} 

	private boolean skip(Method method) throws Exception {
		Class itsClass=method.getDeclaringClass();
		return (itsClass.equals(Object.class) || itsClass.equals(TimingTests.class));
	}

	private void add(Method method, double time)  {
		methodsTime.put(method.getName(), String.valueOf(time));
	}

	public void print(int size)  {
		if(size==1) printHeader();
		printValues(size);
	}

	private void printHeader()  {
		Format.textFormat().print("size", methodsTime.keySet());
	}

	private void printValues(int size)  {
		Format.numberFormat().print(String.valueOf(size), methodsTime.values());
	}
}	