package timer;

import timer.Timer;

/** This test class acts aa a client of the timer framework, requesting the 
 *  framework to run sets of tiiming tests encapsulated as classes. The sets of 
 *  timing tests, encapsulated as classes, used here all come from Kent Beck's 
 *  "Implementation Patterns" book, which has many more timing tests classes. I 
 *  have slightly modified these test classes from Beck's book to fit my needs.  
 *  These test classes are in the same package as this class.
 *
 *  java 1.7 API reference
 *  https://docs.oracle.com/en/java/javase/17/docs/api/
 *
 *  author: Prem Muthedath (the timing tests classes come from Kent Beck)
 */
public class AllTests {
  /* NOTE:
   * I have observed that running all sets of timing tests together, 
   * consecutively, within the same JVM yields slightly different results 
   * compared to running each set of timing tests separately in standalone mode, 
   * without sharing JVMs. This holds true for both Beck's timer and mine.
   *
   * For example, if we run ListSearch.class and SetVsArrayList.class sets of 
   * timing tests consecutively within the same JVM, we observe slightly 
   * different results compared to running ListSearch.class separately in 
   * standalone mode and then running SetVsArrayList.class in separate JVMs.
   *
   * Moreover, running in the same JVM has another problem: the order of 
   * execution seems to affect method timings.  For example, if we ran 
   * ListSearch.class first followed by SetVsArrayList.class in the same JVM, 
   * then the method timings reported for both sets of timing tests are somewhat 
   * different from the ones we get from running SetVsArrayList.class first 
   * followed by ListSearch.class in the same JVM!
   *
   * I am guessing these differences may be due to JVM caching or normal random 
   * variations caused by OS and other factors.  But in nay case, to eliminate 
   * unknown factors, I propose that we run each set of timing tests separately 
   * in standalone mode -- that is, sets of timing tests do not share JVMs.
   */
  public static void main(String[] args) throws Exception {
    /* Notes:
     *  1. Commented out all but one test execution below, because, as explained 
     *     above, if we run these timing tests together, one after the other, in 
     *     the SAME JVM, we notice slightly different results.
     *
     *     So don't run all timing tests together. Run them seperately, in 
     *     standalone mode, by commenting out all except the one you want to 
     *     run, as done below.
     *
     *  2. You can order the results (the method timings) by collection size or 
     *     by method name.  Likewise, you can choose the format of the report as 
     *     well.  Available formats include plain text, xml, and java swing.
     *
     *     The `timer.Timer` class offers methods that you can invoke not only 
     *     to generate method timings for a set of timing tests but also to 
     *     order and format the results generated. For example, if you want 
     *     method timings generated for a set of timing tests defined by the 
     *     `ListSearch.class` and have the generated results formatted as plain 
     *     text and ordered by collection size, you can do so by calling:
     *
     *        new timer.Timer().sizeOrderedTextReport(ListSearch.class)
     *
     *     Note that `timer.Timer` class prints text and xml reports to console, 
     *     and it displays java swing report in a java swing frame view.
     */

    new Timer().sizeOrderedTextReport(ZeroTimingTests.class);
    /* new Timer().methodOrderedTextReport(ListSearch.class); */
    new Timer().sizeOrderedTextReport(SetVsArrayList.class);
    /* new Timer().sizeOrderedTextReport(Lists.class); */
    /* new Timer().methodOrderedTextReport(Maps.class); */
  }
}
