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
import java.util.Scanner;

/**
 * A collection of NameRecords.
 * Stores NameRecord objects and provides methods to select NameRecords based
 * on various criteria.
 */
public class Names {

    private ArrayList<NameRecord> nameRecords;

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
        
        String emptyLine = "\n";
        int baseYear = sc.nextInt();
        int numDecades = sc.nextInt();
        while (sc.hasNextLine()) {
            String rawRecords = sc.nextLine();
            NameRecord currName = new NameRecord(baseYear, numDecades, rawRecords);
            if (currName.getNumDecades() == numDecades && currName.getName().equals(emptyLine)) {
                nameRecords.add(currName);
            }
        }
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

    }

    /**
     * Returns an ArrayList of Strings of names that have been ranked in the
     * top 1000 or better for every decade. The Strings must be in sorted
     * order based on the name of the NameRecords.
     *
     * @return A list of the names that have been ranked in the top 1000 or
     *         better in every decade. The list is in sorted ascending order. If
     *         there
     *         are no NameRecords that meet this criteria, returns an empty list.
     */
    public ArrayList<String> rankedEveryDecade() {

    }

    /**
     * Returns an ArrayList of Strings of names that have been ranked in the
     * top 1000 or better in exactly one decade. The Strings must be in sorted
     * order based on the name of the NameRecords.
     *
     * @return A list of the names that have been ranked in the top 1000 or
     *         better in exactly one decade. The list is in sorted ascending order.
     *         If
     *         there are no NameRecords that meet this criteria, returns an empty
     *         list.
     */
    public ArrayList<String> rankedOnlyOneDecade() {

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

    }

    /**
     * Return the NameRecord in this Names object that matches the given String
     * ignoring case.
     *
     * pre: name != null
     *
     * @param name The name to search for.
     * @return The name record with the given name or null if no NameRecord in
     *         this Names object contains the given name.
     */
    public NameRecord getName(String name) {
        if (name == null) {
            throw new IllegalArgumentException("The parameter name cannot be null");
        }
    }
}