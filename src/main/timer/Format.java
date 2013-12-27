package timer;

public enum Format {
	TEXT, XML;

	public Test test(int size, String method, double timing)  {
		switch(this)  {
			case TEXT:	return new TextTest(size, method, timing);
			case XML:	return null;
		}
		throw new RuntimeException("Unknown format: "+this);
	}
}
