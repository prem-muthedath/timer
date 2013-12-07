package timer;

import java.util.Map;
import java.util.TreeMap;
import java.lang.reflect.Method;

public class Timings  {
	private Map<String, Double> methodsTime=new TreeMap<String, Double>();

	public void run(Method method, TimingTests tests) throws Exception {
		if(skip(method)) return;
		add(method, tests.time(method));
	} 

	private boolean skip(Method method) throws Exception {
		Class itsClass=method.getDeclaringClass();
		return (itsClass.equals(Object.class) || itsClass.equals(TimingTests.class));
	}

	private void add(Method method, double time)  {
		methodsTime.put(method.getName(), new Double(time));
	}

	public void print(int size)  {
		System.out.print(size+"\t");
		for (String each : methodsTime.keySet())
			System.out.print(String.format("%.2f\t", methodsTime.get(each)));
	}
}
