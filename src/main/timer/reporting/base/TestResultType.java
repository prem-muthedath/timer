package timer.reporting.base;

import timer.reporting.TextTestResult;

public enum TestResultType {
	TEXT, XML;

	public TestResult instance(int size, String method, double timing)  {
		switch(this)  {
			case TEXT: return new TextTestResult(size, method, timing);
			case XML: return null;
		}
		throw new RuntimeException("Unknown TestResultType: "+this);
	}	
}
