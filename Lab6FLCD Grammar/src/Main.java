import model.Grammar;
import model.Parser;

public class Main {

    public static void main(String[] args) {

        Grammar g = new Grammar("./Files/g3.txt");

        System.out.println("Set of non terminals:\n"+g.getNonTerminals()+"\n");
        System.out.println("Set of terminals: \n"+g.getTerminals()+"\n");
        System.out.println("Set of productions: \n"+g.getProductions()+"\n");

        System.out.println("Productions for S: " + g.getProductionsForNonTerminal("S"));
        System.out.println("Productions for A: " + g.getProductionsForNonTerminal("A"));
        System.out.println("Productions for B: " + g.getProductionsForNonTerminal("B"));

        System.out.println("\nCFG check: \n" + g.isCFG());

        Parser parser = new Parser(g, "x1 c c s s a");
        parser.descendingRecursiveParsing();
    }
}
