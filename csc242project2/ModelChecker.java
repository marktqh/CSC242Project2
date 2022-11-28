package csc242project2;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

public class ModelChecker {
	ArrayList<Clause> knowledge;
	ArrayList<Clause> query;
	ArrayList<Integer> symbols;
	int p;
	int q;
	ArrayList<Integer> rest = new ArrayList<Integer>();

	// Build a list of symbols and initialize empty assignment
	public void getSymbols(ArrayList<Clause> knowledge, ArrayList<Clause> query) {
		ArrayList<Integer> symbols = new ArrayList<Integer>();
		for(Clause c : knowledge) {
			for (int a : c.var) {
				if (symbols.contains(Math.abs(a))==false) {
					symbols.add(Math.abs(a));
				}
			}
		}
		for (Clause c : query) {
			for (int a : c.var) {
				if (symbols.contains(Math.abs(a))==false) {
					symbols.add(Math.abs(a));
				}
			}
		}
		this.symbols = symbols;
	}

	// Implementation of TT-Entailment Algorithm
	public boolean ttEntail(ArrayList<Clause> kb, ArrayList<Clause> alpha) {
		this.getSymbols(kb, alpha);
		boolean showDetail = false;
		if(this.symbols.size()<=5) {
			showDetail = true;
		}
		Boolean[] assignment = new Boolean[this.symbols.size()];
		if (showDetail) {
			return this.ttCheckAll(kb, alpha, this.symbols, assignment);
		}
		else {
			System.out.println("In this case, the whole process of model checking is too long to display.");
			return this.ttCheckAll(kb, alpha, this.symbols, assignment, false);
		}

	}

	public boolean ttCheckAll(ArrayList<Clause> kb, ArrayList<Clause> alpha, ArrayList<Integer> symbols, Boolean[] model) {
		if (symbols.isEmpty()) {
			if(this.plTrue(kb, model)==true) {
				System.out.print("Assignment: ");
				for(Boolean b : model) {
					System.out.print(b+" ");
				}
				System.out.println();
				System.out.println("Under such assignment, the knowledge base is TRUE");
				boolean result = this.plTrue(alpha, model);
				if(result) {
					System.out.println("The query is TRUE");
					System.out.println("-----------------------------------------------");
				}
				else {
					System.out.println("The query is FALSE");
					System.out.println("-----------------------------------------------");
				}
				return result;
			}
			else {
				System.out.print("Assignment: ");
				for(Boolean b : model) {
					System.out.print(b+" ");
				}
				System.out.println();
				System.out.println("Under such assignment, the knowledge base is FALSE, no need to check the query");
				System.out.println("-----------------------------------------------");
				return true;
			}
		}
		else {
			ArrayList<Integer> symbolsCopy = new ArrayList<Integer>();
			for (int a : symbols) {
				symbolsCopy.add(a);
			}
			p = symbols.get(0);
			symbols.remove(0);
			this.rest = symbols;
			model[p-1] = true;
			boolean resultOne = this.ttCheckAll(kb, alpha, this.rest, model);
			p = symbolsCopy.get(0);
			symbolsCopy.remove(0);
			this.rest = symbolsCopy;
			model[p-1] = false;
			boolean resultTwo = this.ttCheckAll(kb, alpha, this.rest, model);
			return resultOne && resultTwo;
		}
	}


	public boolean ttCheckAll(ArrayList<Clause> kb, ArrayList<Clause> alpha, ArrayList<Integer> symbols, Boolean[] model, boolean showDetail) {
		if (symbols.isEmpty()) {
			if(this.plTrue(kb, model)==true) {
				return this.plTrue(alpha, model);
			}
			else {
				return true;
			}
		}
		else {
			ArrayList<Integer> symbolsCopy = new ArrayList<Integer>();
			for (int a : symbols) {
				symbolsCopy.add(a);
			}
			p = symbols.get(0);
			symbols.remove(0);
			this.rest = symbols;
			model[p-1] = true;
			boolean resultOne = this.ttCheckAll(kb, alpha, this.rest, model, false);
			p = symbolsCopy.get(0);
			symbolsCopy.remove(0);
			this.rest = symbolsCopy;
			model[p-1] = false;
			boolean resultTwo = this.ttCheckAll(kb, alpha, this.rest, model, false);
			return resultOne && resultTwo;
		}
	}

	public boolean plTrue(ArrayList<Clause> sentence, Boolean[] assignment) {
		for (Clause c : sentence) {
			if (c.checkLogic(assignment)==false) {
				return false;
			}
		}
		return true;
	}

	// Read the question in as formatted CNF files
	public void getQuestion(String pathKB, String pathAlpha) throws FileNotFoundException {
		Reader rd = new Reader();
		String[] kb = rd.readIn(pathKB);
		String[] inputOne = kb[0].split(" 0 ");
		ArrayList<Clause> clauses = new ArrayList<Clause>();
		for (String in : inputOne) {
			clauses.add(new Clause(in));
		}

		String[] alpha = rd.readIn(pathAlpha);
		String[] inputTwo = alpha[0].split(" 0 ");
		ArrayList<Clause> clauses2 = new ArrayList<Clause>();
		for (String in : inputTwo){
			clauses2.add(new Clause(in));
		}
		this.knowledge = clauses;
		this.query = clauses2;
	}

	//Integrate all the methods
	public void checkModel(String pathKB, String pathAlpha) throws FileNotFoundException {
		this.getQuestion(pathKB, pathAlpha);
		boolean result = this.ttEntail(this.knowledge, this.query);
		if(result) {
			System.out.println("The knowledge base ENTAILS the query");
		}
		else {
			System.out.println("The knowledge base DOES NOT ENTAIL the query");
		}
	}

	public void checkModel() {
		boolean result = this.ttEntail(this.knowledge, this.query);
		if(result) {
			System.out.println("The knowledge base ENTAILS the query");
		}
		else {
			System.out.println("The knowledge base DOES NOT ENTAIL the query");
		}
	}

	public static void main(String arg[]) throws FileNotFoundException{
		// Part 2 Question 1
		ModelChecker mc1 = new ModelChecker();
		System.out.println("Question 1:");
		System.out.println("-----------------------------------------------");
		mc1.checkModel("Q1-KB.cnf", "Q1-alpha.cnf");
		System.out.println();

		// Part 2 Question 2
		ModelChecker mc2 = new ModelChecker();
		System.out.println("Question 2:");
		System.out.println("-----------------------------------------------");
		System.out.println("The agent starts at [1,1].");
		System.out.println("-----------------------------------------------");
		System.out.println("Show that this knowledge base entails ¬P1,2");
		mc2.checkModel("Q2-KB.cnf", "Q2-alpha.cnf");
		System.out.println("-----------------------------------------------");
		mc2.query.remove(0);
		System.out.println("Show that this knowledge base entails ¬P2,1");
		mc2.query.add(new Clause("-4"));
		mc2.checkModel();
		System.out.println("-----------------------------------------------");
		mc2.query.remove(0);
		System.out.println("Show that this knowledge base does not entail P2,2");
		mc2.query.add(new Clause("5"));
		mc2.checkModel();
		System.out.println("-----------------------------------------------");
		mc2.query.remove(0);
		System.out.println("Show that this knowledge base does not entail ¬P2,2");
		mc2.query.add(new Clause("-5"));
		mc2.checkModel();
		System.out.println("Since the knowledge base neither entail P2,2 nor ¬P2,2, we can not conclude anything about P2,2");
		System.out.println("-----------------------------------------------");
		mc2.query.remove(0);
		System.out.println("The agent moves to [2,1].");
		mc2.knowledge.add(new Clause("9"));
		System.out.println("-----------------------------------------------");
		System.out.println("Show that this knowledge base entails P2,2 ∨ P3,1");
		mc2.query.add(new Clause("5 6"));
		mc2.checkModel();
		System.out.println("-----------------------------------------------");
		System.out.println("Show that this knowledge base does not entail P2,2");
		mc2.query.remove(0);
		mc2.query.add(new Clause("5"));
		mc2.checkModel();
		System.out.println("-----------------------------------------------");
		System.out.println("Show that this knowledge base does not entail ¬P2,2");
		mc2.query.remove(0);
		mc2.query.add(new Clause("-5"));
		mc2.checkModel();
		System.out.println("-----------------------------------------------");
		System.out.println("Show that this knowledge base does not entail P3,1");
		mc2.query.remove(0);
		mc2.query.add(new Clause("6"));
		mc2.checkModel();
		System.out.println("-----------------------------------------------");
		System.out.println("Show that this knowledge base does not entail ¬P3,1");
		mc2.query.remove(0);
		mc2.query.add(new Clause("-6"));
		mc2.checkModel();
		System.out.println("Since the knowledge base neither entail P2,2 nor ¬P2,2 and neither entail ¬P3,1 nor P3,1, we can not conclude anything about them");
		System.out.println("-----------------------------------------------");
		System.out.println("The agent moves to [1,2].");
		mc2.knowledge.add(new Clause("-8"));
		System.out.println("-----------------------------------------------");
		System.out.println("Show that this knowledge base entails ¬P2,2");
		mc2.query.remove(0);
		mc2.query.add(new Clause("-5"));
		mc2.checkModel();
		System.out.println("-----------------------------------------------");
		System.out.println("Show that this knowledge base entails P3,1");
		mc2.query.remove(0);
		mc2.query.add(new Clause("6"));
		mc2.checkModel();
		System.out.println();

		// Part 2 Question 3
		ModelChecker mc3 = new ModelChecker();
		System.out.println();
		System.out.println("Question 3 (a):");
		mc3.checkModel("Q3-KB.cnf", "Q3-alpha1.cnf");
		System.out.println("Therefore, we CANNOT prove that the unicorn is mythical.");
		System.out.println("");
		System.out.println("-----------------------------------------------");
		System.out.println("Question 3 (b):");
		mc3.checkModel("Q3-KB.cnf", "Q3-alpha2.cnf");
		System.out.println("Therefore, we CAN prove that the unicorn is magical.");
		System.out.println("");
		System.out.println("-----------------------------------------------");
		System.out.println("Question 3 (c):");
		mc3.checkModel("Q3-KB.cnf", "Q3-alpha3.cnf");
		System.out.println("Therefore, we CAN prove that the unicorn is horned.");
		System.out.println("");

	}
}
