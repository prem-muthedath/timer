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


Just for fun, I have also designed a reporting model that allows you to:

1.  Output method timings in different formats (text, xml, etc.);
2.  Order the output by collection sizes OR method names;
3.  Render the output in plain view (i.e., simple multi-line text) or in Java Swing grid layout.

NOTE: You can select your output view and format by editing timer.AllTtests.java.


I have re-used the tests in Beck's book.



HOW TO RUN THE TESTS FROM A TERMINAL:

1. cd to timer directory -- the top directory of the app containing this README file.
2. Create an empty directory called bin, directly under the top timer directory.
3. Type the following command to compile: 

		javac -d bin -sourcepath src/main:src/tests  src/tests/timer/AllTests.java

4. Press enter.
5. Type the following command to run the tests:
	
 		java -classpath bin timer.AllTests

6. Press enter.
7. Output: method timings (nano secs) in chosen view, format, & order (collection sizes or method names).
	
