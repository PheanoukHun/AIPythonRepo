
/**
 * CS312 Assignment 5 - Hangman
 *
 * On my honor, Pheanouk Hun, this programming assignment is my own work, 
 * I have not shared it with others and I will not share it in the future.
 *
 * A program to play Hangman.
 *
 *  Name:Pheanouk Hun
 *  UTEID: ph23434
 *
 */

import java.util.Scanner;

public class Hangman {

    public static final int MAX_NUM_GUESSES = 5;

    public static void main(String[] args) {

        // Load phrases from a file into the phrases variable
        PhraseBank phrases = buildPhraseBank(args);
        // Create a scanner for the keyboard. (Do not create another scanner object.)
        Scanner keyboard = new Scanner(System.in);
        // Print the intro to the program.
        intro();

        playRound(phrases, keyboard);

        keyboard.close();
    }

    /**
     * 
     * @param phrases
     * @param keyboard
     */
    public static void playRound(PhraseBank phrases, Scanner keyboard) {
        String playAgain = "y";
        do {
            String notGuessedLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
            String secretPhrase = phrases.getNextPhrase();
            String maskedPhrase = createMaskedPhrase(secretPhrase, notGuessedLetters);
            int numWrongGuess = 0;
            System.out.println("\nI am thinking of a " + phrases.getTopic() + " ...\n");
            while (numWrongGuess < MAX_NUM_GUESSES && maskedPhrase.contains("*")) {
                System.out.println("The current phrase is " + maskedPhrase + "\n");
                String guess = getValidGuess(keyboard, notGuessedLetters);
                notGuessedLetters = updateNotGuessedLetters(guess, notGuessedLetters);
                if (!checkCorrectGuess(guess, notGuessedLetters, secretPhrase)) {
                    numWrongGuess += 1;
                }

                System.out.println("\nNumber of wrong guesses so far: " + numWrongGuess);
                maskedPhrase = createMaskedPhrase(secretPhrase, notGuessedLetters);
            }
        } while ((playAgain.toLowerCase()).equals("y"));
    }

    /**
     * 
     * @param secretPhrase
     * @param notGuessedLetters
     * @return obfuscatedPhrase
     */
    public static String createMaskedPhrase(String secretPhrase, String notGuessedLetters) {
        String obfuscatedPhrase = "";
        for (int i = 0; i < secretPhrase.length(); i++) {
            char currChar = secretPhrase.charAt(i);
            if (((int) currChar) == 32) {
                obfuscatedPhrase += "_";
            } else if (notGuessedLetters.indexOf(currChar) != -1) {
                obfuscatedPhrase += "*";
            } else {
                obfuscatedPhrase += currChar;
            }
        }
        
        return obfuscatedPhrase;
    }

    /**
     * 
     * @param notGuessedLetters
     */
    public static void printNotGuessedLetters(String notGuessedLetters) {
        System.out.println("The letters you have not guessed yet are:");
        char currChar = notGuessedLetters.charAt(0);
        System.out.print(currChar);
        for (int i = 1; i < notGuessedLetters.length(); i++) {
            currChar = notGuessedLetters.charAt(i);
            System.out.print("--" + currChar);
        }
        System.out.println("\n");
    }

    /**
     * 
     * @param keyboard
     * @param notGuessedLetters
     * @return
     */
    public static String getValidGuess(Scanner keyboard, String notGuessedLetters) {
        printNotGuessedLetters(notGuessedLetters);

        System.out.print("Enter your next guess: ");
        String guess = (keyboard.nextLine()).toUpperCase();

        while (!(notGuessedLetters.contains(guess))) {
            System.out.println(guess + " is not a valid guess.");
            printNotGuessedLetters(notGuessedLetters);
            System.out.print("Enter your next guess: ");
            guess = (keyboard.nextLine()).toUpperCase();
        }

        System.out.println("\nYou guessed " + guess + ".\n");
        return guess;
    }

    /**
     * 
     * @param guess
     * @param notGuessedLetters
     * @return
     */
    public static String updateNotGuessedLetters(String guess, String notGuessedLetters) {
        int guessIndex = notGuessedLetters.indexOf(guess);
        String notGuessedPartOne = notGuessedLetters.substring(0, guessIndex);
        String notGuessedPartTwo = notGuessedLetters.substring(guessIndex + 1);
        return notGuessedPartOne + notGuessedPartTwo;
    }

    /**
     * 
     * @param guess
     * @param notGuessedLetters
     * @param secretPhrase
     * @return
     */
    public static Boolean checkCorrectGuess(String guess, String notGuessedLetters,
            String secretPhrase) {
        if (secretPhrase.contains(guess)) {
            System.out.println("This is present in the secrete phrase.");
            return true;
        }

        System.out.println("This is not present in the secret phrase.");
        return false;
    }

    // Build the PhraseBank.
    // Creates a set of secret phrases read from a file using the PhraseBank.java
    // class.
    // ** Except for lines 47 and 48, do not change this method. **
    public static PhraseBank buildPhraseBank(String[] args) {
        PhraseBank result;
        // If no command line arguments,
        if (args == null || args.length == 0
                || args[0] == null || args[0].length() == 0) {
            result = new PhraseBank(); // Creates a PhraseBank with the default file, HangmanMovies.txt
            // result = new PhraseBank("ProSportsTeams.txt"); // Creates a PhraseBank with
            // the filename you specify

            // Yes, command line arguments. The grading script uses this code so that
            // mutiple files can be tested.
        } else {
            result = new PhraseBank(args[0]);
        }

        return result;
    }

    // Print the intro to the program.
    // ** Do not change this method **
    public static void intro() {
        System.out.println("This program plays the game of hangman.");
        System.out.println();
        System.out.println("The computer will pick a random phrase.");
        System.out.println("Input letters for your guess.");
        System.out.println("After 5 wrong guesses you lose.");
    }
}
