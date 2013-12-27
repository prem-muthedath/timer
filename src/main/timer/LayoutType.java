package timer;

public enum LayoutType {
	BY_METHOD, BY_SIZE;

	public Layout instance()  {
		switch(this)  {
			case BY_METHOD:	return new Layout() {
				public void addTest(int size, String method, double timing)  {	
					add(Test.methodOrderedTest(size, method, timing));
				}
			};		
			case BY_SIZE: return new Layout() {
				public void addTest(int size, String method, double timing)  {	
					add(Test.sizeOrderedTest(size, method, timing));			
				}
			};		
		}
		throw new RuntimeException("Unknown LayoutType: "+this);
	}
}