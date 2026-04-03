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

import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class AnagramSolver {

    private final Set<String> dictionary;

    /*
     * pre: list != null
     * @param list Contains the words to form anagrams from.
     */
    public AnagramSolver(Set<String> dictionary) {

        if (dictionary == null) {
            throw new IllegalArgumentException("Dictionary Cannot Equal Null.");
        }

        this.dictionary = dictionary;
    }

    /*
     * Return a list of anagrams that can be formed from s with no more than
     * maxWords, unless maxWords is 0 in which case there is no limit on the
     * number of words in the anagram.
     * pre: maxWords >= 0, s != null, s contains at least one English letter.
     */
    public List<List<String>> getAnagrams(String s, int maxWords) {

        LetterInventory sInv = new LetterInventory(s);

        if (maxWords < 0 || sInv.size() == 0) {
            throw new IllegalArgumentException("Word must have At Least one English Character"
                    + " and Max Word must be >0.");
        }

        return null; // TODO: Change as necessary
    }

    private void getAnagramHeler(LetterInventory sInv, int maxWords, ) {
        if (maxWords != 0 && sInv.size() != 0) {
            for (String word : dictionary) {
                LetterInventory result = sInv.subtract(new LetterInventory(word));
                if (result != null) {

                }
            }
        }
    }
}