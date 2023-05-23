import java.lang.reflect.Method;
import java.lang.reflect.Constructor;

/* Kent Beck's Time framework code.
 *
 * Beck does not provide a complete code listing for his timer framework 
 * anywhere.  Prem Muthedath assembled this code from code fragments Beck 
 * provides in his "Implementation Patterns" book.
 *
 * The `computeTotalTime()` method below is something added by Prem.
 *
 * See instructions at the top of `notes/kent-beck-timer/MethodsTimer.java` file 
 * on how to compile and run Beck's timer.
 * */
public class MethodTimer {
  private final int size;
  private final Method method;
  private Object instance;

  private long totalTime;
  private int iterations;
  private long overhead;

  MethodTimer(int size, Method method) throws Exception {
    this.size= size;
    this.method= method;
    instance= createInstance();
  }

  double getMethodTime() {
    return (double) (totalTime - overhead) / (double) iterations;
  }

  void run() throws Exception {
    iterations= 1;
    while (true) {
      totalTime= computeTotalTime();
      if (totalTime > MethodsTimer.ONE_SECOND)
      break;
      iterations*= 2;
    }
    overhead= overheadTimer(iterations).computeTotalTime();
  }

  /* Prem wrote this method, because it was not listed in Beck's book. */
  private long computeTotalTime() throws Exception {
    long start = System.nanoTime();
    for (int i = 0; i < iterations; i++)
      method.invoke(instance, new Object[0]);
    return System.nanoTime() - start;
  }

  private Object createInstance() throws Exception {
    Constructor<?> constructor= method.getDeclaringClass().getConstructor(new Class[]{int.class});
    return constructor.newInstance(new Object[]{size});
  }

  private static MethodTimer overheadTimer(int iterations) throws Exception {
    return new MethodTimer(iterations);
  }

  private MethodTimer(int iterations) throws Exception {
    this(0, MethodTimer.Overhead.class.getMethod("nothing", new Class[0]));
    this.iterations= iterations;
  }

  public static class Overhead {
    public Overhead(int size) {
    }

    public void nothing() {
    }
  }

}
