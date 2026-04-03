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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class AnagramSolverAI {

    private final List<String> dictionary;
    private final Map<String, LetterInventory> inventories;

    /**
     * Create an AnagramSolver with the given dictionary.
     * pre: dictionary != null
     * @param dictionary the dictionary to use for finding anagrams
     */
    public AnagramSolverAI(Set<String> dictionary) {
        if (dictionary == null) {
            throw new IllegalArgumentException("Dictionary Cannot Equal Null.");
        }
        // Store dictionary in a list to maintain order (TreeSet order from AnagramMain)
        this.dictionary = new ArrayList<String>(dictionary);
        this.inventories = new HashMap<String, LetterInventory>();
        // Pre-calculate inventories for all words in the dictionary for efficiency
        for (String word : this.dictionary) {
            this.inventories.put(word, new LetterInventory(word));
        }
    }

    /**
     * Get all anagrams of the given string that have at most maxWords words.
     * If maxWords is 0, there is no limit on the number of words.
     * pre: maxWords >= 0, s != null, s contains at least one English letter
     * @param s the string to find anagrams of
     * @param maxWords the maximum number of words in an anagram (0 for no limit)
     * @return a list of all anagrams found
     */
    public List<List<String>> getAnagrams(String s, int maxWords) {
        if (s == null) {
            throw new IllegalArgumentException("String cannot be null.");
        }
        LetterInventory sInv = new LetterInventory(s);
        if (maxWords < 0 || sInv.size() == 0) {
            throw new IllegalArgumentException("Word must have At Least one English Character"
                    + " and Max Word must be >= 0.");
        }

        // Pre-processing: build a smaller candidate dictionary for the given phrase
        List<String> candidates = new ArrayList<String>();
        List<LetterInventory> candidateInvs = new ArrayList<LetterInventory>();
        for (String word : dictionary) {
            LetterInventory wordInv = inventories.get(word);
            if (sInv.subtract(wordInv) != null) {
                candidates.add(word);
                candidateInvs.add(wordInv);
            }
        }

        List<List<String>> allAnagrams = new ArrayList<List<String>>();
        findAnagrams(sInv, maxWords, 0, candidates, candidateInvs,
                new ArrayList<String>(), allAnagrams);
        return allAnagrams;
    }

    /*
     * Recursive helper method to find anagrams using backtracking.
     */
    private void findAnagrams(LetterInventory remaining, int maxWords, int startIndex,
            List<String> candidates, List<LetterInventory> candidateInvs,
            List<String> currentWords, List<List<String>> allAnagrams) {

        // Base case: no letters remain, valid anagram found
        if (remaining.isEmpty()) {
            allAnagrams.add(new ArrayList<String>(currentWords));
        } else if (maxWords == 0 || currentWords.size() < maxWords) {
            // Recursive step: try candidate words from startIndex onward
            for (int i = startIndex; i < candidates.size(); i++) {
                LetterInventory nextRemaining = remaining.subtract(candidateInvs.get(i));
                if (nextRemaining != null) {
                    currentWords.add(candidates.get(i));
                    findAnagrams(nextRemaining, maxWords, i, candidates, candidateInvs,
                            currentWords, allAnagrams);
                    // Backtrack: remove last added word
                    currentWords.remove(currentWords.size() - 1);
                }
            }
        }
    }
}
