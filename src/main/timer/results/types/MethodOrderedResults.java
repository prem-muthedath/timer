package timer.results.types;

import timer.results.base.OrderedResults;
import timer.results.base.TestInstance;
import timer.results.base.Timing;

import timer.reporting.base.Format;

public class MethodOrderedResults extends OrderedResults {
	public MethodOrderedResults() {
		super(TestInstance.methodOrder());			
	}

	protected void export(TestInstance testInstance, Timing timing, Format format) {
		testInstance.exportByMethod(timing, format);
	}
}