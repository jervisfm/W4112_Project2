#! /usr/bin/python

__author__ = 'Jervis Muindi'

import argparse
from result import Result


def read_file_to_string(file_path):
	f = open(file_path)
	line = f.readline().strip()
	result = line

	while line:
        	line = f.readline()
        	result += line
	f.close()
	return result

def compile_result_from_file(file):
	""" 
		Compile all the results in the given file
		to a single record by averaging all the values 
		found
	"""
	records_string = read_file_to_string(file)
	MARKER = "Loop start!\nLoop stop!\n"
	
	records = records_string.split(MARKER)
	r = Result()
	record_count = 0
	for record in records:
		if not record: # Skip empty records
			continue
		items = record.split('\n')
		for item in items:
			if 'elapsed time' in item.lower():
				# Format is like this
				# elapsed time : 123123 seconds
				num = float(item.split(':')[1].strip().split(' ')[0])
				r.elapsed_time += num
			elif 'cpu cycles' in item.lower():
				# Format is like this
				# cpu cycles : 123123
				num = float(item.split(':')[1])
				r.cpu_cycles += num
			# This condition should be above the instructions
			# case to ensure proper parsing
			elif 'branch instructions' in item.lower():
				num = float(item.split(':')[1])
				r.branch_instructions += num
			elif 'ipc' in item.lower():
				num = float(item.split(':')[1])
				r.ipc += num
			elif 'branch misses' in item.lower():
				num = float(item.split(':')[1])
				r.branch_misses += num
			elif 'instructions' in item.lower():
				num = float(item.split(':')[1])
				r.instructions += num
			elif 'branch mispred' in item.lower():
				# Format is like this
				# branch mispred rate: 1.12312%
				percentage = item.split(':')[1]
				num = float(percentage.strip()[:-1]) # remove percentage
				r.branch_mispredict_rate += num				
			else:
				pass
		
		record_count += 1
	print 'We found no of  Records = %s' % record_count
	# Average out the results
	r.elapsed_time /= float(record_count)
	r.cpu_cycles /= float(record_count)
	r.instructions /= float(record_count)
	r.ipc /= float(record_count)
	r.branch_misses /= float(record_count)
	r.branch_instructions /= float(record_count)
	r.branch_mispredict_rate /= float(record_count)
	return r

def compile_benchmark(file_prefix):
	"""
		Compiles all results from a given benchmark 
		into summarized records. 
		Returns a string of the summarized records
	"""
	
	NUM = 11 # We have records 0 ... 10
	result = ''
	for i in xrange(NUM):
		file = file_prefix + '_' + str(i) + '.txt'

		compiled_record = compile_result_from_file(file)
		if i == 10:
			compiled_record.name = file_prefix + ' | ' + 'Combined Selectivity 1.0'
		else:
			compiled_record.name = file_prefix + ' | ' + 'Combined Selectivity 0.%d' % i
		result += compiled_record.__str__()
		result += '\n====\n'
	return result
		

def main():
	print "hi you"
	file =  'result_land_q1_0.txt' 
	file_prefix = 'result_land_q1'

	parser = argparse.ArgumentParser(description='Compiles benchmark results together to 1 file')
	parser.add_argument('-p', '--prefix', help='name of file prefix to generate compiled results from', required=True)
	args = vars(parser.parse_args())

	file_prefix = args['prefix']
	print 'Compiling benchmark %s' % file_prefix
	compiled_results = compile_benchmark(file_prefix)
	compiled_filename = 'compiled_' + file_prefix + '.txt'
	f = open(compiled_filename, 'w')
	f.write(compiled_results)
	f.close()
	print 'Compilation complete: Wrote result to %s'% compiled_filename

if __name__ == '__main__':
	main()

