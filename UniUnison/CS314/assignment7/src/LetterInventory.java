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

    public LetterInventory(String word) {

        // Preconditions
        if (word == null) {
            throw new IllegalArgumentException("Word Cannot be Null.");
        }

        counts = new int[ALPHABET_SIZE];

        for (int i = 0; i < size; i++) {
            char currChar = word.charAt(i);
            if ('a' <= currChar && currChar <= 'z') {
                counts[currChar - 'a']++;
                size++;
            }
        }
    }

    public int get(char needed_char) {
        
        // Preconditions
        if ('a' <= needed_char && needed_char <= 'z') {
            throw new IllegalArgumentException("Argument is not an Alphabetical Character");
        }
        
        return counts[needed_char];
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
            throw new IllegalArgumentException("Adding the Other Inventory cannot be null");
        }

        for 
    }
}
