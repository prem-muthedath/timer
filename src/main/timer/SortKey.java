package timer;

public class SortKey implements Comparable<SortKey>  {
	private int size;
	private String method;

	public SortKey(int size, String method) {
		this.size=size;
		this.method=method;
	}

	public String value(Report report, SortKey previous) {
		if(previous!=null && size-previous.size==0) return "";
		return report.size(size);
	}

	public void header(Report report, StringBuffer header)  {
		if(header.indexOf(method) >= 0) return;
		header.append(method);
	}

	public int compareTo(SortKey another)  {
		return size-another.size==0  ?  method.compareTo(another.method)  :  size-another.size;
	}

	public int hashCode() {
		return size+method.hashCode();
	}

	public boolean equals(Object another)  {
		if(another instanceof SortKey) {
			SortKey that=(SortKey) another;
			return that.method.equals(this.method) && that.size==this.size;
		}
		return false;
	}
}