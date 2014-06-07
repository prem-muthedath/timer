package timer.reporting.ordering;

import timer.framework.Report;

import timer.reporting.base.View;

import java.util.Comparator;

public enum Order {
	BY_SIZE, BY_METHOD;

	public Report report()  {
		switch(this)  {
			case BY_SIZE: return new OrderedReport(sizeComparator()) {
				void export(TestInstance testInstance, Timing timing, View view) {
					testInstance.exportBySize(timing, view);
				}
			};
			case BY_METHOD: return new OrderedReport(methodComparator()) {
				void export(TestInstance testInstance, Timing timing, View view) {
					testInstance.exportByMethod(timing, view);
				}
			};					
		}
		throw new RuntimeException("Unknown Order: "+this);
	}

	private Comparator<TestInstance> sizeComparator() {
		return TestInstance.sizeComparator();
	}

	private Comparator<TestInstance> methodComparator() {
		return TestInstance.methodComparator();
	}
}