package model;

import java.util.Scanner;
import java.io.*;

public class FiniteAutomata {

    private int currentState = 0;     // keeps track of current state during string processing
    private int numStates;            // the number of states in the DFA
    private String alphabet;
    private int start;                // start state
    private State[] stateArray;       // collection of states

    /**
     * @return the states of the dfa
     */
    public void getStates(){
        for (int i = 0; i < numStates; i++) 
            System.out.print("q"+i+"\t");
    }

    /**
     * @return the final states of the dfa
     */
    public void getFinalStates(){
        for (int i = 0; i < stateArray.length; i++) {
            if (stateArray[i].isAcceptState())
                System.out.println("q"+i+"\t");
        }
    }

    /**
     * @return the alphabet of the dfa
     */
    public void getAlphabet(){
        System.out.println(alphabet);
    }

    /**
     * @return the transitions formated
     */
    public void getTransitions(){
        for (int i = 0; i < stateArray.length; i++)
            for (int j = 0; j < stateArray[i].transitions.length; j++)
                System.out.println("("+i + "->" + stateArray[i].getTransition(j)+")\t");
    }

    /**
     * Processes the given input
     * @param inputString to be analized
     * @return if its accepted true, false if not
     */
    public boolean processInput(String inputString) {
        if(inputString == null)
            return stateArray[start].isAcceptState();

        boolean accept;

        accept = process(inputString);
        reset();
        return  accept;
    }

    private boolean process(String inputString) {
        int aChar;

        for(int i = 0; i < inputString.length(); ++i) {
            aChar = inputString.charAt(i);
            int charIndex = alphabet.indexOf(aChar);

            if( charIndex == -1 ) {
                return false;
            }
            else
                currentState = stateArray[currentState].getTransition(charIndex);
        }

        return stateArray[currentState].isAcceptState();
    }

    /**
     * Reads a file and then parse it
     * @param fileName to be read
     * @return
     */
    public void loadFile(String fileName) throws IOException {

        InputStream in = getFileInputStream(fileName);

        BufferedReader bf = new BufferedReader(new FileReader(fileName));
        numStates = Integer.parseInt(bf.readLine());

        alphabet = bf.readLine();

        stateArray = new State[numStates];
        for (int i = 0; i < numStates; i++) {
            stateArray[i] = new State( alphabet.length() );
        }

        start = Integer.parseInt(bf.readLine());
        currentState = start;

        String acceptState = bf.readLine();
        int acceptNum;

        if (acceptState.contains("#")) {
            acceptNum = Integer.parseInt(acceptState.split(",")[0]);
            stateArray[acceptNum].setAcceptState();
        }

        int aStateLocation = -1;
        int trans1 = -1;
        int trans2 = -1;

        String line = bf.readLine();
        while (line != null) {

            aStateLocation = Integer.parseInt(line.split(",")[0]);
            trans1 = Integer.parseInt(line.split(",")[1]);
            trans2 = Integer.parseInt(line.split(",")[2]);
            stateArray[aStateLocation].setTransitions(0, trans1);
            stateArray[aStateLocation].setTransitions(1, trans2);
            line = bf.readLine();
        }
    }

    private static InputStream getFileInputStream(String fileName) {

        InputStream inputStream;

        try {
            inputStream = new FileInputStream(new File(fileName));
        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
            inputStream = null;
        }

        return inputStream;
    }

    /**
     * Resets the current state
     */
    public void reset() {
        currentState = start;
    }

}




