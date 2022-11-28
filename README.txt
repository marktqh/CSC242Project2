a) Collaborators:
	1) Qihang Tang
	2) Jiarui Chen
	
b) How to build and run the project:
	1) In the terminal, enter the project directory "CSC242Project2"
	2) All the code files (.java) are stored in the folder "csc242project2"
	3) Run "make ModelChecker" to compile and run the codes for model checking part
	4) Run "make SatChecker" to compile and run the codes for model checking part
	5) You can also compile by "javac" command and run the code by "java" command separately
	6) Read the project description pdf for more information
	
	Note: 
	For model checking part, no user input is required. For each question answered in model checking part, we created cnf files separately for the knowledge base and the query and included them in the same package along with the code. For model checking part, task 2, the cnf file of both knowledge base and the query only include the clauses for the initial location and the first query, we then update the knowledge base and change the query by code in the main method. 
	For satisfiability testing (SatChecker) part, there is a cnf file for each query.
	For SatChecker part, you will need to give input to set the MAX-FLIPS and MAX-TRIES, and for task 2, additionally, you will need to choose from 4queens, 8queens, 12queens, and 16queens to test the satisfiability. Again, all the needed cnf files are included in the same package along with the code, so you won't need to enter any file path. 

c) Project Description
	This is the second project of CSC242 of U of R done by group Qihang Tang and Jiarui Chen. The project focuses on representing knowledge and doing inference with propositional logic. Clause class represents clause with integers and propositional logic. In this project, clauses are stored in DIMACS CNF file (.cnf file). Reader class reads in clauses that are stored in CDF files. 

For the model checking part, we implemented ModelChecker class with TT-entail algorithm, which can check if a set of events (sentences) logically entails the other set of events. This part includes answers to 3 sample tasks, which can prove the feasibility of our implementation.
	Task 1: Show {P, P => Q} |= Q
	Task 2: The Wumpus World example from "Artificial Intelligence A Modern Approach 4th Edition" 7.2
	Task 3: If the unicorn is mythical, then it is immortal, but if it is not mythical, then it is a mortal mammal. If the unicorn is either immortal or a mammal, then it is horned. The unicorn is magical if it is horned.
		a) Can we prove that the unicorn is mythical?
		b) Can we prove that the unicorn is magical?
		c) Can we prove that the unicorn is horned?

For the satisfiability part, which tests for the existence of true and false assignments that make all events (clauses) true (satisfiable). We implemented SatChecker class with GSAT algorithm that can test for satisfiability of clauses. The part includes answers to 3 sample tasks, which can prove the feasibility of our implementation.
	Task 1: Test the satisfiability of: (x1 | x3 | !x4) & (x4) & (x2 | !x3)
	Task 2: N-queens problem for N up to 16 (can be tested for 8, 12, and 16)
	Task 3: Test on the quinn.cnf and aim-50-1 6-yes1-4.cnf example from John Burkhardt’s repository

d) Correspondence	
	Here is a list of the cnf file names and their corresponding questions.
	File Name					Question
	Q1-KB.cnf					Model Checking, Task 1, Knowledge Base
	Q1-alpha.cnf					Model Checking, Task 1, Query
	Q2-KB.cnf					Model Checking, Task 2, Knowledge Base
	Q2-alpha.cnf					Model Checking, Task 2, Query
	Q3-KB.cnf					Model Checking, Task 3, Knowledge Base
	Q3-alpha1.cnf					Model Checking, Task 3(a), Query
	Q3-alpha2.cnf					Model Checking, Task 3(b), Query
	Q3-alpha3.cnf					Model Checking, Task 3(c), Query
	Part3-Q1.cnf					Satisfiability Testing, Task
	nqueens_4 (8/12/16).cnf				Satisfiability Testing, Task 2
	quinn.cnf					Satisfiability Testing, Task 3
	aim-50-1_6-yes1-4.cnf				Satisfiability Testing, Task 3
	
	Here is a list of numbers in cnf files and their corresponding variables.
	File Name					Correspondence
	Q1-KB.cnf					1: P
							2: Q					
	Q2-KB.cnf					1: P1,1
							2: P1,2
							3: P1,3
							4: P2,1
							5: P2,2
							6: P3,1
							7: B1,1
							8: B1,2
							9: B2,1
	Q3-KB.cnf					1: Mythical
							2: Mortal
							3: Mammal
							4: Horned
							5: Magical
	Part3-Q1.cnf					1: x1
							2: x2
							3: x3
							4: x4
	
	