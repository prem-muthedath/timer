package timer.reporting;

/* java 1.7 API reference
 * https://docs.oracle.com/en/java/javase/17/docs/api/
 *
 * author: Prem Muthedath
 */
class TextFormat {
  static String title(String title) {
    return String.format("%-30s", title);
  }

  static String leftMargin() {
    return String.format("%-30s", "");
  }

  static String sizeLabel(int size) {
    return String.format("%-30s", "size=" + size);
  }

  static String methodLabel(String method) {
    return String.format("%-30s", method);
  }

  static String timingValue(double timing) {
    return String.format("%-30.2f", timing);
  }

}
