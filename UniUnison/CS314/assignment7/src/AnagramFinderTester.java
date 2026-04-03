/*
 * AnagramFinderTester.java - CS 314 Assignment 7
 *
 * By signing my/our name(s) below, I/we affirm that this assignment is my/our
 * own work. I/we have neither given nor received unauthorized assistance on
 * this assignment.
 *
 * Name 1:
 * Email address 1:
 * UTEID 1:
 *
 * Name 2:
 * Email address 2:
 * UTEID 2:
 */

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AnagramFinderTester {

    private static final String testCaseFileName = "testCaseAnagrams.txt";
    private static final String dictionaryFileName = "d3.txt";

    /**
     * main method that executes tests.
     * 
     * @param args Not used.
     */
    public static void main(String[] args) {
        LIConstructor();
        LIGet();
    }

    private static void LIConstructor() {
        
        System.out.println("\nConstructor Test:\n");
        
        // Test 1
        Object expected = "dehllloorw";
        LetterInventory inv = new LetterInventory("Hello World");
        Object actual = inv.toString();
        showTestResults(expected, actual, 1, "Two Words One Space");

        // Test 2
        System.out.println();
        expected = "";
        inv = new LetterInventory("");
        actual = inv.toString();
        showTestResults(expected, actual, 2, "Empty Word");
    }

    private static void LIGet() {

        System.out.println("\nGet Test: \n");

        // Test 1
        LetterInventory inv = new LetterInventory("");
        Object expected = 0;
        Object actual = inv.get('a');
        showTestResults(expected, actual, 1, "Empty List Get");

        // Test 2
        inv = new LetterInventory("aaaaaaaaaaaaaaaaaaaaaabbbez");
        expected = 1;
        actual = inv.get('z');
        showTestResults(expected, actual, 2, "")
    }

    private static boolean showTestResults(Object expected, Object actual,
            int testNum, String featureTested) {

        System.out.println("Test Number " + testNum + " testing: "
                + featureTested);
        System.out.println("Expected result: " + expected);
        System.out.println("Actual result: " + actual);
        boolean passed = (actual == null && expected == null)
                || (actual != null && actual.equals(expected));
        if (passed) {
            System.out.println("Passed test " + testNum);
        } else {
            System.out.println("!!! FAILED TEST !!! " + testNum);
        }
        System.out.println();
        return passed;
    }
}