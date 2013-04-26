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

def get_optimal_codes(file_path):
    CODE_SPLIT = '=================================================================='
    CODE_MARKER = '------------------------------------------------------------------'

    file = read_file_to_string(file_path)

    code_sections = file.split(CODE_SPLIT)
    result = []
    for code_section in code_sections:
        if len(code_section) == 0:
            continue
        lines = code_section.split(CODE_MARKER)
        result.append(lines[1])
    return result

def make_branch_mispred_source(code):
    return "%s \n%s\n %s" % (source_code.SOURCE_START, code, source_code.SOURCE_END)

def main():
    parser = argparse.ArgumentParser(description='Generates C-program source code that measures branch mispredictions from a file listing optimal plans for various selectivities')
    parser.add_argument('-f','--file', help='File to read', required=True)
    parser.add_argument('-s','--suffix', help='Suffix to add to filename of generated code', required=True)
    args = vars(parser.parse_args())

    file = args['file']
    suffix = args['suffix']
    codes = get_optimal_codes(file)
    count = 0
    for code in codes:
        print 'Processing optimal code #%d...' % count
        new_code = make_branch_mispred_source(code)
        f = open("branch_mispred_optimal_%s_%d.c" % (suffix,count), 'w')
        f.write(new_code)
        f.close()
        count += 1
    print 'Done Processing'

if __name__ == '__main__':
	main()