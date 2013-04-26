#! /bin/bash

# Runs all the benchmarks in one go. 
echo "Running ALL benchmarks in one go....."
./b1.sh
./b2.sh
./b1_base.sh
./b2_base.sh
echo "All Benchmarks have completed"