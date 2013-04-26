#! /bin/bash

# Runs query 1 Base benchmarks and appends to result files. 
echo "Running Base Benchmarks for query 1..."

echo "1/3)Running Logical And Base ..."
echo "Benchmarking selectivity 0.0"
./branch_mispred_land 0 0 0 0 >> result_land_q1_0.txt
echo "Benchmarking selectivity 0.1"
./branch_mispred_land 0.562341325 0.562341325 0.562341325 0.562341325 >> result_land_q1_1.txt
echo "Benchmarking selectivity 0.2"
./branch_mispred_land 0.668740305 0.668740305 0.668740305 0.668740305 >> result_land_q1_2.txt
echo "Benchmarking selectivity 0.3"
./branch_mispred_land 0.740082804 0.740082804 0.740082804 0.740082804 >> result_land_q1_3.txt
echo "Benchmarking selectivity 0.4"
./branch_mispred_land 0.795270729 0.795270729 0.795270729 0.795270729 >> result_land_q1_4.txt
echo "Benchmarking selectivity 0.5"
./branch_mispred_land 0.840896415 0.840896415 0.840896415 0.840896415 >> result_land_q1_5.txt
echo "Benchmarking selectivity 0.6"
./branch_mispred_land 0.880111737 0.880111737 0.880111737 0.880111737 >> result_land_q1_6.txt
echo "Benchmarking selectivity 0.7"
./branch_mispred_land 0.914691219 0.914691219 0.914691219 0.914691219 >> result_land_q1_7.txt
echo "Benchmarking selectivity 0.8"
./branch_mispred_land 0.945741609 0.945741609 0.945741609 0.945741609 >> result_land_q1_8.txt
echo "Benchmarking selectivity 0.9"
./branch_mispred_land 0.974003746 0.974003746 0.974003746 0.974003746 >> result_land_q1_9.txt
echo "Benchmarking selectivity 1.0"
./branch_mispred_land 1 1 1 1 >> result_land_q1_10.txt
echo "Logical And Done"


echo "2/3)Running Physical And Base ..."
echo "Benchmarking selectivity 0.0"
./branch_mispred_pand 0 0 0 0 >> result_pand_q1_0.txt
echo "Benchmarking selectivity 0.1"
./branch_mispred_pand 0.562341325 0.562341325 0.562341325 0.562341325 >> result_pand_q1_1.txt
echo "Benchmarking selectivity 0.2"
./branch_mispred_pand 0.668740305 0.668740305 0.668740305 0.668740305 >> result_pand_q1_2.txt
echo "Benchmarking selectivity 0.3"
./branch_mispred_pand 0.740082804 0.740082804 0.740082804 0.740082804 >> result_pand_q1_3.txt
echo "Benchmarking selectivity 0.4"
./branch_mispred_pand 0.795270729 0.795270729 0.795270729 0.795270729 >> result_pand_q1_4.txt
echo "Benchmarking selectivity 0.5"
./branch_mispred_pand 0.840896415 0.840896415 0.840896415 0.840896415 >> result_pand_q1_5.txt
echo "Benchmarking selectivity 0.6"
./branch_mispred_pand 0.880111737 0.880111737 0.880111737 0.880111737 >> result_pand_q1_6.txt
echo "Benchmarking selectivity 0.7"
./branch_mispred_pand 0.914691219 0.914691219 0.914691219 0.914691219 >> result_pand_q1_7.txt
echo "Benchmarking selectivity 0.8"
./branch_mispred_pand 0.945741609 0.945741609 0.945741609 0.945741609 >> result_pand_q1_8.txt
echo "Benchmarking selectivity 0.9"
./branch_mispred_pand 0.974003746 0.974003746 0.974003746 0.974003746 >> result_pand_q1_9.txt
echo "Benchmarking selectivity 1.0"
./branch_mispred_pand 1 1 1 1 >> result_pand_q1_10.txt
echo "Physical And Done"



echo "3/3)Running No-Branch And Base ..."
echo "Benchmarking selectivity 0.0"
./branch_mispred_nb 0 0 0 0 >> result_nb_q1_0.txt
echo "Benchmarking selectivity 0.1"
./branch_mispred_nb 0.562341325 0.562341325 0.562341325 0.562341325 >> result_nb_q1_1.txt
echo "Benchmarking selectivity 0.2"
./branch_mispred_nb 0.668740305 0.668740305 0.668740305 0.668740305 >> result_nb_q1_2.txt
echo "Benchmarking selectivity 0.3"
./branch_mispred_nb 0.740082804 0.740082804 0.740082804 0.740082804 >> result_nb_q1_3.txt
echo "Benchmarking selectivity 0.4"
./branch_mispred_nb 0.795270729 0.795270729 0.795270729 0.795270729 >> result_nb_q1_4.txt
echo "Benchmarking selectivity 0.5"
./branch_mispred_nb 0.840896415 0.840896415 0.840896415 0.840896415 >> result_nb_q1_5.txt
echo "Benchmarking selectivity 0.6"
./branch_mispred_nb 0.880111737 0.880111737 0.880111737 0.880111737 >> result_nb_q1_6.txt
echo "Benchmarking selectivity 0.7"
./branch_mispred_nb 0.914691219 0.914691219 0.914691219 0.914691219 >> result_nb_q1_7.txt
echo "Benchmarking selectivity 0.8"
./branch_mispred_nb 0.945741609 0.945741609 0.945741609 0.945741609 >> result_nb_q1_8.txt
echo "Benchmarking selectivity 0.9"
./branch_mispred_nb 0.974003746 0.974003746 0.974003746 0.974003746 >> result_nb_q1_9.txt
echo "Benchmarking selectivity 1.0"
./branch_mispred_nb 1 1 1 1 >> result_nb_q1_10.txt
echo "NoBranch Alg Done"
