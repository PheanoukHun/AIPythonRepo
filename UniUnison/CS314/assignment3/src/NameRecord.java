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
    private int numRankedYears;
    private int numDecades;

    // Constructor

    /**
     * 
     * @param baseYear
     * @param numRankedYears
     * @param rawData
     */
    public NameRecord(int baseYear, int numRankedYears, String rawData) {

        this.baseYear = baseYear;
        rankings = new ArrayList<>();
        this.numRankedYears = numRankedYears;

        Scanner scLine = new Scanner(rawData);
        name = scLine.next();

        while (scLine.hasNextInt()) {
            rankings.add(scLine.nextInt());

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
     * @return
     */
    public int getNumRankedYears() {
        return numRankedYears;
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
            +"than or equal to 0 and less than getNumDecades()");
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

        int mostRecentYear = getBaseYear() + (mostPopularIndex * 10);

        return mostRecentYear;
    }

    public int getNumRankedTop1000() {
        
        int numTimes = 0;

        for (int i = 0; i < getNumDecades(); i++) {
            if (getDecadeNameRank(i) != 0) {
                numTimes++;
            }
        }

        return numTimes;
    }

    public boolean neverBelowTop1000() {

        for (int i = 0; i < getNumDecades(); i++) {
            if (getDecadeNameRank(i) == 0) {
                return false;
            }
        }

        return true;
    }

    public boolean rankedInTop1000Once() {

        int top1000NumTimes = 0;

        for (int i = 0; i < getNumDecades(); i++) {
            if (getDecadeNameRank(i) != 0) {
                top1000NumTimes++;
            }
        }

        return top1000NumTimes == 1;
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