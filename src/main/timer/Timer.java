package timer;

import timer.framework.TimingTests;

import timer.reporting.OrderedResults;
import timer.reporting.SizeOrderedResults;
import timer.reporting.MethodOrderedResults;

import timer.reporting.Report;
import timer.reporting.TextReport;
import timer.reporting.XmlReport;
import timer.reporting.SwingReport;

/** the `Timer` class is the client-facing interface. It coordinates with other 
 *  classes to generate method timings for a set of timing tests over a range of 
 *  collection sizes and report those results in multiple formats and orders.
 *
 *  reported results are either collection-size ordered or method-name ordered, 
 *  and printed in text, xml, or java swing formats. text and xml reports are 
 *  printed to console; java swing report is shown in a java swing frame view.
 *
 *  java 1.7 API reference
 *  https://docs.oracle.com/en/java/javase/17/docs/api/
 *
 *  author: Prem Muthedath
 */
public class Timer {
  private enum Order {BY_SIZE, BY_METHOD}     /* options for sorting results */
  private enum Format {TEXT, XML, JAVA_SWING} /* available report formats */

  /* report method timings for a set of collection timing tests encapsulated in 
   * a class, with results sorted and formatted using the default options.
   *
   * default sorting order is by method name, and default report format is text.
   *
   * report is printed to console.
   */
  public void report(Class<? extends TimingTests> tests) throws Exception {
    this.methodOrderedTextReport(tests);
  }

  /* print to console method timings, sorted by collection size and in plain 
   * text format, for a set of timing tests encapsulated in a class.
   */
  public void sizeOrderedTextReport(Class<? extends TimingTests> tests) throws Exception {
    this.report(tests, Timer.Order.BY_SIZE, Timer.Format.TEXT);
  }

  /* print to console method timings, sorted by collection size and in xml 
   * format, for a set of timing tests encapsulated in a class.
   */
  public void sizeOrderedXmlReport(Class<? extends TimingTests> tests) throws Exception {
    this.report(tests, Timer.Order.BY_SIZE, Timer.Format.XML);
  }

  /* display in java swing frame view method timings, sorted by collection size 
   * and in swing format, for a set of timing tests encapsulated in a class.
   */
  public void sizeOrderedSwingReport(Class<? extends TimingTests> tests) throws Exception {
    this.report(tests, Timer.Order.BY_SIZE, Timer.Format.JAVA_SWING);
  }

  /* print to console method timings, sorted by method name and in plain text 
   * format, for a set of timing tests encapsulated in a class.
   */
  public void methodOrderedTextReport(Class<? extends TimingTests> tests) throws Exception {
    this.report(tests, Timer.Order.BY_METHOD, Timer.Format.TEXT);
  }

  /* print to console method timings, sorted by method name and in xml format, 
   * for a set of timing tests encapsulated in a class.
   */
  public void methodOrderedXmlReport(Class<? extends TimingTests> tests) throws Exception {
    this.report(tests, Timer.Order.BY_METHOD, Timer.Format.XML);
  }

  /* display in java swing frame view method timings, sorted by method name and 
   * in swing format, for a set of timing tests encapsulated in a class.
   */
  public void methodOrderedSwingReport(Class<? extends TimingTests> tests) throws Exception {
    this.report(tests, Timer.Order.BY_METHOD, Timer.Format.JAVA_SWING);
  }

  /* report method timings, sorted by the specified order and in the specified 
   * format, for a set of timing tests encapsulated in a class.
   */
  private void report(Class<? extends TimingTests> tests,
      Timer.Order order, Timer.Format format) throws Exception
  {
    OrderedResults results = this.results(order);
    new timer.framework.CollectionTimer(tests).run(results);
    Report report = this.report(format, results);
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
