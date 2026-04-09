/*
 * BSTTester.java - CS 314 Assignment 8
 *
 * By signing my name below, I affirm that this assignment is my own work. I
 * have neither given nor received unauthorized assistance on this assignment.
 *
 * Name: Pheanouk Hun
 * Email address: ph23434@eid.utexas.edu
 * UTEID: ph23434
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

/**
 * Some test cases for CS314 Binary Search Tree assignment.
 */
public class BSTTester {

    private static int testNum = 1;

    /**
     * The main method runs the tests.
     *
     * @param args Not used
     */
    public static void main(String[] args) {
        testAddMethods();
        testSearchAndMinMax();
        testRemovalLogic();
        testStructureMethods();
        testGetAllGreaterAndLessThan();
    }

    private static void testAddMethods() {
        // Setup
        ArrayList<Integer> expected = new ArrayList<>();
        BinarySearchTree<Integer> t = new BinarySearchTree<>();

        // Test 1: Recursive Add a lot of elements
        System.out.println("\nAdd Method Testing: ");

        Random r = new Random();
        for (int i = 0; i < 1000; i++) {
            int value = r.nextInt() * 1000;
            if (t.add(value)) {
                expected.add(value);
            }
        }

        showTestResults(t.size() == expected.size(), testNum++);

        // Test 2: Duplicate add (should return false)
        showTestResults(t.add(expected.get(0)) == false, testNum++);

        System.out.println("\nIterative Add Testing:");

        // Test 1: Iterative Add

        BinarySearchTree<Integer> tInt = new BinarySearchTree<>();
        tInt.iterativeAdd(50);
        tInt.iterativeAdd(25);
        tInt.iterativeAdd(75);
        showTestResults(tInt.size() == 3, testNum++);

        // Test 2: Iterative Add Duplicate
        showTestResults(tInt.iterativeAdd(50) == false, testNum++);
    }

    private static void testSearchAndMinMax() {
        
        // Setup
        BinarySearchTree<Integer> t = new BinarySearchTree<>();
        int[] vals = { 50, 10, 90, 5, 20 };
        for (int v : vals) {
            t.add(v);
        }

        // isPresent() Method
        System.out.println("\nisPresent() Method Testing: ");

        // Test 1: Element Not Present
        showTestResults(!t.isPresent(100), testNum++);
        
        // Test 2: Element Present
        showTestResults(t.isPresent(20), testNum++);

        // min() Method
        System.out.println("\nmin() Method Testing: ");
        
        // Test 1: min() from Original Values
        showTestResults(t.min() == 5, testNum++);
        
        // Test 2: min() after adding a -5 Values
        t.add(-5);
        showTestResults(t.min() == -5, testNum++);

        // max() Method
        System.out.println("\nmax() Method Testing: ");

        // Test 1: max() from Original Values
        showTestResults(t.max() == 90, testNum++);
        
        // Test 2: max() after adding a 100 Values
        t.add(100);
        showTestResults(t.max() == 100, testNum++);
    }

    private static void testRemovalLogic() {

        // Setup
        BinarySearchTree<Integer> t = new BinarySearchTree<>();
        int[] values = new int[]{50, 25, 75, 12, 30};
        for (int value : values) {
            t.add(value);
        }
        
        // remove() Method
        System.out.println("\nremove() Method Testing: ");
        
        // Test 1: Remove Root Node
        showTestResults(t.remove(50) && t.size() == 4, testNum++);
        
        // Test 2: Remove Leaf Node
        showTestResults(t.remove(12) && t.size() == 3, testNum++);
    }

    private static void testStructureMethods() {
        
        // Setup
        BinarySearchTree<Integer> t = new BinarySearchTree<>();

        // Height Tests
        System.out.println("\nheight() Tests: ");
        
        // Test 1: Height of empty tree
        showTestResults(t.height() == -1, testNum++);

        // Test 2: Height of non-trivial tree
        int[] values = new int[]{50, 25, 75, 12, 30};
        for (int value : values) {
            t.add(value);
        }
        showTestResults(t.height() == 2, testNum++);

        // numNodesAtDepth Tests
        System.out.println("\nnumNodesAtDepth() Tests: ");
        
        // Test 1: Node at Largest Depth
        showTestResults(t.numNodesAtDepth(2) == 2, testNum++);

        // Test 2: Node at Smallest Depth
        showTestResults(t.numNodesAtDepth(0) == 1, testNum++);

        // get() Tests
        System.out.println("\nget() Tests: ");
        
        // Test 1: get(kth) - Smallest
        showTestResults(t.get(0) == 12, testNum++);

        // Test 2: get(kth) - Middle
        showTestResults(t.get(3) == 50, testNum++);
    }

    private static void getAllGreaterAndLessThan() {
        
        // Setup
        BinarySearchTree<Integer> t = new BinarySearchTree<>();
        
        
        // Get all 
        
        
        for (int i = 1; i <= 10; i++) t.add(i * 10); // 10, 20, 30... 100

        // Test 17: getAll
        List<Integer> all = t.getAll();
        showTestResults(all.size() == 10 && all.get(0) == 10, testNum++);

        // Test 18: getAllLessThan
        List<Integer> lessThan = t.getAllLessThan(35);
        // Should be 10, 20, 30
        showTestResults(
            lessThan.size() == 3 && lessThan.get(2) == 30,
            testNum++
        );

        // Test 19: getAllGreaterThan
        List<Integer> greaterThan = t.getAllGreaterThan(85);
        // Should be 90, 100
        showTestResults(
            greaterThan.size() == 2 && greaterThan.get(0) == 90,
            testNum++
        );

        // Test 20: getAllGreaterThan (None found)
        showTestResults(t.getAllGreaterThan(200).isEmpty(), testNum++);
    }

    private static void showTestResults(boolean passed, int testNum) {
        System.out.print(" *  Test " + testNum + ":");
        String passedString = passed ? "PASSED" : "FAILED";
        System.out.println(" Results: " + passedString);
    }
}
