package timer;

public abstract class Test implements Comparable<Test> {
	private int size;
	private String method;
	private double timing;

	private Test(int size, String method, double timing) {
		this.size=size;
		this.method=method;
		this.timing=timing;
	}

	public abstract void export(Content content);
	public abstract int compareTo(Test another);

	public static Test methodOrderedInstance(final int size, final String method, final double timing)  {
		return new Test(size, method, timing) {
			public void export(Content content)  {
				content.addMethodGroup(size, method, timing);
			}
			public int compareTo(Test another)  {
				return method.compareTo(another.method)==0  ?  size-another.size  :  method.compareTo(another.method);				
			}
		};	
	}
		
	public static Test sizeOrderedInstance(final int size, final String method, final double timing)  {
		return new Test(size, method, timing) {
			public void export(Content content)  {
				content.addSizeGroup(size, method, timing);
			}
			public int compareTo(Test another)  {
				return size-another.size==0  ?  method.compareTo(another.method)  :  size-another.size;
			}
		};	
	}
}
