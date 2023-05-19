package timer;

import timer.Timer;

/* java 1.7 API reference
 * https://docs.oracle.com/en/java/javase/17/docs/api/
 *
 * author: Prem Muthedath
 */
public class AllTests {

  /* NOTE:
   * To compare with Beck's results, run the tests in the SAME WAY AND ORDER as 
   * you run Beck's tests.  that is, for Beck's code, if you are running both 
   * ListSearch test and SetVsArrayList test TOGETHER, one after the other, then 
   * run these tests in the SAME WAY and in the SAME ORDER here AS WELL
   * (as shown in the main method below).
   *
   * the reason for this advice is this -- for Beck's code AS WELL AS for this 
   * code, I noticed that the results are slightly different if we run the 
   * ListSearch test first, followed by SetVsArrayList, one after the other,
   * in the SAME JVM, VERSUS if we run SetVsArrayList test WITHOUT running the 
   * ListSearch first.  Specifically, we get slightly different timings for 
   * sizes 100 and 1000 for the arrayListMembership test. For size 100, we 
   * notice 160-170 ms vs 200-210 ms, and for size 1000, we notice 1600-1800 vs 
   * 2000-2100 ms.  The lower values are reported when we run ListSearch first, 
   * followed immediately by SetVsArrayList in the same JVM, as shown in the 
   * main method below.  The differences are slight BUT CONSISTENT.
   */

  public static void main(String[] args) throws Exception {
    AllTests tests=new AllTests();

    /* Commented out one test execution, because, as explained above, if we run 
     * these two tests, one after the other, in the SAME JVM, we notice slightly 
     * different results.
     *
     * So don't run these two tests together. Run them seperately, in standalone 
     * mode, by commenting one of them out, as done below.
     */

    // tests.runListSearchTest();

    tests.runSetVsArrayListTest();
  }

  public void runListSearchTest() throws Exception {
    /* You can order the output by collection sizes or by method names.
     * To set the order of your output, USE THE FOLLOWING VALUES FOR 
     * Timer.Order PARAMETER IN Timer.report():
     *
     * To order by SIZE, use Timer.Order.BY_SIZE
     * To order by METHOD, use Timer.Order.BY_METHOD
     */

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

    new Timer().report(ListSearch.class,
        Timer.Order.BY_SIZE,
        Timer.Format.TEXT);
  }

  public void runSetVsArrayListTest() throws Exception {
    /* You can order the output by collection sizes or by method names.
     * To set the order of your output, USE THE FOLLOWING VALUES FOR 
     * Timer.Order PARAMETER IN Timer.report():
     *
     * To order by SIZE, use Timer.Order.BY_SIZE
     * To order by METHOD, use Timer.Order.BY_METHOD
     */

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

    new Timer().report(SetVsArrayList.class,
        Timer.Order.BY_METHOD,
        Timer.Format.JAVA_SWING);
  }
}
