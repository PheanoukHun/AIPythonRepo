/*
 * AnagramFinderTester.java - CS 314 Assignment 7
 *
 * By signing my/our name(s) below, I/we affirm that this assignment is my/our
 * own work. I/we have neither given nor received unauthorized assistance on
 * this assignment.
 *
 * Name 1: Pheanouk Hun
 * Email address 1: ph23434@eid.utexas.edu
 * UTEID 1: ph23434
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
        LISize();
        LIEmpty();
        LItoString();
        LIAdd();
        LISubtract();
        LIEquals();
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
        inv = new LetterInventory("aaaaaaaaaaaaazaaaaaaaaaabbbe");
        expected = 1;
        actual = inv.get('z');
        showTestResults(expected, actual, 2, "Appearance of 1 Z Character, needs to found.");
    }

    private static void LISize() {

        System.out.println("\nSize Test: \n");

        // Test 1
        LetterInventory inv = new LetterInventory("");
        Object expected = 0;
        Object actual = inv.size();
        showTestResults(expected, actual, 1, "Empty LetterInventory size.");

        // Test 2
        inv = new LetterInventory("aAbBcCdDeEfFgGhHiIjJkKlLmMnNoOpPqQrRsStTuUvVwWxXyYzZ");
        expected = 52;
        actual = inv.size();
        showTestResults(expected, actual, 2, "Size == 52 with all alphabets upper and lower.");
    }

    private static void LIEmpty() {
        
        System.out.println("\nisEmpty Test: \n");

        // Test 1
        LetterInventory inv = new LetterInventory("");
        Object expected = true;
        Object actual = inv.isEmpty();
        showTestResults(expected, actual, 1, "Test isEmpty with Empty LetterInventory");

        // Test 2
        inv = new LetterInventory("asdfghjkl");
        expected = false;
        actual = inv.isEmpty();
        showTestResults(expected, actual, 2, "Test isEmpty with Non-Empty LetterInventory");
    }

    private static void LItoString() {
        System.out.println("\ntoString Test: \n");

        // Test 1
        LetterInventory inv = new LetterInventory("");
        Object expected = "";
        Object actual = inv.toString();
        showTestResults(expected, actual, 1, "Empty to String");

        // Test 2
        inv = new LetterInventory("Hello World");
        expected = "dehllloorw";
        actual = inv.toString();
        showTestResults(expected, actual, 2, "Test toString with real words");
    }

    private static void LIAdd() {
        System.out.println("\nAdd Method Test: \n");

        // Test 1
        LetterInventory inv = new LetterInventory("qwertyuiopasdfghjklzxcvbnm");
        Object expected = inv;
        Object actual = inv.add(new LetterInventory(""));
        showTestResults(expected, actual, 1, "Testing Adding an Empty Inventory.");

        // Test 2
        LetterInventory inv2 = new LetterInventory("");
        actual = inv2.add(inv);
        showTestResults(expected, actual, 2, "Testing Adding to an Empty Inventory");
    }

    private static void LISubtract() {
        System.out.println("\nSubtract Method Test: \n");

        // Test 1
        LetterInventory inv = new LetterInventory("qwertyuiopasdfghjklzxcvbnm");
        Object expected = inv;
        Object actual = inv.subtract(new LetterInventory(""));
        showTestResults(expected, actual, 1, "Testing Subtracting using an Empty Inventory.");

        // Test 2
        inv = new LetterInventory("");
        LetterInventory inv2 = new LetterInventory("qwertyuiopasdfghjklzxcvbnm");
        expected = null;
        actual = inv.subtract(inv2);
        showTestResults(expected, actual, 2, "Testing Adding to an Empty Inventory");
    }

    private static void LIEquals() {
        
        System.out.println("\nSubtract Method Test: \n");

        // Test 1
        LetterInventory inv = new LetterInventory("qwertyuiopasdfghjklzxcvbnm");
        Object expected = false;
        Object actual = inv.equals(new LetterInventory(""));
        showTestResults(expected, actual, 1, "Testing Equals using an Empty Inventory.");

        // Test 2
        inv = new LetterInventory("qwertyuiopasdfghjklzxcvbnm");
        expected = false;
        actual = inv.equals(new ArrayList<>());
        showTestResults(expected, actual, 2, "Testing Equals using an ArrayList Inventory.");
    }

    private static boolean showTestResults(Object expected, Object actual,
            int testNum, String featureTested) {

        System.out.println("Test Number " + testNum + " testing: "
                + featureTested);
        boolean passed = (actual == null && expected == null)
                || (actual != null && actual.equals(expected));
        if (passed) {
            System.out.println("Passed test " + testNum);
        } else {
            System.out.println("!!! FAILED TEST !!! " + testNum);
        }
        System.out.println("Expected result: " + expected);
        System.out.println("Actual result: " + actual);
        System.out.println();
        return passed;
    }
}