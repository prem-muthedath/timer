package timer;

import timer.framework.CollectionTimer;
import timer.framework.Report;

import timer.reporting.types.Order;
import timer.reporting.types.Format;

public class AllTests {
	public static void main(String[] args) throws Exception {
		AllTests tests=new AllTests();
		tests.runListSearchTest();
		tests.runSetVsArrayListTest();
	}

	public void runListSearchTest() throws Exception {
		CollectionTimer timer= new CollectionTimer(ListSearch.class);
		timer.report(report());		
	}

	public void runSetVsArrayListTest() throws Exception {
		CollectionTimer timer= new CollectionTimer(SetVsArrayList.class);
		timer.report(report());	
	}

	private Report report() {
		return Order.BY_METHOD.report(Format.TEXT);
	}
}
