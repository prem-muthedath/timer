package timer.framework;

/** `TestRun` class encapsulates the details of a completed test run to time a 
 *  timing test (i.e., the method timed). Using its information, the `TestRun` 
 *  object knows how to compute the final execution time of a timing test.
 *
 *  the original idea and parts of source code come from Kent Beck's 
 *  'Implementation Patterns' book, but although Beck certainly came up with the 
 *  overall algorithm used here, he did not encapsulate it in the way it has 
 *  been done here. indeed, the design here departs considerably from Beck's.  
 *  Prem came up with this tiny class as part of his refactoring of Beck's code.
 *
 *  java 1.7 API reference
 *  https://docs.oracle.com/en/java/javase/17/docs/api/
 *
 *  author: Prem Muthedath (with parts of source code and ideas from Kent Beck)
 */
 class TestRun {
  private int iterations;
  private long totalTime;

  TestRun(int iterations, long totalTime)  {
    this.iterations = iterations;   /* # of iterations used to compute total time */
    this.totalTime = totalTime;     /* total method time for all iterations */
  }

  /* compute final timing of this timing test run. */
  double timing(TimingTest overhead)  throws Exception {
    long overheadTime = overhead.totalTime(iterations);
    /* time formula from Kent Beck */
    return (double) (this.totalTime - overheadTime) / (double) this.iterations;
  }
 }
