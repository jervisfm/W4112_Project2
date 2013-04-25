# File: Makefile
# Simple make file to build the executable

TARGETS = branch_mispred branch_mispred_pand branch_mispred_land branch_mispred_nb
CC=gcc
CFLAGS = -g -Wall -O3 -save-temps
INCLUDE = -I/lib/modules/`uname -r`/build/include
LIBS = 

all: $(TARGETS)

branch_mispred: branch_mispred.o
	$(CC) $(CFLAGS) $(LIB_INCLUDE) -o $@ branch_mispred.o $(LIBS)

branch_mispred_pand: branch_mispred_pand.o
	$(CC) $(CFLAGS) $(LIB_INCLUDE) -o $@ branch_mispred_pand.o $(LIBS)

branch_mispred_land: branch_mispred_land.o
	$(CC) $(CFLAGS) $(LIB_INCLUDE) -o $@ branch_mispred_land.o $(LIBS)

branch_mispred_nb: branch_mispred_nb.o
	$(CC) $(CFLAGS) $(LIB_INCLUDE) -o $@ branch_mispred_nb.o $(LIBS)

.c.o:
	$(CC) $(CFLAGS) $(INCLUDE) -c -o  $*.o $<
clean:
	rm -f *.o *~ $(TARGETS)
