package model;

import SymbolTable.SymbolTable;

import java.io.InputStream;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LexicalScanner {

    /**
     * Checks the regex
     * @param input, regex given
     * @return the token and its type, if not valid one, error
     */
    public ArrayList<Token> lex(String input){
        final ArrayList<Token> tokens = new ArrayList<>();
        final StringTokenizer st = new StringTokenizer(input);

        while(st.hasMoreElements()) {
            String word = st.nextToken();
            boolean matched = false;

            for (Types tokenType: Types.values()){
                Pattern pattern = Pattern.compile(tokenType.pattern);
                Matcher matcher = pattern.matcher(word);
                if(matcher.find()){
                    Token tk = new Token();
                    tk.setType(tokenType);
                    tk.setValue(word);
                    tokens.add(tk);
                    matched = true;
                }
            }

            if (!matched)
                System.err.println("Invalid token found");
        }
        return tokens;
    }
}
