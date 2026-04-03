/*
 * AnagramSolver.java - CS 314 Assignment 7
 *
 * By signing my/our name(s) below, I/we affirm that this assignment is my/our
 * own work. I/we have neither given nor received unauthorized assistance on
 * this assignment.
 *
 * Name 1: Pheanouk Hun
 * Email address 1: ph23434@eid.utexas.edu
 * UTEID 1: ph23434
 */

/**
 * This class represents an Inventory of all the english letters found inside a
 * word. It stores the frequency of all letters from 'a' to 'z' (Case
 * Insensitive).
 */
public class LetterInventory {

    // Class Constants and Varaibles of the Class
    final int ALPHABET_SIZE = 26;
    int[] counts;
    int size;

    /**
     * Public Constructor that takes in a Word and Generate a Frequency Array Based
     * on the Word. It will only take in the characters that letters and no other
     * characters.
     * 
     * @param word - A String of Letters and Any other Characters
     */
    public LetterInventory(String word) {

        // Preconditions
        if (word == null) {
            throw new IllegalArgumentException("Word Cannot be Null.");
        }

        word = word.toLowerCase();
        counts = new int[ALPHABET_SIZE];

        for (int i = 0; i < word.length(); i++) {
            char currChar = word.charAt(i);
            if ('a' <= currChar && currChar <= 'z') {
                counts[currChar - 'a']++;
                size++;
            }
        }
    }

    /**
     * Privte Constructor Method that takes in an already constructed frequency
     * array and size.
     * 
     * @param counts - The frequency array for the new LetterInventory Object
     * @param size   - The new size of the LetterInventory Object.
     */
    private LetterInventory(int[] counts, int size) {
        this.counts = counts;
        this.size = size;
    }

    /**
     * Gets the Frequency at which the letter appears
     * 
     * @param needed_char - The char being requested, needs to be a English letter
     * @return - The Number of times the char is found in the LetterInventory.
     */
    public int get(char needed_char) {

        // Preconditions
        if (!Character.isLetter(needed_char)) {
            throw new IllegalArgumentException("Argument is not an Alphabetical Character");
        }

        return counts[Character.toLowerCase(needed_char) - 'a'];
    }

    /**
     * Returns the Size of the LetterInventory
     * 
     * @return - Returns the Size of the LetterInventory
     */
    public int size() {
        return size;
    }

    /**
     * Checks to See if the LetterInventory is Empty or Not
     * 
     * @return - Boolean Value based on the Emptiness of the LetterInventory.
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Shows the String Representation of the LetterInventory.
     * 
     * @return - The String Representation of LetterInventory.
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < ALPHABET_SIZE; i++) {
            for (int j = 0; j < counts[i]; j++) {
                sb.append((char) ('a' + i));
            }
        }
        return sb.toString();
    }

    /**
     * Returns a new LetterInventory where the Frequency is the sum of both
     * LetterInventory Objects Given to It.
     * 
     * @param other - The other LetterInventory Object that's frequency will be
     *              added to the current LetterInventory Object. other != null
     * @return - A new LetterFrquency Object that is the sum of both objects.
     */
    public LetterInventory add(LetterInventory other) {

        // Precondition
        if (other == null) {
            throw new IllegalArgumentException("the Other Inventory cannot be null");
        }

        // New LetterInventory Object
        int[] newCounts = new int[ALPHABET_SIZE];
        int newSize = 0;

        for (int i = 0; i < ALPHABET_SIZE; i++) {
            newCounts[i] = this.counts[i] + other.counts[i];
            newSize += newCounts[i];
        }

        return new LetterInventory(newCounts, newSize);
    }

    public LetterInventory subtract(LetterInventory other) {

        // Precondition
        if (other == null) {
            throw new IllegalArgumentException("the Other Inventory cannot be null.");
        }

        int[] newCounts = new int[ALPHABET_SIZE];
        int newSize = 0;

        for (int i = 0; i < ALPHABET_SIZE; i++) {
            newCounts[i] = this.counts[i] - other.counts[i];
            if (newCounts[i] < 0) {
                return null;
            }
            newSize += newCounts[i];
        }

        return new LetterInventory(newCounts, newSize);
    }

    public boolean equals(Object other) {

        if (this == other) {
            return true;
        }

        if (!(other instanceof LetterInventory)) {
            return false;
        }

        LetterInventory otherLet = (LetterInventory) other;

        if (this.size != otherLet.size) {
            return false;
        }

        for (int i = 0; i < ALPHABET_SIZE; i++) {
            if (this.counts[i] != otherLet.counts[i]) {
                return false;
            }
        }

        return true;
    }
}
