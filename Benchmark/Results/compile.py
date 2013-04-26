#! /usr/bin/python

__author__ = 'Jervis Muindi'

import argparse

class Result:
	"""
		Class for storing the benchmark result
	"""
	def __init__(self):
		self.elapsed_time = 0
		self.cpu_cycles = 0
		self.instructions = 0
		self.ipc = 0
		self.branch_misses = 0
		self.branch_instructions = 0
		self.branch_mispredict_rate = 0
		self.name = ''

	def __str__(self):
		result = ''
		result += 'Benchmark name: %s\n' % self.name
		result += 'Elapsed Time: %s\n' % self.elapsed_time
		result += 'Cpu Cycles: %s\n' % self.cpu_cycles
		result += 'Instructions: %s\n' % self.instructions
		result += 'IPC : %s\n' % self.ipc
		result += 'Branch Misses: %s\n' % self.branch_misses
		result += 'Branch Instructions: %s\n' % self.branch_instructions
		result += 'Branch MisPredict Rate: %s\n' % self.branch_mispredict_rate
		return result


class Person:
	def __init__(self):
		self.age = 10
		self.name = 'test name'
		self.sex = 'Male'

	def __str__(self):
		
		return 'Name:%s\nAge:%s\nSex:%s' %(self.name, self.age, self.sex)

	def __add__(self, other):
		p = Person()
		p.age = self.age + other.age
		p.name = self.name + ' | ' + other.name
		p.sex = self.sex + ' | ' + other.sex
		return p


def read_file_to_string(file_path):
	f = open(file_path)
	line = f.readline().strip()
	result = line

	while line:
        	line = f.readline().strip()
	        result += '\n'
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
	

	print records_string
	print '********'

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
			# This condition should be above the instrunctions
			# case to ensure properly parsing
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
	
def main():
	
	print "hi you"
	p = Person()
	p2 = Person()
	p.name = 'Jervis'
	p.age = 17
	p3 = p + p2
	print p3
	file =  'result_land_q1_0.txt' 
	print compile_result_from_file(file)

if __name__ == '__main__':
	main()

