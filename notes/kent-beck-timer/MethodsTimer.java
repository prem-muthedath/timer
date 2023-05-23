import java.lang.reflect.Method;

/* Kent Beck's Timer framework code.
 *
 * Beck does not provide a complete code listing for his timer framework 
 * anywhere.  Prem Muthedath assembled this code from code fragments Beck 
 * provides in his "Implementation Patterns" book.
 *
 * The `main()` method below is something added by Prem.
 *
 * HOW TO RUN BECK'S TIMER AT THE TERMINAL:
 *  1. `cd` to `notes/kent-beck-timer` directory that contains this file;
 *  2. Two collection tests, `ListSearch` and `SetVsArrayList`, are available 
 *     for timing.  You can choose either one to run Beck's timer on by 
 *     commenting or uncommenting the related code in the `main()` method in 
 *     this file below.  See `main()` method below for more details. Once you 
 *     have made the changes in the `main()` method below, save this file.
 *  3. Type below command at the terminal & press `ENTER` to compile the code:
 *
 *      javac MethodsTimer.java
 *
 *  4. Next, type below command and press `ENTER` to run the timer:
 *
 *      java MethodsTimer
 *
 *  5. You will now see the timer output (method timings in nanoseconds for 
 *     various collection sizes starting from 1) printed to console.
 *
 * */
public class MethodsTimer {
  private final Method[] methods;
  private static final int MAXIMUM_SIZE= 100000;
  static final int ONE_SECOND= 1000000000;

  public MethodsTimer(Method[] methods) {
    this.methods= methods;
  }

  public void report() throws Exception {
    for (Method each : methods) {
      System.out.print(each.getName() + "\t");
      for (int size= 1; size <= MAXIMUM_SIZE; size*= 10) {
        MethodTimer r= new MethodTimer(size, each);
        r.run();
        System.out.print(String.format("%.2f\t", r.getMethodTime()));
      }
      System.out.println();
    }
  }


  /* Prem added this `main()` method.
   * See instructions given at the top of this file on how to run Beck's timer.
   */
  public static void main(String[] args) throws Exception {
    /*  Commented out one test execution, because, if we run these two tests, 
     *  one after the other in the SAME JVM, we notice slightly different 
     *  results due to caching.
     *
     *  So don't run these two tests together. Run them seperately, in 
     *  standalone mode, by commenting one of them out, as done below.
     *
     *  MethodsTimer tester1= new MethodsTimer(ListSearch.class.getDeclaredMethods());
     *  tester1.report();
     */

    MethodsTimer tester= new MethodsTimer(SetVsArrayList.class.getDeclaredMethods());
    tester.report();
  }
}
