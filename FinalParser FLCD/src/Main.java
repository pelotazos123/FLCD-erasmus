import model.Grammar;
import model.Parser;

import java.io.*;

/**
 * Main class maded for starting the app and printing the grammar info
 */
public class Main {

    public static void main(String[] args) {

        Grammar g = new Grammar("./Files/g1.txt"); // Creates the grammar with the given file

        System.out.println("Set of non terminals:\n"+g.getNonTerminals()+"\n"); // Prints all the info about it
        System.out.println("Set of terminals: \n"+g.getTerminals()+"\n");
        System.out.println("Set of productions: \n"+g.getProductions()+"\n");

        System.out.println("Productions for S: " + g.getProductionsForNonTerminal("S"));
        System.out.println("Productions for A: " + g.getProductionsForNonTerminal("A"));
        System.out.println("Productions for B: " + g.getProductionsForNonTerminal("B"));

        System.out.println("\nCFG check: \n" + g.isCFG()); // CFG check

        Parser parser = new Parser(g, getSequence(), false); // Creates the parser to check the sequence recieved from the given file
        parser.descendingRecursiveParsing(); // Calls the algorithm for parsing it
    }

    /**
     * Method for reading the file containing the sequence
     * @return
     */
    public static String getSequence(){
        String sequence = "";
        BufferedReader br = null;
        try{
            br = new BufferedReader(new FileReader("./Files/seq.txt"));

            String line = br.readLine();

            while(line != null && ! line.equals("")){
                sequence += line + " ";
                line = br.readLine();
            }
            return sequence;
        }
        catch (IOException e){
            System.err.println(e.getMessage());
        }

        return sequence;
    }
}
