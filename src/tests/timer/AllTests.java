package timer;

public class AllTests {
	public static void main(String[] args) throws Exception {
		AllTests tests=new AllTests();
		tests.testList();
		tests.testSetVsArray();
	}

	public void testList() throws Exception {
		MethodsTimer timer= new MethodsTimer(ListSearch.class.getDeclaredMethods());
		timer.report();		
	}

	public void testSetVsArray() throws Exception {
		MethodsTimer timer= new MethodsTimer(SetVsArrayList.class.getDeclaredMethods());
		timer.report();		
	}
}
