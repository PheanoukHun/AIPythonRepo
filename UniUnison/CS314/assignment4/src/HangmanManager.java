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

import java.util.Map;
import java.util.TreeMap;

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

    private final char HIDDEN_CHAR = '-';

    private final int MED_NUM_RNDS = 4;
    private final int MED_SPEC_RND = 3;

    private final int EASY_NUM_RNDS = 2;
    private final int EASY_SPEC_RND = 1;

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
        for (int i = 0; i < wordLen; i++) {
            maskBuilder.append(this.HIDDEN_CHAR);
        }
        this.wordMask = maskBuilder.toString();

        if (this.debugOn) {
            System.out.println("\nDEBUGGING INSIDE prepForRound METHOD:");
            System.out.println("\tTurn Number: " + this.turn);
            System.out.println("\tCurrent Words Alive: " + this.currWords.toString());
            System.out.println("\tWord Mask: " + this.wordMask);
            System.out.println("DEBUGGING ENDED\n");
        }
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

        // Getting List of Words with Guesses Masked
        TreeMap<String, ArrayList<String>> allowedWords = getAllowedWords(guess);

        // Sorted Set Based on the CompareFamilies Orderings and Results for Debugging
        // Purposes
        TreeSet<ComparableFamilies> sortedFamilies = new TreeSet<>();
        TreeMap<String, Integer> resultsMap = new TreeMap<>();

        for (Map.Entry<String, ArrayList<String>> entry : allowedWords.entrySet()) {
            String currFamily = entry.getKey();
            ArrayList<String> currFamilyList = entry.getValue();
            sortedFamilies.add(new ComparableFamilies(currFamily, currFamilyList));
            resultsMap.put(currFamily, currFamilyList.size());
        }

        // Get the Best Result based on the Difficulty
        playBasedDifficulty(sortedFamilies);

        // Updating Game States
        updateGameStates(allowedWords, prevMask, guess);

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
     * Groups all the Currently Alive Words based on the Mask based on the guess and
     * Previous Mask. The Map created based on that is then returned for use in the
     * Make Guess Method.
     * 
     * @param guess - The character being guessed currently.
     * @return - A Map containing all the possible masks as the key set and an
     *         ArrayList of Words that share the same word mask as the value set.
     */
    private TreeMap<String, ArrayList<String>> getAllowedWords(char guess) {

        TreeMap<String, ArrayList<String>> allowedWords = new TreeMap<>();

        for (String word : this.currWords) {
            String currMask = getNewMaskedWord(guess, word);

            if (allowedWords.get(currMask) == null) {
                allowedWords.put(currMask, new ArrayList<>());
            }

            allowedWords.get(currMask).add(word);
        }

        return allowedWords;
    }

    /**
     * This method checks the difficulty of the Current Game and runs the Method
     * that correspond with that difficulty.
     * 
     * @param sortedFamilies - A TreeSet of sorted ComparableFamilies Objects which
     *                       contains all masks and its corresponding ArrayLists.
     */
    private void playBasedDifficulty(TreeSet<ComparableFamilies> sortedFamilies) {
        if (this.diff == HangmanDifficulty.HARD) {
            this.wordMask = getHardDiffs(sortedFamilies);
        } else if (this.diff == HangmanDifficulty.MEDIUM) {
            this.wordMask = getOtherDiffs(sortedFamilies, this.MED_NUM_RNDS, this.MED_SPEC_RND);
        } else {
            this.wordMask = getOtherDiffs(sortedFamilies, this.EASY_NUM_RNDS, this.EASY_SPEC_RND);
        }
    }

    /**
     * Updates all of the games states based on the current guess.
     * 
     * @param aliveWords - A TreeMap containing the currently alive and still
     *                   allowed for play.
     * @param prevMask   - The mask that is shown for the user in at the start of
     *                   the current round.
     * @param guess      - The character being guessed currently.
     */
    private void updateGameStates(TreeMap<String, ArrayList<String>> aliveWords,
            String prevMask, char guess) {
        this.currWords = aliveWords.get(this.wordMask);
        this.guessesMade.add(guess);
        if (this.wordMask.equals(prevMask)) {
            this.numGuesses--;
        }
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
     * This method gets the hardest word family based on the Conditions found in the
     * ComparableFamilies Class.
     * 
     * @param sortedFamilies - A TreeSet of sorted ComparableFamilies Objects which
     *                       contains all masks and its corresponding ArrayLists.
     * @return - A word mask that corresponds to the hardest mask family.
     */
    private String getHardDiffs(TreeSet<ComparableFamilies> sortedFamilies) {

        ComparableFamilies lastFamily = sortedFamilies.last();

        if (this.debugOn) {
            System.out.println("\nDEBUGGING: Picking hardest list.");
            System.out.println("DEBUGGING: New pattern is: " + lastFamily.getFamily()
                    + ". New family has " + lastFamily.getFamilyList().size() + " words.\n");
        }

        return lastFamily.getFamily();
    }

    /**
     * This method gets the hardest or Second Hardest word family based on the
     * Conditions found in the ComparableFamilies Class.
     * 
     * @param sortedFamilies - A TreeSet of sorted ComparableFamilies Objects which
     *                       contains all masks and its corresponding ArrayLists.
     * @param numRounds      - The Number of Rounds the Difficulty has per Cycle.
     * @param specialRound   - The Round where the Difficulty picks the second
     *                       hardest word family.
     * @return - A word mask that corresponds to the hardest or second hardes mask
     *         family.
     */
    private String getOtherDiffs(TreeSet<ComparableFamilies> sortedFamilies,
            int numRounds, int specialRound) {

        ComparableFamilies family = sortedFamilies.last();

        // Check if it is a Special Round
        if (this.turn % numRounds == specialRound) {

            // Debug
            if (this.debugOn) {
                System.out.println("\nDEBUGGING: Should pick second hardest pattern this turn,"
                        + " but only one pattern available.");
            }

            // If there is any other families left
            if (sortedFamilies.lower(family) != null) {
                family = sortedFamilies.lower(family);
            } else {
                if (this.debugOn) {
                    System.out.println("\nDEBUGGING: Picking hardest list.");
                }
            }

        } else {
            if (this.debugOn) {
                System.out.println("\nDEBUGGING: Picking hardest list.");
            }
        }

        // DEBUG
        if (this.debugOn) {
            System.out.println("DEBUGGING: New pattern is: " + family.getFamily()
                    + ". New family has " + family.getFamilyList().size() + " words.\n");
        }

        this.turn++;
        return family.getFamily();
    }

    /**
     * Represents a family of words that share the same mask
     */
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
    }
}