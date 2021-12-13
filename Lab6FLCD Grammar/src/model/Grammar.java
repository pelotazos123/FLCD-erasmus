package model;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Grammar {

    Set<String> nonTerminals = new HashSet<>();
    Set<String> terminals = new HashSet<>();

    String startingSymbol;

    Map<List<String>, List<List<String>>> productions = new HashMap<>();

    String fileName;

    public Grammar(String fileName){
        this.fileName = fileName;
        fileReader();
    }

    public Set<String> getNonTerminals() {
        return nonTerminals;
    }

    public Set<String> getTerminals() {
        return terminals;
    }

    public void fileReader(){
        try{
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String line = br.readLine();

            int i = 0;

            while(line != null){
                if(!line.equals("")){
                    if (i == 0)
                        nonTerminals.addAll(Arrays.stream(line.split(" ")).toList());
                    else if (i == 1)
                        terminals.addAll(Arrays.stream(line.split(" ")).toList());
                    else if (i == 2){
                        String[] startingSymbol = line.split(" ");
                        this.startingSymbol = startingSymbol[0];
                    }
                    else
                        this.addProductions(line);
                    i += 1;
                }
                line = br.readLine();
            }
            br.close();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public void addProductions(String line){
        String[] sent = line.trim().split("->");

        List<String> left = Arrays.stream(sent[0].split(" ")).toList();

        String[] right = sent[1].split(" \\| ");
        List<List<String>> rightProd = new ArrayList<>();

        for(String p: right){
            String[] rSymb = p.split(" ");
            List<String> rightList = new ArrayList<>();

            for(String s: rSymb)
                if(!s.equals(""))
                    rightList.add(s);

            if(!rightProd.contains(rightList))
                rightProd.add(rightList);
        }
        if(!productions.containsKey(left))
            productions.put(left, rightProd);
        else{
            List<List<String>> prod = productions.get(left);
            for(List<String> r: rightProd)
                if(!prod.contains(r))
                    prod.add(r);
        }
    }

    public Map<List<String>, List<List<String>>> getProductions() {
        return productions;
    }

    public String getStartingSymbol() {
        return startingSymbol;
    }

    public List<List<String>> getProductionsForNonTerminal(String nonTerminal){
        return productions.get(Arrays.asList(nonTerminal));
    }

    public boolean isCFG(){
        for(List<String> lhs : productions.keySet())
            if(lhs.size() > 1)
                return false;

        return true;
    }

    @Override
    public String toString() {
        String grammar = "---------------------------------------------\n";

        for(List<String> lhs: productions.keySet()){
            for(String s: lhs){
                grammar += s;
            }
            grammar += "->";
            for(List<String> p: this.productions.get(lhs)){
                for(String sym: p){
                    grammar += sym + " ";
                }
                grammar += "| ";
            }
            grammar += "\n";
        }
        grammar += "---------------------------------------------";

        return grammar;
    }
}
