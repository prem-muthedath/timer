package timer.reporting;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Arrays;
import java.util.Map;
import java.util.EnumMap;
import java.util.Comparator;

import timer.framework.Results;

/* java 1.7 API reference
 * https://docs.oracle.com/en/java/javase/17/docs/api/
 *
 * author: Prem Muthedath
 */
public abstract class OrderedResults extends Results {
  private enum Schema {SIZE, METHOD, TIMING};
  private List<Map<Schema, String>> results = new ArrayList<Map<Schema, String>>();

  protected void add(int size, String method, double timing) {
    Map<Schema, String> result = new EnumMap<Schema, String>(Schema.class);
    result.put(Schema.SIZE, String.valueOf(size));
    result.put(Schema.METHOD, method);
    result.put(Schema.TIMING, String.valueOf(timing));
    this.results.add(result);
  }

  private String getSize(Map<Schema, String> item) {
    return item.get(Schema.SIZE);
  }

  private String getMethod(Map<Schema, String> item) {
    return item.get(Schema.METHOD);
  }

  private String getTiming(Map<Schema, String> item) {
    return item.get(Schema.TIMING);
  }

  int count() {
    return this.results.size();
  }

  private void sort() {
    // see /u/ paul mckenzie @ https://tinyurl.com/3fjxenyf (so)
    Collections.sort(this.results, new Comparator<Map<Schema, String>>() {
        public int compare(Map<Schema, String> one, Map<Schema, String> another) {
          return sort(one.get(Schema.SIZE), another.get(Schema.SIZE),
              one.get(Schema.METHOD), another.get(Schema.METHOD));
        }
    });
  }

  protected abstract int sort(String oneSize, String anotherSize, String oneMethod, String anotherMethod);
  abstract void report(Report report);

  public double[][] timings() {
    this.sort();
    return this.allTimings();
  }

  protected abstract double[][] allTimings();

  double[] timingsSlice(int from, int to) {
    List<Map<Schema, String>> slice = this.results.subList(from, to);
    double[] timingsSlice = new double [slice.size()];
    for (int i=0; i < slice.size(); i ++)
      timingsSlice[i] = Double.parseDouble(this.getTiming(slice.get(i)));
    return timingsSlice;
  }

  public int[] sortedSizes() {
    List<String> uniqueSizes = new ArrayList<String>();
    for (Map<Schema, String> each : this.results) {
      String size=this.getSize(each);
      if (uniqueSizes.contains(size)) continue;
      uniqueSizes.add(size);
    }
    return this.sortedSizes(uniqueSizes);
  }

  private int[] sortedSizes(List<String> uniqueSizes) {
    int[] result = new int [uniqueSizes.size()];
    for (int i=0; i < uniqueSizes.size(); i++)
      result[i] = Integer.parseInt(uniqueSizes.get(i));
    Arrays.sort(result);
    return result;
  }

  public String[] sortedMethods() {
    List<String> uniqueMethods = new ArrayList<String>();
    for (Map<Schema, String> each : this.results) {
      String method = this.getMethod(each);
      if (uniqueMethods.contains(method)) continue;
      uniqueMethods.add(method);
    }
    Collections.sort(uniqueMethods);
    // see /u/ waxwing @ https://tinyurl.com/5a3dj2ck (so)
    return Arrays.copyOf(uniqueMethods.toArray(), uniqueMethods.size(), String[].class);
  }

}
