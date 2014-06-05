package timer.reporting.base;

public interface Format {
	public void add(Field[] fields);	
	public String size(int size);
	public String method(String method);
	public String timing(double timing);
	public void render(View view);	
}