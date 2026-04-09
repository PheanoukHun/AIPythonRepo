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
        testFilteringMethods();
    }
    
    private static void testAddMethods() {
        
        System.out.println("\nAdd Method Testing: ");
        BinarySearchTree<String> t = new BinarySearchTree<>();

        // Test 1: Recursive Add a lot of elements
        
        
        Collections.sort(expectedList);
        

        // Test 2: Duplicate add (should return false)
        showTestResults(t.add("M") == false, testNum++);

        System.out.println("\nIterative Add Method Testing:");

        // Test 1: Iterative Add
        
        BinarySearchTree<Integer> tInt = new BinarySearchTree<>();
        tInt.iterativeAdd(50);
        tInt.iterativeAdd(25);
        tInt.iterativeAdd(75);
        showTestResults(tInt.size() == 3 && tInt.isPresent(25), testNum++);

        // Test 4: Iterative Add Duplicate
        showTestResults(tInt.iterativeAdd(50) == false, testNum++);
    }

    private static void testSearchAndMinMax() {
        System.out.println("\n--- Testing Search, Min, and Max ---");
        BinarySearchTree<Integer> t = new BinarySearchTree<>();
        int[] vals = { 50, 10, 90, 5, 20 };
        for (int v : vals) t.add(v);

        // Test 5: min()
        showTestResults(t.min() == 5, testNum++);

        // Test 6: max()
        showTestResults(t.max() == 90, testNum++);

        // Test 7: isPresent()
        showTestResults(t.isPresent(20) && !t.isPresent(100), testNum++);
    }

    private static void testRemovalLogic() {
        System.out.println(
            "\n--- Testing Removal Logic (Fix Verification) ---"
        );
        BinarySearchTree<Integer> t = new BinarySearchTree<>();
        // Structure: 50 is root, 25 left child, 75 right child
        t.add(50);
        t.add(25);
        t.add(75);
        t.add(12);
        t.add(30);

        // Test 8: Remove leaf
        showTestResults(t.remove(12) && t.size() == 4, testNum++);

        // Test 9: Remove node with one child
        showTestResults(t.remove(25) && t.size() == 3, testNum++);

        // Test 10: Remove root with two children (The Previous Bug Case)
        // At this point, tree has 50, 30, 75. 30 is left of 50.
        // Let's add 60 so 75 has a left child.
        t.add(60);
        // Tree: 50 root, 30 left, 75 right, 60 is left of 75.
        showTestResults(t.remove(50) && t.size() == 3, testNum++);

        // Test 11: Verify size after complex removals
        showTestResults(t.max() == 75 && t.min() == 30, testNum++);
    }

    private static void testStructureMethods() {
        System.out.println("\n--- Testing Height, Depth, and Kth ---");
        BinarySearchTree<Integer> t = new BinarySearchTree<>();

        // Test 12: Height of empty tree
        showTestResults(t.height() == -1, testNum++);

        // Test 13: Height of non-trivial tree
        int[] vals = { 50, 25, 75, 10, 30, 60, 80 };
        for (int v : vals) t.add(v);
        showTestResults(t.height() == 2, testNum++);

        // Test 14: numNodesAtDepth
        showTestResults(t.numNodesAtDepth(2) == 4, testNum++);

        // Test 15: get(kth) - Smallest
        showTestResults(t.get(0) == 10, testNum++);

        // Test 16: get(kth) - Middle/Root
        showTestResults(t.get(3) == 50, testNum++);
    }

    private static void testFilteringMethods() {
        System.out.println(
            "\n--- Testing getAll, LessThan, and GreaterThan ---"
        );
        BinarySearchTree<Integer> t = new BinarySearchTree<>();
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
        System.out.println("\nTest " + testNum + ":");
        String passedString = passed ? "PASSED" : "FAILED";
        System.out.println(" * Results: " + passedString);
    }
}
