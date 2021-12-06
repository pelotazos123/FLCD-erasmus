import model.Grammar;

public class Main {

    public static void main(String[] args) {

        Grammar g = new Grammar("./Files/g1.txt");

        System.out.println("Set of non terminals:\n"+g.getNonTerminals()+"\n");
        System.out.println("Set of terminals: \n"+g.getTerminals()+"\n");
        System.out.println("Set of productions: \n"+g.getProductions()+"\n");

        System.out.println("Productions for S: " + g.getProductionsForNonTerminal("S"));
        System.out.println("Productions for A: " + g.getProductionsForNonTerminal("A"));
        System.out.println("Productions for B: " + g.getProductionsForNonTerminal("B"));

        System.out.println("\nCFG check: \n" + g.isCFG());
    }
}
