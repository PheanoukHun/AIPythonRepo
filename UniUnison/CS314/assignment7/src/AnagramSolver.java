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

    private final Map<String, LetterInventory> dictionary;

    /*
     * pre: list != null
     * 
     * @param list Contains the words to form anagrams from.
     */
    public AnagramSolver(Set<String> dictionary) {

        if (dictionary == null) {
            throw new IllegalArgumentException("Dictionary Cannot Equal Null.");
        }

        this.dictionary = new HashMap<>();
        for (String word : dictionary) {
            this.dictionary.put(word, new LetterInventory(word));
        }
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

        // Get Candidates and Creates a Result List
        List<List<String>> results = new ArrayList<>();
        List<String> candidates = new ArrayList<>();
        
        for (String word : dictionary.keySet()) {
            if (sInv.subtract(dictionary.get(word)) != null) {
                candidates.add(word);
            }
        }

        // Solve Anagrams
        getAnagramHeler(sInv, candidates, new ArrayList<>(), results, maxWords, 0);
        return results;
    }

    private void getAnagramHeler(LetterInventory sInv, List<String> candidates,
            List<String> currList, List<List<String>> results, int maxWords, int start) {

        // Base Case (Invalid, Successful)
        if (sInv.size() == 0 && maxWords == 0) {
            results.add(currList);
        } else if (sInv.size() == 0 && maxWords != 0) {

        } else {
            for (int i = start; i < candidates.size(); i++) {
                
            }
        }

        if (maxWords != 0 && sInv.size() != 0 && candidates.size() != 0) {

        }
    }

    private static class AnagramComparator implements Comparator<List<String>> {
        public int compare(List<String> listOne, List<String> listTwo) {

            // Comparing Size
            if (listOne.size() != listTwo.size()) {
                return listOne.size() - listTwo.size();
            }

            // Comparing Elements
            for (int i = 0; i < listOne.size(); i++) {
                int compared = listOne.get(i).compareTo(listTwo.get(i));
                if (compared != 0) {
                    return compared;
                }
            }

            return 0;
        }
    }
}