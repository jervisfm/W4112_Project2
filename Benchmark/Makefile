# File: Makefile
# Simple make file to build the executable

TARGETS = branch_mispred branch_mispred_pand branch_mispred_land branch_mispred_nb
CC=gcc
CFLAGS = -g -Wall -O3 -save-temps
INCLUDE = -I/lib/modules/`uname -r`/build/include
LIBS = 


SRCS = $(wildcard *.c)
PROGS = $(patsubst %.c,%,$(SRCS))
all: $(PROGS)

%: %.c
	$(CC) $(CFLAGS) $(LIB_INCLUDE) -o $@ $<

clean:
	rm -f *.o *.s *.i *~ $(TARGETS)
