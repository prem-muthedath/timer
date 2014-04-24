package timer.output.base;

import timer.framework.Report;
import timer.framework.TestResult;

import java.util.Collections;

public enum Order {
	BY_SIZE, BY_METHOD;

	public Report report()  {
		switch(this)  {
			case BY_SIZE: return new Report() {
				protected void sort()  {	
					Collections.sort(results);
				}
				protected void export(TestResult result, Format format) {
					result.exportBySize(format);
				}
			};
			case BY_METHOD: return new Report() {
				protected void sort()  {	
					Collections.sort(results, results.isEmpty()  ?   null  :  results.get(0));
				}
				protected void export(TestResult result, Format format) {
					result.exportByMethod(format);
				}
			};					
		}
		throw new RuntimeException("Unknown Order: "+this);
	}
}