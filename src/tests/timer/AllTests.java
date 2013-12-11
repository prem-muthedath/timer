package timer;

public class AllTests {
	public static void main(String[] args) throws Exception {
		AllTests tests=new AllTests();
		tests.testListSearch();
		tests.testSetVsArray();
	}

	public void testListSearch() throws Exception {
		CollectionTimer timer= new CollectionTimer(new ListSearch(1));
		timer.report(new Format());		
	}

	public void testSetVsArray() throws Exception {
		CollectionTimer timer= new CollectionTimer(new SetVsArrayList(1));
		timer.report(new Format());		
	}
}
