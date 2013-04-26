#! /bin/bash

# Runs query 2 benchmarks and appends to result files. 
echo "Running Benchmarks..."
echo "Benchmarking selectivity 0.0"
./branch_mispred_optimal_q2_0 0 0 0 0 >> result_optimal_q2_0.txt
echo "Benchmarking selectivity 0.1"
./branch_mispred_optimal_q2_1 0.590 0.536 0.585 0.541 >> result_optimal_q2_1
echo "Benchmarking selectivity 0.2"
./branch_mispred_optimal_q2_2 0.702 0.637 0.695 0.643 >> result_optimal_q2_2
echo "Benchmarking selectivity 0.3"
./branch_mispred_optimal_q2_3 0.777 0.705 0.770 0.712 >> result_optimal_q2_3
echo "Benchmarking selectivity 0.4"
./branch_mispred_optimal_q2_4 0.835 0.757 0.827 0.765 >> result_optimal_q2_4
echo "Benchmarking selectivity 0.5"
./branch_mispred_optimal_q2_5 0.883 0.801 0.875 0.809 >> result_optimal_q2_5
echo "Benchmarking selectivity 0.6"
./branch_mispred_optimal_q2_6 0.924 0.838 0.915 0.846 >> result_optimal_q2_6
echo "Benchmarking selectivity 0.7"
./branch_mispred_optimal_q2_7 0.960 0.871 0.951 0.880 >> result_optimal_q2_7
echo "Benchmarking selectivity 0.8"
./branch_mispred_optimal_q2_8 0.993 0.901 0.984 0.909 >> result_optimal_q2_8
echo "Benchmarking selectivity 0.9"
./branch_mispred_optimal_q2_9 0.993 0.955 0.984 0.964 >> result_optimal_q2_9
echo "Benchmarking selectivity 1.0"
./branch_mispred_optimal_q2_10 1 1 1 1 >> result_optimal_q2_10
echo "Done"