package timer.reporting.ordering;

import timer.framework.Report;

import timer.reporting.base.View;

import java.util.Map;

public enum Order {
	BY_SIZE, BY_METHOD;

	public Report report()  {
		switch(this)  {
			case BY_SIZE: return new OrderedReport() {
				Map<TestInstance, Timing> sort()  {	
					return sizeSort();
				}
				void export(TestInstance testInstance, Timing timing, View view) {
					testInstance.exportBySize(timing, view);
				}
			};
			case BY_METHOD: return new OrderedReport() {
				Map<TestInstance, Timing> sort()  {	
					return methodSort();
				}
				void export(TestInstance testInstance, Timing timing, View view) {
					testInstance.exportByMethod(timing, view);
				}
			};					
		}
		throw new RuntimeException("Unknown Order: "+this);
	}
}