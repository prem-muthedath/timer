package timer.reporting;

import java.util.List;
import java.util.ArrayList;

import timer.framework.Results;

/** Represents timing tests results ordered in some way for reporting.
 *
 *  java 1.7 API reference
 *  https://docs.oracle.com/en/java/javase/17/docs/api/
 *
 *  author: Prem Muthedath
 */
public abstract class OrderedResults extends Results {
  /* report results */
  abstract void report(Report report);

  /* unique sorted sizes. */
  abstract int[] sortedSizes();

  /* unique sorted methods. */
  abstract String[] sortedMethods();

}
