package timer.framework;

import java.util.Arrays;

public enum ReportType {
	BY_METHOD, BY_SIZE;

	public Report instance()  {
		switch(this)  {
			case BY_METHOD: return new Report() {
				protected void sort(TestResultExport[] exports)  {	
					Arrays.sort(exports, exports[0]);
				}
				protected void export(TestResultExport export, Title title) {
					export.exportByMethod(this, title);
				}
			};		
			case BY_SIZE: return new Report() {
				protected void sort(TestResultExport[] exports)  {	
					Arrays.sort(exports);
				}
				protected void export(TestResultExport export, Title title) {
					export.exportBySize(this, title);
				}
			};		
		}
		throw new RuntimeException("Unknown ReportType: "+this);
	}
}