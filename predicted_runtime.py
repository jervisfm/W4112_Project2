#! /usr/bin/python

__author__ = 'Jervis Muindi'

import argparse
import source_code

def read_file_to_string(file_path):
    f = open(file_path)
    line = f.readline().strip()
    result = line
    while line:
        line = f.readline().strip()
        result += '\n'
        result += line
    return result

def get_predicted_costs(file_path):
    CODE_SPLIT = '=================================================================='
    CODE_MARKER = '------------------------------------------------------------------'

    file = read_file_to_string(file_path)

    code_sections = file.split(CODE_SPLIT)
    result = []
    for code_section in code_sections:
        if len(code_section) == 0:
            continue
        lines = code_section.split(CODE_MARKER)
	cost_string = lines[2]
	# cost_string will have format
	# cost: 123.0
	cost = float(cost_string.split(':').strip())
        result.append(cost)
    return result


def main():
    parser = argparse.ArgumentParser(description='Produces a file with the predicted runtime in number of cpu cycle for a query')
    parser.add_argument('-f','--file', help='File to read from the optimizer', required=True)
    parser.add_argument('-s','--suffix', help='suffix to append to output file', required=True)
    args = vars(parser.parse_args())

    file = args['file']
    suffix = args['suffix']
    costs = get_predicted_costs(file)
    count = 0
    f = open('predicted_runtime_cpu_cycles_%s.txt' % suffix, 'w')
    for cost in costs:
        f.write(cost)
        f.close()
    print 'Done Processing'

if __name__ == '__main__':
	main()
