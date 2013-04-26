#! /usr/bin/python

__author__ = 'Jervis Muindi'

import argparse

def read_file_to_string(file_path):
    f = open(file_path)
    line = f.readline().strip()
    result = line
    while line:
        line = f.readline().strip()
        result += '\n'
        result += line
    return result




def main():
    parser = argparse.ArgumentParser(description='Generates C-program source code that measures branch mispredictions from a file listing optimal plans for various selectivities')
    parser.add_argument('-f','--file', help='File to read', required=True)
    args = vars(parser.parse_args())

    file = args['file']
    print read_file_to_string(file)


if __name__ == '__main__':
	main()
