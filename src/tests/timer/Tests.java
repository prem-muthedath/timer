package timer;

public class Tests {
	public static void main(String[] args) throws Exception {
		Tests tests=new Tests();
		tests.testList();
		tests.testSetVsArray();
	}

	public void testList() throws Exception {
		MethodsTimer tester= new MethodsTimer(ListSearch.class.getDeclaredMethods());
		tester.report();		
	}

	public void testSetVsArray() throws Exception {
		MethodsTimer tester= new MethodsTimer(SetVsArrayList.class.getDeclaredMethods());
		tester.report();		
	}
}
