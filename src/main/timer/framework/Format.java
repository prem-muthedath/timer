package timer.framework;

import timer.reports.TextResult;

public enum Format {
	TEXT, XML;

	public void print(Report report, Timings timings) {
		TestResult[] results=timings.export();
		report.print(format(results));
	}

	private TestResultExport[] format(TestResult[] results) {
		int i=0;
		TestResultExport[] exports=new TestResultExport[results.length];
		for (TestResult each : results)  {
			exports[i]=instance();
			each.export(exports[i++]);
		}	
		return exports;
	}

	public TestResultExport instance()  {
		switch(this)  {
			case TEXT: return new TextResult();
			case XML: return null;
		}
		throw new RuntimeException("Unknown Format: "+this);
	}	
}
