#! /bin/bash

echo "Getting Predicted Times..."
./predicted_runtime.py -f bq1_output.txt -s q1
./predicted_runtime.py -f bq2_output.txt -s q2

echo "All predicted times have been resolved"
