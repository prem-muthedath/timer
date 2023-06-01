package timer.reporting;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.EnumMap;
import java.util.Comparator;

import timer.framework.Results;

/** Represents timing tests results ordered in some way.
 *  This class has all common code that subclasses use.
 *
 *  java 1.7 API reference
 *  https://docs.oracle.com/en/java/javase/17/docs/api/
 *
 *  author: Prem Muthedath
 */
public abstract class OrderedResults extends Results {
  private enum Schema {SIZE, METHOD, TIMING};
  /* I used Map, because I couldn't see any real behavior for an object. */
  private List<Map<Schema, String>> results = new ArrayList<Map<Schema, String>>();

  /* add a new timing test result.
   * we expect result to be unique & valid, so no dups & other checks done here. 
   */
  protected void add(int size, String method, double timing) {
    Map<Schema, String> result = new EnumMap<Schema, String>(Schema.class);
    result.put(Schema.SIZE, String.valueOf(size));
    result.put(Schema.METHOD, method);
    result.put(Schema.TIMING, String.valueOf(timing));
    this.results.add(result);
  }

  /* sort results. subclasses are expected to implement `sort()` with args. */
  private void sort() {
    /* see /u/ paul mckenzie @ https://tinyurl.com/3fjxenyf (so) */
    Collections.sort(this.results, new Comparator<Map<Schema, String>>() {
        public int compare(Map<Schema, String> one, Map<Schema, String> another) {
          return sort(one.get(Schema.SIZE), another.get(Schema.SIZE),
              one.get(Schema.METHOD), another.get(Schema.METHOD));
        }
    });
  }

  /* `sort()` implemented by subclasses; returns values like `compare()` */
  protected abstract int sort(String oneSize, String anotherSize, String oneMethod, String anotherMethod);
  /* report these results. subclasses decide how to report their results. */
  abstract void report(Report report);
  /* all equi-slices or bundles of timings. subclasses decide the slice size. */
  protected abstract double[][] allSlicesOfTimings();

  /* how many timing test results do we have? */
  private int size() {
    return this.results.size();
  }

  /* all timings, well, always after sorting results. subclasses do the sorting.
   * timings are bundled into a slice or bundle for each value of the sorting 
   * parameter. all such slices of timings together form the reported timings, 
   * which is why you have a 2-dimensional array as result.
   */
  double[][] timings() {
    this.sort();
    return this.allSlicesOfTimings();
  }

  /* slice up all timings based on a slice count. */
  double[][] allSlicesOfTimings(int sliceCount) {
    int sliceSize = this.size()/sliceCount;
    double[][] timings = new double [sliceCount] [];
    for (int i = 0; i < sliceCount; i ++) {
      int from = i*sliceSize, to = from + sliceSize;
      timings[i] = this.aSliceOfTimings(from, to);
    }
    return timings;
  }

  /* a slice of timings defined by the specified index range of results. */
  private double[] aSliceOfTimings(int from, int to) {
    List<Map<Schema, String>> slice = this.results.subList(from, to);
    double[] aSliceOfTimings = new double [slice.size()];
    for (int i=0; i < slice.size(); i ++)
      aSliceOfTimings[i] = Double.parseDouble(slice.get(i).get(Schema.TIMING));
    return aSliceOfTimings;
  }

  /* unique sorted sizes. */
  int[] sortedSizes() {
    String[] sizes = new String [this.size()];
    for (int i=0; i < sizes.length; i++)
      sizes[i] = this.results.get(i).get(Schema.SIZE);
    return new SizeOrMethodParameter().sortedSizes(sizes);
  }

  /* unique sorted methods. */
  String[] sortedMethods() {
    String[] methods = new String [this.size()];
    for (int i=0; i < methods.length; i++)
      methods[i] = this.results.get(i).get(Schema.METHOD);
    return new SizeOrMethodParameter().sortedMethods(methods);
  }
}
