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

import org.omg.CORBA.UNKNOWN;

public class NameRecord implements Comparable<NameRecord> {

    // Private Properties
    private String name;
    private int baseYear;
    private ArrayList<Integer> rankings;

    // Private Variable
    final private int UNRANKED_VAL = 5000;

    // Constructor
    /**
     * This method creates a NameRecord Object that takes in the base year the name
     * was first added to the Popularity Data Rankings and a rawData string that
     * contains the name and
     * its popularity over the decades.
     * 
     * @param baseYear - The base year the name was first added to the Popularity
     *                 Data Rankings
     * @param rawData  - A string that contains the name and the rank for each each
     *                 in the Popularity Data Rankings
     */
    public NameRecord(int baseYear, String rawData) {

        this.baseYear = baseYear;
        rankings = new ArrayList<>();

        // Scans for Data
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
     * Returns the Name of the Current NameRecord Object.
     * 
     * @return - Returns the Name of the NameRecord
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the Base Year the Name was recorded in
     * 
     * @return - Returns the Base Year the Name was recorded in
     */
    public int getBaseYear() {
        return baseYear;
    }

    /**
     * Return the size of the rankings arraylist which is the number of decades that
     * each NameRecord have.
     * 
     * @return - An int that represents the number of times the name has been
     *         recorded in the Popularity Data Rankings
     */
    public int getNumDecades() {
        return rankings.size();
    }

    /**
     * This method returns the ranking a name has at in a particular decade
     * specificied by the client.
     * 
     * @param decade - Int value; 0 <= decade < getNumDecades(); The decade that
     *               user wants to know the ranking of for this particular name.
     * @return - An int value that represents the ranking at that that specific
     *         decade.
     */
    public int getRank(int decade) {

        // Checking Preconditions
        if (decade < 0 || decade >= getNumDecades()) {
            throw new IllegalArgumentException("The decade parameter must be greater "
                    + "than or equal to 0 and less than getNumDecades()");
        }

        return rankings.get(decade);
    }

    /**
     * This method gets the year in which the name was the most popular according to
     * the current data in the NameRecord Object and Text file.
     * 
     * @return - An int value that represents the year that the name was most
     *         popular.
     */
    public int getMostPopDec() {

        // Useful Constants
        final int LEN_DECADE = 10;

        int mostPopularIndex = 0;
        int currMinRank = UNRANKED_VAL;

        for (int i = 0; i < getNumDecades(); i++) {

            // Get Value of Current Decade Rank and Converts ZEROs
            int currRank = getRank(i);
            if (currRank == 0) {
                currRank = UNRANKED_VAL;
            }

            // Comparing Values
            if (currRank <= currMinRank) {
                mostPopularIndex = i;
                currMinRank = currRank;
            }
        }

        // Return most Popular and Most Recent Year
        int mostRecentPopYear = getBaseYear() + (mostPopularIndex * LEN_DECADE);
        return mostRecentPopYear;
    }

    /**
     * This method tells the user the number of times a name has been ranked in the
     * top 1000 throughout its history in the Popularity Data Rankings.
     * 
     * @return - Returns an int that represents the number of times the name has
     *         been in the top 1000.
     */
    public int getNumRanked() {

        int numTimes = 0;

        // Count the Number of times it has been in the top 1000
        for (int i = 0; i < getNumDecades(); i++) {
            if (getRank(i) != 0) {
                numTimes++;
            }
        }

        return numTimes;
    }

    /**
     * This method returns a method that checks to see if the name is always ranked
     * in the top 1000 names during its entire time in the Popularity Data Rankings.
     * 
     * @return - Returns the boolean value based on whether the name has always been
     *         in the top 1000 the entire time.
     */
    public boolean alwaysRanked() {
        return getNumRanked() == getNumDecades();
    }

    /**
     * This method returns a method that checks to see if the name is only ranked
     * in the top 1000 names once during its entire time in the Popularity Data
     * Rankings.
     * 
     * @return - Returns the boolean value based on whether the name has only been
     *         in the top 1000 once during the entire time.
     */
    public boolean isOnlyRankedOnce() {
        return getNumRanked() == 1;
    }

    /**
     * This methods returns a boolean value based on whether the rankings is always
     * decreasing or not.
     * 
     * @return
     */
    public boolean alwayMorePop() {

        int prev = getRank(0);
        if (prev == 0) {
            prev = UNRANKED_VAL;
        }

        for (int i = 1; i < getNumDecades(); i++) {

            // Getting Rank and Forcing 0 to be UNRANKED_VAL 
            int curr = getRank(i);
            if (curr == 0) {
                curr = UNRANKED_VAL;
            }

            // Check to See if the rankings stay the same or more causing to be false
            if (prev <= curr) {
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