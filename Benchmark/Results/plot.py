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

def get_ipc(results):
	ans = []
	check_results(results)
	for result in results:
		val = result.ipc
		ans.append(val)
	return ans
	

def plot_benchmark_result(results):
	title = ''
	if results:
		title = results[0].name.split('|')[1].strip()
	for result in results:
		


def main():	
	
	parser = argparse.ArgumentParser(description='Plots compiled benchmark results')
	
	file = 'compiled_result_land_q1.txt'
	
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
	print results

if __name__ == '__main__':
	main()
