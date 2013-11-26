Timer
=======

Refactored version of Kent Beck's timer for timing methods.  

After reading Beck's excellent book Implementation Patterns, I decided to put what I had learned into practice.  Beck's code (see the book) is really good, but I personally felt two areas needed refactoring: 

1. Violation of the Rate of Change principle (see Beck's book).
2. One class -- MethodTimer -- was having more than one responsibility.

In this refactoring, I have addressed these concerns through small objects, each doing just 1 thing.  

I have used the tests in Beck's book.



HOW TO RUN THE TESTS FROM A TERMINAL:

1. cd to timer directory -- the top directory of the app containing this README file.
2. Type the following command to compile: 

		javac -d bin -sourcepath src/main:src/tests  src/tests/timer/Tests.java

3. Press enter.
4. Type the following command to run the tests:
	
 		java -classpath bin timer.Tests

5. Press enter.
6. You will see the following output: for each method, clocked times for various collection sizes.
	