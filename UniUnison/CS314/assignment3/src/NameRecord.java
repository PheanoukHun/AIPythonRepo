/*
 * NameRecord.java - CS 314 Assignment 3
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

    // Constructor
    /**
     * This method creates a NameRecord Object that takes in the base year the name
     * was first added to the census and a rawData string that contains the name and
     * its popularity over the decades.
     * 
     * @param baseYear v
     * @param rawData
     */
    public NameRecord(int baseYear, String rawData) {

        this.baseYear = baseYear;
        rankings = new ArrayList<>();

        Scanner scLine = new Scanner(rawData);
        if (scLine.hasNext()) {
            name = scLine.next();
            while (scLine.hasNextInt()) {
                rankings.add(scLine.nextInt());
            }
        }

        scLine.close();
    }

    // Accessor Methods

    /**
     * Returns the Name of the NameRecord
     * 
     * @return Returns the Name of the NameRecord
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the Base Year the Name was recorded in
     * 
     * @return Returns the Base Year the Name was recorded in
     */
    public int getBaseYear() {
        return baseYear;
    }

    /**
     * 
     * @return
     */
    public int getNumDecades() {
        return rankings.size();
    }

    /**
     * 
     * @param decade - Int value; 0 <= decade < getNumDecades()
     * @return
     */
    public int getRank(int decade) {

        // Checking Preconditions
        if (decade < 0 || decade >= getNumDecades()) {
            throw new IllegalArgumentException("The decade parameter must be greater "
                    + "than or equal to 0 and less than getNumDecades()");
        }

        return rankings.get(decade);
    }

    public int getMostPopDec() {

        final int LEN_DECADE = 10;
        int mostPopularIndex = 0;

        while (getRank(mostPopularIndex) == 0 && mostPopularIndex < getNumDecades()) {
            mostPopularIndex++;
        }

        int currMinRank = getRank(mostPopularIndex);
        for (int i = 0; i < getNumDecades(); i++) {
            int currRank = getRank(i);
            if (currRank <= currMinRank && currRank != 0) {
                mostPopularIndex = i;
                currMinRank = currRank;
            }
        }

        int mostRecentPopYear = getBaseYear() + (mostPopularIndex * LEN_DECADE);
        return mostRecentPopYear;
    }

    public int getNumRanked() {

        int numTimes = 0;

        for (int i = 0; i < getNumDecades(); i++) {
            if (getRank(i) != 0) {
                numTimes++;
            }
        }

        return numTimes;
    }

    public boolean alwaysRanked() {
        boolean result = getNumRanked() == getNumDecades();
        return result;
    }

    public boolean isOnlyRankedOnce() {
        boolean result = getNumRanked() == 1;
        return result;
    }

    public boolean alwayMorePop() {

        int prev = getRank(0);
        for (int i = 1; i < getNumDecades(); i++) {
            int curr = getRank(i);
            if (prev < curr || (prev != 0 && curr == 0)) {
                return false;
            }
            prev = curr;
        }
        return true;
    }

    public boolean alwaysLessPopular() {

        int prev = getRank(0);
        for (int i = 1; i < getNumDecades(); i++) {
            int curr = getRank(i);
            if ((prev != 0 && curr != 0 && curr <= prev) || (prev == 0 && curr == 0)
                    || (prev == 0 && curr != 0)) {
                return false;
            }
            prev = curr;
        }
        return true;
    }

    public double getAvgRank() {
        double average = 0;
        int sum = 0;
        int count = 0;

        for (int i = 0; i < getNumDecades(); i++) {
            if (getRank(i) != 0) {
                sum += getRank(i);
                count++;
            }
        }

        average = sum / count;

        return average;
    }

    public double getSDev() {

        double mean = getAvgRank();
        double sum = 0.0;

        // Summation of (x_i - mean)^2
        for (int i = 0; i < getNumDecades(); i++) {
            int currVal = getRank(i);
            sum += Math.pow(currVal - mean, 2);
        }

        double variance = sum / (getNumDecades() - 1);
        return Math.sqrt(variance);
    }

    public String toString() {

        final int LEN_DECADE = 10;

        StringBuilder results = new StringBuilder(name + "\n");
        for (int i = 0; i < getNumDecades(); i++) {
            results.append((baseYear + (i * LEN_DECADE)) + ": " + getRank(i) + "\n");
        }
        return results.toString();
    }

    public int compareTo(NameRecord other) {

        // Checking Precondition
        if (other == null) {
            throw new IllegalArgumentException("The parameter cannot be null.");
        }

        int result = getName().compareTo(other.getName());
        return result;
    }
}