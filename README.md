#### Timer

My refactored version of Kent Beck's timer framework for timing collection methods.

Beck's timer framework times methods over a pre-defined set of collection sizes.  
See Kent Beck's book "Implementation Patterns" (Appendix A) for a discussion of 
the timer framework. Instead of a full listing of the framework code, Beck gives 
code fragments as he explains his framework. I have assembled these code pieces 
together, supplying 1 missing method myself, in `notes/kent-beck-timer` folder, 
where you can view `MethodsTimer.java` and `MethodTimer.java`, the two classes 
that comprise Beck's framework. The folder also has 2 tests from Beck's book.

Beck keeps his framework deliberately minimal and simple, because his aim is not 
to develop a robust framework for the public domain with thousands of users but 
to demonstrate certain principles for readers of his book. So Beck correctly 
accepts stuff that he otherwise would have improved on or redesigned.

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
objects, each doing just 1 thing.  My core framework has 5 objects, compared to 
2 in Beck's, but I believe in more tiny objects than a few big ones. Also, I 
found my object names in Beck's own description of his timer framework!

Just for fun, I have also designed a reporting model that allows you to:

1. Output method timings in different formats (text, xml, java swing);
2. Order the output by collection sizes OR by method names.

Text and xml reports are printed to console; java swing report is displayed in a 
java swing frame view. Sample reports are in `notes/sample-timer-reports` 
folder.  This folder also contains a sample output from Beck's timer.

NOTE: You can select your output order and format by passing the right values 
for order and format to `timer/src/main/timer/Timer.report()`.  For an example 
usage, see `timer/src/tests/timer/AllTests.java`.

I have re-used the tests in Beck's book.

##### Benchmarking:

I benchmarked both Beck's timer as well as my refactored version to compare how 
they preform. The complete set of benchmark data are in `notes/benchmaks` 
folder.  Both Beck's timer as well as my version report broad but similar 
variations for methods whose timings (in nanoseconds) increase with collection 
size.  These variations become more pronounced for collection sizes of 1000 
elements or more. Despite these variations, reported timings (in nanoseconds) 
from my version agree well with Beck's.

I did these benchmarks on a Macbook Air mid-2012 running Java 1.7.  This stuff 
is quite old, and I suspect the large variations in the data most likely stem 
from the behavior of the operating system under load.  If I were to bet on what 
would be the accurate timings, I would go with the lower values reported. For  
statistically-valid conclusions on timings, however, you would need something 
like the Haskell library Criterion, but that is beyond scope here.

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

