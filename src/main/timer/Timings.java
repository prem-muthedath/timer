package timer;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.lang.reflect.Method;

public class Timings  {
	private List<MethodTime> methodsTime=new ArrayList<MethodTime>();

	public void run(TimingTest test) throws Exception {
		add(test.time());
	} 

	private void add(MethodTime methodTime)  {
		methodsTime.add(methodTime);
	}

	public void export(Format format)  {
		for (MethodTime each : methodsTime)
			each.export(format);
	}
}	