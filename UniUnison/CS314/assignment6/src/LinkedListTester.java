/*
 * LinkedListTester.java - CS 314 Assignment 6
 *
 * By signing my name below, I affirm that this assignment is my own work. I
 * have neither given nor received unauthorized assistance on this assignment.
 *
 * Name: Pheanouk Hun
 * Email address: ph23434@eid.utexas.edu
 * UTEID: ph23434
 */


/*
 * TODO: Place your experiment results here:
 *
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class LinkedListTester {

    public static void main(String[] args) {

        // basicTests();
        // spring2021StressTests();
        // itRemoveStressTests();

        // Uncomment the following line to run tests comparing
        // your LL314 class to the java ArrayList class.
        // comparison();

        myTestSuite();
    }

    private static void myTestSuite() {
        
        LL314<String> actualLL = new LL314<>();
        ArrayList<String> expectedList = new ArrayList<>();
        
        // Add Tests

        actualLL.add("A");
        expectedList.add("A");
        expectedList.add("B");
        actualLL.add("B");
        expectedList.add("C");
        actualLL.add("C");
        
        boolean result = arraysSame(toArray(actualLL), toArray(actualLL));
        if (result) {
            System.out.println("\nPassed Test Case 1, Adding Ordered");
        } else {
            System.out.println("\nTest 1 Failed");
        }
        
        expectedList.add("A");
        actualLL.add("A");

        result = arraysSame(toArray(actualLL), toArray(actualLL));
        if (result) {
            System.out.println("Passed Test Case 2, Adding Based with Duplicates");
        } else {
            System.out.println("\nTest 2 Failed");
        }

        // Set Tests

        actualLL.insert(1, "Z");
        expectedList.add(1, "Z");

        result = arraysSame(toArray(actualLL), toArray(actualLL));
        if (result) {
            System.out.println("Passed Test Case 3, Inserting Closer to front");
        } else {
            System.out.println("\nTest 3 Failed");
        }

        actualLL.insert(actualLL.size() - 2, "X");
        expectedList.add(actualLL.size() - 2, "X");

        result = arraysSame(toArray(actualLL), toArray(actualLL));
        if (result) {
            System.out.println("Passed Test Case 4, Inserting Closer to End");
        } else {
            System.out.println("\nTest 4 Failed");
        }

        // Get Tests
        result = actualLL.get(1).equals("A");
        if (result) {
            System.out.println("Passed Test Case 5, Getting Val Closer to front");
        } else {
            System.out.println("\nTest 5 Failed");
        }
    }

    // Convert elements of list to an array. Uses the list
    // size method and iterator.
    private static Object[] toArray(LL314<String> list) {
        Object[] result = new Object[list.size()];
        Iterator<String> it = list.iterator();
        int index = 0;
        while(it.hasNext()){
            result[index] = it.next();
            index++;
        }
        return result;
    }

    // pre: none
    // post: return true if the 
    private static boolean arraysSame(Object[] one, Object[] two)  {
        return Arrays.equals(one, two);
    }

    private static void printResults(boolean results, LL314 list, String detail, int testNum) {
        StringBuilder sb = new StringBuilder();
        sb.append("\n");
        sb.append("Test ", testNum);sb.append(testNum);

    }
}