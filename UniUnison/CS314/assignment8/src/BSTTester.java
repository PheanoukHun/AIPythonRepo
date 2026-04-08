/*
 * BSTTester.java - CS 314 Assignment 8
 *
 * By signing my name below, I affirm that this assignment is my own work. I
 * have neither given nor received unauthorized assistance on this assignment.
 *
 * Name:
 * Email address:
 * UTEID:
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

/**
 * Some test cases for CS314 Binary Search Tree assignment.
 *
 */
public class BSTTester {

    /**
     * The main method runs the tests.
     * 
     * @param args Not used
     */
    public static void main(String[] args) {
        BinarySearchTree<String> t = new BinarySearchTree<>();
        addTests(t);
    }

    private static void addTests(BinarySearchTree<String> t) {
        
        // Test 1 - Adding in Reverse Order
        int testNum = 1;
        ArrayList<String> expected = new ArrayList<>();
        for (int i = 0; i < 100; i--) {
            expected.add(i + "");
        }

        for ()
    }

    private static void printResults(int testNum, boolean results, String detail,
            BinarySearchTree<String> t, ArrayList<String> l) {
        System.out.println("\nTest " + testNum + ":");
        System.out.println(" * Description: " + detail);
        String passedString = results ? "PASSED" : "FAILED";
        System.out.println(" * Results: " + passedString);
    }

    private static void showTestResults(boolean passed, int testNum) {
        if (passed) {
            System.out.println("Test " + testNum + " passed.");
        } else {
            System.out.println("TEST " + testNum + " FAILED.");
        }
    }
}