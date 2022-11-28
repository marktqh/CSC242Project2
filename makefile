
all: 


.java:
	javac csc242project2/*.java -d bin

ModelChecker: .java
	java -cp bin csc242project2.ModelChecker

SatChecker: .java
	java -cp bin csc242project2.SatChecker
