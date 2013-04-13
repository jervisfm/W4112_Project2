all:
	mkdir -p bin/
	javac src/*.java -d bin/

.PHONY: clean

clean:
	rm -f bin/*.class
