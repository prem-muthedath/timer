package timer.reporting.base;

public interface Content extends Format {
	public void add(Field[] fields);
	public void render(View view);
}