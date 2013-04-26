#! /bin/bash

# Compile Logical And Base result
echo "Compiling logical and base results ..."
./compile.py result_land_q1
./compile.py result_land_q2
echo "done"

# Compile Branching And base result
echo "Compile branching and base results"
./compile.py result_pand_q1
./compile.py reuslt_pand_w2
echo "done"

# Compile No-Branch Base result
echo "compile no-branch base result..."
./compile.py result_nb_q1
./compile.py result_nb_q2
echo "done"

echo "Compile optimal result q1+q2..."
# Compile Optimal Results Q1 + Q2
./compile.py result_optimal_q1
./compile.py result_optimal_q2
echo "done"

echo "All Compilations done"
