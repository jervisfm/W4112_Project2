#! /usr/bin/python

__author__ = 'Jervis Muindi'


import argparse
from compile import parse_record
from compile import read_file_to_string

import matplotlib.pyplot as plt
from matplotlib.pyplot import figure
from plot import get_graph_title
from plot import get_x_points
from plot import get_mispredict_rate
from plot import get_elapsed_time
from plot import get_ipc


def get_dict_from_compiled_files(files_to_plot): 
	"""
		Returns a dictionary of compiled_file -> All Result records
		from the list of the given files
	"""
	if not files_to_plot:
		raise ValueError('files cannot be None')
	
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
	return dict



def init_fig(xlabel, ylabel, title):
	fig = figure()
	plt.xlabel(xlabel)
	plt.ylabel(ylabel)
	plt.title(title)
	return fig



def plot_combined_results(x, y_land, y_nb, y_pand, y_opt):
	plt.plot(x, y_land, 'ro--', markersize=10, label='Logical And')
        plt.plot(x, y_nb, 'g2--', markersize=20, markeredgewidth=2, label='No Branch')
        plt.plot(x, y_pand, 'b+--', markersize=20, markeredgewidth=2, label='Branching And')
        plt.plot(x, y_opt, 'k', markersize=5, markeredgewidth=5, linewidth=3, label='Optimized Plan')
        plt.legend()
 

def plot_mispredict_rate(dict):

        # Query 1
        title = 'Branch MisPrediction Rate Query 1'
        ylabel = '% Branch Misprediction Rate'
        xlabel = 'Combined Selectivity'
        fig = init_fig(xlabel, ylabel, title)
        fname = 'combined_graph_q1_mispredict_rate.png'

        # Get the X-Points
        x = get_x_points()

        # Get the Y-Points
        y_land_q1 = get_mispredict_rate(dict['compiled_result_land_q1'])
        y_nb_q1 = get_mispredict_rate(dict['compiled_result_nb_q1'])
        y_pand_q1 = get_mispredict_rate(dict['compiled_result_pand_q1'])
        y_opt_q1 = get_mispredict_rate(dict['compiled_result_optimal_q1'])

        #Plot the Combined Results
        plot_combined_results(x, y_land_q1, y_nb_q1, y_pand_q1, y_opt_q1)
        fig.savefig(fname)


        # Query 2
        title = 'Branch MisPrediction Rate Query 2'
        fig = init_fig(xlabel, ylabel, title)
        fname = 'combined_graph_q2_mispredict_rate.png'

        # Get the Y-Points
        y_land_q2 = get_mispredict_rate(dict['compiled_result_land_q2'])
        y_nb_q2 = get_mispredict_rate(dict['compiled_result_nb_q2'])
        y_pand_q2 = get_mispredict_rate(dict['compiled_result_pand_q2'])
        y_opt_q2 = get_mispredict_rate(dict['compiled_result_optimal_q2'])

        #Plot the Combined Results
        plot_combined_results(x, y_land_q2, y_nb_q2, y_pand_q2, y_opt_q2)
        fig.savefig(fname)

def plot_elapsed_time(dict):
	
	# Query 1
	title = 'Elapsed Time Query 1'
	ylabel = 'Elapsed Time (seconds)'
	xlabel = 'Combined Selectivity'
	fig = init_fig(xlabel, ylabel, title)
	fname = 'combined_graph_q1_elapsed_time.png'
	
	# Get the X-Points
	x = get_x_points()

	# Get the Y-Points
 	y_land_q1 = get_elapsed_time(dict['compiled_result_land_q1'])
	y_nb_q1 = get_elapsed_time(dict['compiled_result_nb_q1'])
	y_pand_q1 = get_elapsed_time(dict['compiled_result_pand_q1'])	
	y_opt_q1 = get_elapsed_time(dict['compiled_result_optimal_q1'])

	#Plot the Combined Results	
	plot_combined_results(x, y_land_q1, y_nb_q1, y_pand_q1, y_opt_q1)
	fig.savefig(fname)
	

	# Query 2
	title = 'Elapsed Time QQuery 2'
	fig = init_fig(xlabel, ylabel, title)
	fname = 'combined_graph_q2_elapsed_time.png'

	# Get the Y-Points
        y_land_q2 = get_elapsed_time(dict['compiled_result_land_q2'])
        y_nb_q2 = get_elapsed_time(dict['compiled_result_nb_q2'])
        y_pand_q2 = get_elapsed_time(dict['compiled_result_pand_q2'])
        y_opt_q2 = get_elapsed_time(dict['compiled_result_optimal_q2'])

        #Plot the Combined Results
        plot_combined_results(x, y_land_q2, y_nb_q2, y_pand_q2, y_opt_q2)
        fig.savefig(fname)

	
	
		

def main():

	parser = argparse.ArgumentParser(description='Group Plots from compiled results')
	
	

	files_to_plot = ['compiled_result_nb_q1.txt', 'compiled_result_nb_q2.txt', 
			 'compiled_result_land_q1.txt', 'compiled_result_land_q2.txt', 
			 'compiled_result_pand_q1.txt', 'compiled_result_pand_q2.txt',
			 'compiled_result_optimal_q1.txt', 'compiled_result_optimal_q2.txt']

	


	dict = get_dict_from_compiled_files(files_to_plot)	
	

	# Make the Combined Group Plots

	print 'Making Combined Group graps...'
	# Plot All MisPredict Rate Graphs Together
	plot_mispredict_rate(dict)

	# Plot All ELapsed Time Graphs Together
	plot_elapsed_time(dict)

	# Plot the Instruction Per Clock Cycle Together
	

	print 'Our dict of size == %d' % len(dict)
	#print dict

	

	
	

	


	pass

if __name__ == '__main__':
	main()
