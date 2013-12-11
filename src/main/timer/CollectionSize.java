package timer;

public class CollectionSize implements Formatable {
	private String name="size";
	private int size;

	public CollectionSize(int size) {
		this.size=size;
	}

	public void print(Timings timings) {
		if(firstOne()) timings.printHeader(this);
		timings.printValues(this);
	}

	private boolean firstOne() {
		return size==CollectionTimer.MINIMUM_SIZE;
	}

	public String header(Format format) {
		return format.simpleHeader(name);
	}

	public String value(Format format) {
		return format.value(size);
	}
}
