package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * Parser class in charge of making the Descending Recursive Parsing algorithm
 */
public class Parser {

    private Grammar grammar; // Grammar
    private List<String> w = new ArrayList<>(); // Words to be parsed

    private String state; // current state
    private int i;

    private Stack<String> a; // working stack
    private Stack<String> b; // input stack

    private ParserOutput po; // ParserOutput object

    private String fileName = "./outputs/"; // Name of the file depending on the given input

    List<String> aList = new ArrayList<>(); // Symbol list

    private boolean onPif; // True if pif, false if not

    public Parser(Grammar grammar, String w, boolean onPif) {
        this.grammar = grammar;

        a = new Stack<>();
        b = new Stack<>();

        String[] wAsList = w.split(" ");
        this.w.addAll(Arrays.asList(wAsList));

        state = "q";
        i = 0;

        b.push(grammar.getStartingSymbol());

        this.onPif = onPif;
    }

    /**
     * Parses the given sequence with the descensive recursive parsing and print if is acepted or not
     */
    public void descendingRecursiveParsing(){
        grammar.solveLeftRecursivity();
        System.out.println(grammar);

        while (!state.equals("f") && !state.equals("e")){
            if (state.equals("q")){
                if (i == w.size() && b.empty())
                    success();
                else if (b.empty())
                    momentaryInsuccess();
                else if (grammar.getNonTerminals().contains(b.peek()))
                    expand();
                else if (i < w.size() && (b.peek().equals(w.get(i))) || b.peek().equals("epsilon"))
                    advance();
                else
                    momentaryInsuccess();
            } else if (state.equals("b")){
                if (grammar.getTerminals().contains(a.peek()))
                    back();
                else
                    anotherTry();
            }
        }

        if (state.equals("f")){
            System.out.println("Sequence accepted");
            if (onPif)
                po = new ParserOutput(grammar, a, fileName+"out2.txt");
            else
                po = new ParserOutput(grammar, a, fileName+"out1.txt");
        }else
            System.err.println("Error");
    }

    public void expand(){
        String nt = b.pop();
        a.push(nt + " 0");
        List<List<String>> prodsOfnt = grammar.getProductionsForNonTerminal(nt);

        List<String> prod = prodsOfnt.get(0);

        for(int j = prod.size() - 1; j >= 0; j--)
            b.push(prod.get(j));
    }

    public void advance(){
        if(!b.peek().equals("epsilon")) i++;
        a.push(b.pop());
    }

    public void momentaryInsuccess(){
        state = "b";
    }

    public void back(){
        state = "b";
        if(!a.peek().equals("epsilon"))
            i = i - 1;
        b.push(a.pop());
    }

    public void success(){
        state = "f";
        b.push("epsilon");
    }

    public void anotherTry(){
        String currentProduction = a.pop();

        String[] nonTerminalAndProductionNumber = currentProduction.split(" ");

        String currentNonTerminal = nonTerminalAndProductionNumber[0];

        int prodNmbr = Integer.parseInt(nonTerminalAndProductionNumber[1]);

        List<List<String>> allProds = grammar.getProductionsForNonTerminal(currentNonTerminal);

        deleteFromB(currentNonTerminal, prodNmbr);

        if(prodNmbr < allProds.size() - 1){
            int newProd = prodNmbr + 1;
            a.push(currentNonTerminal + " " + newProd);

            List<String> prod = allProds.get(newProd);
            for(int j = prod.size() - 1; j >= 0; j--)
                b.push(prod.get(j));

            state = "q";
        }
        else{
            if(i == 0 && currentNonTerminal.equals(grammar.getStartingSymbol()))
                state = "e";
            else
                b.push(currentNonTerminal);
        }

    }

    /**
     * Deletes all symbols in the top from the beta stack
     * @param currentNonTerminal
     * @param productionNumber
     */
    private void deleteFromB(String currentNonTerminal, int productionNumber){
        List<String> currentProductionInBeta = grammar.getProductionsForNonTerminal(currentNonTerminal).get(productionNumber);
        int j = 0;
        while (j < currentProductionInBeta.size() && b.pop().equals(currentProductionInBeta.get(j))) j++;
    }
}
