

import model.LexicalScanner;
import model.Token;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static List<String> code;

    public static void main(String[] args){
        LexicalScanner ls = new LexicalScanner();

        System.out.println("Start writing: ");

        while (true) {
            Scanner entry = new Scanner(System.in);
            String str = entry.nextLine();
            ArrayList<Token> tokens = ls.lex(str);

            for (Token token: tokens)
                System.out.println("(" + token.getType() + ":" + token.getValue()+ ")");

        }
    }

    public static void reader(String file){
        BufferedReader bf = null;
        String line;
        code = new ArrayList<>();
        try{
            bf = new BufferedReader(new FileReader(file));
            line = bf.readLine();
            while (line != null){
                code.add(line);
            }
        } catch (FileNotFoundException e){
            System.out.println("The file doesn't exist: "+file);
        } catch (IOException e) {
            System.out.println("Error reading the file: "+file);
        } finally {
            if (bf!=null)
                try {
                    bf.close();
                } catch (IOException e) {
                    System.out.println("Error closing the file: "+file);
                    e.printStackTrace();
                }
        }

    }


}
