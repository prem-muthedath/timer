#### Timer

My refactored version of Kent Beck's timer framework for timing collection methods.

See Kent Beck's book "Implementation Patterns" (Appendix A) for the timer 
framework and source code.

After reading Beck's excellent book, I decided to put my learnings to practice.  
Beck's code (see the book) is a masterpiece, but I personally felt, for clarity, 
a few areas needed refactoring:

1. Logic and data not together (mainly related to reflection);
2. One class -- MethodTimer in Beck's code -- doing more than one thing;
3. Class structure for overhead calculations, in Beck's own words, is strange;
4. Again, in MethodTimer, rate of change principle violation (see Beck's book);
5. In Beck's words, temporal dependency between `run()` & querying methods;
6. Better object names needed (I felt so).

These concerns led me to a wholly different design with a new set of small 
objects, each doing just 1 thing.  Also, I found my object names in Beck's own 
description of his timer framework!

Just for fun, I have also designed a reporting model that allows you to:

1. Output method timings in different formats (text, xml, java swing);
2. Order the output by collection sizes OR by method names.

Text and xml reports are printed to console; if you opt for java swing format, 
you will see the output in a java swing frame view.

NOTE: You can select your output order and format by passing the right values 
for order and format to timer/src/main/timer/Timer.report().  For an example 
usage, see timer/src/tests/timer/AllTests.java.

I have re-used the tests in Beck's book.


##### HOW TO RUN THE TESTS FROM A TERMINAL:

1. `cd` to timer directory -- the top directory having this README file.
2. If you do not have a `bin` directory, create an empty `bin` directory at the 
   same level as this README file. this is needed for compilation in step 3.
3. Type the following command to compile:

     ````
     javac -d bin -sourcepath src/main:src/tests  src/tests/timer/AllTests.java

4. Press enter.
5. Type the following command to run the tests:

     ````
     java -classpath bin timer.AllTests

6. Press enter.
7. Output: method timings (in nanoseconds) in chosen format & order.

