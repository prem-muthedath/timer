#### Timer

My refactored version of Kent Beck's timer framework for timing collection methods.

Beck's timer framework times methods over a pre-defined set of collection sizes.  
See Kent Beck's book "Implementation Patterns" (Appendix A) for a discussion of 
the timer framework. Instead of a full code listing, Beck gives code fragments 
as he explains his framework. I have assembled these fragments together, 
supplying 1 missing method myself, in `notes/kent-beck-timer` folder, where you 
can view `MethodsTimer.java` and `MethodTimer.java`, the two classes that 
comprise Beck's framework. The folder also has tests from Beck's book.

Beck keeps his framework deliberately minimal and simple, because his aim is not 
to develop a robust framework for the public domain with thousands of users but 
to demonstrate key principles for his readers. He therefore rightly accepts 
tradeoffs that he otherwise would not have.

Over here, my goal is to try and do a good object design by putting stuff that I 
learned from Beck's book into practice. So, as part of my learning, I decided to 
refactor parts of Beck's code that I felt needed clarity, including:

1. Logic and data not together (mainly related to reflection);
2. One class -- MethodTimer in Beck's code -- doing more than one thing;
3. Strange class structure for overhead calculations (in Beck's own words);
4. Again, in MethodTimer, rate of change principle violation (see Beck's book);
5. In Beck's words, temporal dependency between `run()` & querying methods;
6. Better object names needed (I felt so).

These concerns led me to a wholly different design with a new set of small 
objects, each doing just 1 thing. At the heart of my framework is an abstraction 
called `TimingTests`, an object that represents a set of timing tests (i.e., the 
set of test methods to be timed) for a given collection size.  Each timing test 
in `TimingTests` is in turn represented by an object called `TimingTest`.  At 
the framework level, `TimingTests` is an abstract class with no timing tests but 
with code to time all timing tests.  Subclasses (such as `SetVsArrayList.java` 
in `src/tests/timer/`) with concrete timing tests (i.e., public test methods to 
be timed) resuse this code to time all their timing tests.  The other object, 
`TimingTest`, is  responsible for timing the test method it encapsulates.  My 
core framework boils down to 5 objects, compared to 2 in Beck's, but this 
increase is just fine, because we now have tinier objects with clear 
responsibilities. Finally, I found my object names in Beck's own description of 
his timer framework!

The framework here does exactly the same thing as Beck's: it times a set of test 
methods over a range of collection sizes.  Just for fun, I have also designed a 
reporting model that allows you to:

1. Output method timings in different formats (text, xml, java swing);
2. Order the output by collection sizes OR by method names.

Text and xml reports are printed to console; java swing report is displayed in a 
java swing frame view. Sample reports are in `notes/sample-timer-reports` 
folder.  This folder also contains a sample output from Beck's timer.

NOTE: `src/main/timer/Timer.java` offers public methods that clients can invoke 
to not only generate method timings for a set of timing tests over a range of 
collection sizes but also to order and format the results. For example usage, 
see `src/tests/timer/AllTests.java`.

I have re-used the tests in Beck's book; they are in `src/tests/timer` folder.

##### Benchmarking:

I benchmarked both Beck's timer as well as my refactored version to compare how 
they preform. The complete set of benchmark data are in `notes/benchmaks` 
folder.  Both Beck's timer as well as my version report broad but similar 
variations for methods whose timings (in nanoseconds) increase with collection 
size.  These variations become more pronounced for collection sizes of 1000 
elements or more. Despite these variations, reported timings (in nanoseconds) 
from my version agree well with Beck's.

I did these benchmarks on a Macbook Air mid-2012 running Java 1.7.  This stuff 
is quite old, and I suspect the upper swings in the data most likely stem from 
the behavior of the operating system under load.  So if I were to guess on what 
would be the accurate timings, I would go with the lower values reported.  To 
reach statistically-valid conclusions, however, you would need something like 
the Haskell library Criterion, which is beyond our scope.

##### Using the framework:

You use the framework to run a set of timing tests encapsulated in a class. You 
can see existing sets of timing tests (such as `SetVsArrayList.java`) that I have 
taken from Beck's book in `src/tests/timer` folder.  These timing tests are 
invoked from the `main()` method in `src/tests/timer/AllTests.java`.

If you would like to add your own set of timing tests, however, you can do so by 
following the below steps:

1. Create a class that encapsulates the set of your timing tests.  This class 
   must be a subclass of `TimingTests.java` in the `framework` folder. Ensure as 
   well that all timing tests (i.e., test methods to be timed) in your class are 
   public methods.  You can use existing set of timing tests (such as 
   `SetVsArrayList.java`) as a template;
2. Place the java file of your new class in `src/tests/timer` folder;
3. Invoke your timing tests class in the `main()` method of 
   `src/tests/timer/AllTests.java`.  You can use existing invocations of timing 
   test classes in the `main()` method as a reference;
4. Finally compile and run the program (see the next section).

##### HOW TO RUN THE TIMING TESTS FROM A TERMINAL:

1. `cd` to `timer` directory -- the top directory having this `README` file.
2. If you do not have a `bin` directory, create an empty `bin` directory at the 
   same level as this `README` file. this is needed for compilation in step 3.
3. Type the following command to compile:

     ````
     javac -d bin -sourcepath src/main:src/tests  src/tests/timer/AllTests.java

4. Press enter.
5. Type the following command to run the tests:

     ````
     java -classpath bin timer.AllTests

6. Press enter.
7. Output: method timings (in nanoseconds) in chosen format & order. See sample 
   reports generated by this `timer` in `notes/sample-timer-reports` folder.

