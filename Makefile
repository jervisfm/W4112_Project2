CLASSPATH="lib/*:."

all: algorithm

algorithm: setup
	javac -cp $(CLASSPATH) src/*.java -d bin/

.PHONY: clean test setup

setup:
	@mkdir -p bin/ lib/

test:
	java -cp bin/:$(CLASSPATH) org.junit.runner.JUnitCore UnitTests

clean:
	rm -f bin/*.class
