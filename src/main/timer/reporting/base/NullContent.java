package timer.reporting.base;


public class NullContent extends Content {
	public NullContent() {
		super();
	}

	Content addUnique(Content another)  {
		return add(another);
	}

	Content add(Content another)  {
		return another;
	}
}