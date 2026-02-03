/*
 * NameSurfer.java - CS 314 Assignment 3
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

public class NameRecord implements Comparable<NameRecord> {

    // Private Properties
    private String name;
    private int baseYear;
    private ArrayList<Integer> rankings;
    private int numDecades;

    // Constructor

    /**
     * 
     * @param baseYear
     * @param rawData
     */
    public NameRecord(int baseYear, String rawData) {

        this.baseYear = baseYear;
        rankings = new ArrayList<>();

        Scanner scLine = new Scanner(rawData);
        System.out.println("test");
        if (scLine.hasNext()) {
            name = scLine.next();
            while (scLine.hasNextInt()) {
                rankings.add(scLine.nextInt());
            }
        }

        scLine.close();
        numDecades = rankings.size();
    }

    // Accessor Methods

    /**
     * 
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * 
     * @return
     */
    public int getBaseYear() {
        return baseYear;
    }

    /**
     * 
     * @return
     */
    public int getNumDecades() {
        return numDecades;
    }

    /**
     * 
     * @param decade - Int value; 0 <= decade < getNumDecades()
     * @return
     */
    public int getDecadeNameRank(int decade) {

        // Checking Preconditions
        if (decade < 0 || decade >= getNumDecades()) {
            throw new IllegalArgumentException("The decade parameter must be greater "
                    + "than or equal to 0 and less than getNumDecades()");
        }

        return rankings.get(decade);
    }

    public int getMostPopularDecade() {

        int mostPopularIndex = 0;

        while (getDecadeNameRank(mostPopularIndex) == 0 && mostPopularIndex < getNumDecades()) {
            mostPopularIndex++;
        }

        for (int i = mostPopularIndex; i < getNumDecades(); i++) {
            if (getDecadeNameRank(i) <= mostPopularIndex) {
                mostPopularIndex = i;
            }
        }

        int mostRecentPopYear = getBaseYear() + (mostPopularIndex * 10);
        return mostRecentPopYear;
    }

    public int getNumRanked() {

        int numTimes = 0;

        for (int i = 0; i < getNumDecades(); i++) {
            if (getDecadeNameRank(i) != 0) {
                numTimes++;
            }
        }

        return numTimes;
    }

    public boolean isAlwaysRanked() {
        boolean result = getNumRanked() == getNumDecades();
        return result;
    }

    public boolean isOnlyRankedOnce() {
        boolean result = getNumRanked() == 1;
        return result;
    }

    public boolean isIncreasingInPopularity() {

        int prev = getDecadeNameRank(0);
        for (int i = 1; i < getNumDecades(); i++) {
            int curr = getDecadeNameRank(i);
            if (prev < curr || (prev != 0 && curr == 0)) {
                return false;
            }
            prev = curr;
        }
        return true;
    }

    // TODO: ASK ABOUT THE EXAMPLE GIVEN IN THE ASSIGNMENT PDF AND WHY IS IT FALSE;
    public boolean isDecreasingInPopularity() {

        int prev = getDecadeNameRank(0);
        for (int i = 1; i < getNumDecades(); i++) {
            int curr = getDecadeNameRank(i);
            if (prev > curr || (prev == 0 && curr == 0) || (curr != 0 && prev > curr)) {
                return false;
            }
            prev = curr;
        }
        return true;
    }

    public String toString() {
        StringBuilder results = new StringBuilder(name + "\n");
        for (int i = 0; i < getNumDecades(); i++) {
            results.append((baseYear + (i * 10)) + ": " + getDecadeNameRank(i) + "\n");
        }
        return results.toString();
    }

    public int compareTo(NameRecord other) {
        int result = getName().compareTo(other.getName());
        return result;
    }
}