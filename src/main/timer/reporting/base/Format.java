package timer.reporting.base;

import timer.framework.Timings;
import timer.framework.TestTiming;

import timer.reporting.TextTestResult;

public enum Format {
	TEXT, XML;

	public void print(Report report, Timings timings) {
		TestTiming[] testTimings=timings.export();
		report.print(format(testTimings));
	}

	private TestResult[] format(TestTiming[] testTimings) {
		int i=0;
		TestResult[] results=new TestResult[testTimings.length];
		for (TestTiming each : testTimings)  {
			results[i]=instance();
			each.export(results[i++]);
		}	
		return results;
	}

	public TestResult instance()  {
		switch(this)  {
			case TEXT: return new TextTestResult();
			case XML: return null;
		}
		throw new RuntimeException("Unknown Format: "+this);
	}	
}
