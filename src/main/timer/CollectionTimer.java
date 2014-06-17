package timer;

import timer.framework.TimingTests;
import timer.framework.Results;

import timer.results.types.SizeOrderedResults;
import timer.results.types.MethodOrderedResults;

import timer.reporting.composition.types.TextReport;
import timer.reporting.composition.types.XmlReport;

import timer.reporting.rendering.PlainView;
import timer.reporting.rendering.SwingView;

public class CollectionTimer {
	public enum Order {BY_SIZE, BY_METHOD}
	public enum Format {TEXT, XML, JAVA_SWING}

	public void report(Class<? extends TimingTests> tests, 
		CollectionTimer.Order order, CollectionTimer.Format format) throws Exception  
	{
		Results results=results(order);
		new timer.framework.CollectionTimer(tests).run(results);
		render(results, format);
	}

	private Results results(CollectionTimer.Order order)  {
		switch(order)  {
			case BY_SIZE: return new SizeOrderedResults();
			case BY_METHOD: return new MethodOrderedResults();
		}
		throw new RuntimeException("Unknown Order: "+order);
	}

	private void render(Results results, CollectionTimer.Format format) {
		switch(format)  {
			case TEXT: results.render(new PlainView(), new TextReport());
				return;				
			case XML: results.render(new PlainView(), new XmlReport());
				return;
			case JAVA_SWING: results.render(new SwingView(), new TextReport());
				return;
		}
		throw new RuntimeException("Unknown Format: "+format);
	}
}