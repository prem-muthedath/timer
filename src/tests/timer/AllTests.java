package timer;

public class AllTests {
	public static void main(String[] args) throws Exception {
		AllTests tests=new AllTests();
		tests.testListSearch();
		tests.testSetVsArray();
	}

	public void testListSearch() throws Exception {
		CollectionPerformance timer= new CollectionPerformance(new ListSearch(1));
		timer.report();		
	}

	public void testSetVsArray() throws Exception {
		CollectionPerformance timer= new CollectionPerformance(new SetVsArrayList(1));
		timer.report();		
	}
}
