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

public class LetterInventory {

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

    private LetterInventory(int[] counts, int size) {
        this.counts = counts;
        this.size = size;
    }

    public int get(char needed_char) {

        // Preconditions
        if (!Character.isLetter(needed_char)) {
            throw new IllegalArgumentException("Argument is not an Alphabetical Character");
        }

        return counts[Character.toLowerCase(needed_char) - 'a'];
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < ALPHABET_SIZE; i++) {
            for (int j = 0; j < counts[i]; j++) {
                sb.append((char) ('a' + i));
            }
        }
        return sb.toString();
    }

    public LetterInventory add(LetterInventory other) {

        // Precondition
        if (other == null) {
            throw new IllegalArgumentException("the Other Inventory cannot be null");
        }

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
