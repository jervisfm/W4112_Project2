#! /bin/bash

# Runs only the q3 benchmarks again. 
echo "Running Q3 benchmarks in one go....."
./b3.sh
./b3_base.sh
echo "All Q3 Benchmarks have completed"
