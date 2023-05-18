package timer;

import timer.framework.TimingTests;

import timer.reporting.OrderedResults;
import timer.reporting.SizeOrderedResults;
import timer.reporting.MethodOrderedResults;

import timer.reporting.Report;
import timer.reporting.TextReport;
import timer.reporting.XmlReport;
import timer.reporting.SwingReport;

/* java 1.7 API reference
 * https://docs.oracle.com/en/java/javase/17/docs/api/
 *
 * author: Prem Muthedath
 */
public class Timer {
  public enum Order {BY_SIZE, BY_METHOD}
  public enum Format {TEXT, XML, JAVA_SWING}

  public void report(Class<? extends TimingTests> tests,
      Timer.Order order, Timer.Format format) throws Exception
  {
    OrderedResults results = this.results(order);
    new timer.framework.CollectionTimer(tests).run(results);
    Report report = report(format, results);
    report.view();
  }

  /* You can order the output by collection sizes or by method names.
   * To set the order of your output, USE THE FOLLOWING VALUES FOR 
   * Timer.Order PARAMETER IN Timer.report():
   *
   * To order by SIZE, use Timer.Order.BY_SIZE
   * To order by METHOD, use Timer.Order.BY_METHOD
   */
  private OrderedResults results(Timer.Order order)  {
    switch(order)  {
      case BY_SIZE: return new SizeOrderedResults();
      case BY_METHOD: return new MethodOrderedResults();
    }
    throw new RuntimeException("Unknown Order: " + order);
  }

  /* To select your report format, USE THE FOLLOWING VALUES FOR 
   * Timer.Format PARAMETER IN Timer.report():
   *
   * For Text report (tabular format printed to console), use 
   * Timer.Format.TEXT
   *
   * For XML report (printed to console), use Timer.Format.XML
   *
   * For Java Swing report, use Timer.Format.JAVA_SWING
   */
  private Report report(Timer.Format format, OrderedResults results) {
    switch(format) {
      case TEXT: return new TextReport(results);
      case XML: return new XmlReport(results);
      case JAVA_SWING: return new SwingReport(results);
    }
    throw new RuntimeException("Unknown Format: " + format);
  }
}
