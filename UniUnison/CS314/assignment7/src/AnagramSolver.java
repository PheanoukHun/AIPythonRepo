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

import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

public class AnagramSolver {

    private final Set<String> dictionary;

    /*
     * pre: list != null
     * 
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

        // Preconditions Part 1
        if (maxWords < 0 || s == null) {
            throw new IllegalArgumentException("Word cannot be null and Max Word must be >0.");
        }

        // Get Letter Inventory for current Word
        LetterInventory sInv = new LetterInventory(s);

        // Preconditions Part 2
        if (sInv.size() == 0) {
            throw new IllegalArgumentException("There must be at least one English letter"
                    + " inside the word.");
        }

        // Get Candidates and Creates a Result

        Map<String, LetterInventory> candidates = new HashMap<>();

        for (String candidate : dictionary) {
            candidates.put(candidate, new LetterInventory(candidate));
        }

        candidates = getCandidates(sInv, candidates);
        List<List<String>> results = new ArrayList<>();

        // Solve Anagrams
        getAnagramHeler(sInv, candidates, results, maxWords, new ArrayList<>());
        return results;
    }

    private void getAnagramHeler(LetterInventory sInv, Map<String, LetterInventory> candidates,
            List<List<String>> results, int maxWords, ArrayList<String> currList) {

        // Base Case (Invalid, Successful)
        if (sInv.size() == 0 && maxWords == 0) {
            results.add();
        } else if (sInv.size() == 0 && maxWords != 0) {

        } else {
            for (String currCand : candidates.keySet()) {
                LetterInventory currInventory = sInv.subtract(candidates.get(currCand));
                Map<String, LetterInventory> newCands = getCandidates(sInv, candidates);
                getAnagramHeler(currInventory, newCands, results, maxWords - 1, currList);
            }
        }

        if (maxWords != 0 && sInv.size() != 0 && candidates.size() != 0) {

        }
    }

    /**
     * 
     * @param sInv
     * @param pastCand
     */
    private Map<String, LetterInventory> getCandidates(LetterInventory sInv,
            Map<String, LetterInventory> pastCand) {

        Map<String, LetterInventory> newCands = new HashMap<>();

        for (String currInventory : pastCand.keySet()) {
            if (sInv.subtract(pastCand.get(currInventory)) != null) {
                newCands.put(currInventory, pastCand.get(currInventory));
            }
        }

        return newCands;
    }

    private static class AnagramComparator implements Comparator<List<String>> {
        public int compare(List<String> listOne, List<String> listTwo) {
            if (listOne.size() != listTwo.size()) {
                return listOne.size() - listTwo.size();
            }
        }
    }
}