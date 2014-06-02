package timer.framework;

import java.lang.reflect.Method;

import timer.output.base.View;

public abstract class Report {
	public void run(Method method, TimingTests instance) throws Exception {
		add(instance.size(), method.getName(), new TimingTest(method, instance).timing());
	}

	protected abstract void add(int size, String method, double timing);
	public abstract void render(View view);
}			

			