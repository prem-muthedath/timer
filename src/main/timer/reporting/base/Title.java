package timer.reporting.base;

public class Title {
	private Content title;

	public void add(Content testTitle) {
		this.title=this.title==null  ?  testTitle  :  title.addUnique(testTitle);
	}

	public String toString() {
		return title==null  ?   ""  :  title.toString();
	}
}