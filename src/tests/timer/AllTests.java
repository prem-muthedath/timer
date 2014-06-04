package timer;

import timer.framework.CollectionTimer;
import timer.framework.Report;

import timer.reporting.ordering.Order;

import timer.reporting.composition.TextDocument;
import timer.reporting.composition.XmlDocument;

import timer.reporting.rendering.PlainView;
import timer.reporting.rendering.SwingView;

public class AllTests {
	private Report report;

/*  NOTE: To compare with Beck's results, run the tests in the SAME WAY AND ORDER as you run Beck's tests.
	That is, for Beck's code, if you are running both ListSerach test and SetVsArrayList test TOGETHER, 
	one after the other, then run these tests in the SAME WAY and in the SAME ORDER here AS WELL 
	(as shown in the main method below).

	The reason for this advice is this -- for Beck's code AS WELL AS for this code, I noticed that the results 
	are slightly different if we run the ListSearch test first, followed by SetVsArrayList, one after the other, 
	in the SAME JVM, VERSUS if we run SetVsArrayList test WITHOUT running the ListSearch first.  Specifically,
	we get slightly different timings for sizes 100 and 1000 for the arrayListMembership test. For size 100, 
	we notice 160-170 ms vs 200-210 ms, and for size 1000, we notice 1600-1800 vs 2000-2100 ms.  The lower 
	values are reported when we run ListSearch first, followed immediately by SetVsArrayList in the same JVM,
	as shown in the main method below.  The differences are slight BUT CONSISTENT.
*/ 

	public static void main(String[] args) throws Exception {
		AllTests tests=new AllTests();
	
	/*  Commented out one test execution, because, as explained above, if we run these two tests, 
		one after the other, in the SAME JVM, we notice slightly different results.  
		
		So don't run these two tests together. Run them seperately, in standalone mode, 
		by commenting one of them out, as done below.

		tests.runListSearchTest();
	*/	
		tests.runSetVsArrayListTest();
	}

	public void runListSearchTest() throws Exception {
		CollectionTimer timer= new CollectionTimer(ListSearch.class);
		timer.report(report());		
		report.render(new PlainView(new TextDocument()));
	}

	public void runSetVsArrayListTest() throws Exception {
		CollectionTimer timer= new CollectionTimer(SetVsArrayList.class);
		timer.report(report());	

		/** To select your view and format options, OVERWRITE report.render() code BELOW with copy-paste of ONE of the following:
		**
		** For Plain View and Text Format, copy-paste --> report.render(new PlainView(new TextDocument())); 
		** For Plain View and XML Format, copy-paste --> report.render(new PlainView(new XmlDocument()));
		** For Swing View, copy-paste --> report.render(new SwingView());  
		**  NOTE: SwingView restricts itself to TextDocument by default. 
		*/

		report.render(new PlainView(new TextDocument()));
	}

	private Report report() {
		/** You can order the output by collection sizes or by method names.
		**  To set the order of your output, OVERWRITE THE SINGLE LINE OF CODE THAT DIRECTLY FOLLOWS THESE COMMENTS with 
		**  copy-paste of one of the following: 
		** To order by SIZE, copy-paste --> this.report=Order.BY_SIZE.report();
		** To order by METHOD, copy-paste --> this.report=Order.BY_METHOD.report();
		*/

		this.report=Order.BY_METHOD.report();
		return this.report;
	}
}
