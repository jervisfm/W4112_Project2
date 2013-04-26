#! /usr/bin/python

__author__ = 'Jervis Muindi'

class Result:
	def __init__(self):
		self.elapsed_time = 0
		self.cpu_cycles = 0
		self.instructions = 0
		self.ipc = 0
		self.branch_misses = 0
		self.branch_instructions = 0
		self.branch_mispredict_rate = 0

	

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


def main():
	
	print "hi you"
	p = Person()
	p2 = Person()
	p.name = 'Jervis'
	p.age = 17
	p3 = p + p2
	print p3

if __name__ == '__main__':
	main()

