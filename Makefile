CLASSPATH="lib/*:."

all: algorithm

algorithm:
	javac -cp $(CLASSPATH) src/*.java -d bin/

.PHONY: clean test

setup:
	@mkdir -p bin/ lib/

test:
	java -cp bin/:$(CLASSPATH) org.junit.runner.JUnitCore UnitTests

clean:
	rm -f bin/*.class
