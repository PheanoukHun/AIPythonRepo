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
 * Which Data Structure is More Efficient for Each Method?
 * 
 * Adding at End: LinkedList but It is in Basically the Same.
 * _________________________________________________________
 * | Number of Elements | ArrayList Time | LinkedList Time |
 * |--------------------|----------------|-----------------|
 * |       30000        |     0.0391     |     0.0286      |
 * |       60000        |     0.0762     |     0.0539      |
 * |      120000        |     0.1535     |     0.1069      |
 * |      240000        |     0.2732     |     0.2197      |
 * |      480000        |     0.5314     |     0.3995      |
 * |____________________|________________|_________________|
 * 
 * Reason: LinkedList adding at the End is Always O(1) while
 * ArrayList is mostly O(1) with some operations needing to
 * be O(N) because the array needed to resize.
 * 
 * Big O Notation of the Method:
 *      LinkedList: O(1)
 *      ArrayList: O(N)
 * ---------------------------------------------------------
 * 
 * Adding at Front: LinkedList
 * _________________________________________________________
 * | Number of Elements | ArrayList Time | LinkedList Time |
 * |--------------------|----------------|-----------------|
 * |        2000        |     0.0396     |     0.0094      |
 * |        4000        |     0.1415     |     0.0176      |
 * |        8000        |     0.5341     |     0.0323      |
 * |       16000        |     2.1195     |     0.0593      |
 * |       32000        |     8.4911     |     0.1293      |
 * |____________________|________________|_________________|
 * 
 * Reason: LinkedList adding to the front is Always O(1) 
 * because you just need change the prev instance variable 
 * of the previous first element while ArrayList is always 
 * O(N) because you need to shift all the other elements over
 * one position to the right.
 * 
 * Big O Notation:
 *      LinkedList: O(1)
 *      ArrayList: O(N)
 * ---------------------------------------------------------
 * 
 * Removing from Front: LinkedList
 * _________________________________________________________
 * | Number of Elements | ArrayList Time | LinkedList Time |
 * |--------------------|----------------|-----------------|
 * |        2000        |     0.0352     |     0.0036      |
 * |        4000        |     0.1276     |     0.0051      |
 * |        8000        |     0.4729     |     0.0256      |
 * |       16000        |     1.9549     |     0.0785      |
 * |       32000        |     8.2465     |     0.1241      |
 * |____________________|________________|_________________|
 * 
 * Reason: LinkedList removing from the front is Always O(1) 
 * because you just need change the prev instance variable 
 * of the previous second element while ArrayList is always 
 * O(N) because you need to shift all the other elements over
 * one position to the left.
 * 
 * Big O Notation:
 *      LinkedList: O(1)
 *      ArrayList: O(N)
 * ---------------------------------------------------------
 * 
 * Getting Random Element: ArrayList
 * _________________________________________________________
 * | Number of Elements | ArrayList Time | LinkedList Time |
 * |--------------------|----------------|-----------------|
 * |       10000        |     0.0158     |     0.0582      |
 * |       20000        |     0.0300     |     0.2407      |
 * |       40000        |     0.0560     |     1.0000      |
 * |       80000        |     0.1227     |     4.0262      |
 * |      160000        |     0.3299     |    16.2074      |
 * |____________________|________________|_________________|
 * 
 * Reason: LinkedList is slower because you would need to
 * need to search through the list from the start every time
 * you call the get method, so it will always be O(N).
 * ArrayLists is always faster because it directly access the
 * data at the specific pos int without having to iterate
 * through the list.
 * 
 * Big O Notation:
 *      LinkedList: O(N)
 *      ArrayList: O(1)
 * ---------------------------------------------------------
 * 
 * Getting All Using Iterator: ArrayList but Basically the
 *                             Same
 * _________________________________________________________
 * | Number of Elements | ArrayList Time | LinkedList Time |
 * |--------------------|----------------|-----------------|
 * |       50000        |     0.0151     |     0.0174      |
 * |      100000        |     0.0142     |     0.0279      |
 * |      200000        |     0.0265     |     0.0552      |
 * |      400000        |     0.0574     |     0.1237      |
 * |      800000        |     0.1159     |     0.2403      |
 * |____________________|________________|_________________|
 * 
 * Reason: Since both Implement the Iterator Interface and
 * both use the same logic to run through the list it will
 * similar results.
 * 
 * Big O Notation:
 *      LinkedList: O(N)
 *      ArrayList: O(N)
 * ---------------------------------------------------------
 * 
 * Getting All Using get() Method: ArrayList
 * _________________________________________________________
 * | Number of Elements | ArrayList Time | LinkedList Time |
 * |--------------------|----------------|-----------------|
 * |      100000        |     0.0078     |     0.0534      |
 * |      200000        |     0.0177     |     0.2319      |
 * |      400000        |     0.0445     |     0.9890      |
 * |      800000        |     0.0877     |     4.0216      |
 * |     1600000        |     0.1533     |    16.1169      |
 * |____________________|________________|_________________|
 * 
 * Reason:
 * 
 * Big O Notation:
 *      LinkedList: 
 *      ArrayList:
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class LinkedListTester {

        public static void main(String[] args) {

                // myTestSuite();
                // basicTests();
                // spring2021StressTests();
                // itRemoveStressTests();

                // Uncomment the following line to run tests comparing
                // your LL314 class to the java ArrayList class.
                comparison();

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

                result = arraysSame(toArray(actualLL), expectedList.toArray());
                printResults(result, actualLL, expectedList, "Removing the First Element using Iterator",
                                testNum);
                testNum++;

                while (llIterator.hasNext() && alIterator.hasNext()) {
                        llIterator.next();
                        alIterator.next();
                }

                llIterator.remove();
                alIterator.remove();

                result = arraysSame(toArray(actualLL), expectedList.toArray());
                printResults(result, actualLL, expectedList, "Removing the Last Element using Iterator",
                                testNum);
                testNum++;

                // Reseting the Lists
                actualLL = addAllLLWithStrs(new String[] { "A", "B", "C", "D", "E", "F", "G" });
                expectedList = copyLLToALForStr(actualLL);

                // RemoveRange
                actualLL.removeRange(0, actualLL.size() - 1);

                expectedList.clear();
                expectedList.add("G");

                result = arraysSame(toArray(actualLL), expectedList.toArray());
                printResults(result, actualLL, expectedList,
                                "Removing Range from from Start to 2nd Last Element", testNum);
                testNum++;

                actualLL = addAllLLWithStrs(new String[] { "A", "B", "C", "D", "E", "F", "G" });
                expectedList = copyLLToALForStr(actualLL);

                actualLL.removeRange(1, actualLL.size());

                expectedList.clear();
                expectedList.add("A");

                result = arraysSame(toArray(actualLL), expectedList.toArray());
                printResults(result, actualLL, expectedList, "Removing from the 2nd Element to the End",
                                testNum);
                testNum++;

                expectedList.add("G");

                actualLL = addAllLLWithStrs(new String[] { "A", "B", "C", "D", "E", "F", "G" });
                actualLL.removeRange(1, actualLL.size() - 1);

                result = arraysSame(toArray(actualLL), expectedList.toArray());
                printResults(result, actualLL, expectedList, "Removing Range from the Middle", testNum);
                testNum++;

                // Reseting the Lists
                actualLL = addAllLLWithStrs(new String[] { "A", "B", "C", "D", "E", "F", "G" });
                expectedList = copyLLToALForStr(actualLL);

                // toString
                result = actualLL.toString().equals(expectedList.toString());
                printResults(result, actualLL, expectedList, "To String for LL with Elements",
                                testNum);
                testNum++;

                actualLL.makeEmpty();
                expectedList.clear();

                result = actualLL.toString().equals(expectedList.toString());
                printResults(result, actualLL, expectedList, "LL with no Elements", testNum);
                testNum++;

                // Equals
                LL314<String> actualLL2 = addAllLLWithStrs(new String[] { "A", "B", "C", "D" });
                actualLL = addAllLLWithStrs(new String[] { "A", "B", "C", "D" });

                result = actualLL.equals(actualLL2);
                printResults(result, actualLL, null, "Comparing 2 LL with same elements",
                                testNum);
                testNum++;

                result = !actualLL.equals(expectedList);
                printResults(result, actualLL, expectedList, "Comparing LL to AL", testNum);
                testNum++;

                // Reset LL
                actualLL = addAllLLWithStrs(new String[] { "A" });

                // addFirst
                actualLL.addFirst("Z");
                result = actualLL.get(0).equals("Z");
                printResults(result, actualLL, null,
                                "Adding to the Front with One Element", testNum);
                testNum++;

                actualLL.makeEmpty();

                actualLL.addFirst("Z");
                result = actualLL.size() == 1;
                printResults(result, actualLL, null,
                                "Adding ot the Front with Zero Element", testNum);
                testNum++;

                // Reset LL
                actualLL = addAllLLWithStrs(new String[] { "A" });

                // addLast
                actualLL.addLast("Z");
                result = actualLL.get(actualLL.size() - 1).equals("Z");
                printResults(result, actualLL, null,
                                "Adding to the Last to a List with One Element", testNum);
                testNum++;

                actualLL.makeEmpty();
                actualLL.addLast("Z");
                result = actualLL.size() == 1;
                printResults(result, actualLL, null, "Adding Last to an Empty List",
                                testNum);
                testNum++;

                // Reset LL
                actualLL = addAllLLWithStrs(new String[] { "A", "B" });

                // removeFirst
                actualLL.removeFirst();
                result = actualLL.size() == 1;
                printResults(result, actualLL, null,
                                "Removing First from List with >1 Element", testNum);
                testNum++;

                actualLL.removeLast();
                result = actualLL.size() == 0;
                printResults(result, actualLL, null,
                                "Removing First from list with Only 1 Element", testNum);
                testNum++;

                // Reset LL
                actualLL = addAllLLWithStrs(new String[] { "A", "B" });

                // removeLast
                actualLL.removeLast();
                result = actualLL.size() == 1;
                printResults(result, actualLL, null,
                                "Removing Last from List with >1 Element", testNum);
                testNum++;

                actualLL.removeLast();
                printResults(result, actualLL, null,
                                "Removing First from List with Only 1 Element", testNum);

        }

        private static void printResults(boolean results, LL314<String> list,
                        ArrayList<String> arrList, String detail, int testNum) {

                StringBuilder sb = new StringBuilder();

                sb.append("\nTest " + testNum + ":");
                sb.append("\n * Description: " + detail);

                String passedString = results ? "PASSED" : "FAILED";
                sb.append("\n * Results: " + passedString);

                String displayActual = list != null ? "\n * Actual List:    "
                                + list.toString() : "";
                sb.append(displayActual);

                String displayExpected = arrList != null ? "\n * Expected Lists: "
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

        /*
         * Runs very basic tests on the LinkedList class for
         * CS314 assignment 4.
         */
        private static void basicTests() {

                System.out.println("****** BASIC TESTS *******\n");
                LL314<String> list = new LL314<>();

                // test 0
                System.out.println("\nTest 0: initial list is empty");
                if (list.toString().equals("[]")) {
                        System.out.println("Passed test 0");
                } else {
                        System.out.println("Failed test 0");
                }

                // test 0.1
                System.out.println("\nTest 0.1: add to end");
                list.add("A");
                if (list.get(0).equals("A")) {
                        System.out.println("Passed test 0.1");
                } else {
                        System.out.println("Failed test 0.1");
                }

                // test 0.2
                System.out.println("\nTest 0.2: size");
                if (list.size() == 1) {
                        System.out.println("Passed test 0.2");
                } else {
                        System.out.println("Failed test 0.2");
                }

                // test 0.3
                System.out.println("\nTest 0.3: remove from position 0");
                String removed = list.remove(0);
                if (removed.equals("A")) {
                        System.out.println("Passed test 0.31");
                } else {
                        System.out.println("Failed test 0.31");
                }

                System.out.println("\nTest 0.31: toString after remove");

                // test 0.31
                if (list.toString().equals("[]")) {
                        System.out.println("Passed test 0.3");
                } else {
                        System.out.println("Failed test 0.3");
                }

                // test 0.4
                System.out.println("\nTest 0.4: size");
                if (list.size() == 0) {
                        System.out.println("Passed test 0.4");
                } else {
                        System.out.println("Failed test 0.4");
                }

                // test 0.5
                System.out.println("\nTest 0.5: add and toString");
                list.add("A");
                list.add("B");
                if (list.toString().equals("[A, B]")) {
                        System.out.println("Passed test 0.5");
                } else {
                        System.out.println("Failed test 0.5");
                }

                // test 0.6
                System.out.println("\nTest 0.6: size");
                if (list.size() == 2) {
                        System.out.println("Passed test 0.6");
                } else {
                        System.out.println("Failed test 0.6");
                }

                // test 0.7
                System.out.println("\nTest 0.7: makeEmpty");
                list.makeEmpty();
                if (list.size() == 0) {
                        System.out.println("Passed test 0.7");
                } else {
                        System.out.println("Failed test 0.7");
                }

                // test 0.8
                System.out.println("\nTest 0.8: makeEmpty on empty list");
                list.makeEmpty();
                if (list.size() == 0) {
                        System.out.println("Passed test 0.8");
                } else {
                        System.out.println("Failed test 0.8");
                }

                // test 1
                System.out.println("\nTest 1: Adding at end");
                list = new LL314<>();
                list.add("A");
                Object[] actual = toArray(list);
                Object[] expected = new Object[] { "A" };
                System.out.println("Expected result: " + Arrays.toString(expected));
                System.out.println("Actual result: " + Arrays.toString(actual));
                if (arraysSame(actual, expected)) {
                        System.out.println("Passed test 1");
                } else {
                        System.out.println("Failed test 1");
                }

                // test 2
                System.out.println("\nTest 2: making empty");
                list.makeEmpty();
                actual = toArray(list);
                expected = new Object[] {};
                System.out.println("Expected result: " + Arrays.toString(expected));
                System.out.println("Actual result: " + Arrays.toString(actual));
                if (arraysSame(actual, expected)) {
                        System.out.println("Passed test 2");
                } else {
                        System.out.println("Failed test 2");
                }

                // test 3
                System.out.println("\nTest 3: Adding at pos 0 in empty list");
                list.insert(0, "A");
                actual = toArray(list);
                expected = new Object[] { "A" };
                System.out.println("Expected result: " + Arrays.toString(expected));
                System.out.println("Actual result: " + Arrays.toString(actual));
                if (arraysSame(actual, expected)) {
                        System.out.println("Passed test 3");
                } else {
                        System.out.println("Failed test 3");
                }

                // test 4
                System.out.println("\nTest 4: Adding at front");
                list = new LL314<>();
                list.addFirst("A");
                actual = toArray(list);
                expected = new Object[] { "A" };
                System.out.println("Expected result: " + Arrays.toString(expected));
                System.out.println("Actual result: " + Arrays.toString(actual));
                if (arraysSame(actual, expected)) {
                        System.out.println("Passed test 4");
                } else {
                        System.out.println("Failed test 4");
                }

                // test 5
                System.out.println("\nTest 5: Removing from front");
                list.removeFirst();
                actual = toArray(list);
                expected = new Object[] {};
                System.out.println("Expected result: " + Arrays.toString(expected));
                System.out.println("Actual result: " + Arrays.toString(actual));
                if (arraysSame(actual, expected)) {
                        System.out.println("Passed test 5");
                } else {
                        System.out.println("Failed test 5");
                }

                // test 6
                list = new LL314<>();
                System.out.println("\nTest 6: Adding at end");
                list.addLast("A");
                actual = toArray(list);
                expected = new Object[] { "A" };
                System.out.println("Expected result: " + Arrays.toString(expected));
                System.out.println("Actual result: " + Arrays.toString(actual));
                if (arraysSame(actual, expected)) {
                        System.out.println("Passed test 6");
                } else {
                        System.out.println("Failed test 6");
                }

                // test 7
                System.out.println("\nTest 7: Removing from back");
                list.removeLast();
                actual = toArray(list);
                expected = new Object[] {};
                System.out.println("Expected result: " + Arrays.toString(expected));
                System.out.println("Actual result: " + Arrays.toString(actual));
                if (arraysSame(actual, expected)) {
                        System.out.println("Passed test 7");
                } else {
                        System.out.println("Failed test 7");
                }

                // test 8
                System.out.println("\nTest 8: Adding at middle");
                list = new LL314<>();
                list.add("A");
                list.add("C");
                list.insert(1, "B");
                actual = toArray(list);
                expected = new Object[] { "A", "B", "C" };
                System.out.println("Expected result: " + Arrays.toString(expected));
                System.out.println("Actual result: " + Arrays.toString(actual));
                if (arraysSame(actual, expected)) {
                        System.out.println("Passed test 8");
                } else {
                        System.out.println("Failed test 8");
                }

                // test 9
                System.out.println("\nTest 9: Setting");
                list = new LL314<>();
                list.add("A");
                list.add("D");
                list.add("C");
                String oldValue = list.set(1, "B");
                actual = toArray(list);
                expected = new Object[] { "A", "B", "C" };
                System.out.println("Expected result: " + Arrays.toString(expected));
                System.out.println("Actual result: " + Arrays.toString(actual));
                if (arraysSame(actual, expected)) {
                        System.out.println("Passed test 9.1");
                } else {
                        System.out.println("Failed test 9.1");
                }
                if (oldValue.equals("D")) {
                        System.out.println("Passed test 9.2");
                } else {
                        System.out.println("Failed test 9.2");
                }

                // test 10
                System.out.println("\nTest 10: Removing");
                list = new LL314<>();
                list.add("A");
                list.add("B");
                list.add("C");
                list.add("D");
                list.remove(0);
                list.remove(list.size() - 1);
                actual = toArray(list);
                expected = new Object[] { "B", "C" };
                System.out.println("Expected result: " + Arrays.toString(expected));
                System.out.println("Actual result: " + Arrays.toString(actual));
                if (arraysSame(actual, expected)) {
                        System.out.println("Passed test 10");
                } else {
                        System.out.println("Failed test 10");
                }
        }

        // constants for the maximum length of the lists used in the tests as well as
        // the number of times each method should be tested
        private static final int MAX_LENGTH = 15;
        private static final int NUM_TESTS_PER_METHOD = 50;

        // From Spring 2021 students:
        // Tests use randomness to find edge cases,
        // so the test numbering is irrelevant, each test being different every time the
        // program is run.
        private static void spring2021StressTests() {
                System.out.println("\n****** SPRING 2021 RANDOM STRESS TESTS *******\n");

                // performs all the tests. The console displays some private methods I have as
                // well, but it isn't actually directly calling those private methods. It merely
                // sets the conditions to where those methods would be called in my personal
                // program. It still is useful to test for edge cases

                final String methodNamesRaw = "void addFirst(E item)\r\n" + "void addLast(E item)\r\n"
                                + "E removeFirst()\r\n"
                                + "E removeLast()\r\n" + "void add(E item)\r\n" + "void insert(int pos, E item)\r\n"
                                + "void insertBeforeLast(E item)\r\n" + "void insertAfterFirst(E item)\r\n"
                                + "E set(pos, E item)\r\n"
                                + "E get(int pos)\r\n" + "E remove(int pos)\r\n" + "E removeAFterFirst()\r\n"
                                + "E removeBeforeLast()\r\n" + "boolean remove(E obj)\r\n"
                                + "Ilist<E> getSubList(int start, int stop)\r\n" + "int size()\r\n"
                                + "int indexOf(E item)\r\n"
                                + "int indexOf(E item, int pos)\r\n" + "void makeEmpty()\r\n"
                                + "void removeRange(int start, int stop)\r\n" + "string tosString()\r\n"
                                + "boolean equals(other)\r\n"
                                + "ITERATOR LLIterator()\r\n" + "ITERATOR boolean hasNext()\r\n"
                                + "ITERATOR E next()\r\n"
                                + "ITERATOR void remove()\r\n";
                final String[] methodNames = methodNamesRaw.split("\r\n");
                String methodName = methodNames[0];
                int methodNum = 0;
                LL314<String> testList = new LL314<>();
                int numTestsFailed = 0;
                HashSet<String> failedTests = new HashSet<>();
                // void addFirst(E item)
                for (int i = 0; i < NUM_TESTS_PER_METHOD; i++) {
                        System.out.print("Testing Method " + methodName + " test number " + i);
                        testList = newResetedTestList(testList);
                        ArrayList<String> toCompare = linkedListToArrayList(testList);
                        testList.addFirst("FIRST ELEMENT");
                        toCompare.add(0, "FIRST ELEMENT");
                        String[] expected = toCompare.toArray(new String[toCompare.size()]);
                        String[] actual = toArray2(testList);
                        if (arraysSame(expected, actual)) {
                                System.out.println("   " + methodName + " test " + i + " PASSED");
                        } else {
                                System.out.println("   " + methodName + " test " + i + " FAILED    Expected OutPut: "
                                                + Arrays.toString(expected) + " Actual Output = "
                                                + Arrays.toString(actual));
                                numTestsFailed++;
                                failedTests.add(methodName);
                        }
                }
                System.out.println();
                methodNum++;
                methodName = methodNames[methodNum];
                for (int i = 0; i < NUM_TESTS_PER_METHOD; i++) {
                        System.out.print("Testing Method " + methodName + " test number " + i);
                        testList = newResetedTestList(testList);
                        ArrayList<String> toCompare = linkedListToArrayList(testList);

                        // perform actions here
                        testList.addLast("LAST ELEMENT");
                        toCompare.add("LAST ELEMENT");

                        String[] expected = toCompare.toArray(new String[toCompare.size()]);
                        String[] actual = toArray2(testList);
                        if (arraysSame(expected, actual)) {
                                System.out.println("   " + methodName + " test " + i + " PASSED");
                        } else {
                                System.out.println("   " + methodName + " test " + i + " FAILED    Expected OutPut: "
                                                + Arrays.toString(expected) + " Actual Output = "
                                                + Arrays.toString(actual));
                                numTestsFailed++;
                                failedTests.add(methodName);
                        }
                }
                System.out.println();
                // E removeFirst()
                methodNum++;
                methodName = methodNames[methodNum];
                for (int i = 0; i < NUM_TESTS_PER_METHOD; i++) {
                        System.out.print("Testing Method " + methodName + " test number " + i);
                        testList = newResetedTestList(testList);
                        ArrayList<String> toCompare = linkedListToArrayList(testList);

                        // perform actions here
                        testList.removeFirst();
                        toCompare.remove(0);

                        String[] expected = toCompare.toArray(new String[toCompare.size()]);
                        String[] actual = toArray2(testList);
                        if (arraysSame(expected, actual)) {
                                System.out.println("   " + methodName + " test " + i + " PASSED");
                        } else {
                                System.out.println("   " + methodName + " test " + i + " FAILED    Expected OutPut: "
                                                + Arrays.toString(expected) + " Actual Output = "
                                                + Arrays.toString(actual));
                                numTestsFailed++;
                                failedTests.add(methodName);
                        }
                }
                System.out.println();
                // E removeLast()
                methodNum++;
                methodName = methodNames[methodNum];
                for (int i = 0; i < NUM_TESTS_PER_METHOD; i++) {
                        System.out.print("Testing Method " + methodName + " test number " + i);
                        testList = newResetedTestList(testList);
                        ArrayList<String> toCompare = linkedListToArrayList(testList);

                        // perform actions here
                        testList.removeLast();
                        toCompare.remove(toCompare.size() - 1);

                        String[] expected = toCompare.toArray(new String[toCompare.size()]);
                        String[] actual = toArray2(testList);
                        if (arraysSame(expected, actual)) {
                                System.out.println("   " + methodName + " test " + i + " PASSED");
                        } else {
                                System.out.println("   " + methodName + " test " + i + " FAILED    Expected OutPut: "
                                                + Arrays.toString(expected) + " Actual Output = "
                                                + Arrays.toString(actual));
                                numTestsFailed++;
                                failedTests.add(methodName);
                        }
                }
                System.out.println();

                // void add(E item)
                methodNum++;
                methodName = methodNames[methodNum];
                for (int i = 0; i < NUM_TESTS_PER_METHOD; i++) {
                        System.out.print("Testing Method " + methodName + " test number " + i);
                        testList = newResetedTestList(testList);
                        ArrayList<String> toCompare = linkedListToArrayList(testList);

                        // perform actions here
                        testList.add(methodName);
                        toCompare.add(methodName);

                        String[] expected = toCompare.toArray(new String[toCompare.size()]);
                        String[] actual = toArray2(testList);
                        if (arraysSame(expected, actual)) {
                                System.out.println("   " + methodName + " test " + i + " PASSED");
                        } else {
                                System.out.println("   " + methodName + " test " + i + " FAILED    Expected OutPut: "
                                                + Arrays.toString(expected) + " Actual Output = "
                                                + Arrays.toString(actual));
                                numTestsFailed++;
                                failedTests.add(methodName);
                        }
                }
                System.out.println();
                // void insert(int pos, E item)
                methodNum++;
                methodName = methodNames[methodNum];
                for (int i = 0; i < NUM_TESTS_PER_METHOD; i++) {
                        System.out.print("Testing Method " + methodName + " test number " + i);
                        testList = newResetedTestList(testList);
                        ArrayList<String> toCompare = linkedListToArrayList(testList);

                        int randomPos = (int) (Math.random() * toCompare.size());

                        // perform actions here
                        testList.insert(randomPos, methodName);
                        toCompare.add(randomPos, methodName);

                        String[] expected = toCompare.toArray(new String[toCompare.size()]);
                        String[] actual = toArray2(testList);
                        if (arraysSame(expected, actual)) {
                                System.out.println("   " + methodName + " test " + i + " PASSED");
                        } else {
                                System.out.println("   " + methodName + " test " + i + " FAILED    Expected OutPut: "
                                                + Arrays.toString(expected) + " Actual Output = "
                                                + Arrays.toString(actual));
                                numTestsFailed++;
                                failedTests.add(methodName);
                        }
                }
                System.out.println();

                // void insertBeforeLast(E item)
                methodNum++;
                methodName = methodNames[methodNum];
                for (int i = 0; i < NUM_TESTS_PER_METHOD; i++) {
                        System.out.print("Testing Method " + methodName + " test number " + i);
                        testList = newResetedTestList(testList);
                        ArrayList<String> toCompare = linkedListToArrayList(testList);

                        int pos = toCompare.size() - 1;

                        // perform actions here
                        testList.insert(pos, methodName);
                        toCompare.add(pos, methodName);

                        String[] expected = toCompare.toArray(new String[toCompare.size()]);
                        String[] actual = toArray2(testList);
                        if (arraysSame(expected, actual)) {
                                System.out.println("   " + methodName + " test " + i + " PASSED");
                        } else {
                                System.out.println("   " + methodName + " test " + i + " FAILED    Expected OutPut: "
                                                + Arrays.toString(expected) + " Actual Output = "
                                                + Arrays.toString(actual));
                                numTestsFailed++;
                                failedTests.add(methodName);
                        }
                }
                System.out.println();
                // void insertAfterFirst(E item)
                methodNum++;
                methodName = methodNames[methodNum];
                for (int i = 0; i < NUM_TESTS_PER_METHOD; i++) {
                        System.out.print("Testing Method " + methodName + " test number " + i);
                        testList = newResetedTestList(testList);
                        ArrayList<String> toCompare = linkedListToArrayList(testList);

                        int pos = 1;

                        // perform actions here
                        testList.insert(pos, methodName);
                        toCompare.add(pos, methodName);

                        String[] expected = toCompare.toArray(new String[toCompare.size()]);
                        String[] actual = toArray2(testList);
                        if (arraysSame(expected, actual)) {
                                System.out.println("   " + methodName + " test " + i + " PASSED");
                        } else {
                                System.out.println("   " + methodName + " test " + i + " FAILED    Expected OutPut: "
                                                + Arrays.toString(expected) + " Actual Output = "
                                                + Arrays.toString(actual));
                                numTestsFailed++;
                                failedTests.add(methodName);
                        }
                }
                System.out.println();

                // E set(pos, E item)
                methodNum++;
                methodName = methodNames[methodNum];
                for (int i = 0; i < NUM_TESTS_PER_METHOD; i++) {
                        System.out.print("Testing Method " + methodName + " test number " + i);
                        testList = newResetedTestList(testList);
                        ArrayList<String> toCompare = linkedListToArrayList(testList);

                        int pos = (int) (Math.random() * toCompare.size());

                        // perform actions here
                        testList.set(pos, methodName);
                        toCompare.set(pos, methodName);

                        String[] expected = toCompare.toArray(new String[toCompare.size()]);
                        String[] actual = toArray2(testList);
                        if (arraysSame(expected, actual)) {
                                System.out.println("   " + methodName + " test " + i + " PASSED");
                        } else {
                                System.out.println("   " + methodName + " test " + i + " FAILED    Expected OutPut: "
                                                + Arrays.toString(expected) + " Actual Output = "
                                                + Arrays.toString(actual));
                                numTestsFailed++;
                                failedTests.add(methodName);
                        }
                }
                System.out.println();

                // E get(int pos)
                methodNum++;
                methodName = methodNames[methodNum];
                for (int i = 0; i < NUM_TESTS_PER_METHOD; i++) {
                        System.out.print("Testing Method " + methodName + " test number " + i);
                        testList = newResetedTestList(testList);
                        ArrayList<String> toCompare = linkedListToArrayList(testList);

                        int pos = (int) (Math.random() * toCompare.size());

                        // perform actions here

                        String expected = toCompare.get(pos);
                        String actual = testList.get(pos);

                        if (expected.equals(actual)) {
                                System.out.println("   " + methodName + " test " + i + " PASSED");
                        } else {
                                System.out.println("   " + methodName + " test " + i + " FAILED    Expected OutPut: "
                                                + expected
                                                + " Actual Output = " + actual);
                                numTestsFailed++;
                                failedTests.add(methodName);
                        }
                }
                System.out.println();
                // E remove(int pos)
                methodNum++;
                methodName = methodNames[methodNum];
                for (int i = 0; i < NUM_TESTS_PER_METHOD; i++) {
                        System.out.print("Testing Method " + methodName + " test number " + i);
                        testList = newResetedTestList(testList);
                        ArrayList<String> toCompare = linkedListToArrayList(testList);

                        int pos = (int) (Math.random() * toCompare.size());

                        // perform actions here
                        testList.remove(pos);
                        toCompare.remove(pos);

                        String[] expected = toCompare.toArray(new String[toCompare.size()]);
                        String[] actual = toArray2(testList);
                        if (arraysSame(expected, actual)) {
                                System.out.println("   " + methodName + " test " + i + " PASSED");
                        } else {
                                System.out.println("   " + methodName + " test " + i + " FAILED    Expected OutPut: "
                                                + Arrays.toString(expected) + " Actual Output = "
                                                + Arrays.toString(actual));
                                numTestsFailed++;
                                failedTests.add(methodName);
                        }
                }
                System.out.println();
                // E removeAFterFirst()
                methodNum++;
                methodName = methodNames[methodNum];
                for (int i = 0; i < NUM_TESTS_PER_METHOD; i++) {
                        System.out.print("Testing Method " + methodName + " test number " + i);
                        testList = newResetedTestList(testList);
                        ArrayList<String> toCompare = linkedListToArrayList(testList);
                        if (toCompare.size() == 1) {
                                toCompare.add("Item " + 2);
                                testList.add("Item " + 2);
                        }
                        // perform actions here
                        testList.remove(1);
                        toCompare.remove(1);

                        String[] expected = toCompare.toArray(new String[toCompare.size()]);
                        String[] actual = toArray2(testList);
                        if (arraysSame(expected, actual)) {
                                System.out.println("   " + methodName + " test " + i + " PASSED");
                        } else {
                                System.out.println("   " + methodName + " test " + i + " FAILED    Expected OutPut: "
                                                + Arrays.toString(expected) + " Actual Output = "
                                                + Arrays.toString(actual));
                                numTestsFailed++;
                                failedTests.add(methodName);
                        }
                }
                System.out.println();
                // E removeBeforeLast()
                methodNum++;
                methodName = methodNames[methodNum];
                for (int i = 0; i < NUM_TESTS_PER_METHOD; i++) {
                        System.out.print("Testing Method " + methodName + " test number " + i);
                        testList = newResetedTestList(testList);
                        ArrayList<String> toCompare = linkedListToArrayList(testList);

                        int pos = toCompare.size() - 2;
                        if (pos == -1) {
                                pos = 0;
                        }
                        // perform actions here

                        String expected = toCompare.remove(pos);
                        String actual = testList.remove(pos);

                        if (expected.equals(actual)) {
                                System.out.println("   " + methodName + " test " + i + " PASSED");
                        } else {
                                System.out.println("   " + methodName + " test " + i + " FAILED    Expected OutPut: "
                                                + expected
                                                + " Actual Output = " + actual);
                                numTestsFailed++;
                                failedTests.add(methodName);
                        }
                }
                System.out.println();
                // boolean remove(E obj)
                methodNum++;
                methodName = methodNames[methodNum];
                for (int i = 0; i < NUM_TESTS_PER_METHOD; i++) {
                        System.out.print("Testing Method " + methodName + " test number " + i);
                        testList = newResetedTestList(testList);
                        ArrayList<String> toCompare = linkedListToArrayList(testList);

                        String objToRemove = toCompare.get((int) (Math.random() * toCompare.size()));

                        // perform actions here
                        testList.remove(objToRemove);
                        toCompare.remove(objToRemove);

                        String[] expected = toCompare.toArray(new String[toCompare.size()]);
                        String[] actual = toArray2(testList);
                        if (arraysSame(expected, actual)) {
                                System.out.println("   " + methodName + " test " + i + " PASSED");
                        } else {
                                System.out.println("   " + methodName + " test " + i + " FAILED    Expected OutPut: "
                                                + Arrays.toString(expected) + " Actual Output = "
                                                + Arrays.toString(actual));
                                numTestsFailed++;
                                failedTests.add(methodName);
                        }
                }
                System.out.println();
                // Ilist<E> getSubList(int start, int stop)
                methodNum++;
                methodName = methodNames[methodNum];
                for (int i = 0; i < NUM_TESTS_PER_METHOD; i++) {
                        System.out.print("Testing Method " + methodName + " test number " + i);
                        testList = newResetedTestList(testList);
                        ArrayList<String> toCompare = linkedListToArrayList(testList);

                        int start = (int) (Math.random() * toCompare.size());
                        int stop = (int) (Math.random() * (toCompare.size() - start) + start);
                        // perform actions here
                        IList<String> actualA = testList.getSubList(start, stop);
                        List<String> expectedB = toCompare.subList(start, stop);
                        String[] expected = expectedB.toArray(new String[expectedB.size()]);
                        String[] actual = toArray2(actualA);
                        if (arraysSame(expected, actual)) {
                                System.out.println("   " + methodName + " test " + i + " PASSED");
                        } else {
                                System.out.println("   " + methodName + " test " + i + " FAILED    Expected OutPut: "
                                                + Arrays.toString(expected) + " Actual Output = "
                                                + Arrays.toString(actual));
                                numTestsFailed++;
                                failedTests.add(methodName);
                        }
                }
                System.out.println();
                // int size()
                methodNum++;
                methodName = methodNames[methodNum];
                for (int i = 0; i < NUM_TESTS_PER_METHOD; i++) {
                        System.out.print("Testing Method " + methodName + " test number " + i);
                        testList = newResetedTestList(testList);
                        ArrayList<String> toCompare = linkedListToArrayList(testList);

                        int start = (int) (Math.random() * toCompare.size());
                        int stop = (int) (Math.random() * (toCompare.size() - start) + start);
                        // perform actions here
                        IList<String> actualA = testList.getSubList(start, stop);
                        List<String> expectedB = toCompare.subList(start, stop);
                        String[] expected = expectedB.toArray(new String[expectedB.size()]);
                        String[] actual = toArray2(actualA);
                        if (actualA.size() == expectedB.size()) {
                                System.out.println("   " + methodName + " test " + i + " PASSED");
                        } else {
                                System.out.println("   " + methodName + " test " + i + " FAILED    Expected OutPut: "
                                                + Arrays.toString(expected) + " Actual Output = "
                                                + Arrays.toString(actual));
                                numTestsFailed++;
                                failedTests.add(methodName);
                        }
                }
                System.out.println();
                // int indexOf(E item)
                methodNum++;
                methodName = methodNames[methodNum];
                for (int i = 0; i < NUM_TESTS_PER_METHOD; i++) {
                        System.out.print("Testing Method " + methodName + " test number " + i);
                        testList = newResetedTestList(testList);
                        ArrayList<String> toCompare = linkedListToArrayList(testList);

                        int pos = (int) (Math.random() * toCompare.size());
                        toCompare.add(pos, methodName);
                        testList.insert(pos, methodName);
                        // perform actions here

                        int expected = toCompare.indexOf(methodName);
                        int actual = testList.indexOf(methodName);

                        if (expected == actual) {
                                System.out.println("   " + methodName + " test " + i + " PASSED");
                        } else {
                                System.out.println("   " + methodName + " test " + i + " FAILED    Expected OutPut: "
                                                + expected
                                                + " Actual Output = " + actual);
                                numTestsFailed++;
                                failedTests.add(methodName);
                        }
                }
                System.out.println();

                // int indexOf(E item, int pos)
                methodNum++;
                methodName = methodNames[methodNum];
                for (int i = 0; i < NUM_TESTS_PER_METHOD; i++) {
                        System.out.print("Testing Method " + methodName + " test number " + i);
                        testList = newResetedTestList(testList);
                        ArrayList<String> toCompare = linkedListToArrayList(testList);

                        int pos2 = (int) (Math.random() * toCompare.size()) + 1;
                        int pos1 = (int) (Math.random() * pos2);
                        toCompare.add(pos1, methodName);
                        toCompare.add(pos2, methodName);
                        testList.insert(pos1, methodName);
                        testList.insert(pos2, methodName);

                        int posToCheckFrom = (int) (Math.random() * toCompare.size());
                        // perform actions here
                        int expected;
                        if (posToCheckFrom > pos2) {
                                expected = -1;
                        } else if (posToCheckFrom > pos1 && posToCheckFrom <= pos2) {
                                expected = pos2;
                        } else {
                                expected = pos1;
                        }

                        int actual = testList.indexOf(methodName, posToCheckFrom);

                        if (expected == actual) {
                                System.out.println("   " + methodName + " test " + i + " PASSED");
                        } else {
                                System.out.println("   " + methodName + " test " + i + " FAILED    Expected OutPut: "
                                                + expected
                                                + " Actual Output = " + actual + "  toCompare Array: "
                                                + toCompare.toString()
                                                + " testList array " + testList.toString() + "  POS1: " + pos1
                                                + " POS2: " + pos2
                                                + " POSTTOCHECK: " + posToCheckFrom);
                                numTestsFailed++;
                                failedTests.add(methodName);
                        }
                }
                System.out.println();
                // void makeEmpty()
                methodNum++;
                methodName = methodNames[methodNum];
                for (int i = 0; i < NUM_TESTS_PER_METHOD; i++) {
                        System.out.print("Testing Method " + methodName + " test number " + i);
                        testList = newResetedTestList(testList);
                        ArrayList<String> toCompare = linkedListToArrayList(testList);

                        // perform actions here
                        testList.makeEmpty();
                        toCompare.clear();

                        String[] expected = toCompare.toArray(new String[toCompare.size()]);
                        String[] actual = toArray2(testList);
                        if (arraysSame(expected, actual)) {
                                System.out.println("   " + methodName + " test " + i + " PASSED");
                        } else {
                                System.out.println("   " + methodName + " test " + i + " FAILED    Expected OutPut: "
                                                + Arrays.toString(expected) + " Actual Output = "
                                                + Arrays.toString(actual));
                                numTestsFailed++;
                                failedTests.add(methodName);
                        }
                }
                System.out.println();

                // void removeRange(int start, int stop)
                methodNum++;
                methodName = methodNames[methodNum];
                for (int i = 0; i < NUM_TESTS_PER_METHOD; i++) {
                        System.out.print("Testing Method " + methodName + " test number " + i);
                        testList = newResetedTestList(testList);
                        ArrayList<String> toCompare = linkedListToArrayList(testList);
                        int start = (int) (Math.random() * toCompare.size());
                        int stop = (int) (Math.random() * (toCompare.size() - start) + start);
                        // perform actions here
                        testList.removeRange(start, stop);
                        for (int j = stop - 1; j >= start; j--) {
                                toCompare.remove(j);
                        }
                        String[] expected = toCompare.toArray(new String[toCompare.size()]);
                        String[] actual = toArray2(testList);
                        if (arraysSame(expected, actual)) {
                                System.out.println("   " + methodName + " test " + i + " PASSED");
                        } else {
                                System.out.println("   " + methodName + " test " + i + " FAILED    Expected OutPut: "
                                                + Arrays.toString(expected) + " Actual Output = "
                                                + Arrays.toString(actual) + " START: " + start
                                                + " STOP: " + stop);
                                numTestsFailed++;
                                failedTests.add(methodName);
                        }
                }
                System.out.println();
                // string tosString()
                methodNum++;
                methodName = methodNames[methodNum];
                for (int i = 0; i < NUM_TESTS_PER_METHOD; i++) {
                        System.out.print("Testing Method " + methodName + " test number " + i);
                        testList = newResetedTestList(testList);
                        ArrayList<String> toCompare = linkedListToArrayList(testList);

                        // perform actions here

                        String expected = toCompare.toString();
                        String actual = testList.toString();
                        if (expected.equals(actual)) {
                                System.out.println("   " + methodName + " test " + i + " PASSED");
                        } else {
                                System.out.println("   " + methodName + " test " + i + " FAILED    Expected OutPut: "
                                                + expected
                                                + " Actual Output = " + actual);
                                numTestsFailed++;
                                failedTests.add(methodName);
                        }
                }
                System.out.println();
                // boolean equals(other)
                methodNum++;
                methodName = methodNames[methodNum];
                for (int i = 0; i < NUM_TESTS_PER_METHOD; i++) {
                        System.out.print("Testing Method " + methodName + " test number " + i);
                        testList = newResetedTestList(testList);
                        ArrayList<String> toCompare = linkedListToArrayList(testList);
                        LL314<String> toCompareLinkedList = arrayListToLinkedList(toCompare);
                        // perform actions here

                        if (testList.equals(toCompareLinkedList)) {
                                System.out.println("   " + methodName + " test " + i + " PASSED");
                        } else {
                                System.out.println("   " + methodName + " test " + i + " FAILED    Expected OutPut: "
                                                + toCompare.toString() + " Actual Output = " + testList.toString());
                                numTestsFailed++;
                                failedTests.add(methodName);
                        }
                }
                System.out.println();
                // ITERATOR LLIterator()
                methodNum++;
                methodName = methodNames[methodNum];
                for (int i = 0; i < NUM_TESTS_PER_METHOD; i++) {
                        System.out.print("Testing Method " + methodName + " test number " + i);
                        testList = newResetedTestList(testList);
                        ArrayList<String> toCompare = linkedListToArrayList(testList);
                        // perform actions here

                        if (testList.iterator().hasNext() && testList.iterator().next().equals(testList.get(0))) {
                                System.out.println("   " + methodName + " test " + i + " PASSED");
                        } else {
                                System.out.println("   " + methodName + " test " + i + " FAILED    Expected OutPut: "
                                                + toCompare.toString() + " Actual Output = " + testList.toString());
                                numTestsFailed++;
                                failedTests.add(methodName);
                        }
                }
                System.out.println();

                // ITERATOR boolean hasNext()
                methodNum++;
                methodName = methodNames[methodNum];
                for (int i = 0; i < NUM_TESTS_PER_METHOD; i++) {
                        System.out.print("Testing Method " + methodName + " test number " + i);
                        testList = newResetedTestList(testList);
                        ArrayList<String> toCompare = linkedListToArrayList(testList);

                        // perform actions here
                        Iterator<String> testListIterator = testList.iterator();
                        Iterator<String> toCompareIterator = toCompare.iterator();
                        int count1 = 0;
                        int count2 = 0;
                        while (testListIterator.hasNext()) {
                                count1++;
                                testListIterator.next();
                        }
                        while (toCompareIterator.hasNext()) {
                                count2++;
                                toCompareIterator.next();
                        }

                        if (count1 == count2) {
                                System.out.println("   " + methodName + " test " + i + " PASSED");
                        } else {
                                System.out.println("   " + methodName + " test " + i + " FAILED    Expected OutPut: ");
                                numTestsFailed++;
                                failedTests.add(methodName);
                        }
                }
                System.out.println();
                // ITERATOR E next()
                methodNum++;
                methodName = methodNames[methodNum];
                for (int i = 0; i < NUM_TESTS_PER_METHOD; i++) {
                        System.out.print("Testing Method " + methodName + " test number " + i);
                        testList = newResetedTestList(testList);
                        ArrayList<String> toCompare = linkedListToArrayList(testList);

                        // perform actions here
                        Iterator<String> testListIterator = testList.iterator();
                        Iterator<String> toCompareIterator = toCompare.iterator();
                        boolean pass = true;
                        while (testListIterator.hasNext() && toCompareIterator.hasNext() && pass) {
                                if (!testListIterator.next().equals(toCompareIterator.next())) {
                                        pass = false;
                                }
                        }
                        if (testListIterator.hasNext() != toCompareIterator.hasNext()) {
                                pass = false;
                        }

                        if (pass) {
                                System.out.println("   " + methodName + " test " + i + " PASSED");
                        } else {
                                System.out.println("   " + methodName + " test " + i + " FAILED    Expected OutPut: ");
                                numTestsFailed++;
                                failedTests.add(methodName);
                        }
                }
                System.out.println();
                // ITERATOR void remove()
                methodNum++;
                methodName = methodNames[methodNum];
                for (int i = 0; i < NUM_TESTS_PER_METHOD; i++) {
                        System.out.print("Testing Method " + methodName + " test number " + i);
                        testList = newResetedTestList(testList);
                        ArrayList<String> toCompare = linkedListToArrayList(testList);

                        // perform actions here
                        Iterator<String> testListIterator = testList.iterator();
                        Iterator<String> toCompareIterator = toCompare.iterator();
                        int random = (int) (Math.random() * toCompare.size()) + 1;
                        for (int j = 0; j < random; j++) {
                                if (testListIterator.hasNext()) {
                                        testListIterator.next();
                                }
                                if (toCompareIterator.hasNext()) {
                                        toCompareIterator.next();
                                }
                        }
                        toCompareIterator.remove();
                        testListIterator.remove();
                        String[] expected = toCompare.toArray(new String[toCompare.size()]);
                        String[] actual = toArray2(testList);
                        if (arraysSame(expected, actual)) {
                                System.out.println("   " + methodName + " test " + i + " PASSED");
                        } else {
                                System.out.println("   " + methodName + " test " + i + " FAILED    Expected OutPut: "
                                                + Arrays.toString(expected) + " Actual Output = "
                                                + Arrays.toString(actual));
                                numTestsFailed++;
                                failedTests.add(methodName);
                        }
                }
                System.out.println();
                System.out.println("RESULTS:");
                System.out.println("TOTAL TESTS: " + (NUM_TESTS_PER_METHOD * methodNames.length) + " | TOTAL FAILED: "
                                + numTestsFailed + " | FAILED METHODS: " + failedTests.toString() + " |");
        }

        private static LL314<String> newResetedTestList(LL314<String> a) {
                a.makeEmpty();
                int random = (int) (Math.random() * MAX_LENGTH) + 1;
                for (int j = 0; j < random; j++) {
                        a.add(String.valueOf((char) (j + 'A')));
                }
                return a;
        }

        private static ArrayList<String> linkedListToArrayList(LL314<String> testList) {
                ArrayList<String> result = new ArrayList<>();
                Iterator<String> s = testList.iterator();
                while (s.hasNext()) {
                        result.add(s.next());
                }
                return result;
        }

        private static LL314<String> arrayListToLinkedList(ArrayList<String> toCompare) {
                LL314<String> result = new LL314<>();
                Iterator<String> s = toCompare.iterator();
                while (s.hasNext()) {
                        result.add(s.next());
                }
                return result;
        }

        private static String[] toArray2(IList<String> actualA) {
                String[] result = new String[actualA.size()];
                Iterator<String> it = actualA.iterator();
                int index = 0;
                while (it.hasNext()) {
                        result[index] = it.next();
                        index++;
                }
                return result;
        }

        private static void itRemoveStressTests() {
                /*
                 * Test that the iterator remove is O(1).
                 * Total time to remove half of list should roughly double
                 * when size of list is doubled.
                 */
                final int SEED = 19431215;
                Random r = new Random(SEED);
                Stopwatch st = new Stopwatch();
                final int NUM_DOUBLINGS = 6;
                final int INITIAL_N = 50_000;
                int n = INITIAL_N;
                for (int i = 0; i < NUM_DOUBLINGS; i++) {
                        LL314<Double> list = new LL314<>();
                        for (int j = 0; j < n; j++) {
                                list.add(r.nextDouble());
                        }
                        Iterator<Double> it = list.iterator();
                        final int LIMIT = n / 2;
                        for (int j = 0; j < LIMIT; j++) {
                                it.next();
                        }
                        st.start();
                        while (it.hasNext()) {
                                it.next();
                                it.remove();
                        }
                        st.stop();
                        System.out.println("number of elements = " + n
                                        + " time to remove half of list with iterator = " + st);
                        n *= 2;
                }
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

        private static final int NUM_DOUBLINGS_OF_N = 5;
        private static final int NUM_REPEATS_OF_TEST = 100;

        // A method to be run to compare the LinkedList you are completing and the Java
        // ArrayList class
        private static void comparison() {
                Stopwatch s = new Stopwatch();

                int initialN = 30000;
                addEndArrayList(s, initialN, NUM_DOUBLINGS_OF_N);
                addEndLinkedList(s, initialN, NUM_DOUBLINGS_OF_N);

                initialN = 2000;
                addFrontArrayList(s, initialN, NUM_DOUBLINGS_OF_N);
                initialN = 10000;
                addFrontLinkedList(s, initialN, NUM_DOUBLINGS_OF_N);

                initialN = 2000;
                removeFrontArrayList(s, initialN, NUM_DOUBLINGS_OF_N);
                initialN = 5000;
                removeFrontLinkedList(s, initialN, NUM_DOUBLINGS_OF_N);

                initialN = 10000;
                getRandomArrayList(s, initialN, NUM_DOUBLINGS_OF_N);
                initialN = 1000;
                getRandomLinkedList(s, initialN, NUM_DOUBLINGS_OF_N);

                initialN = 50000;
                getAllArrayListUsingIterator(s, initialN, NUM_DOUBLINGS_OF_N);
                getAllLinkedListUsingIterator(s, initialN, NUM_DOUBLINGS_OF_N);

                initialN = 100000;
                getAllArrayListUsingGetMethod(s, initialN, NUM_DOUBLINGS_OF_N);
                initialN = 1000;
                getAllLinkedListUsingGetMethod(s, initialN, NUM_DOUBLINGS_OF_N);

        }

        // These methods illustrate a failure to use polymorphism.
        // If the students had implemented the Java list interface there
        // could be a single method. Also we could use function objects to
        // reduce the awful repetition of code.
        private static void addEndArrayList(Stopwatch s, int initialN, int numTests) {
                double[] totalTimes = new double[numTests];
                for (int t = 0; t < NUM_REPEATS_OF_TEST; t++) {
                        int n = initialN;
                        for (int i = 0; i < numTests; i++) {
                                ArrayList<Integer> javaList = new ArrayList<>();
                                s.start();
                                for (int j = 0; j < n; j++) {
                                        javaList.add(j);
                                }
                                s.stop();
                                totalTimes[i] += s.time();
                                n *= 2;
                        }
                }
                showResults("Adding at end: ArrayList", totalTimes, initialN);
        }

        private static void showResults(String title, double[] times, int initialN) {
                System.out.println();
                System.out.println("Number of times test run: " + NUM_REPEATS_OF_TEST);
                System.out.println(title);
                for (double time : times) {
                        System.out.print("N = " + initialN + ", total time: ");
                        System.out.printf("%7.4f\n", time);
                        initialN *= 2;
                }
                System.out.println();
        }

        private static void addEndLinkedList(Stopwatch s, int initialN, int numTests) {
                double[] totalTimes = new double[numTests];
                for (int t = 0; t < NUM_REPEATS_OF_TEST; t++) {
                        int n = initialN;
                        for (int i = 0; i < numTests; i++) {
                                LL314<Integer> studentList = new LL314<>();
                                s.start();
                                for (int j = 0; j < n; j++) {
                                        studentList.add(j);
                                }
                                s.stop();
                                totalTimes[i] += s.time();
                                n *= 2;
                        }
                }
                showResults("Adding at end: LinkedList", totalTimes, initialN);
        }

        private static void addFrontArrayList(Stopwatch s, int initialN, int numTests) {
                double[] totalTimes = new double[numTests];
                for (int t = 0; t < NUM_REPEATS_OF_TEST; t++) {
                        int n = initialN;
                        for (int i = 0; i < numTests; i++) {
                                ArrayList<Integer> javaList = new ArrayList<>();
                                s.start();
                                for (int j = 0; j < n; j++) {
                                        javaList.add(0, j);
                                }
                                s.stop();
                                totalTimes[i] += s.time();
                                n *= 2;
                        }
                }
                showResults("Adding at front: ArrayList", totalTimes, initialN);
        }

        private static void addFrontLinkedList(Stopwatch s, int initialN, int numTests) {
                double[] totalTimes = new double[numTests];
                for (int t = 0; t < NUM_REPEATS_OF_TEST; t++) {
                        int n = initialN;
                        for (int i = 0; i < numTests; i++) {
                                LL314<Integer> studentList = new LL314<>();
                                s.start();
                                for (int j = 0; j < n; j++) {
                                        studentList.insert(0, j);
                                }
                                s.stop();
                                totalTimes[i] += s.time();
                                n *= 2;
                        }
                }
                showResults("Adding at front: LinkedList", totalTimes, initialN);
        }

        private static void removeFrontArrayList(Stopwatch s, int initialN, int numTests) {
                double[] totalTimes = new double[numTests];
                for (int t = 0; t < NUM_REPEATS_OF_TEST; t++) {
                        int n = initialN;
                        for (int i = 0; i < numTests; i++) {
                                ArrayList<String> javaList = new ArrayList<>();
                                for (int j = 0; j < n; j++)
                                        javaList.add(j + "");
                                s.start();
                                while (!javaList.isEmpty()) {
                                        javaList.remove(0);
                                }
                                s.stop();
                                totalTimes[i] += s.time();
                                n *= 2;
                        }
                }
                showResults("Removing from front: ArrayList", totalTimes, initialN);
        }

        private static void removeFrontLinkedList(Stopwatch s, int initialN, int numTests) {
                double[] totalTimes = new double[numTests];
                for (int t = 0; t < NUM_REPEATS_OF_TEST; t++) {
                        int n = initialN;
                        for (int i = 0; i < numTests; i++) {
                                LL314<String> studentList = new LL314<>();
                                for (int j = 0; j < n; j++) {
                                        studentList.add(j + "");
                                }
                                s.start();
                                while (studentList.size() != 0) {
                                        studentList.removeFirst();
                                }
                                s.stop();
                                totalTimes[i] += s.time();
                                n *= 2;
                        }
                }
                showResults("removing from front: LinkedList", totalTimes, initialN);
        }

        private static void getRandomArrayList(Stopwatch s, int initialN, int numTests) {
                double[] totalTimes = new double[numTests];
                for (int t = 0; t < NUM_REPEATS_OF_TEST; t++) {
                        int n = initialN;
                        int total = 0;
                        Random r = new Random();
                        for (int i = 0; i < numTests; i++) {
                                ArrayList<Integer> javaList = new ArrayList<>();
                                for (int j = 0; j < n; j++) {
                                        javaList.add(j);
                                }
                                s.start();
                                for (int j = 0; j < n; j++) {
                                        total += javaList.get(r.nextInt(javaList.size()));
                                }
                                s.stop();
                                totalTimes[i] += s.time();
                                n *= 2;
                        }
                }
                showResults("Getting random: ArrayList", totalTimes, initialN);
        }

        private static void getRandomLinkedList(Stopwatch s, int initialN, int numTests) {
                double[] totalTimes = new double[numTests];
                for (int t = 0; t < NUM_REPEATS_OF_TEST; t++) {
                        int n = initialN;
                        Random r = new Random();
                        for (int i = 0; i < numTests; i++) {
                                LL314<Integer> studentList = new LL314<>();
                                for (int j = 0; j < n; j++) {
                                        studentList.add(j);
                                }
                                int total = 0;
                                s.start();
                                for (int j = 0; j < n; j++) {
                                        total += studentList.get(r.nextInt(studentList.size()));
                                }
                                s.stop();
                                totalTimes[i] += s.time();
                                n *= 2;
                        }
                }
                showResults("Getting random: LinkedList", totalTimes, initialN);
        }

        private static void getAllArrayListUsingIterator(Stopwatch s, int initialN, int numTests) {

                double[] totalTimes = new double[numTests];
                for (int t = 0; t < NUM_REPEATS_OF_TEST; t++) {
                        int n = initialN;
                        for (int i = 0; i < numTests; i++) {
                                ArrayList<Integer> javaList = new ArrayList<>();
                                for (int j = 0; j < n; j++) {
                                        javaList.add(j);
                                }
                                Iterator<Integer> it = javaList.iterator();
                                s.start();
                                int total = 0;
                                while (it.hasNext()) {
                                        total += it.next();
                                }
                                s.stop();
                                totalTimes[i] += s.time();
                                n *= 2;
                        }
                }
                showResults("Getting all using iterator: ArrayList", totalTimes, initialN);
        }

        private static void getAllLinkedListUsingIterator(Stopwatch s, int initialN, int numTests) {
                double[] totalTimes = new double[numTests];
                for (int t = 0; t < NUM_REPEATS_OF_TEST; t++) {
                        int n = initialN;
                        for (int i = 0; i < numTests; i++) {
                                LL314<Integer> studentList = new LL314<>();
                                for (int j = 0; j < n; j++) {
                                        studentList.add(j);
                                }
                                Iterator<Integer> it = studentList.iterator();
                                s.start();
                                int total = 0;
                                while (it.hasNext()) {
                                        total += it.next();
                                }
                                s.stop();
                                totalTimes[i] += s.time();
                                n *= 2;
                        }
                }
                showResults("Getting all using iterator: LinkedList", totalTimes, initialN);
        }

        private static void getAllArrayListUsingGetMethod(Stopwatch s, int initialN, int numTests) {
                double[] totalTimes = new double[numTests];
                for (int t = 0; t < NUM_REPEATS_OF_TEST; t++) {
                        int n = initialN;
                        for (int i = 0; i < numTests; i++) {
                                ArrayList<Integer> javaList = new ArrayList<>();
                                for (int j = 0; j < n; j++) {
                                        javaList.add(j);
                                }
                                s.start();
                                int x = 0;
                                for (int j = 0; j < javaList.size(); j++) {
                                        x += javaList.get(j);
                                }
                                s.stop();
                                totalTimes[i] += s.time();
                                n *= 2;
                        }
                }
                showResults("Getting all using get method: ArrayList", totalTimes, initialN);
        }

        private static void getAllLinkedListUsingGetMethod(Stopwatch s, int initialN, int numTests) {
                double[] totalTimes = new double[numTests];
                for (int t = 0; t < NUM_REPEATS_OF_TEST; t++) {
                        int n = initialN;
                        for (int i = 0; i < numTests; i++) {
                                LL314<Integer> studentList = new LL314<>();
                                for (int j = 0; j < n; j++) {
                                        studentList.add(j);
                                }
                                s.start();
                                int x = 0;
                                for (int j = 0; j < studentList.size(); j++) {
                                        x += studentList.get(j);
                                }
                                s.stop();
                                totalTimes[i] += s.time();
                                n *= 2;
                        }
                }
                showResults("Getting all using get method: LinkedList", totalTimes, initialN);
        }
}