#! /usr/bin/python

__author__ = 'Jervis Muindi'

import argparse

def main():
    parser = argparse.ArgumentParser(description='Benchmarks the selectivties in query 1')
    parser.add_argument('-f','--file', help='Selectivity query file to read', required=True)
    args = vars(parser.parse_args())

    file = args['file']




if __name__ == '__main__':
    main()