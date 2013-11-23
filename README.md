Timer
=======

Refactored version of Kent Beck's timer for timing methods.  

After reading Beck's excellent book Implementation Patterns, I decided to put what I had learned into practice.  This refactoring was driven by really two things I saw in the code: 

1. MethodTimer had more than one responsibility; 
2. Violation of the Rate of Change principle (see Beck's book).

In this refactoring, I have addressed these concerns through small objects, each doing just 1 thing.



HOW TO RUN THE TESTS FROM A TERMINAL:

1. cd to timer directory -- the top directory of the app containing this README file.
2. Type the following command to compile: 

		javac -d bin -sourcepath src/main:src/tests  src/tests/timer/Runner.java

3. Press enter.
4. Type the following command to run the tests:
	
 		java -classpath bin timer.Runner

5. Press enter.
6. You will see the following output: clocked times for multiple iterations for each method.
	