package csc242project2;

import java.util.ArrayList;

public class Clause {
    ArrayList<Integer> var;
    //public static Boolean[] logicList; this attribute is moved to Class ModelChecker and is renamed as assignment.
    Boolean isLogical;
    
    // Generate a clause based on string inputs (In file reading, 0 is used for clause separation)
    public Clause(String input){
        this.var = new ArrayList<Integer>();
        String[] in = input.split(" ");
        for(String c : in){
            if(!c.equals(" ")&&!c.equals("0")&&!c.equals("")) {
                this.var.add((Integer.parseInt(c)));
            }
        }
    }

    // Print a clause using integer representation and {}
    public void showClause(){
        if(this.var.size()<=1){
            System.out.print("{" + var.get(0) + "}");
        }else {
            System.out.print("{" + var.get(0) + ",");
            for (int i = 1; i < this.var.size() - 1; i++) {
                System.out.print(this.var.get(i) + ",");
            }
            System.out.print(var.get(this.var.size() - 1) + "}");
        }
        System.out.println("");
    }
    
    // Check whether a clause is true or false
    public boolean checkLogic(Boolean[] assignment){
    	for (int i : this.var) {
    		if (i>0) {
    			// The difference between index of the variable and the index of its corresponding logic is 1
    			if(assignment[i-1]==true) {
    				return true;
    			}
    			else {
    				continue;
    			}
    		}
    		else {
    			if(assignment[-i-1]==false) {
    				return true; 
    			}
    			else {
    				continue;
    			}	
    		}
    	}
    	return false;
    }
}
