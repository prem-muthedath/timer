package timer.framework;

import java.lang.reflect.Method;

import timer.reporting.base.View;
import timer.reporting.base.Format;

public abstract class Results {
	public void run(Method method, TimingTests instance) throws Exception {
		add(instance.size(), method.getName(), new TimingTest(method, instance).timing());
	}

	protected abstract void add(int size, String method, double timing);
	public abstract void render(View view, Format format);
}			

			