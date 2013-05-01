#! /bin/bash

# Runs query 3 benchmarks and appends to result files. 
echo "Running Optimal Benchmarks for Query 3..."

P0="0 0 0 0"
P1="0.316227766 0.316227766 0.316227766 0.316227766"
P2="0.376060309 0.376060309 0.376060309 0.376060309"
P3="0.416179145 0.416179145 0.416179145 0.416179145"
P4="0.447213595 0.447213595 0.447213595 0.447213595"
P5="0.472870805 0.472870805 0.472870805 0.472870805"
P6="0.4949232 0.4949232 0.4949232 0.4949232"
P7="0.514368672 0.514368672 0.514368672 0.514368672"
P8="0.53182959 0.53182959 0.53182959 0.53182959"
P9="0.547722558 0.547722558 0.547722558 0.547722558"
P10="0.562341325 0.562341325 0.562341325 0.562341325"

echo "Benchmarking selectivity 0.0"
./branch_mispred_optimal_q3_0 $P0 >> result_optimal_q3_0.txt
echo "Benchmarking selectivity 0.01"
./branch_mispred_optimal_q3_1 $P1 >> result_optimal_q3_1.txt
echo "Benchmarking selectivity 0.02"
./branch_mispred_optimal_q3_2 $P2 >> result_optimal_q3_2.txt
echo "Benchmarking selectivity 0.03"
./branch_mispred_optimal_q3_3 $P3 >> result_optimal_q3_3.txt
echo "Benchmarking selectivity 0.04"
./branch_mispred_optimal_q3_4 $P4 >> result_optimal_q3_4.txt
echo "Benchmarking selectivity 0.05"
./branch_mispred_optimal_q3_5 $P5 >> result_optimal_q3_5.txt
echo "Benchmarking selectivity 0.06"
./branch_mispred_optimal_q3_6 $P6 >> result_optimal_q3_6.txt
echo "Benchmarking selectivity 0.07"
./branch_mispred_optimal_q3_7 $P7 >> result_optimal_q3_7.txt
echo "Benchmarking selectivity 0.08"
./branch_mispred_optimal_q3_8 $P8 >> result_optimal_q3_8.txt
echo "Benchmarking selectivity 0.09"
./branch_mispred_optimal_q3_9 $P9 >> result_optimal_q3_9.txt
echo "Benchmarking selectivity 0.10"
./branch_mispred_optimal_q3_10 $P10 >> result_optimal_q3_10.txt
echo "Done"
