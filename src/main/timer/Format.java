package timer;

import java.util.Collection;

public abstract class Format {
	public void print(Formatable[] items) {
		for (Formatable each : items)
			System.out.print(format(each));
		System.out.println();
	}

	protected abstract String format(Formatable item);

	public static Format headerFormat()  {
		return new Format() {
			protected String format(Formatable item) {
				return item.header(this);
			}
		};
	}

	public static Format dataFormat()  {
		return new Format() {
			protected String format(Formatable item) {
				return item.value(this);
			}
		};
	}

	public String simpleHeader(String header) {
		return String.format("%-10s\t", header);
	}

	public String bigHeader(String header) {
		return String.format("%-25s\t", header);
	}

	public String value(int value) {
		return String.format("%-10d\t", value);
	}

	public String value(double value) {
		return String.format("%-25.2f\t", value);
	}
}	
