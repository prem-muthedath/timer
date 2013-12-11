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

	public void printHeader(Formatable size)  {
		Format.headerFormat().print(items(size));
	}

	public void printValues(Formatable size) {
		Format.dataFormat().print(items(size));
	}

	private Formatable[] items(Formatable size) {
		List<Formatable> items=new ArrayList<Formatable>(Arrays.asList(size));
		items.addAll(Arrays.asList(timings()));
		return items.toArray(new Formatable[methodsTime.size()+1]);
	}

	private Formatable[] timings() {
		MethodTime[] timings=methodsTime.toArray(new MethodTime[methodsTime.size()]);
		Arrays.sort(timings);
		return timings;		
	}
}	