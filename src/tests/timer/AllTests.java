package timer;

import timer.framework.CollectionTimer;
import timer.framework.Report;

import timer.reporting.base.ReportType;
import timer.reporting.base.TestResultType;

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
		return ReportType.BY_METHOD.instance(TestResultType.TEXT);
	}
}
