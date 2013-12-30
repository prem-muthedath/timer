package timer.framework;

public class Title {
	private TestGroup title;

	public void add(TestGroup testTitle) {
		this.title=this.title==null  ?  testTitle  :  title.addUnique(testTitle);
	}

	public String toString() {
		return title==null  ?   ""  :  title.toString();
	}
}