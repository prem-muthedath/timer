package timer;

import timer.framework.CollectionTimer;
import timer.framework.Report;

import timer.reporting.types.Order;
import timer.reporting.types.Format;

public class AllTests {
	public static void main(String[] args) throws Exception {
		AllTests tests=new AllTests();
		tests.testListSearch();
		tests.testSetVsArray();
	}

	public void testListSearch() throws Exception {
		CollectionTimer timer= new CollectionTimer(new ListSearch(0));
		timer.report(report());		
	}

	public void testSetVsArray() throws Exception {
		CollectionTimer timer= new CollectionTimer(new SetVsArrayList(0));
		timer.report(report());	
	}

	private Report report() {
		return Order.BY_METHOD.report(Format.TEXT);
	}
}
