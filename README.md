Timer
=======

My refactored version of Kent Beck's timer framework for timing collection methods.  

See Kent Beck's book "Implementation Patterns" (Appendix A) for the timer source code. 

After reading Beck's excellent book, I decided to put my learnings into practice.  Beck's code (see the book) is really good, but I personally felt, for clarity, two areas needed refactoring: 

1. Violation of the Rate of Change principle (see Beck's book);
2. One class -- MethodTimer -- had more than one responsibility.

In this refactoring, I have addressed these concerns through small objects, each doing just 1 thing.  

I have re-used the tests in Beck's book.



HOW TO RUN THE TESTS FROM A TERMINAL:

1. cd to timer directory -- the top directory of the app containing this README file.
2. Type the following command to compile: 

		javac -d bin -sourcepath src/main:src/tests  src/tests/timer/AllTests.java

3. Press enter.
4. Type the following command to run the tests:
	
 		java -classpath bin timer.AllTests

5. Press enter.
6. Output: for each method, clocked times for various collection sizes (1, 10, 100, 1000, 10,000, etc.).
	