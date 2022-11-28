package csc242project2;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class SatChecker {
	ArrayList<Clause> sentence = new ArrayList<Clause>();
	ArrayList<Integer> symbols;
	int maxTry;
	int maxFlip;
	
	// Build a list of symbols and initialize empty assignment
	public void getSymbols() {
		ArrayList<Integer> symbols = new ArrayList<Integer>();
		for(Clause c : this.sentence) {
			for (int a : c.var) {
				if (symbols.contains(Math.abs(a))==false) {
					symbols.add(Math.abs(a));
				}
			}
		}
		this.symbols = symbols;
	}
	
	// Check the truth value of a set of clause
	public boolean plTrue(Boolean[] assignment) {
		for (Clause c : this.sentence) {
			if (c.checkLogic(assignment)==false) {
				return false;
			}
		}
		return true; 
	}
	
	//Helper function of GSAT, choose the variable to flip the boolean value, return the index of the variable.
	public int whichFlip(Boolean[] assignment) {
		// Check how many clauses the original assignment satisfies
		int baseLine = 0;
		ArrayList<Integer> record = new ArrayList<Integer>();
		for (Clause c : this.sentence) {
			if(c.checkLogic(assignment)) {
				baseLine = baseLine + 1; 
			}
		}
		// Make comparison by changing one assignment at a time
		for (int i = 0; i<assignment.length; i++) {
			Boolean[] copy = assignment.clone();
			int sat = 0;
			if (copy[i]) {
				copy[i]=false;
			}
			else {
				copy[i]=true;
			}
			for(Clause c : this.sentence) {
				if(c.checkLogic(copy)) {
					sat++;
				}
			}
			record.add(sat-baseLine);
		}
		// Find the variable change that will lead to the most satisfiable clauses
		int max = Collections.max(record);
		int index = record.indexOf(max);
		return index;
	}
	
	// Satisfiability Checker
	public boolean GSAT(int maxFlip, int maxTry) {
		Boolean[] assignment = new Boolean[this.symbols.size()];
		for (int i=0; i<=maxTry; i++) {
			// Create a randomly generated truth assignment (random restart)
			for(int a = 0; a<assignment.length; a++) {
				if(Math.random()<0.5) {
					assignment[a]=true;
				}
				else {
					assignment[a]=false;
				}
			}
			// Try n flips for each restart
			for(int j=1; j<=maxFlip; j++) {
				// If the random assignment satisfies the sentence, then there is no need to continue
				if (this.plTrue(assignment)) {
					System.out.println("The ordered assignment that satisfies the sentence is:");
					String output = Arrays.toString(assignment);
					System.out.println(output);
					return true;
				}
				else {
					int index = this.whichFlip(assignment);
					if(assignment[index]) {
						assignment[index]=false;
					}
					else {
						assignment[index]=true;
					}
				}
			}
		}
		System.out.println("No satisfying assignment found.");
		System.out.println("You may want to enter higher values for MAX-TRIES and MAX_FLIPS to find a satisfying assignment.");
		return false;
	}
	
	public void askInput() {
		Scanner sc = new Scanner(System.in);

		System.out.println("Do you want to use default value (10) for MAX-TRIES? enter Y for yes and N for no");
		String default1 = sc.next();
		if(default1.equals("Y")) {
			this.maxTry = 10;
		}
		else{
			System.out.println("Please enter the maximum amount of tries you want: ");
			this.maxTry = sc.nextInt();
		}

		System.out.println("Do you want to use default value for MAX-FLIPS? enter Y for yes and N for no");
		String default2 = sc.next();
		if(default2.equals("Y")) {
			this.maxFlip = this.symbols.size();
			System.out.println("The default value for MAX-FLIPS is "+this.maxFlip+".");
		}
		else{
			System.out.println("Please enter the maximum amount of flips you want: ");
			int maxFlip = sc.nextInt();
			this.maxFlip = maxFlip;
		}
	}

	public void getQuestion(String nameCNF) throws FileNotFoundException {
		Reader rd = new Reader();
		String[] kb = rd.readIn(nameCNF);
		String[] inputOne = kb[0].split(" 0 ");
		ArrayList<Clause> clauses = new ArrayList<Clause>();
		for (String in : inputOne) {
			clauses.add(new Clause(in));
		}
		this.sentence = clauses;
		this.getSymbols();
	}

	public static void main(String args[]) throws FileNotFoundException{
		//Question1
		System.out.println("Question 1:");
		System.out.println("------------------------------");
		SatChecker sat1 = new SatChecker();
		sat1.getQuestion("Part3-Q1.cnf");
		sat1.askInput();
		sat1.GSAT(sat1.maxFlip,sat1.maxTry);
		System.out.println("");

		//Question2
		Scanner sc1 = new Scanner(System.in);
		System.out.println("Question 2:");
		System.out.println("------------------------------");
		System.out.println("Which n-queens problem would you like to try?");
		System.out.println("Available n choice are: 4 / 8 / 12 / 16.");
		System.out.println("Enter Your Choice: ");
		String choice = sc1.next();
		int choiceNum = Integer.parseInt(choice);
		while( choiceNum!= 4 && choiceNum!= 8 &&choiceNum!= 12 &&choiceNum!= 16){
			System.out.println("Please enter an valid selection: ");
			choice = sc1.next();
			choiceNum = Integer.parseInt(choice);
		}
		SatChecker sat2 = new SatChecker();
		sat2.getQuestion("nqueens_"+choice+".cnf");
		sat2.askInput();
		sat2.GSAT(sat2.maxFlip,sat2.maxTry);
		System.out.println("");

		//Question3
		System.out.println("Question 3: quinn.cnf");
		System.out.println("------------------------------");
		SatChecker sat3 = new SatChecker();
		sat3.getQuestion("quinn.cnf");
		sat3.askInput();
		sat3.GSAT(sat3.maxFlip,sat3.maxTry);
		System.out.println("");

		System.out.println("Question 3: aim-50-1_6-yes1-4.cnf");
		System.out.println("------------------------------");
		SatChecker sat4 = new SatChecker();
		sat4.getQuestion("aim-50-1_6-yes1-4.cnf");
		sat4.askInput();
		sat4.GSAT(sat4.maxFlip,sat4.maxTry);
	}
}
