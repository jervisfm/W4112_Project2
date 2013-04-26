#! /bin/bash

# Runs query 2 benchmarks and appends to result files. 
echo "Running Benchmarks..."
echo "1/3)Running Logical And Base ..."
echo "Benchmarking selectivity 0.0"
./branch_mispred_land 0 0 0 0 >> result_land_q2_0.txt
echo "Benchmarking selectivity 0.1"
./branch_mispred_land 0.590 0.536 0.585 0.541 >> result_land_q2_1.txt
echo "Benchmarking selectivity 0.2"
./branch_mispred_land 0.702 0.637 0.695 0.643 >> result_land_q2_2.txt
echo "Benchmarking selectivity 0.3"
./branch_mispred_land 0.777 0.705 0.770 0.712 >> result_land_q2_3.txt
echo "Benchmarking selectivity 0.4"
./branch_mispred_land 0.835 0.757 0.827 0.765 >> result_land_q2_4.txt
echo "Benchmarking selectivity 0.5"
./branch_mispred_land 0.883 0.801 0.875 0.809 >> result_land_q2_5.txt
echo "Benchmarking selectivity 0.6"
./branch_mispred_land 0.924 0.838 0.915 0.846 >> result_land_q2_6.txt
echo "Benchmarking selectivity 0.7"
./branch_mispred_land 0.960 0.871 0.951 0.880 >> result_land_q2_7.txt
echo "Benchmarking selectivity 0.8"
./branch_mispred_land 0.993 0.901 0.984 0.909 >> result_land_q2_8.txt
echo "Benchmarking selectivity 0.9"
./branch_mispred_land 0.993 0.955 0.984 0.964 >> result_land_q2_9.txt
echo "Benchmarking selectivity 1.0"
./branch_mispred_land 1 1 1 1 >> result_land_q2_10
echo "Done"


echo "2/3)Running Physical And Base ..."
echo "Benchmarking selectivity 0.0"
./branch_mispred_pand 0 0 0 0 >> result_pand_q2_0.txt
echo "Benchmarking selectivity 0.1"
./branch_mispred_pand 0.590 0.536 0.585 0.541 >> result_pand_q2_1.txt
echo "Benchmarking selectivity 0.2"
./branch_mispred_pand 0.702 0.637 0.695 0.643 >> result_pand_q2_2.txt
echo "Benchmarking selectivity 0.3"
./branch_mispred_pand 0.777 0.705 0.770 0.712 >> result_pand_q2_3.txt
echo "Benchmarking selectivity 0.4"
./branch_mispred_pand 0.835 0.757 0.827 0.765 >> result_pand_q2_4.txt
echo "Benchmarking selectivity 0.5"
./branch_mispred_pand 0.883 0.801 0.875 0.809 >> result_pand_q2_5.txt
echo "Benchmarking selectivity 0.6"
./branch_mispred_pand 0.924 0.838 0.915 0.846 >> result_pand_q2_6.txt
echo "Benchmarking selectivity 0.7"
./branch_mispred_pand 0.960 0.871 0.951 0.880 >> result_pand_q2_7.txt
echo "Benchmarking selectivity 0.8"
./branch_mispred_pand 0.993 0.901 0.984 0.909 >> result_pand_q2_8.txt
echo "Benchmarking selectivity 0.9"
./branch_mispred_pand 0.993 0.955 0.984 0.964 >> result_pand_q2_9.txt
echo "Benchmarking selectivity 1.0"
./branch_mispred_pand 1 1 1 1 >> result_pand_q2_10.txt
echo "Done"