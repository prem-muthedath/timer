package timer.framework;

/* java 1.7 API reference
 * https://docs.oracle.com/en/java/javase/17/docs/api/
 *
 * the original idea and source of this code come from Kent beck's 
 * 'Implementation Patterns' book, but although Beck certainly came up with the 
 * overall algorithm used here, he did not encapsulate it in the way it has been 
 * done here. indeed, the design here departs considerably from Beck's.  Prem 
 * came up with this tiny class as part of his refactoring of Beck's code.
 *
 * author: Prem Muthedath
 */
 public class TestRun {
  private int iterations;
  private long totalTime;

  TestRun(int iterations, long totalTime)  {
    this.iterations=iterations;
    this.totalTime=totalTime;
  }

  double timing(TimingTest overhead)  throws Exception {
    long overheadTime=overhead.totalTime(iterations);
    /* time formula from Kent Beck */
    return (double) (totalTime - overheadTime) / (double) iterations;
  }
 }
