package timer.reporting.base;


public class NullContent extends Content {
	public NullContent() {
		super();
	}

	public Content addUnique(Content another)  {
		return add(another);
	}

	public Content add(Content another)  {
		return another;
	}
}