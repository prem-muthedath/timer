package timer.output.base;

import timer.framework.Report;
import timer.framework.TestTiming;

import java.util.Collections;

public enum Order {
	BY_SIZE, BY_METHOD;

	public Report report()  {
		switch(this)  {
			case BY_SIZE: return new Report() {
				protected void sort()  {	
					Collections.sort(timings);
				}
				protected void export(TestTiming timing, Format format) {
					timing.exportBySize(format);
				}
			};
			case BY_METHOD: return new Report() {
				protected void sort()  {	
					Collections.sort(timings, timings.isEmpty()  ?   null  :  timings.get(0));
				}
				protected void export(TestTiming timing, Format format) {
					timing.exportByMethod(format);
				}
			};					
		}
		throw new RuntimeException("Unknown Order: "+this);
	}
}