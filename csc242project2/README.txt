a) Collaborators:
	1) Jiarui Chen (jchen134)
	2) Qihang Tang (qtang5)
	
b) How to build and run the project:
	1) Download and uncompress the project2.zip file
	2) Open the terminal and enter the directory where the compressed project file is stored by using the command "cd"
	3) Your file, which stores all the .java files, is named by "csc242project2", and you should not change this name.
	4) run "javac *.java" to compile all the .java file
	5) run "cd .." to go back to directory above since a package is created for the java project
	6) To test part2, please run java csc242project2.ModelChecker. For part3, please run java csc242project2.SatChecker
	
	Note: 
	For part2, no user input is required. For each question in Part2, we created cnf files separately for the
	knowledge base and the query and included them in the same package along with the code. For Question 2, Part 2, the cnf 
	file or both knowledge base and the query only include the clauses for the initial location and the first query, we then
	update the knowledge base and change the query by code in the main method. For Question 3, Part 2, there is a cnf file 
	for each query.
	
	For part3, you will need to give input to set the MAX-FLIPS and MAX-TRIES, and for question 2, additionally, you will need
	to choose from 4queens, 8queens, 12queens, and 16queens to test the satisfiability. Again, all the needed cnf files are included
	in the same package along with the code, so you won't need to enter any file path. 

c) Correspondence	
	Here is a list of the cnf file names and their corresponding questions.
	File Name						Question
	Q1-KB.cnf						Part 2, Question 1, Knowledge Base
	Q1-alpha.cnf					Part 2, Question 1, Query
	Q2-KB.cnf						Part 2, Question 2, Knowledge Base
	Q2-alpha.cnf					Part 2, Question 2, Query
	Q3-KB.cnf						Part 2, Question 3, Knowledge Base
	Q3-alpha1.cnf					Part 2, Question 3(a), Query
	Q3-alpha2.cnf					Part 2, Question 3(b), Query
	Q3-alpha3.cnf					Part 2, Question 3(c), Query
	Part3-Q1.cnf					Part 3, Question 1
	nqueens_4 (8/12/16).cnf			Part 3, Question 2
	quinn.cnf						Part 3, Question 3
	aim-50-1_6-yes1-4.cnf			Part 3, Question 3
	
	Here is a list of numbers in cnf files and their corresponding variables.
	File Name						Correspondence
	Q1-KB.cnf						1: P
									2: Q					
	Q2-KB.cnf						1: P1,1
									2: P1,2
									3: P1,3
									4: P2,1
									5: P2,2
									6: P3,1
									7: B1,1
									8: B1,2
									9: B2,1
	Q3-KB.cnf						1: Mythical
									2: Mortal
									3: Mammal
									4: Horned
									5: Magical
	Part3-Q1.cnf					1: x1
									2: x2
									3: x3
									4: x4
	
	