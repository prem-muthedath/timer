package timer.framework;

import timer.output.base.View;

import java.util.Map;
import java.util.TreeMap;
import java.util.Comparator;

public enum Order {
	BY_SIZE, BY_METHOD;

	public Report report()  {
		switch(this)  {
			case BY_SIZE: return new Report() {
				protected Map<TestInstance, Timing> sort()  {	
					return new TreeMap<TestInstance, Timing>(timings);
				}
				protected void export(TestInstance testInstance, Timing timing, View view) {
					testInstance.exportBySize(timing, view);
				}
			};
			case BY_METHOD: return new Report() {
				protected Map<TestInstance, Timing> sort()  {	
					Map<TestInstance, Timing> sorted=new TreeMap<TestInstance, Timing>(comparator());
					sorted.putAll(timings);
					return sorted;
				}
				private TestInstance comparator() {
					return timings.isEmpty()  ?   null  :  timings.keySet().toArray(new TestInstance[0])[0];
				}
				protected void export(TestInstance testInstance, Timing timing, View view) {
					testInstance.exportByMethod(timing, view);
				}
			};					
		}
		throw new RuntimeException("Unknown Order: "+this);
	}
}