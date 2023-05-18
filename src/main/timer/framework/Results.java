package timer.framework;

import java.lang.reflect.Method;

/* java 1.7 API reference
 * https://docs.oracle.com/en/java/javase/17/docs/api/
 *
 * author: Prem Muthedath
 */
public abstract class Results {
  public void run(Method method, TimingTests instance) throws Exception {
    add(instance.size(), method.getName(), new TimingTest(method, instance).timing());
  }

  protected abstract void add(int size, String method, double timing);
}

