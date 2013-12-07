Timer
=======

My refactored version of Kent Beck's timer framework for timing collection methods.  

See Kent Beck's book "Implementation Patterns" (Appendix A) for the timer source code. 

After reading Beck's excellent book, I decided to put my learnings into practice.  Beck's code (see the book) is really good, but I personally felt, for clarity, few areas needed refactoring: 

1. Code (mainly related to reflection) not in the right objects;
2. One class -- MethodTimer class in Beck's code -- had more than one responsibility;
3. Rate of Change principle violation (see Beck's book);
4. Better object names (I felt so).

As I refactored, these concerns led me to a different design with a new set of small objects, each doing just 1 thing.  After much struggle, I picked up the object names from Beck's own description of his timer framework in his book!

I have re-used the tests in Beck's book.



HOW TO RUN THE TESTS FROM A TERMINAL:

1. cd to timer directory -- the top directory of the app containing this README file.
2. Type the following command to compile: 

		javac -d bin -sourcepath src/main:src/tests  src/tests/timer/AllTests.java

3. Press enter.
4. Type the following command to run the tests:
	
 		java -classpath bin timer.AllTests

5. Press enter.
6. Output: for each method, clocked times (nano secs) for collection sizes 1, 10, 100, 1000, 10000, ...
	