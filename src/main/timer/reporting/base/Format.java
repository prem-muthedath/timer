package timer.reporting.base;

public interface Format {
	public String size(int size);
	public String method(String method);
	public String timing(double timing);
	public void add(String column, String row, String timing);
	public void render(View view);	
}