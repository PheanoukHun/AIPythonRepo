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

/**
Question 1 - 2:
Run for Tree of 1000:
 * Time taken: 0.006534759 seconds
 * Height of Tree: 999
 * Number of Nodes: 1000

Question 3:
Average of the 10 Runs Results for Tree of 1000:
 * Time taken: 0.0021600871 seconds
 * Height of Tree: 999
 * Number of Nodes: 1000

Question 4:
Average of the 10 Runs Results for Tree of 2000:
 * Time taken: 0.008569375399999999 seconds
 * Height of Tree: 1999
 * Number of Nodes: 2000

Average of the 10 Runs Results for Tree of 4000:
 * Time taken: 0.0334673702 seconds
 * Height of Tree: 3999
 * Number of Nodes: 4000

Average of the 10 Runs Results for Tree of 8000:
 * Time taken: 0.1336590114 seconds
 * Height of Tree: 7999
 * Number of Nodes: 8000

Average of the 10 Runs Results for Tree of 16000:
 * Time taken: 0.5391714908 seconds
 * Height of Tree: 15999
 * Number of Nodes: 16000

Average of the 10 Runs Results for Tree of 32000:
 * Time taken: 2.2370041185000002 seconds
 * Height of Tree: 31999
 * Number of Nodes: 32000

Average of the 10 Runs Results for Tree of 64000:
 * Time taken: 9.342193508200001 seconds
 * Height of Tree: 63999
 * Number of Nodes: 64000

Question 5: (Predicted Results)
Average of the 10 Runs Results for Tree of 128000:
 * Time taken: 37.368774033 seconds
 * Height of Tree: 127999
 * Number of Nodes: 128000

Average of the 10 Runs Results for Tree of 256000:
 * Time taken: 149.4753096131 seconds
 * Height of Tree: 255999
 * Number of Nodes: 256000

Average of the 10 Runs Results for Tree of 512000:
 * Time taken: 597.901238452 seconds
 * Height of Tree: 511999
 * Number of Nodes: 512000
 
Question 6:
1 Run for TreeSet of 1000 Elements:
 * Time taken: 0.002628803 seconds

Average of the 10 Runs Results for TreeSet of 1000 Elements:
 * Time taken: 3.7402310000000005E-4 seconds

Average of the 10 Runs Results for TreeSet of 2000 Elements:
 * Time taken: 4.793184000000001E-4 seconds

Average of the 10 Runs Results for TreeSet of 4000 Elements:
 * Time taken: 5.859439E-4 seconds

Average of the 10 Runs Results for TreeSet of 8000 Elements:
 * Time taken: 8.678467000000003E-4 seconds

Average of the 10 Runs Results for TreeSet of 16000 Elements:
 * Time taken: 0.0016239432000000002 seconds

Average of the 10 Runs Results for TreeSet of 32000 Elements:
 * Time taken: 0.0033143756999999994 seconds

Average of the 10 Runs Results for TreeSet of 64000 Elements:
 * Time taken: 0.007284705800000001 seconds
 
Question 7: 
*/

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.TreeSet;

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
        // My Test Suite:
        // testAddMethods();
        // testSearchAndMinMax();
        // testRemovalLogic();
        // testStructureMethods();
        // testGetAllGreaterAndLessThan();

        // Experiment Code:
        // experimentCodeForMyClass();
        experimentCodeWithTreeSet();
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
        int[] values = new int[] { 50, 25, 75, 12, 30 };
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
        int[] values = new int[] { 50, 25, 75, 12, 30 };
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

        // size() Test
        // Test 1: size() on empty tree
        t = new BinarySearchTree<>();
        showTestResults(t.size() == 0, testNum++);

        // Test 2: size() on non-empty tree
        for (int i = 1; i <= 5; i++) {
            t.add(i * 10);
        }
        showTestResults(t.size() == 5, testNum++);
    }

    private static void testGetAllGreaterAndLessThan() {
        // Setup
        BinarySearchTree<Integer> t = new BinarySearchTree<>();

        // getAll() method Test
        System.out.println("\ngetAll() Tests: ");

        // Test 1: getAll with empty tree
        List<Integer> allEmpty = t.getAll();
        showTestResults(allEmpty.isEmpty(), testNum++);

        // Test 2: getAll with non-empty tree
        for (int i = 1; i <= 10; i++) {
            t.add(i * 10);
        }

        List<Integer> all = t.getAll();
        showTestResults(all.size() == 10, testNum++);

        // getAllGreaterAndLessThan Tests
        System.out.println("\ngetAllGreaterThan() Tests: ");

        // Test 1: getAllGreaterThan with value greater than max value of tree.
        List<Integer> greaterThanMax = t.getAllGreaterThan(110);
        showTestResults(greaterThanMax.isEmpty(), testNum++);

        // Test 2: getAllGreaterThan with value less than min value of tree.
        List<Integer> lessThanMin = t.getAllGreaterThan(5);
        showTestResults(lessThanMin.size() == 10, testNum++);

        // getAllLessThan Tests
        System.out.println("\ngetAllLessThan() Tests: ");

        // Test 1: getAllLessThan with value greater than max value of tree.
        List<Integer> lessThanMax = t.getAllLessThan(110);
        showTestResults(lessThanMax.size() == 10, testNum++);

        // Test 2: getAllLessThan with value less than min value of tree.
        List<Integer> greaterThanMin = t.getAllLessThan(5);
        showTestResults(greaterThanMin.isEmpty(), testNum++);
    }

    private static void showTestResults(boolean passed, int testNum) {
        System.out.print(" *  Test " + testNum + ":");
        String passedString = passed ? "PASSED" : "FAILED";
        System.out.println(" Results: " + passedString);
    }

    private static void experimentCodeForMyClass() {
        Stopwatch sw = new Stopwatch();

        // Part 1: Adding Values 1000 values once;
        BinarySearchTree<Integer> t1 = new BinarySearchTree<>();
        sw.start();
        for (int j = 0; j < 1000; j++) {
            t1.add(j);
        }
        sw.stop();
        System.out.println("\n1 Run for Tree of 1000:");
        System.out.println(" * Time taken: " + sw.time() + " seconds");
        System.out.println(" * Height of Tree: " + t1.height());
        System.out.println(" * Number of Nodes: " + t1.size());

        // Part 2: Repeat Tree of 1000, 10 times
        double totalTime = 0;
        int totalHeight = 0;

        for (int i = 0; i < 10; i++) {
            BinarySearchTree<Integer> t2 = new BinarySearchTree<>();

            // Timing the Add Method
            sw.start();
            for (int j = 0; j < 1000; j++) {
                t2.add(j);
            }
            sw.stop();

            totalTime += sw.time();
            totalHeight += t2.height();
        }
        System.out.println("\nAverage of the 10 Runs Results for Tree of 1000 Nodes: ");
        System.out.println(" * Time taken: " + totalTime / 10 + " seconds");
        System.out.println(" * Height of Tree: " + totalHeight / 10);
        System.out.println(" * Number of Nodes: " + 1000);

        // Part 3: Repeat 10 Times for 2000, 4000, 8000, 16000, 32000, 64000
        for (int i = 2000; i <= 64000; i *= 2) {
            totalTime = 0;
            totalHeight = 0;
            for (int j = 0; j < 10; j++) {
                BinarySearchTree<Integer> t3 = new BinarySearchTree<>();
                sw.start();
                for (int k = 0; k < i; k++) {
                    t3.add(k);
                }
                sw.stop();
                totalTime += sw.time();
                totalHeight += t3.height();
            }
            System.out.println("\nAverage of the 10 Runs Results for Tree of " + i + "Nodes:");
            System.out.println(" * Time taken: " + totalTime / 10 + " seconds");
            System.out.println(" * Height of Tree: " + totalHeight / 10);
            System.out.println(" * Number of Nodes: " + i);
        }
    }

    private static void experimentCodeWithTreeSet() {
        Stopwatch sw = new Stopwatch();

        // Part 4: Part 1-3 for Java TreeSet Instead

        // Part 1:
        TreeSet<Integer> t1 = new TreeSet<>();
        sw.start();
        for (int k = 0; k < 1000; k++) {
            t1.add(k);
        }
        sw.stop();

        System.out.println("\n1 Run for TreeSet of 1000 Elements:");
        System.out.println(" * Time taken: " + sw.time() + " seconds");

        // Part 2:
        double totalTime = 0;
        for (int i = 0; i < 10; i++) {
            TreeSet<Integer> t2 = new TreeSet<>();
            sw.start();
            for (int j = 0; j < 1000; j++) {
                t2.add(j);
            }
            sw.stop();
            totalTime += sw.time();
        }
        System.out.println("\nAverage of the 10 Runs Results for TreeSet of 1000 Elements:");
        System.out.println(" * Time taken: " + totalTime / 10 + " seconds");

        // Part 3:
        for (int i = 2000; i <= 64000; i *= 2) {
            totalTime = 0.0;
            for (int j = 0; j < 10; j++) {
                TreeSet<Integer> t3 = new TreeSet<>();
                sw.start();
                for (int k = 0; k < i; k++) {
                    t3.add(k);
                }
                sw.stop();
                totalTime += sw.time();
            }
            System.out.println(
                "\nAverage of the 10 Runs  Results for TreeSet of " + i + " Elements:"
            );
            System.out.println(" * Time taken: " + totalTime / 10 + " seconds");
        }
    }
}
