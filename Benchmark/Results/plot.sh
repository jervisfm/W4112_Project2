#! /bin/bash

# Plot Logical And Base 
echo "Plotting Logical And Base Results ..."
./plot.py -f compiled_result_land_q1.txt
./plot.py -f compiled_result_land_q2.txt
echo "Done"


# Plot Physical And Base Results
echo "Plotting Physical And Base Results..."
./plot.py -f compiled_result_pand_q1.txt
./plot.py -f compiled_result_pand_q2.txt
echo "Done"

# Plot No Branch Alg Results
echo "Plotting No Branch Alg Results..."
./plot.py -f compiled_result_nb_q1.txt
./plot.py -f compiled_result_nb_q2.txt
echo "Done"

# Plot Optimal Q1 + Q2
echo "Plotting Optimal Q1 and Q2 results..."
./plot.py -f compiled_result_optimal_q1.txt
./plot.py -f compiled_result_optimal_q2.txt
echo "Done"


echo "All Plots have been done"

