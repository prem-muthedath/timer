package timer;

import java.lang.reflect.Method;

public class MethodsTimer {
	private static final int MAXIMUM_SIZE= 100000;
	static final int ONE_SECOND= 1000000000;	
	private Method[] methods;

	public MethodsTimer(Method[] methods) {
		this.methods=methods;
	}

	public void report() throws Exception { 
		for (Method each : methods) {
			System.out.print(each.getName() + "\t");
			reportMethodTimes(each);
			System.out.println();
		}
	}

	private void reportMethodTimes(Method method) throws Exception {
		for (int size= 1; size <= MAXIMUM_SIZE; size*= 10) {
			MethodTimer timer=new MethodTimerFactory(method, size).create();
			System.out.print(String.format("%.2f\t", timer.time()));
		}
	}
}	
