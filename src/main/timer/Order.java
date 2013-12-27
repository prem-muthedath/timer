package timer;

import java.util.Collections;

public enum Order {
	BY_METHOD, BY_SIZE;
	
	public Report report(Format format)  {
		switch(this)  {
			case BY_METHOD:	return new Report(format) {
				protected void sort()  {
					Collections.sort(tests);
				}
				protected void export(Test test, Content content)  {
					test.export(content);
				}
			};	
			case BY_SIZE: return new Report(format) {
				protected void sort()  {
					Collections.sort(tests, Test.sizeComparator());
				}
				protected void export(Test test, Content content)  {
					test.exportBySize(content);
				}
			};	
		}
		throw new RuntimeException("Unknown order: "+this);
	}
}
