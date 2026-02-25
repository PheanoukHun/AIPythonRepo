/*
 * HangmanManager.java - CS 314 Assignment 4
 *
 * By signing my name below, I affirm that this assignment is my own work. I
 * have neither given nor received unauthorized assistance on this assignment.
 *
 * Name: Pheanouk Hun
 * Email address: ph23434@eid.utexas.edu
 * UTEID: ph23434
 */

// TODO: add imports as necessary
import java.util.Set;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.TreeMap;

/**
 * Manages the details of EvilHangman. This class keeps track of the possible
 * words from a dictionary during rounds of hangman, based on guesses so far.
 */
public class HangmanManager {

    private boolean debugOn;

    private TreeMap<Integer, ArrayList<String>> wordPatterns;
    private ArrayList<String> currWords;

    private Set<Character> guessesMade;

    private int wordLen;
    private int numGuesses;
    private int guessesLeft;

    private HangmanDifficulty diff;

    private String wordMask;

    /**
     * Create a new HangmanManager from the provided set of words and phrases.
     * pre: words != null, words.size() > 0
     *
     * @param words   A set with the words for this instance of Hangman.
     * @param debugOn true if we should print out debugging to System.out.
     */
    public HangmanManager(Set<String> words, boolean debugOn) {
        this(words);
        this.debugOn = debugOn;
    }

    /**
     * Create a new HangmanManager from the provided set of words and phrases.
     * Debugging is off.
     * pre: words != null, words.size() > 0
     *
     * @param words A set with the words for this instance of Hangman.
     */
    public HangmanManager(Set<String> words) {

        // Checking Preconditions
        if (words == null || words.size() == 0) {
            throw new IllegalArgumentException("Words cannot be null and"
                    + " its length must be greater than 0.");
        }

        // Copying the Values
        for (String word : words) {
            if (wordPatterns.get(word.length()) == null) {
                this.wordPatterns.put(word.length(), new ArrayList<>());
                this.wordPatterns.get(word.length()).add(word);
            } else {
                this.wordPatterns.get(word.length()).add(word);
            }
        }
    }

    /**
     * Get the number of words in this HangmanManager of the given length.
     * pre: none
     *
     * @param length The given length to check.
     * @return the number of words in the original Dictionary with the given
     *         length
     */
    public int numWords(int length) {
        return this.wordPatterns.get(length).size();
    }

    /**
     * Get for a new round of Hangman. Think of a round as a complete game of
     * Hangman.
     *
     * @param wordLen    the length of the word to pick this time.
     *                   numWords(wordLen) > 0
     * @param numGuesses the number of wrong guesses before the player loses
     *                   the round. numGuesses >= 1
     * @param diff       The difficulty for this round.
     */
    public void prepForRound(int wordLen, int numGuesses, HangmanDifficulty diff) {

        // Checking Precondition
        if (wordLen <= 0 || numGuesses <= 0) {
            throw new IllegalArgumentException("Word Length cannot be less than 1 or "
                    + "Number of guesses cannot be less than 1");
        }

        // Reseting Variables
        this.diff = diff;

        this.numGuesses = numGuesses;
        this.guessesLeft = numGuesses;

        this.wordLen = wordLen;
        this.currWords = wordPatterns.get(wordLen);

        this.guessesMade = new HashSet<>();

        StringBuilder maskBuilder = new StringBuilder();
        char UNKNOWN_CHAR = '-';
        for (int i = 0; i < wordLen; i++) {
            maskBuilder.append(UNKNOWN_CHAR);
        }
        this.wordMask = maskBuilder.toString();
    }

    /**
     * The number of words still possible (live) based on the guesses so far.
     * Guesses will eliminate possible words.
     *
     * @return the number of words that are still possibilities based on the
     *         original dictionary and the guesses so far.
     */
    public int numWordsCurrent() {
        return this.currWords.size();
    }

    /**
     * Get the number of wrong guesses the user has left in this round (game)
     * of Hangman.
     *
     * @return the number of wrong guesses the user has left in this round
     *         (game) of Hangman.
     */
    public int getGuessesLeft() {
        return this.guessesLeft;
    }

    /**
     * Return a String that contains the letters the user has guessed so far
     * during this round. The characters in the String are in alphabetical
     * order. The String is in the form [let1, let2, let3, ... letN].
     * For example: [a, c, e, s, t, z]
     *
     * @return a String that contains the letters the user has guessed so far
     *         during this round.
     */
    public String getGuessesMade() {
        return this.guessesMade.toString();
    }

    /**
     * Check the status of a character.
     *
     * @param guess The characater to check.
     * @return true if guess has been used or guessed this round of Hangman,
     *         false otherwise.
     */
    public boolean alreadyGuessed(char guess) {
        return this.guessesMade.contains(guess);
    }

    /**
     * Get the current pattern. The pattern contains '-''s for unrevealed (or
     * guessed) characters and the actual character for "correctly guessed"
     * characters.
     *
     * @return the current pattern.
     */
    public String getPattern() {
        return this.wordMask;
    }

    /**
     * Update the game status (pattern, wrong guesses, word list), based on
     * the given guess.
     *
     * @param guess pre: !alreadyGuessed(ch), the current guessed character
     * @return return a tree map with the resulting patterns and the number of
     *         words in each of the new patterns. The return value is for
     *         testing and debugging purposes.
     */
    public TreeMap<String, Integer> makeGuess(char guess) {

        // Checking Preconditions
        if (alreadyGuessed(guess)) {
            throw new IllegalArgumentException("Already Guessed the Character");
        }

        this.guessesMade.add(guess);
        TreeMap<String, ArrayList<String>> allowedWords = new TreeMap<>();
        TreeMap<String, Integer> resultsMap = new TreeMap<>();

        // Gettings List of Words with Guesses
        for (String word : this.currWords) {

            String currMask = getNewMaskedWord(guess, word);

            if (allowedWords.get(currMask) == null) {
                allowedWords.put(currMask, new ArrayList<>());
                allowedWords.get(currMask).add(word);
            } else {
                allowedWords.get(currMask).add(word);
            }
        }

        // Get the Best Result based on the g
        if (this.diff == HangmanDifficulty.HARD) {
            this.wordMask = getHardestWords(allowedWords);
        } else if (this.diff == HangmanDifficulty.MEDIUM) {
            this.wordMask = getMediumWords(allowedWords);
        } else {
            this.wordMask = getEasyWords(allowedWords);
        }

        this.currWords = allowedWords.get(this.wordMask);

        return resultsMap;
    }

    /**
     * Return the secret word this HangmanManager finally ended up picking for
     * this round. If there are multiple possible words left one is selected
     * at random.
     * pre: numWordsCurrent() > 0
     *
     * @return return the secret word the manager picked.
     */
    public String getSecretWord() {

        if (currWords.size() == 0) {
            throw new IllegalArgumentException("There is no words found.");
        }

        return currWords.get(0);
    }

    /**
     * Generate the masked version of a given masked word based on the previous mask
     * format and the guess.
     * 
     * @param guess - The char value of the guess that user made.
     * @param word  - The word that the mask will be based on.
     * 
     * @return - The String representation of the word of the updated masked pattern.
     */
    private String getNewMaskedWord(char guess, String word) {

        StringBuilder resultBuilder = new StringBuilder();

        // Looking through all Characters
        for (int i = 0; i < this.wordLen; i++) {
            if (guess == word.charAt(i)) {
                resultBuilder.append(guess);
            } else {
                resultBuilder.append(this.wordMask.charAt(i));
            }
        }

        return resultBuilder.toString();
    }

    private String getHardestWords(TreeMap<String, ArrayList<String>> allowedWords) {

        return null;
    }

    private String getMediumWords(TreeMap<String, ArrayList<String>> allowedWords) {

        return null;
    }

    private String getEasyWords(TreeMap<String, ArrayList<String>> allowedWords) {

        return null;
    }
}