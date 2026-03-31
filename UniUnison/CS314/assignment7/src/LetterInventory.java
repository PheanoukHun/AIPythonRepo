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

    int[] numOccured;
    final int ALPHABET_SIZE = 26;

    public LetterInventory(String word) {

        int[] numOccured = new int[ALPHABET_SIZE];
        for (int i = 0; i < word.length(); i++) {
            char currChar = word.charAt(i);
            if (currChar >= 'a' && currChar <= 'z') {
                numOccured[currChar - 'a']++;
            }
        }
    }

    public int getFrequency(char needed_char) {
        return numOccured[needed_char];
    }
}
