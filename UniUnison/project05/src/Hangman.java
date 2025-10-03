
/**
 * CS312 Assignment 5 - Hangman
 *
 * On my honor, <NAME>, this programming assignment is my own work, 
 * I have not shared it with others and I will not share it in the future.
 *
 * A program to play Hangman.
 *
 *  Name:
 *  UTEID:
 *
 */

import java.util.Scanner;

public class Hangman {

    public static void main(String[] args) {

        // Load phrases from a file into the phrases variable
        PhraseBank phrases = buildPhraseBank(args);
        // Create a scanner for the keyboard. (Do not create another scanner object.)
        Scanner keyboard = new Scanner(System.in);
        // Print the intro to the program.
        intro();

        // CS12 students: add your code here, delete this comment when finished.

        keyboard.close();
    }

    // CS12 students: add your methods here. Delete this comment when finished.

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
