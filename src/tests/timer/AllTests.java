package timer;

import timer.framework.CollectionTimer;
import timer.framework.Report;

import timer.output.base.Order;
import timer.output.base.NodeFactory;

import timer.output.types.TextFormat;
import timer.output.types.XmlFormat;
import timer.output.types.SwingFormat;

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
		report.print(new TextFormat(new NodeFactory()));
	}

	public void runSetVsArrayListTest() throws Exception {
		CollectionTimer timer= new CollectionTimer(SetVsArrayList.class);
		timer.report(report());	
		report.print(new TextFormat(new NodeFactory()));		
	}

	private Report report() {
		this.report=Order.BY_SIZE.report();
		return this.report;
	}
}
