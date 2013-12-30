package timer.reporting.base;

import java.util.Arrays;

public enum ReportType {
	BY_METHOD, BY_SIZE;

	public Report instance()  {
		switch(this)  {
			case BY_METHOD: return new Report() {
				protected void sort(TestResult[] results)  {	
					Arrays.sort(results, results[0]);
				}
				protected void export(TestResult result, Title title) {
					result.exportByMethod(this, title);
				}
			};		
			case BY_SIZE: return new Report() {
				protected void sort(TestResult[] results)  {	
					Arrays.sort(results);
				}
				protected void export(TestResult result, Title title) {
					result.exportBySize(this, title);
				}
			};		
		}
		throw new RuntimeException("Unknown ReportType: "+this);
	}
}