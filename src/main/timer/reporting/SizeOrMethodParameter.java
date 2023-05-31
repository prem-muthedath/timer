package timer.reporting;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Arrays;

/** A helper class. As the name implies, represents a size or mathod parameter.  
 *  It offers API to generate a set of unique, sorted values for the parameter.
 *
 *  java 1.7 API reference
 *  https://docs.oracle.com/en/java/javase/17/docs/api/
 *
 *  author: Prem Muthedath
 */
class SizeOrMethodParameter {
  private List<String> uniqueValues;

  int[] sortedSizes(String[] sizes) {
    this.addValues(sizes);
    int[] uniqueSizes = new int [this.uniqueValues.size()];
    for (int i=0; i < this.uniqueValues.size(); i++)
      uniqueSizes[i] = Integer.parseInt(this.uniqueValues.get(i));
    Arrays.sort(uniqueSizes);
    return uniqueSizes;
  }

  private void addValues(String[] values) {
    this.uniqueValues = new ArrayList<String>();
    for (int i=0; i < values.length; i++) {
      String aValue = values[i].trim();
      if (this.uniqueValues.contains(aValue)) continue;
      this.uniqueValues.add(aValue);
    }
  }

  String[] sortedMethods(String[] methods) {
    this.addValues(methods);
    Collections.sort(this.uniqueValues);
    /* see /u/ waxwing @ https://tinyurl.com/5a3dj2ck (so) */
    return Arrays.copyOf(uniqueValues.toArray(), uniqueValues.size(), String[].class);
  }

}



