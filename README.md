Title: COMS W4112 Project 2    
Author: Varun Ravishankar    
		Jervis Muindi     
Email:  vr2263@columbia.edu    
       jjm2190@columbia.edu   
Date: April 17th, 2013     

# COMS W4112 Project 2

This project contains an implementation of Algorithm 4.11 from
the paper [Selection Conditions in Main Memory] by Kenneth Ross. The algorithm optimizes SQL
plans that involve selection filters which run completely in RAM to minimize the effect of branch 
mis-predictions. The algorithm uses a dynamic programming approach and it has a cost of O(4^n).
For more details on the operation of the algorithm , please refer to the cited paper. 

# Usage

1. `$ git clone https://github.com/jervisfm/W4112_Project2` (Only if you don't have the source already)
2. `$ cd W4112_Project2`
3. `$ make`
4. `$ ./stage2.sh query.txt config.txt`


# Dependency
* JUnit - You need this to run the tests. It is included in the lib/ folder by default. If for some reason it is not present, you can obtain a copy of [Junit] online. Please note that you need both the junit.jar and hamcrest-core.jar files together. 

# Query File Format
Query file just contains a different query on each line. Each query will have the selectivity listed for the functions that are applied in that query. 

For instance: 

	0.1 0.2 

Here you have a query with two functions f1 and f2 that have the selectivity of 0.1 and 0.2 respectively. 

You can see the included query.txt file for a fuller concrete example of a valid query file. 


# Config File Format
The config file should have the following parameters set: 

r = 1  
t = 2  
l = 1  
m = 16  
a = 2  
f = 4  

The meaning of these parameters is as follows: 

	 r = Cost of accessing an array element rj[i]  
	 t = Cost of performing an if test
	 l = Cost of performing a logical "and" test
	 m = Cost of Branch mis-prediction
	 a = Cost of writing an answer to the answer array			
	 f = Cost of apply function f to its argument
	 	
The included config.txt includes valid parameter that have been tuned to match machines in Columbia's CLIC-lab machines. (clic-lab.cs.columbia.edu)

# Output Format
The output of the program is the estimated optimal plan that should used in order minimize the negative effects of branch-mispredictions. The format of this output is like so: 

<pre>
======================================
0.4 0.6
--------------------------------------
answer[j] = i;
j += (t1[o1[i]] & t2[o2[i]]);
--------------------------------------
cost = 13.0
</pre>

The number in the first line are the sensitivities found in the query being optimized. 
The code is the middle is C-code that describes the optimal plan with minimal cost in branch-mispredictions. 
The number in the last line is the (estimated) total cost for this query using the specified config parameters. 

# Tests
We followed a TDD approach in developing the software and we have made some unit tests to test out some of the different pieces of the program. You can find these tests in the UnitTest.java file. 

Assuming you have JUnit dependency installed, you can run all the tests we have as follows: 

`make test`

In case, you don't have JUnit installed, please refer to the 'Dependency' section for instructions on doing so. 

# Sample Runs
We have some sample queries and config files included with the project. 
To see the program in action being executed on sample inputs, do the following:

`./stage2.sh query.txt config.txt`


# Error Checking
We try to do reasonable error checking in our code to handle unexpected or missing inputs. Thus, more most reasonable inputs the program should run without issues.


[Selection Conditions in Main Memory]: http://www.cs.columbia.edu/~kar/pubsk/selcondsTODS.pdf
[Junit]: https://github.com/junit-team/junit/wiki/Download-and-Install
