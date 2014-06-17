package timer.results.types;

import timer.results.base.OrderedResults;
import timer.results.base.TestInstance;
import timer.results.base.Timing;

import timer.reporting.base.Format;

public class SizeOrderedResults extends OrderedResults {
	public SizeOrderedResults() {
		super(TestInstance.sizeOrder());			
	}

	protected void export(TestInstance testInstance, Timing timing, Format format) {
		testInstance.exportBySize(timing, format);
	}
}