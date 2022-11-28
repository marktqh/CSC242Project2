package csc242project2;

import java.io.*;

import java.io.*;
import java.util.ArrayList;

public class Reader {
    public String[] readIn(String path) throws FileNotFoundException {
        String[] output = new String[3];
        String input = "";
        String numOfClause = "";
        String numOfVar = "";
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(path)); //input
            String entry = reader.readLine();
            while (entry != null) {
                if(!entry.startsWith("c")) {
                    if(entry.startsWith("p")){
                        String[] temp = entry.split(" ");
                        numOfClause = temp[3];
                        numOfVar = temp[2];
                    }else{
                        input = input+entry+" ";
                    }
                }
                entry = reader.readLine();

            }
            output[0] = input.substring(0,input.length()-1);
            output[1] = numOfClause;
            output[2] = numOfVar;
            return output;
        }
        catch (Exception e) {
            System.out.println("The file can not be found");
            return output;
        }
    }
}


