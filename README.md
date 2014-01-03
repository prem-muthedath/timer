Collection Timer
================

My refactored version of Kent Beck's timer framework for timing collection methods.  

See Kent Beck's book "Implementation Patterns" (Appendix A) for the timer framework and source code. 

After reading Beck's excellent book, I decided to put my learnings into practice.  Beck's code (see the book) is a masterpiece, but I personally felt, for clarity, a few areas needed refactoring: 

1. Logic and data not together (mainly related to reflection);
2. One class -- MethodTimer class in Beck's code -- doing more than one thing;
3. Rate of Change principle violation (see Beck's book);
4. Better object names needed (I felt so).

These concerns led me to a different design with a new set of small objects, each doing just 1 thing.  After much struggle, I found my object names from Beck's own description of his timer framework!

Just for fun, I have designed a reporting model that can -- (a) output timings sorted by sizes OR by methods; and (b) in different formats (e.g., text, xml, etc.).

I have re-used the tests in Beck's book.



HOW TO RUN THE TESTS FROM A TERMINAL:

1. cd to timer directory -- the top directory of the app containing this README file.
2. Type the following command to compile: 

		javac -d bin -sourcepath src/main:src/tests  src/tests/timer/AllTests.java

3. Press enter.
4. Type the following command to run the tests:
	
 		java -classpath bin timer.AllTests

5. Press enter.
6. Output: for each method, average time (nano secs) per run for collection sizes 1, 10, 100, 1000, ...
	
