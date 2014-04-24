package timer;

import timer.framework.CollectionTimer;
import timer.framework.Report;

import timer.output.base.Order;
import timer.output.types.TextFormat;

public class AllTests {
	private Report report;

	public static void main(String[] args) throws Exception {
		AllTests tests=new AllTests();
		tests.runListSearchTest();
		tests.runSetVsArrayListTest();
	}

	public void runListSearchTest() throws Exception {
		CollectionTimer timer= new CollectionTimer(ListSearch.class);
		timer.report(report());		
		report.print(new TextFormat());
	}

	public void runSetVsArrayListTest() throws Exception {
		CollectionTimer timer= new CollectionTimer(SetVsArrayList.class);
		timer.report(report());	
		report.print(new TextFormat());		
	}

	private Report report() {
		this.report=Order.BY_METHOD.report();
		return this.report;
	}
}
