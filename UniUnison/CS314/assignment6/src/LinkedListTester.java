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
        int testNum = 1;

        // Add Tests

        actualLL.add("A");
        expectedList.add("A");
        expectedList.add("B");
        actualLL.add("B");
        expectedList.add("C");
        actualLL.add("C");

        boolean result = arraysSame(toArray(actualLL), expectedList.toArray());
        printResults(result, actualLL, expectedList, "Adding Looking for Ordered", testNum);
        testNum++;

        expectedList.add("A");
        actualLL.add("A");

        result = arraysSame(toArray(actualLL), expectedList.toArray());
        printResults(result, actualLL, expectedList, "Adding Duplicate Values", testNum);
        testNum++;

        // Set Tests

        actualLL.insert(1, "Z");
        expectedList.add(1, "Z");

        result = arraysSame(toArray(actualLL), expectedList.toArray());
        printResults(result, actualLL, expectedList, "Inserting Closer to the Front", testNum);
        testNum++;

        actualLL.insert(actualLL.size() - 2, "X");
        expectedList.add(actualLL.size() - 2, "X");

        result = arraysSame(toArray(actualLL), expectedList.toArray());
        printResults(result, actualLL, expectedList, "Inserting Closer to the End", testNum);
        testNum++;

        // Get Tests
        result = actualLL.get(1).equals("Z");
        printResults(result, actualLL, expectedList, "Getting the Value Closer to the Front",
                testNum);
        testNum++;

        result = actualLL.get(actualLL.size() - 2).equals("C");
        printResults(result, actualLL, expectedList, "Getting the Value Closer to the End",
                testNum);
        testNum++;

        // Remove(pos) Test
        actualLL.remove(2);
        expectedList.remove(2);

        result = arraysSame(toArray(actualLL), expectedList.toArray());
        printResults(result, actualLL, expectedList, "Removing the Value closer to the Front",
                testNum);
        testNum++;

        actualLL.remove(actualLL.size() - 2);
        expectedList.remove(expectedList.size() - 2);

        result = arraysSame(toArray(actualLL), expectedList.toArray());
        printResults(result, actualLL, expectedList, "Removing the Value Closer to the End",
                testNum);
        testNum++;

        // Reseting the Lists
        actualLL.makeEmpty();

        replenishLLWithStrings(actualLL, new String[] { "A", "B", "C", "D", "E", "F", "G" });
        expectedList = copyLLToALForStr(actualLL);

        // Remove(obj)
        actualLL.remove("C");
        expectedList.remove("C");

        result = arraysSame(toArray(actualLL), expectedList.toArray());
        printResults(result, actualLL, expectedList, "Removing the Object Closer to the Front",
                testNum);
        testNum++;

        actualLL.remove("F");
        expectedList.remove("F");

        result = arraysSame(toArray(actualLL), expectedList.toArray());
        printResults(result, actualLL, expectedList, "Removing the Object Closer to the End",
                testNum);
        testNum++;
    }

    // Convert elements of list to an array. Uses the list
    // size method and iterator.
    private static Object[] toArray(LL314<String> list) {
        Object[] result = new Object[list.size()];
        Iterator<String> it = list.iterator();
        int index = 0;
        while (it.hasNext()) {
            result[index] = it.next();
            index++;
        }
        return result;
    }

    // pre: none
    // post: return true if the
    private static boolean arraysSame(Object[] one, Object[] two) {
        return Arrays.equals(one, two);
    }

    private static void printResults(boolean results, LL314<String> list,
            ArrayList<String> arrList, String detail, int testNum) {

        StringBuilder sb = new StringBuilder();

        sb.append("\nTest " + testNum + ":");
        sb.append("\n\tDescription: " + detail);

        sb.append("\n\tResults: ");

        if (results) {
            sb.append("PASSED");
        } else {
            sb.append("FAILED");
        }

        sb.append("\n\tActual List:" + list.toString());
        sb.append("\n\tExpected Lists: " + arrList.toString());

        System.out.println(sb.toString());
    }

    private static void replenishLLWithStrings(LL314<String> list, String[] str) {
        for (String s : str) {
            list.add(s);
        }
    }

    private static ArrayList<String> copyLLToALForStr(LL314<String> lL) {

        Iterator<String> llIterator = lL.iterator();
        ArrayList<String> arrList = new ArrayList<>();

        while (llIterator.hasNext()) {
            arrList.add(llIterator.next());
        }

        return arrList;
    }
}