#! /usr/bin/python

import argparse
from compile import parse_record
from compile import read_file_to_string

def main():	
	
	parser = argparse.ArgumentParser(description='Plots compiled benchmark results')
	
	file = 'compile_result_land_q1.txt'
	
	file_string = read_file_to_string(file)
	record_marker = '===='
	records = file_string.split(record_marker)
	results = []
	for record in records:
		if not record: # skip empty records
			continue

		r = parse_record(record)
		results.append(r)

	
	print results[]

if __name__ == '__main__':
	main()
