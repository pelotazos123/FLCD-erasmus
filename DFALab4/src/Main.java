import model.FiniteAutomata;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    private static final String path = "./Files/FA.in";

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        FiniteAutomata fa = new FiniteAutomata();

        try {
            fa.loadFile(path);
        } catch (IOException e) {
            e.printStackTrace();
        }

        while (true){
            System.out.println("\n");
            System.out.println("1 - Display the set of states");
            System.out.println("2 - Display the alphabet");
            System.out.println("3 - Display the set of final states");
            System.out.println("4 - Display all the transitions");
            System.out.println("5 - Enter a sentence");

            System.out.println("\n0 - Exit the program");

            Scanner entry = new Scanner(System.in);
            int opt;
            opt = entry.nextInt();
            menu(opt, input, fa);
        }
    }

    public static void menu(int value, Scanner input, FiniteAutomata automata){
        switch (value){
            case 0:
                System.exit(0);
                break;
            case 1:
                System.out.println("Set of states: ");
                automata.getStates();
                break;
            case 2:
                System.out.println("Alphabet: ");
                automata.getAlphabet();
                break;
            case 3:
                System.out.println("Set of final states: ");
                automata.getFinalStates();
                break;
            case 4:
                System.out.println("Transitions: ");
                automata.getTransitions();
                break;
            case 5:
                publicTest(input, automata);
                break;
            default:
                System.err.println("Invalid option selected");
                break;
        }
    }

    private static void publicTest(Scanner input, FiniteAutomata automata) {
        String inputString = getString(input);
        printResults(automata.processInput(inputString));
    }

    /**
     * Prints if the sentence is accepted or not
     * @param result, the result to print
     */
    private static void printResults(boolean result) {
        if(result)
            System.out.println("\n\tACCEPT ");
        else
            System.out.println("\n\tREJECT ");
    }

    /**
     * Gets a string to be tested
     * @param input, the string to test
     * @return
     */
    private static String getString(Scanner input) {
        System.out.print("\nPlease enter a string to test: ");
        return input.nextLine();
    }
}



