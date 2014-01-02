package timer.reporting.types;

import timer.reporting.base.TestResult;

public enum Format {
	TEXT, XML;

	public TestResult testResult(int size, String method, double timing)  {
		switch(this)  {
			case TEXT: return new TextTestResult(size, method, timing);
			case XML: return null;
		}
		throw new RuntimeException("Unknown Format: "+this);
	}	
}
