package timer;

import java.util.Collection;

public abstract class Format {
	public void print(String size, Collection<String> values) {
		System.out.print(size(size));
		for (String each : values)
			System.out.print(value(each));
		System.out.println();
	}

	public abstract String size(String size);
	public abstract String value(String data);

	public static Format textFormat()  {
		return new Format() {
			public String size(String size) {
				return String.format("%-10s\t", size);
			}
			public String value(String data) {
				return String.format("%-25s\t", data);
			}
		};
	}

	public static Format numberFormat()  {
		return new Format() {
			public String size(String size) {
				return String.format("%-10d\t", Integer.parseInt(size));
			}
			public String value(String data) {
				return String.format("%-25.2f\t", Double.parseDouble(data));
			}
		};
	}
}	
