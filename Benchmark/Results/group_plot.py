#! /usr/bin/python

__author__ = 'Jervis Muindi'


import argparse
from compile import parse_record
from compile import read_file_to_string
import matplotlib.pyploy as plt
from matplotlib.pyplot import figure
from plot import get_graph_title





def main():

	parser = argparse.ArgumentParser(description='Group Plots from compiled results')
	
	

	files_to_plot = ['compiled_result_nb_q1.txt', 'compiled_result_nb_q2.txt', 
			 'compiled_result_land_q1.txt', 'compiled_result_land_q2.txt', 
			 'compiled_result_pand_q1.txt', 'compiled_result_pand_q2.txt',
			 'compiled_result_optimal_q1.txt', 'compiled_result_optiomal_q2.txt']
	file_strings = []

	dict = {}
	record_marker = '===='
	for file in files_to_plot:
		file_string = read_file_to_string(file)
		records = file_string.split(record_marker)
		results = []
		
		for record in records:
			if not record.strip(): # skip empty records
				continue
			r = parse_record(record)
			results.append(r)
		key = file[:-4] # skip the extension
		dict[key] = results
	
	print 'Our dict of size == %d' % len(dict)
	

	

	
	

	


	pass

if __name__ == '__main__':
	main()
