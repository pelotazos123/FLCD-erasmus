package model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Grammar {

    private List<String> nonTerminal = new ArrayList<String>();
    private List<String> terminals = new ArrayList<String>();
    HashMap<String, List<String>> prds = new HashMap<>();


    public Grammar(String fileName) throws IOException {
        fileReader(fileName);
    }

    public void fileReader(String fileName) throws IOException {
        BufferedReader bf = new BufferedReader(new FileReader(fileName));

        String nt = bf.readLine();
        nonTerminal = Arrays.stream(nt.split(",")).toList();

        String ter = bf.readLine();
        terminals = Arrays.stream(ter.split(",")).toList();

        while (bf.readLine() != null){
            String prd = bf.readLine();
            String[] prdEls = prd.trim().split(":");

            String prdNT = prdEls[0];
            String[] prdNTList = prdNT.split("\\|");

            String prdRes = prdEls[1];
            List<String> prdResList = List.of(prdRes.split("\\|"));

            for (String nts: prdNTList){
                List<String> existingPrds = prds.get(nonTerminal);
            }
        }


    }
}
