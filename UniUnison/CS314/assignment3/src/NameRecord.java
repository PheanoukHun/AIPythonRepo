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

    private String name;
    private int baseYear;
    private ArrayList<Integer> rankings;
    private int numRankedYears;
    private int numDecades;

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

    public String getName() {
        return name;
    }

    public int getBaseYear() {
        return baseYear;
    }

    public int getNumDecades() {
        return numDecades;
    }

    public int getSpecificDecadeRank(int decade) {

        // Checking Preconditions
        if (decade < 0 || decade >= getNumDecades()) {
            throw new IllegalArgumentException("The decade parameter must be greater "
            +"than or equal to 0 and less than getNumDecades()");
        }

        return rankings.get(decade);
    }

    public int getNumRankedYears() {
        return numRankedYears;
    }

    public String toString() {
        StringBuilder results = new StringBuilder(name + "\n");
        for (int i = 0; i < getNumDecades(); i++) {
            results.append((baseYear + (i * 10)) + ": " + getSpecificDecadeRank(i) + "\n");
        }
        return results.toString();
    }

    public int compareTo(NameRecord other) {
        return name.compareTo(other.getName());
    }
}