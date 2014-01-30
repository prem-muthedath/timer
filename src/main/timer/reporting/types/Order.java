package timer.reporting.types;

import timer.framework.Report;

import timer.reporting.base.OrderedReport;
import timer.reporting.base.TestResult;
import timer.reporting.base.Layout;

import java.util.Collections;

public enum Order {
	BY_SIZE, BY_METHOD;

	public Report report(Format format)  {
		switch(this)  {
			case BY_SIZE: return new OrderedReport(format) {
				protected void sort()  {	
					Collections.sort(results);
				}
				protected void export(TestResult result, Layout layout) {
					result.exportBySize(layout);
				}
			};
			case BY_METHOD: return new OrderedReport(format) {
				protected void sort()  {	
					Collections.sort(results, results.isEmpty()  ?   null  :  results.get(0));
				}
				protected void export(TestResult result, Layout layout) {
					result.exportByMethod(layout);
				}
			};					
		}
		throw new RuntimeException("Unknown Order: "+this);
	}
}