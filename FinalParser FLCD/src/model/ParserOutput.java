package model;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

/**
 * Gets the out files with the derivation string witten in them
 */
public class ParserOutput {

    private Grammar grammar; // Grammar
    private Stack<String> a; // Working stack
    private String fileName; // Name of the file where the output is going to be written
    private List<String> aList = new ArrayList<>(); // Symbol list from the working stack

    public ParserOutput(Grammar grammar, Stack<String> a, String fileName){
        this.grammar = grammar;
        this.a = a;
        this.fileName = fileName;

        getAlpha();
        getDerivationsString();
        writeDerivationToFile(fileName);
    }

    /**
     * Returns a list with the derivations from the recursive parsing
     * @return
     */
    public List<String> getDerivationsString(){
        List<String> derivations = new ArrayList<>();
        derivations.add(grammar.getStartingSymbol());

        for(String element: aList) {
            String derivation = derivations.get(derivations.size() - 1);
            if(derivation.contains("epsilon")){
                derivation = derivation.replaceFirst("epsilon ", "").replaceFirst("epsilon", "");
                derivations.add(derivation);
            }

            if (!grammar.getTerminals().contains(element)) {
                String[] nonTerminalAndProductionNumber = element.split(" ");
                String nonTerminal = nonTerminalAndProductionNumber[0];
                int productionNumber = Integer.parseInt(nonTerminalAndProductionNumber[1]);

                if(!nonTerminal.equals("")){
                    List<String> usedProduction = grammar.getProductionsForNonTerminal(nonTerminal).get(productionNumber);

                    String production = "";
                    for(String s: usedProduction)
                        production += s + " ";

                    production = production.trim();

                    derivation = derivations.get(derivations.size() - 1);
                    derivation = derivation.replaceFirst(nonTerminal, production);
                    derivations.add(derivation);
                }
            }
        }

        return derivations;
    }

    /**
     * Writes the derivations into a file
     * @param fileName
     */
    public void writeDerivationToFile(String fileName){
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter(fileName));

            for (int i = 0; i < getDerivationsString().size()-1; i++)
                bw.write(getDerivationsString().get(i) + " => ");

            bw.write(getDerivationsString().get(getDerivationsString().size()-1));
            bw.close();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Transforms stack to list
     */
    private void getAlpha(){
        while (!a.empty()){
            String el = a.pop();
            aList.add(el);
        }
        Collections.reverse(aList);
    }

}
