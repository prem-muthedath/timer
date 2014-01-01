package timer.reporting.base;

import timer.framework.Report;

import java.util.Collections;

public enum ReportType {
	BY_SIZE, BY_METHOD;

	public Report instance(TestResultType format)  {
		switch(this)  {
			case BY_SIZE: return new TestResultsReport(format) {
				protected void sort()  {	
					Collections.sort(results);
				}
				protected void export(TestResult result, Contents contents) {
					result.exportBySize(contents);
				}
			};
			case BY_METHOD: return new TestResultsReport(format) {
				protected void sort()  {	
					Collections.sort(results, results.isEmpty()  ?   null  :  results.get(0));
				}
				protected void export(TestResult result, Contents contents) {
					result.exportByMethod(contents);
				}
			};					
		}
		throw new RuntimeException("Unknown ReportType: "+this);
	}
}