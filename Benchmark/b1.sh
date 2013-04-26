#! /bin/bash

# Runs query 1 benchmarks and appends to result files. 
echo "Running Optimal Benchmarks for Query 1..."
echo "Benchmarking selectivity 0.0"
./branch_mispred_optimal_q1_0 0 0 0 0 >> result_optimal_q1_0.txt
echo "Benchmarking selectivity 0.1"
./branch_mispred_optimal_q1_1 0.562341325 0.562341325 0.562341325 0.562341325 >> result_optimal_q1_1.txt
echo "Benchmarking selectivity 0.2"
./branch_mispred_optimal_q1_2 0.668740305 0.668740305 0.668740305 0.668740305 >> result_optimal_q1_2.txt
echo "Benchmarking selectivity 0.3"
./branch_mispred_optimal_q1_3 0.740082804 0.740082804 0.740082804 0.740082804 >> result_optimal_q1_3.txt
echo "Benchmarking selectivity 0.4"
./branch_mispred_optimal_q1_4 0.795270729 0.795270729 0.795270729 0.795270729 >> result_optimal_q1_4.txt
echo "Benchmarking selectivity 0.5"
./branch_mispred_optimal_q1_5 0.840896415 0.840896415 0.840896415 0.840896415 >> result_optimal_q1_5.txt
echo "Benchmarking selectivity 0.6"
./branch_mispred_optimal_q1_6 0.880111737 0.880111737 0.880111737 0.880111737 >> result_optimal_q1_6.txt
echo "Benchmarking selectivity 0.7"
./branch_mispred_optimal_q1_7 0.914691219 0.914691219 0.914691219 0.914691219 >> result_optimal_q1_7.txt
echo "Benchmarking selectivity 0.8"
./branch_mispred_optimal_q1_8 0.945741609 0.945741609 0.945741609 0.945741609 >> result_optimal_q1_8.txt
echo "Benchmarking selectivity 0.9"
./branch_mispred_optimal_q1_9 0.974003746 0.974003746 0.974003746 0.974003746 >> result_optimal_q1_9.txt
echo "Benchmarking selectivity 1.0"
./branch_mispred_optimal_q1_10 1 1 1 1 >> result_optimal_q1_10.txt
echo "Done"