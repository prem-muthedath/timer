package timer.output.base;

public class SizeValidator  {
	private int rightSize;

	public SizeValidator(int rightSize)  {
		this.rightSize=rightSize;
	}

	public void validate(Node node)  {
		if(node.meAndDescendants()==rightSize) return;
		throw new RuntimeException("INVALID SIZE FOR NODE: "+node+" EXPECTED: "+rightSize+" FOUND: "+node.meAndDescendants());
	}
}
