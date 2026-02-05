/*
 * Names.java - CS 314 Assignment 3
 *
 * By signing my name below, I affirm that this assignment is my own work. I
 * have neither given nor received unauthorized assistance on this assignment.
 *
 * Name: Pheanouk Hun
 * Email address: ph23434@my.utexas.edu
 * UTEID: ph23434
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * A collection of NameRecords.
 * Stores NameRecord objects and provides methods to select NameRecords based
 * on various criteria.
 */
public class Names {

    private ArrayList<NameRecord> names;

    /**
     * Construct a new Names object based on the data source the Scanner sc is
     * connected to. Assume the first two lines in the data source are the base
     * year and number of decades to use. Any lines without the correct number
     * of decades are discarded and are not part of the resulting Names object.
     * Any names with ranks of all 0 are discarded and not part of the
     * resulting Names object.
     *
     * @param sc Is connected to a data file with baby names
     *           and positioned at the start of the data source.
     */
    public Names(Scanner sc) {

        names = new ArrayList<>();

        int baseYear = sc.nextInt();
        int numDecades = sc.nextInt();

        while (sc.hasNextLine()) {
            String rawRecords = sc.nextLine().trim();

            NameRecord currName = new NameRecord(baseYear, rawRecords);
            if (!(currName.getNumDecades() != numDecades || currName.getNumRanked() == 0)) {
                names.add(currName);
            }
        }

        Collections.sort(names);
    }

    public Names(ArrayList<NameRecord> names) {
        this.names = names;
    }

    /**
     * Returns an ArrayList of NameRecord objects that contain a given
     * substring, ignoring case. The names must be in sorted order based on
     * the names of the NameRecords.
     *
     * @param partialName != null, partialName.length() > 0
     * @return an ArrayList of NameRecords whose names contains partialName. If
     *         there are no NameRecords that meet this criteria, returns an empty
     *         list.
     */
    public ArrayList<NameRecord> getMatches(String partialName) {

        // Checking Preconditions
        if (partialName == null || partialName.length() == 0) {
            throw new IllegalArgumentException("The Partial Name String cannot "
                    + "be null or empty.");
        }

        partialName = partialName.toLowerCase();
        ArrayList<NameRecord> matches = new ArrayList<>();

        // Adding all the Matches to the Array List
        for (int i = 0; i < names.size(); i++) {
            NameRecord currName = names.get(i);
            String currNameStr = currName.getName().toLowerCase();
            if (currNameStr.indexOf(partialName) != -1) {
                matches.add(currName);
            }
        }

        return matches;
    }

    /**
     * Returns an ArrayList of Strings of names that have been ranked in the
     * top 1000 or better for every decade. The Strings must be in sorted
     * order based on the name of the NameRecords.
     *
     * @return A list of the names that have been ranked in the top 1000 or
     *         better in every decade. The list is in sorted ascending order. If
     *         there are no NameRecords that meet this criteria, returns an empty
     *         list.
     */
    public ArrayList<String> rankedEveryDecade() {

        ArrayList<String> alwaysRankedNames = new ArrayList<>();

        // Searching Through the Names List
        for (int i = 0; i < names.size(); i++) {
            NameRecord currName = names.get(i);
            if (currName.isAlwaysRanked()) {
                alwaysRankedNames.add(currName.getName());
            }
        }

        return alwaysRankedNames;
    }

    /**
     * Returns an ArrayList of Strings of names that have been ranked in the
     * top 1000 or better in exactly one decade. The Strings must be in sorted
     * order based on the name of the NameRecords.
     *
     * @return A list of the names that have been ranked in the top 1000 or
     *         better in exactly one decade. The list is in sorted ascending order.
     *         If there are no NameRecords that meet this criteria, returns an empty
     *         list.
     */
    public ArrayList<String> rankedOnlyOneDecade() {

        ArrayList<String> rankedOnlyOnceNames = new ArrayList<>();

        // Searching Through the Names List
        for (int i = 0; i < names.size(); i++) {
            NameRecord currName = names.get(i);
            if (currName.isOnlyRankedOnce()) {
                rankedOnlyOnceNames.add(currName.getName());
            }
        }

        return rankedOnlyOnceNames;
    }

    /**
     * Returns an ArrayList of Strings of names that have been getting more
     * popular every decade. The Strings must be in sorted order based on the
     * name of the NameRecords.
     *
     * @return A list of the names that have been getting more popular in every
     *         decade. The list is in sorted ascending order. If there are no
     *         NameRecords that meet this criteria, returns an empty list.
     */
    public ArrayList<String> alwaysMorePopular() {

        ArrayList<String> alwaysIncreasePopNames = new ArrayList<>();

        // Searching Through the Names List
        for (int i = 0; i < names.size(); i++) {
            NameRecord currName = names.get(i);
            if (currName.isIncreasingInPopularity()) {
                alwaysIncreasePopNames.add(currName.getName());
            }
        }

        return alwaysIncreasePopNames;
    }

    /**
     * Returns an ArrayList of Strings of names that have been getting less
     * popular every decade. The Strings must be in sorted order based on the
     * name of the NameRecords.
     *
     * @return A list of the names that have been getting less popular in every
     *         decade. The list is in sorted ascending order. If there are no
     *         NameRecords that meet this criteria, returns an empty list.
     */
    public ArrayList<String> alwaysLessPopular() {

        ArrayList<String> alwaysDecreasePopNames = new ArrayList<>();

        // Searching Through the Names List
        for (int i = 0; i < names.size(); i++) {
            NameRecord currName = names.get(i);
            if (currName.isDecreasingInPopularity()) {
                alwaysDecreasePopNames.add(currName.getName());
            }
        }

        return alwaysDecreasePopNames;
    }

    /**
     * Return the NameRecord in this Names object that matches the given String
     * ignoring case.
     *
     * @param name The name to search for. name != null
     * @return The name record with the given name or null if no NameRecord in
     *         this Names object contains the given name.
     */
    public NameRecord getName(String name) {

        // Checking Proconditions
        if (name == null) {
            throw new IllegalArgumentException("The parameter name cannot be null");
        }

        // Searching Through the Names List
        for (int i = 0; i < names.size(); i++) {
            NameRecord currName = names.get(i);
            String currNameStr = currName.getName().toLowerCase();
            if (currNameStr.equals(name.toLowerCase())) {
                return currName;
            }
        }

        // When there are No Name Records Found
        return null;
    }

    public ArrayList<NameRecord> namesWithAllVowels() {
        final String VOWELS = "aeiou"; 
        
        ArrayList<NameRecord> filteredRecords = getMatches(VOWELS.charAt(0) + "");
        Names filterNamesDB = new Names(filteredRecords);
        for (int i = 1; i < VOWELS.length(); i++) {
            filteredRecords = filterNamesDB.getMatches(VOWELS.charAt(i) + "");
            filterNamesDB = new Names(filteredRecords);
        }

        return filteredRecords;
    }
}