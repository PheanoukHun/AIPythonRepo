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

    public String getName() {
        return name;
    }

    public int getBaseYear() {
        return baseYear;
    }

    public int getNumDecades() {
        return rankings.size();
    }
}