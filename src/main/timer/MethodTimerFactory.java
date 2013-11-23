package timer;

import java.lang.reflect.Method;
import java.lang.reflect.Constructor;

public class MethodTimerFactory {
	private Method method;
	private int size;

	public MethodTimerFactory(Method method, int size) {
		this.method=method;
		this.size=size;
	}

	public MethodTimer create() throws Exception {
		TimedMethod timedMethod=timedMethod();
		TimedMethod overhead=overheadTimerFactory(dummy(), size).timedMethod();
		return new MethodTimer(timedMethod, overhead);
	}

	private TimedMethod timedMethod() throws Exception {
		return new TimedMethod(method, createInstance());
	}

	private Object createInstance() throws Exception { 
		Constructor<?> constructor= method.getDeclaringClass().getConstructor(new Class[]{int.class});
		return constructor.newInstance(new Object[]{size});
	}

	private static MethodTimerFactory overheadTimerFactory(Method dummy, int size) throws Exception { 
		return new MethodTimerFactory(dummy, size);
	}

	private Method dummy() throws Exception {
		return Dummy.class.getMethod("nothing", new Class[0]);
	}
}
