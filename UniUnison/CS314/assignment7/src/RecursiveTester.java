/*
 * RecursiveTester.java - CS 314 Assignment 7
 *
 * By signing my/our name(s) below, I/we affirm that this assignment is my/our
 * own work. I/we have neither given nor received unauthorized assistance on
 * this assignment.
 *
 * Name 1: Pheanouk Hun
 * Email address 1: ph23434@eid.utexas.edu
 * UTEID 1: ph23434
 */

/**
 * Tester class for the methods in Recursive.java
 * 
 * @author scottm
 *
 */

import java.util.ArrayList;
import java.util.Collections;

public class RecursiveTester {

    // run the tests
    public static void main(String[] args) {
        // doNextIsDoubleTests();
        doCarpetTest();
        // doFairTeamsTests();

        // myNextIsDoubleTests();
        // myListMnemonicsTests();
        // myCanFlowOffMapTests();
        // myMinDifferenceTest();
    }

    private static void doNextIsDoubleTests() {
        int[] numsForDouble = { 1, 2, 4, 8, 16, 32, 64, 128, 256 };
        int actualDouble = Recursive.nextIsDouble(numsForDouble);
        int expectedDouble = 8;
        if (actualDouble == expectedDouble) {
            System.out.println("Test 1 passed. next is double.");
        } else {
            System.out.println("Test 1 failed. next is double. expected: "
                    + expectedDouble + ", actual: " + actualDouble);
        }

        numsForDouble = new int[] { 1, 3, 4, 2, 32, 8, 128, -5, 6 };
        actualDouble = Recursive.nextIsDouble(numsForDouble);
        expectedDouble = 0;
        if (actualDouble == expectedDouble) {
            System.out.println("Test 2 passed. next is double.");
        } else {
            System.out.println("Test 2 failed. next is double. expected: "
                    + expectedDouble + ", actual: " + actualDouble);
        }

        numsForDouble = new int[] { 1, 0, 0, -5, -10, 32, 64, 128, 2, 9, 18 };
        actualDouble = Recursive.nextIsDouble(numsForDouble);
        expectedDouble = 5;
        if (actualDouble == expectedDouble) {
            System.out.println("Test 3 passed. next is double.");
        } else {
            System.out.println("Test 3 failed. next is double. expected: "
                    + expectedDouble + ", actual: " + actualDouble);
        }

        numsForDouble = new int[] { 37 };
        actualDouble = Recursive.nextIsDouble(numsForDouble);
        expectedDouble = 0;
        if (actualDouble == expectedDouble) {
            System.out.println("Test 4 passed. next is double.");
        } else {
            System.out.println("Test 4 failed. next is double. expected: "
                    + expectedDouble + ", actual: " + actualDouble);
        }

        numsForDouble = new int[] { 37, 74 };
        actualDouble = Recursive.nextIsDouble(numsForDouble);
        expectedDouble = 1;
        if (actualDouble == expectedDouble) {
            System.out.println("Test 5 passed. next is double.");
        } else {
            System.out.println("Test 5 failed. next is double. expected: "
                    + expectedDouble + ", actual: " + actualDouble);
        }
        System.out.println();
    }

    // Test the Sierpinski carpet method.
    private static void doCarpetTest() {
        Recursive.drawCarpet(729, 4);
        Recursive.drawCarpet(729, 1);
    }

    private static void doFairTeamsTests() {
        System.out.println("Stress test for minDifference - may take up to a minute");
        int[] testerArr = new int[] { 5, 10, 15, 20, 25, 30, 35, 40, 45, 50, 55, 60,
                65, 70, 75, 100000 };
        Stopwatch s = new Stopwatch();
        s.start();
        int actualInt = Recursive.minDifference(4, testerArr);
        s.stop();
        System.out.println("Time to solve for 16 people on 4 teams: " + s.time() +
                "\n");
        System.out.println(actualInt);

        int[] abilities = { 1, 2, 3, 4, 5, 6, 7 };
        showFairTeamsResults(Recursive.minDifference(3, abilities), 1, 1);
        showFairTeamsResults(Recursive.minDifference(5, abilities), 2, 2);
        showFairTeamsResults(Recursive.minDifference(6, abilities), 4, 3);

        abilities = new int[] { 1, 12, 46, 60, 53, 86, 72, 79, 44, 7 };
        showFairTeamsResults(Recursive.minDifference(3, abilities), 3, 4);
        showFairTeamsResults(Recursive.minDifference(5, abilities), 19, 5);

        abilities = new int[] { 10, 10, 7, 7, 7 };
        showFairTeamsResults(Recursive.minDifference(2, abilities), 1, 6);

        abilities = new int[] { -10, -10, -8, -8, -8 };
        showFairTeamsResults(Recursive.minDifference(2, abilities), 4, 7);

        abilities = new int[] { -5, 5, 10, 5, 10, -15 };
        showFairTeamsResults(Recursive.minDifference(2, abilities), 0, 8);
    }

    // Show the results of a fair teams test by comparing actual and expected
    // result.
    private static void showFairTeamsResults(int actual, int expected, int testNum) {
        if (actual == expected) {
            System.out.println("Test " + testNum + " passed. min difference.");
        } else {
            System.out.println("Test " + testNum + " failed. min difference.");
            System.out.println("Expected result: " + expected);
            System.out.println("Actual result  : " + actual);
        }
    }

    private static void printResults(int testNum, boolean results, String detail) {
        System.out.println("\nTest " + testNum + ":");
        System.out.println(" * Description: " + detail);
        String passedString = results ? "PASSED" : "FAILED";
        System.out.println(" * Results: " + passedString);
    }

    private static void myNextIsDoubleTests() {

        System.out.println("\nMy nextIsDouble Tests:");

        // Test 1
        int testNum = 1;
        int[] data = new int[] {};
        int actual = Recursive.nextIsDouble(data);
        printResults(testNum, actual == 0, "nextIsDouble with Empty List.");

        // Test 2
        testNum++;
        data = new int[] { -4, -8 };
        actual = Recursive.nextIsDouble(data);
        printResults(testNum, actual == 1, "nextIsDouble with only two items");
    }

    private static void myListMnemonicsTests() {
        System.out.println("\nMy listMnemonics Tests:");

        // Test 1
        int testNum = 1;

        ArrayList<String> result = Recursive.listMnemonics("623");
        Collections.sort(result);

        String[] expectedArr = new String[] { "MAD", "MBD", "MCD", "NAD", "NBD", "NCD", "OAD",
                "OBD", "OCD", "MAE", "MBE", "MCE", "NAE", "NBE", "NCE", "OAE", "OBE", "OCE",
                "MAF", "MBF", "MCF", "NAF", "NBF", "NCF", "OAF", "OBF", "OCF" };

        ArrayList<String> expected = copyMnemoicArrtoArrList(expectedArr);
        Collections.sort(expected);

        printResults(testNum, result.equals(expected), "listMnemonics with with 623");

        // Test 2
        testNum++;

        result = Recursive.listMnemonics("01");
        int actual = result.size();
        boolean equals = actual == 1;
        printResults(testNum, equals, "Digits 0 and 1 each have 1 letter, except 1 mnemoncis");
    }

    private static ArrayList<String> copyMnemoicArrtoArrList(String[] arr) {
        ArrayList<String> result = new ArrayList<>();
        for (String code : arr) {
            result.add(code);
        }
        return result;
    }

    private static void myCanFlowOffMapTests() {
        System.out.println("\nMy CanFlowOffMap Tests:");

        // Test 1
        int testNum = 1;

        int[][] map = { { 5, 5, 5 }, { 5, 9, 5 }, { 5, 5, 5 } };
        boolean result = Recursive.canFlowOffMap(map, 1, 1);
        printResults(testNum, result, "Placed in the Center and runs off the edge");

        // Test 2
        testNum++;
        result = Recursive.canFlowOffMap(map, 0, 0);
        printResults(testNum, result, "Placed on (0,0) and must run off the edge");

        // Test 3
        testNum++;
        map = new int[][] { { 10, 10, 10 }, { 10, 10, 10 }, { 10, 10, 10 } };
        result = Recursive.canFlowOffMap(map, 1, 1);
        printResults(testNum, !result, "Placed on the center of a flat map,"
                + " cannot run off the edge");
    }

    private static void myMinDifferenceTest() {
        
        System.out.println("\nMy MinDifferenceTests: ");

        // Test 1
        int testNum = 1;
        int[] abilities = new int[] {5, 5};
        int results = Recursive.minDifference(2, abilities);
        printResults(testNum, results == 0, "Two Teams, two people with equal abilities");

        // Test 2
        testNum++;
        abilities = new int[] {1, 5, 9};
        results = Recursive.minDifference(3, abilities);
        printResults(testNum, results == 8, "Three Treams, One Person per Team");
    }
}