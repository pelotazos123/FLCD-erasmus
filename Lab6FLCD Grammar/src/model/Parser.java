package model;

import java.util.*;

public class Parser {

    private Grammar grammar;
    private List<String> w = new ArrayList<>();

    private String state;
    private int i;

    private Stack<String> a;
    private Stack<String> b;

    List<String> aList = new ArrayList<>();

    public Parser(Grammar grammar, String w) {
        this.grammar = grammar;

        a = new Stack<>();
        b = new Stack<>();

        String[] wAsList = w.split(" ");
        this.w.addAll(Arrays.asList(wAsList));

        state = "q";
        i = 0;

        b.push(grammar.getStartingSymbol());
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

    public void momentaryInsucces(){
        state = "b";
    }

    public void back(){
        state = "b";
        if(!a.peek().equals("epsilon"))
            i = i - 1;
        b.push(a.pop());
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

    private void deleteFromB(String currentNonTerminal, int productionNumber){
        List<String> currentProductionInBeta = grammar.getProductionsForNonTerminal(currentNonTerminal).get(productionNumber);
        int j = 0;
        while (j < currentProductionInBeta.size() && b.pop().equals(currentProductionInBeta.get(j))) j++;
    }

    public void success(){
        state = "f";
        b.push("epsilon");
    }

    private void getAlpha(){
        while (!a.empty()){
            String el = a.pop();
            aList.add(el);
        }
        Collections.reverse(aList);
    }





}
