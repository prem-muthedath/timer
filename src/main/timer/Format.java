package timer;

import java.util.Collection;

public abstract class Format {
	public void print(String size, Collection<String> values) {
		System.out.print(size(size));
		for (String each : values)
			System.out.print(value(each));
		System.out.println();
	}

	protected abstract String size(String size);
	protected abstract String value(String data);

	public static Format headerFormat()  {
		return new Format() {
			protected String size(String sizeHeader) {
				return String.format("%-10s\t", sizeHeader);
			}
			protected String value(String dataHeader) {
				return String.format("%-25s\t", dataHeader);
			}
		};
	}

	public static Format dataFormat()  {
		return new Format() {
			protected String size(String size) {
				return String.format("%-10d\t", Integer.parseInt(size));
			}
			protected String value(String data) {
				return String.format("%-25.2f\t", Double.parseDouble(data));
			}
		};
	}
}	
