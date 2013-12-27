package timer;

public enum Order {
	BY_METHOD, BY_SIZE;

	public Test[] tests(int size, Timing[] timings)  {
		int i=0;
		Test[] tests=new Test[timings.length];
		for (Timing each : timings)
			tests[i++]=each.export(size, this);
		return tests;
	}
	
	public Test test(int size, String method, double timing)  {
		switch(this)  {
			case BY_METHOD:	
				return Test.methodOrderedInstance(size, method, timing);
			case BY_SIZE: 
				return Test.sizeOrderedInstance(size, method, timing);			
		}
		throw new RuntimeException("Unknown order: "+this);
	}
}
