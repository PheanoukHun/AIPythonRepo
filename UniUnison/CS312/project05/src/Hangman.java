
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

        playGame(phrases, keyboard);

        keyboard.close();
    }

    /**
     * Controls the overall Hangman game flow and allows the player to play multiple rounds.
     * @param phrases - PhraseBank object that provides the secret phrases.
     * @param keyboard - Scanner object for reading user input.
     * @return void
     */
    public static void playGame(PhraseBank phrases, Scanner keyboard) {
        String playAgain;

        // Loop runs once per round, and continues if the player chooses to play again.
        do {
            playRound(phrases, keyboard);
            System.out.print("Do you want to play again?\nEnter 'Y' or 'y' to play again: ");
            playAgain = keyboard.nextLine();
            playAgain = playAgain.toUpperCase();
        } while (playAgain.equals("Y"));
    }

    /**
     * Plays one full round of Hangman until the player wins or reaches the maximum wrong guesses.
     * @param phrases - PhraseBank object that provides the secret phrases.
     * @param keyboard - Scanner object for reading user input.
     * @return void
     */
    public static void playRound(PhraseBank phrases, Scanner keyboard) {
        String notGuessedLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String secretPhrase = phrases.getNextPhrase();
        String maskedPhrase = createMaskedPhrase(secretPhrase, notGuessedLetters);
        int numWrongGuess = 0;
        System.out.println("\nI am thinking of a " + phrases.getTopic() + " ...\n");

        // Loop runs once per player guess until the phrase is solved or guesses run
        // out.
        while (numWrongGuess < MAX_NUM_GUESSES && maskedPhrase.contains("*")) {
            String guess = getValidGuess(keyboard, notGuessedLetters, maskedPhrase);
            notGuessedLetters = updateNotGuessedLetters(guess, notGuessedLetters);

            // If: the guessed letter is not in the secret phrase.
            if (!checkCorrectGuess(guess, secretPhrase)) {
                numWrongGuess += 1;
            }

            System.out.println("\nNumber of wrong guesses so far: " + numWrongGuess);
            maskedPhrase = createMaskedPhrase(secretPhrase, notGuessedLetters);
        }

        // If: player has reached the maximum number of wrong guesses.
        if (numWrongGuess == MAX_NUM_GUESSES) {
            System.out.println("You lose. The secret phrase was " + secretPhrase);
        } else {
            // Else: player has guessed the entire phrase before running out of guesses.
            System.out.println("The phrase is " + secretPhrase + ".\nYou win!!");
        }
    }

    /**
     * Creates a version of the secret phrase where unguessed letters are
     * masked with '*' and spaces are replaced with underscores.
     * @param secretPhrase - the phrase to be guessed.
     * @param notGuessedLetters - string of letters the player has not yet guessed.
     * @return A masked version of the secret phrase showing only guessed letters.
     */
    public static String createMaskedPhrase(String secretPhrase, String notGuessedLetters) {
        String obfuscatedPhrase = "";

        // Loop runs once per character in the secret phrase.
        for (int i = 0; i < secretPhrase.length(); i++) {
            char currChar = secretPhrase.charAt(i);

            // If: current character is a space.
            if ((currChar) == ' ') {
                obfuscatedPhrase += "_";
            }
            // Else if: current character has not been guessed yet.
            else if (notGuessedLetters.indexOf(currChar) != -1) {
                obfuscatedPhrase += "*";
            }
            // Else: current character has been guessed and should be shown.
            else {
                obfuscatedPhrase += currChar;
            }
        }

        return obfuscatedPhrase;
    }

    /**
     * Prints all letters that the player has not yet guessed, separated by double dashes..
     * Displays the formatted list of unguessed letters to the console.
     * @param notGuessedLetters - a string containing letters that have not been guessed yet.
     * @return void
     */
    public static void printNotGuessedLetters(String notGuessedLetters) {

        System.out.println("The letters you have not guessed yet are: ");

        // Print the first letter without leading dashes.
        char currChar = notGuessedLetters.charAt(0);
        System.out.print(currChar);

        // Loop through remaining letters, adding separators between them.
        for (int i = 1; i < notGuessedLetters.length(); i++) {
            currChar = notGuessedLetters.charAt(i);
            System.out.print("--" + currChar);
        }

        System.out.println("\n");
    }

    /**
     * Asks the user for a valid letter guess making sure it is valid.
     * Displays the masked phrase, remaining letters, and valid guess message.
     * @param keyboard - Scanner object used for reading user input.
     * @param notGuessedLetters - a string containing letters that have not been guessed yet.
     * @param maskedPhrase - the current masked version of the secret phrase.
     * @return - A valid guess from the user.
     */
    public static String getValidGuess(Scanner keyboard, String notGuessedLetters,
            String maskedPhrase) {

        // Prompt the user for a guess and convert it to uppercase.
        System.out.println("The current phrase is " + maskedPhrase + "\n");
        printNotGuessedLetters(notGuessedLetters);

        // Ask the user for their guess.
        System.out.print("Enter your next guess: ");
        String guess = keyboard.nextLine();
        boolean isValid = false;

        // Repeat until the user enters a valid guess from the remaining letters.
        while (!isValid) {

            // Makes sure that the guess variable is valid or not
            if (getIsValid(guess, notGuessedLetters)) {
                guess = guess.toUpperCase();
                guess = guess.substring(0, 1);
                isValid = true;
            }

            // Only prompt again if guess is still invalid.
            if (!isValid) {
                System.out.println("\n"+ guess + " is not a valid guess.");
                printNotGuessedLetters(notGuessedLetters);
                System.out.print("Enter your next guess: ");
                guess = keyboard.nextLine();
            }
        }

        // Confirms that the user has guessed something valid.
        System.out.println("\nYou guessed " + guess + ".\n");
        return guess;
    }

    /**
     * Makes sure that the user guess is invalid if it is empty or not a valid input.
     * @param guess - the User's input
     * @param notGuessedLetters - a string containing letters that have not been guessed yet.
     * @return a boolean value indicating whether it is valid or not.
     */
    public static boolean getIsValid(String guess, String notGuessedLetters) {

        // If the guess is empty, then guess is invalid
        if (guess.length() == 0) {
            return false;
        }

        // Return the boolean of whether the non-empty guess string is valid still
        String first = guess.toUpperCase();
        first = first.substring(0, 1);
        return notGuessedLetters.contains(first);
    }

    /**
     * Removes the letter that user guessed from the string of not-guessed letters.
     * @param guess - the letter guessed by the user.
     * @param notGuessedLetters - a string containing letters that have not been guessed yet.
     * @return - The updated string of not-guessed letters after removing the guessed letter.
     */
    public static String updateNotGuessedLetters(String guess, String notGuessedLetters) {
        int guessIndex = notGuessedLetters.indexOf(guess);

        // Split the string into two parts: before and after the guessed letter.
        String notGuessedPartOne = notGuessedLetters.substring(0, guessIndex);
        String notGuessedPartTwo = notGuessedLetters.substring(guessIndex + 1);

        return notGuessedPartOne + notGuessedPartTwo;
    }

    /**
     * Checks whether the user's guessed letter is present in the secret phrase.
     * Prints a message indicating whether the guess is correct.
     * @param guess - the letter guessed by the user.
     * @param secretPhrase - the phrase the player is trying to guess.
     * @return - Returns a true or false value based on the correctness of guess.
     */
    public static boolean checkCorrectGuess(String guess, String secretPhrase) {

        // If: the secret phrase contains the guessed letter.
        if (secretPhrase.contains(guess)) {
            System.out.println("That is present in the secret phrase.");
            return true;
        }

        // Else: the guessed letter is not in the secret phrase.
        System.out.println("That is not present in the secret phrase.");
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
