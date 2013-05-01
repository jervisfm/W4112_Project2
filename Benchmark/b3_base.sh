#! /bin/bash

# Runs query 3 BASE benchmarks and appends to result files. 
echo "Running BASE Benchmarks for query 3..."
echo "1/3)Running Logical And Base ..."
echo "Benchmarking selectivity 0.0"
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

./branch_mispred_land $P0 >> result_land_q3_0.txt
echo "Benchmarking selectivity 0.01"
./branch_mispred_land $P1 >> result_land_q3_1.txt
echo "Benchmarking selectivity 0.02"
./branch_mispred_land $P2 >> result_land_q3_2.txt
echo "Benchmarking selectivity 0.03"
./branch_mispred_land $P3 >> result_land_q3_3.txt
echo "Benchmarking selectivity 0.04"
./branch_mispred_land $P4 >> result_land_q3_4.txt
echo "Benchmarking selectivity 0.05"
./branch_mispred_land $P5 >> result_land_q3_5.txt
echo "Benchmarking selectivity 0.06"
./branch_mispred_land $P6 >> result_land_q3_6.txt
echo "Benchmarking selectivity 0.07"
./branch_mispred_land $P7 >> result_land_q3_7.txt
echo "Benchmarking selectivity 0.08"
./branch_mispred_land $P8 >> result_land_q3_8.txt
echo "Benchmarking selectivity 0.09"
./branch_mispred_land $P9 >> result_land_q3_9.txt
echo "Benchmarking selectivity 0.1"
./branch_mispred_land $P10 >> result_land_q3_10.txt
echo "Done"


echo "2/3)Running Physical And Base ..."
./branch_mispred_pand $P0 >> result_pand_q3_0.txt
echo "Benchmarking selectivity 0.01"
./branch_mispred_pand $P1 >> result_pand_q3_1.txt
echo "Benchmarking selectivity 0.02"
./branch_mispred_pand $P2 >> result_pand_q3_2.txt
echo "Benchmarking selectivity 0.03"
./branch_mispred_pand $P3 >> result_pand_q3_3.txt
echo "Benchmarking selectivity 0.04"
./branch_mispred_pand $P4 >> result_pand_q3_4.txt
echo "Benchmarking selectivity 0.05"
./branch_mispred_pand $P5 >> result_pand_q3_5.txt
echo "Benchmarking selectivity 0.06"
./branch_mispred_pand $P6 >> result_pand_q3_6.txt
echo "Benchmarking selectivity 0.07"
./branch_mispred_pand $P7 >> result_pand_q3_7.txt
echo "Benchmarking selectivity 0.08"
./branch_mispred_pand $P8 >> result_pand_q3_8.txt
echo "Benchmarking selectivity 0.09"
./branch_mispred_pand $P9 >> result_pand_q3_9.txt
echo "Benchmarking selectivity 0.1"
./branch_mispred_pand $P10 >> result_pand_q3_10.txt
echo "Done"


echo "3/3)Running No-Branch Algorithm ..."
./branch_mispred_nb $P0 >> result_nb_q3_0.txt
echo "Benchmarking selectivity 0.01"
./branch_mispred_nb $P1 >> result_nb_q3_1.txt
echo "Benchmarking selectivity 0.02"
./branch_mispred_nb $P2 >> result_nb_q3_2.txt
echo "Benchmarking selectivity 0.03"
./branch_mispred_nb $P3 >> result_nb_q3_3.txt
echo "Benchmarking selectivity 0.04"
./branch_mispred_nb $P4 >> result_nb_q3_4.txt
echo "Benchmarking selectivity 0.05"
./branch_mispred_nb $P5 >> result_nb_q3_5.txt
echo "Benchmarking selectivity 0.06"
./branch_mispred_nb $P6 >> result_nb_q3_6.txt
echo "Benchmarking selectivity 0.07"
./branch_mispred_nb $P7 >> result_nb_q3_7.txt
echo "Benchmarking selectivity 0.08"
./branch_mispred_nb $P8 >> result_nb_q3_8.txt
echo "Benchmarking selectivity 0.09"
./branch_mispred_nb $P9 >> result_nb_q3_9.txt
echo "Benchmarking selectivity 0.1"
./branch_mispred_nb $P10 >> result_nb_q3_10.txt
echo "Done"

echo "Completely Done"
