package timer;

import timer.framework.CollectionTimer;
import timer.framework.Timings;
import timer.framework.ReportType;
import timer.framework.Format;

public class AllTests {
	public static void main(String[] args) throws Exception {
		AllTests tests=new AllTests();
		tests.testListSearch();
		tests.testSetVsArray();
	}

	public void testListSearch() throws Exception {
		Timings timings=new Timings();
		CollectionTimer timer= new CollectionTimer(new ListSearch(1));
		timer.report(timings);		
		ReportType.BY_SIZE.instance().print(Format.TEXT, timings);
	}

	public void testSetVsArray() throws Exception {
		Timings timings=new Timings();		
		CollectionTimer timer= new CollectionTimer(new SetVsArrayList(1));
		timer.report(timings);	
		ReportType.BY_SIZE.instance().print(Format.TEXT, timings);			
	}
}
