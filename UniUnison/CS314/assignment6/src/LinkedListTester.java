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

        // Insert Tests

        actualLL.insert(1, "Z");
        expectedList.add(1, "Z");

        result = arraysSame(toArray(actualLL), expectedList.toArray());
        printResults(result, actualLL, expectedList, "Inserting Closer to the Front", testNum);
        testNum++;

        actualLL.insert(actualLL.size() - 2, "X");
        expectedList.add(actualLL.size() - 3, "X");

        result = arraysSame(toArray(actualLL), expectedList.toArray());
        printResults(result, actualLL, expectedList, "Inserting Closer to the End", testNum);
        testNum++;

        // Get Tests

        result = actualLL.get(1).equals("Z");
        printResults(result, actualLL, null, "Getting the Value Closer to the Front",
                testNum);
        testNum++;

        result = actualLL.get(actualLL.size() - 2).equals("C");
        printResults(result, actualLL, null, "Getting the Value Closer to the End",
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

        actualLL = addAllLLWithStrs(new String[] { "A", "B", "C", "D", "E", "F", "G" });
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

        // GetSubList

        expectedList = addAllArrListWithStrs(new String[] { "B", "D", "E" });
        actualLL = (LL314<String>) actualLL.getSubList(1, actualLL.size() - 1);

        result = arraysSame(toArray(actualLL), expectedList.toArray());
        printResults(result, actualLL, expectedList, "Getting the Sublists from the Middle",
                testNum);
        testNum++;

        actualLL = addAllLLWithStrs(new String[] { "A", "B", "C", "D" });
        expectedList = copyLLToALForStr(actualLL);
        actualLL = (LL314<String>) actualLL.getSubList(0, actualLL.size());

        result = arraysSame(toArray(actualLL), expectedList.toArray());
        printResults(result, actualLL, expectedList, "Getting the Sublists of the Entire LL",
                testNum);
        testNum++;

        // Size

        result = actualLL.size() == expectedList.size();
        printResults(result, actualLL, expectedList, "Size Method Tests", testNum);
        testNum++;

        actualLL.add("FDSFHDSJKj");
        expectedList.add("FDSFHDSJKj");
        result = actualLL.size() == expectedList.size();
        printResults(result, actualLL, expectedList, "Size Method Tests After Add", testNum);
        testNum++;

        // IndexOf(item)

        result = actualLL.indexOf("B") == expectedList.indexOf("B");
        printResults(result, actualLL, expectedList, "Get Index Close to the Front", testNum);
        testNum++;

        result = actualLL.indexOf("FDSFHDSJKj") == expectedList.indexOf("FDSFHDSJKj");
        printResults(result, actualLL, expectedList, "Get Index Close to the End", testNum);
        testNum++;

        // Reseting the Lists

        actualLL = addAllLLWithStrs(new String[] { "A", "B", "C", "D", "E", "A", "G" });
        expectedList = copyLLToALForStr(actualLL);

        // IndexOf(item, pos)

        result = actualLL.indexOf("A", 3) == 5;
        printResults(result, actualLL, null, "Index of A skipping over index 3", testNum);
        testNum++;

        result = actualLL.indexOf("A", 0) == 0;
        printResults(result, actualLL, null, "Index of A not skipping any values", testNum);
        testNum++;

        // makeEmpty

        actualLL.makeEmpty();
        result = actualLL.size() == 0;
        printResults(result, actualLL, null, "Make Empty Method", testNum);
        testNum++;

        expectedList = addAllArrListWithStrs(new String[] { "A", "B", "C", "D", "E", "A", "G" });

        actualLL.makeEmpty();
        expectedList.clear();

        result = actualLL.size() == expectedList.size();
        printResults(result, actualLL, expectedList, "Make Empty, Comparing LL to AL", testNum);
        testNum++;

        // Reseting the Lists
        actualLL = addAllLLWithStrs(new String[] { "A", "B", "C", "D", "E", "F", "G" });
        expectedList = copyLLToALForStr(actualLL);

        // Set
        actualLL.set(2, "Z");
        expectedList.set(2, "Z");

        result = arraysSame(toArray(actualLL), expectedList.toArray());
        printResults(result, actualLL, expectedList, "Setting Closer to the Front", testNum);
        testNum++;

        actualLL.set(actualLL.size() - 2, "X");
        expectedList.set(expectedList.size() - 2, "X");

        result = arraysSame(toArray(actualLL), expectedList.toArray());
        printResults(result, actualLL, expectedList, "Setting Closer to the End", testNum);
        testNum++;

        // Iterator.hasNext() and next()

        Iterator<String> llIterator = actualLL.iterator();
        Iterator<String> alIterator = expectedList.iterator();

        // Start
        result = llIterator.hasNext() == alIterator.hasNext();
        printResults(result, actualLL, expectedList, "Checking Iterator.hasNext()", testNum);
        testNum++;

        result = llIterator.next().equals(alIterator.next());
        printResults(result, actualLL, expectedList, "First Value of Iterator Same", testNum);
        testNum++;

        // End
        String llVal = "";
        String alVal = "";

        while (llIterator.hasNext() && alIterator.hasNext()) {
            llVal = llIterator.next();
            alVal = alIterator.next();
        }

        result = llIterator.hasNext() == alIterator.hasNext();
        printResults(result, actualLL, expectedList, "Checking hasNext when no more elements",
                testNum);
        testNum++;

        result = llVal.equals(alVal);
        printResults(result, actualLL, expectedList, "End Value of Iterator Same", testNum);
        testNum++;

        // Iterator.remove()
        llIterator = actualLL.iterator();
        alIterator = expectedList.iterator();

        llIterator.next();
        alIterator.next();

        llIterator.remove();
        alIterator.remove();

        result = actualLL.equals(expectedList);
        printResults(result, actualLL, expectedList, "Removing the First Element using Iterator",
                testNum);
        testNum++;

        while (llIterator.hasNext() && alIterator.hasNext()) {
            llIterator.next();
            alIterator.next();
        }

        llIterator.remove();
        alIterator.remove();

        result = actualLL.equals(expectedList);
        printResults(result, actualLL, expectedList, "Removing the Last Element using Iterator",
                testNum);
        testNum++;

        // Reseting the Lists
        actualLL = addAllLLWithStrs(new String[] { "A", "B", "C", "D", "E", "F", "G" });
        expectedList = copyLLToALForStr(actualLL);
        
        // RemoveRange
        result = 

        // toString

        // Equals

        // addFirst

        // addLast

        // removeFirst

        // removeLast
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

        String passedString = results ? "PASSED" : "FAILED";
        sb.append("\n\tResults: " + passedString);

        String displayActual = list != null ? "\n\tActual List:    "
                + list.toString() : "";
        sb.append(displayActual);

        String displayExpected = arrList != null ? "\n\tExpected Lists: "
                + arrList.toString() : "";
        sb.append(displayExpected);

        System.out.println(sb.toString());
    }

    private static LL314<String> addAllLLWithStrs(String[] str) {
        LL314<String> list = new LL314<>();

        for (String s : str) {
            list.add(s);
        }

        return list;
    }

    private static ArrayList<String> addAllArrListWithStrs(String[] str) {
        ArrayList<String> list = new ArrayList<>();

        for (String s : str) {
            list.add(s);
        }

        return list;
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