#! /bin/bash

# Runs query 2 benchmarks and appends to result files. 
echo "Running Benchmarks..."
./branch_mispred_optimal_q1_0 0 0 0 0 >> result_q2_0.txt
./branch_mispred_optimal_q1_1 0.590 0.536 0.585 0.541 >> result_q2_1
./branch_mispred_optimal_q1_2 0.702 0.637 0.695 0.643 >> result_q2_2
./branch_mispred_optimal_q1_3 0.777 0.705 0.770 0.712 >> result_q2_3
./branch_mispred_optimal_q1_4 0.835 0.757 0.827 0.765 >> result_q2_4
./branch_mispred_optimal_q1_5 0.883 0.801 0.875 0.809 >> result_q2_5
./branch_mispred_optimal_q1_6 0.924 0.838 0.915 0.846 >> result_q2_6
./branch_mispred_optimal_q1_7 0.960 0.871 0.951 0.880 >> result_q2_7
./branch_mispred_optimal_q1_8 0.993 0.901 0.984 0.909 >> result_q2_8
./branch_mispred_optimal_q1_9 0.993 0.955 0.984 0.964 >> result_q2_9
./branch_mispred_optimal_q1_10 1 1 1 1 >> result_q2_10
echo "Done"