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

import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;
import java.util.TreeMap;
import java.util.Map;

/**
 * Manages the details of EvilHangman. This class keeps track of the possible
 * words from a dictionary during rounds of hangman, based on guesses so far.
 */
public class HangmanManager {

    private boolean debugOn;

    private TreeMap<Integer, ArrayList<String>> wordPatterns;
    private ArrayList<String> currWords;

    private TreeSet<Character> guessesMade;

    private int wordLen;
    private int numGuesses;
    private int turn;

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
        if (words == null || words.isEmpty()) {
            throw new IllegalArgumentException("Words cannot be null and"
                    + " its length must be greater than 0.");
        }

        this.wordPatterns = new TreeMap<>();

        // Copying the Values
        for (String word : words) {

            // New Entry
            if (wordPatterns.get(word.length()) == null) {
                this.wordPatterns.put(word.length(), new ArrayList<>());
                this.wordPatterns.get(word.length()).add(word);

                // Updating an Entry
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
        ArrayList<String> listAtLen = this.wordPatterns.get(length);
        if (listAtLen == null) {
            return 0;
        }
        return listAtLen.size();
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

        // Resetting Variables
        this.diff = diff;

        this.turn = 0;
        this.numGuesses = numGuesses;

        this.wordLen = wordLen;
        this.currWords = wordPatterns.get(wordLen);

        this.guessesMade = new TreeSet<>();

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
        return this.numGuesses;
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
            throw new IllegalStateException("Already Guessed the Character");
        }

        // Saving Previous Mask
        String prevMask = this.wordMask;

        // Getting List of Words with Guesses
        TreeMap<String, ArrayList<String>> allowedWords = new TreeMap<>();
        for (String word : this.currWords) {
            String currMask = getNewMaskedWord(guess, word);

            if (allowedWords.get(currMask) == null) {
                allowedWords.put(currMask, new ArrayList<>());
            }

            allowedWords.get(currMask).add(word);
        }

        // Sorted Set Based on the CompareFamilies Orderings
        TreeSet<ComparableFamilies> sortedFamilies = new TreeSet<>();
        TreeMap<String, Integer> resultsMap = new TreeMap<>();
        for (Map.Entry<String, ArrayList<String>> entry : allowedWords.entrySet()) {
            String currFamily = entry.getKey();
            ArrayList<String> currFamilyList = entry.getValue();
            sortedFamilies.add(new ComparableFamilies(currFamily, currFamilyList));
            resultsMap.put(currFamily, currFamilyList.size());
        }

        // Get the Best Result based on the Difficulty
        if (this.diff == HangmanDifficulty.HARD) {
            this.wordMask = getHardestWords(sortedFamilies);
        } else if (this.diff == HangmanDifficulty.MEDIUM) {
            int numRounds = 4, specialRound = 3;
            this.wordMask = getNonHardDiff(sortedFamilies, numRounds, specialRound);
        } else {
            int numRounds = 2, specialRound = 1;
            this.wordMask = getNonHardDiff(sortedFamilies, numRounds, specialRound);
        }

        // Updating Game States
        this.currWords = allowedWords.get(this.wordMask);
        this.guessesMade.add(guess);
        if (this.wordMask.equals(prevMask)) {
            this.numGuesses--;
        }

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

        // Precondition
        if (currWords.isEmpty()) {
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
     * @return - The String representation of the word of the updated masked
     *         pattern.
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

    /**
     * 
     * @param sortedFamilies
     * @return
     */
    private String getHardestWords(TreeSet<ComparableFamilies> sortedFamilies) {
        TreeMap<String, Integer> result = new TreeMap<>();
        ComparableFamilies lastFamily = sortedFamilies.last();
        return lastFamily.getFamily();
    }

    /**
     * 
     * @param sortedFamilies
     * @param numRounds
     * @param specialRound
     * @return
     */
    private String getNonHardDiff(TreeSet<ComparableFamilies> sortedFamilies,
            int numRounds, int specialRound) {

        ComparableFamilies family = sortedFamilies.last();

        if (this.turn % numRounds == specialRound && sortedFamilies.lower(family) != null) {
            family = sortedFamilies.lower(family);
        }

        this.turn++;
        return family.getFamily();
    }

    private class ComparableFamilies implements Comparable<ComparableFamilies> {

        private final String family;
        private final ArrayList<String> familyList;

        /**
         * 
         * @param family
         * @param familyList
         */
        public ComparableFamilies(String family, ArrayList<String> familyList) {

            // Preconditions
            if (family == null || familyList == null) {
                throw new IllegalArgumentException("Family nor FamilyList may be Empty");
            }

            this.family = family;
            this.familyList = familyList;
        }

        /**
         * 
         * @param other
         * @return
         */
        public int compareTo(ComparableFamilies other) {

            // Getting the Size of Each ArrayList
            if (this.familyList.size() > other.familyList.size()) {
                return 1;
            }

            // Compare Based on Number of Words in Each List
            if (this.familyList.size() == other.familyList.size()) {
                if (this.getNumHidden() > other.getNumHidden()) {
                    return 1;
                }

                // Compare Lexographically
                if (this.getNumHidden() == other.getNumHidden()) {
                    return this.family.compareTo(other.family);
                }
            }

            return -1;
        }

        /**
         * 
         * @return
         */
        public int getNumHidden() {
            char HIDDEN_CHAR = '-';
            int result = 0;

            for (int i = 0; i < this.family.length(); i++) {
                if (this.family.charAt(i) == HIDDEN_CHAR) {
                    result++;
                }
            }

            return result;
        }

        /**
         * 
         * @return
         */
        public String getFamily() {
            return this.family;
        }

        /**
         * 
         * @return
         */
        public ArrayList<String> getFamilyList() {
            return this.familyList;
        }
    }
}