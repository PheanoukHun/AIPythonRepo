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


public class NameRecord {
    
    private String name;
    private int baseYear;
    private ArrayList<Integer> rankings;
    private int numRankedYears;

    public NameRecord(String name, int baseYear, int numRankedYears, ArrayList<Integer> rankings) {
        this.name = name;
        this.baseYear = baseYear;
        this.rankings = rankings;
        this.numRankedYears = numRankedYears;
    }

    public String getName() {
        return name;
    }

    public int getBaseYear() {
        return baseYear;
    }

    public int getNumDecades() {
        return rankings.size();
    }

    public int getSpecificDecadeRank(int decade) {
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
}