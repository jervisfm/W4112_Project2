Title: COMS W4112 Project 2    
Author: Varun Ravishankar    
		Jervis Muindi     
Email:  vr2263@columbia.edu    
       jjm2190@columbia.edu   
Date: April 17th, 2013     

# COMS W4112 Project 2

This project contains an implementation of Algorithm 4.11 from
[Selection Conditions in Main Memory]. This algorithm optimizes SQL
plans for to minimize branch prediction costs using a dynamic
programming algorithm. This algorithm's cost is O(4^n).

# Usage

1. `$ git clone https://github.com/jervisfm/W4112_Project2` (Only if you don't have the source already)
2. `$ cd W4112_Project2`
3. `$ make`
4. `$ ./stage2.sh query.txt config.txt`


# Dependency
* JUnit - You need this to run the tests. It is included in the lib/ folder by default. If for some reason it is not present, you can obtain a copy of [Junit] online. Please note that you need both the junit.jar and hamcrest-core.jar files. 

# Query File Format
Query file just contains a different query on each line. Each query will have the selectivity listed for the functions that are applied in that query. See the included query.txt for an example. 


# Config File Format
The config file should have the following values set: 

r = 1  
t = 2  
l = 1  
m = 16  
a = 2  
f = 4  

Where

	 r = Cost of accessing an array element rj[i]  
	 t = Cost of performing an if test
	 l = Cost of performing a logical "and" test
	 m = Cost of Branch mis-prediction
	 a = Cost of writing an answer to the answer array			
	 f = Cost of apply function f to its argument
	 	

# Output Format
The output that the program produces will have the following format: 

<pre>
======================================
0.4 0.6
--------------------------------------
answer[j] = i;
j += (t1[o1[i]] & t2[o2[i]]);
--------------------------------------
cost = 13.0
</pre>

The number in the first line are the sensitivities found in the query. 
The code is the middle is C-code that describes the optimal plan with minimal cost in branch-mispredictions. 
The number in the last line is the estimated cost for this query using the specified config parameters. 

# Tests
We have made some unit tests to test out some of the different pieces of the program. You can find these tests in the UnitTest.java file. 

Assuming you have JUnit dependency installed, you can run all the tests we have as follows: 

`make test`

# Sample Runs
We have some sample queries and config files included with the project. 
To see the program in action being executed on sample inputs, do the following:

`./stage2.sh query.txt config.txt`


# Error Checking
We try to do reasonable error checking in our code to handle unexpected or missing inputs. 


[Selection Conditions in Main Memory]: http://www.cs.columbia.edu/~kar/pubsk/selcondsTODS.pdf
[Junit]: https://github.com/junit-team/junit/wiki/Download-and-Install
