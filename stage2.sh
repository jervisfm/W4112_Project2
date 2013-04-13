#!/usr/bin/env bash

args=("$@")

java -cp bin/ QueryDriver ${args[0]} ${args[1]}
