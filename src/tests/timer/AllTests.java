package timer;

import timer.framework.CollectionTimer;
import timer.framework.LayoutType;

import timer.reports.TextReport;

public class AllTests {
	public static void main(String[] args) throws Exception {
		AllTests tests=new AllTests();
		tests.testListSearch();
		tests.testSetVsArray();
	}

	public void testListSearch() throws Exception {
		CollectionTimer timer= new CollectionTimer(new ListSearch(1));
		timer.report(new TextReport(LayoutType.BY_METHOD.instance()));		
	}

	public void testSetVsArray() throws Exception {
		CollectionTimer timer= new CollectionTimer(new SetVsArrayList(1));
		timer.report(new TextReport(LayoutType.BY_METHOD.instance()));		
	}
}
