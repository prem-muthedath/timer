package timer;

public class Runner {
	public static void main(String[] args) throws Exception {
		MethodsTimer tester= new MethodsTimer(ListSearch.class.getDeclaredMethods());
		tester.report();
	}
}
