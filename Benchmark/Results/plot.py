#! /usr/bin/python

import argparse
from compile import parse_record
from compile import read_file_to_string
import matplotlib.pyplot as plt
from matplotlib.pyplot import figure

__author__ = 'Jervis Muindi'


def get_x_points():
	"""
		Get the x-points to use in the graph plot
	"""
	ans = []
	for i in xrange(11):
		ans.append(i * 0.1)
	return ans


def check_results(results):
	if not results:
		raise ValueError('results cannot be empty')

def get_mispredict_rate(results):
	ans = []
	check_results(results)
	for result in results:
		val = result.branch_mispredict_rate
		ans.append(val)
	return ans		

def get_elapsed_time(results):
	ans = []
	check_results(results)
	for result in results:
		val = result.elapsed_time
		ans.append(val)

	return ans

def get_cpu_cyles(results):
	"""
		Gets the *PER-RECORD* cpu cycle time
	"""
	ans = []
	check_results(results)
	for result in results:
		# 100 million is the number of records processed in test benchmark
		LOOP = float(10 ** 8)
		val = result.cpu_cycles
		val /= LOOP
		ans.append(val)

	return ans

def get_ipc(results):
	ans = []
	check_results(results)
	for result in results:
		val = result.ipc
		ans.append(val)
	return ans
	

def plot_graph(x, y, ylabel, title, filename):
	if not filename:
		raise ValueError('file name must be specified')
	fig = figure()
	plt.xlabel('Combined Selectivity')
	plt.ylabel(ylabel)
	plt.plot(x, y, 'r--', x, y, 'bo')
	plt.title(title)
	fig.savefig(filename)
	


def get_graph_title(t):
	"""
		Returns the title for the graph
	"""
	return { 
		'result_nb_q1': 'Query 1 No Branch', 
		'result_nb_q2': 'Query 2 No Branch',
		'result_pand_q1': 'Query 1 Branching And',
		'result_pand_q2' : 'Query 2 Branching And',
		'result_land_q1': 'Query 1 Logical And',
		'result_land_q2' : 'Query 2 Logical And',
		'result_optimal_q1' : 'Query 1 Optimal Plan',
		'result_optimal_q2' : 'Query 2 Optimal Plan'
		 }.get(t, '')

def plot_benchmark_result(results):
	check_results(results)
	name = results[0].name.split('|')[0].strip()
	graph_title = get_graph_title(name)	

	# Plot the Mispredict rate graph
	fname = name + '_mispredict_rate.png'
	x = get_x_points()
	y = get_mispredict_rate(results)
	ylabel = '% Mispredict rate'
	plot_graph(x,y,ylabel, graph_title, fname)	


	# Plot the Elapsed Time Graph
	fname = name + '_elapsed_time.png'
	y = get_elapsed_time(results)
	ylabel = 'Elapsed Time (seconds)'
	plot_graph(x,y,ylabel,graph_title,fname)

	# Plot the Instructions Per Clock Cycle Rate
	fname = name + '_ipc.png'
	y = get_ipc(results)
	ylabel = 'Instructions Per Clock Cycle'
	plot_graph(x,y,ylabel,graph_title,fname)

def main():	
	
	parser = argparse.ArgumentParser(description='Plots compiled benchmark results')
	parser.add_argument('-f', '--file', help='Compiled Result file to generate graphs from', required=True)
	args = vars(parser.parse_args())
		
	file = args['file']

	file_string = read_file_to_string(file)
	record_marker = '===='
	records = file_string.split(record_marker)
	results = []

	for record in records:
		if not record.strip(): # skip empty records
			continue

		r = parse_record(record)
		results.append(r)


	print 'we have this many results: %d' % len(results)
	plot_benchmark_result(results)
	print 'Plot Completed'
		
if __name__ == '__main__':
	main()
