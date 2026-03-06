/*
 * SetTester.java - CS 314 Assignment 7
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
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

import javax.swing.JFileChooser;

/*
 * CS 314 Students, put your results to the experiments and answers to questions
 * here: CS314 Students, why is it unwise to implement all three of the
 * intersection, union, and difference methods in the AbstractSet class:
 * 
 * It is unwise to implement all three of the intersection, union, and difference methods in 
 * AbstractSet class because you need to call one of the methods to create a ISet<E> object 
 * and if you implemented all three of the methods in AbstractSet class, you would have created
 * a recurive cycle leading to a stack overflow error as the memory starts to fill up.
 */

public class SetTester {

        public static void main(String[] args) {
                // CS314 Students. Uncomment this section when ready to
                // run your experiments
                // try {
                // UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                // }
                // catch(Exception e) {
                // System.out.println("Unable to change look and feel");
                // }
                // Scanner sc = new Scanner(System.in);
                // String response = "";
                // do {
                // largeTest();
                // System.out.print("Another file? Enter y to do another file: ");
                // response = sc.next();
                // } while( response != null && response.length() > 0
                // && response.substring(0,1).equalsIgnoreCase("y") );

                int testNum = 1;
                boolean actual;

                // UnsortedSet Tests

                // UnsortedSets - Add()
                ISet<String> s1 = new UnsortedSet<>();

                s1.add("a");
                s1.add("ab");
                s1.add("A");
                s1.add("c");
                s1.add("Ca");

                actual = s1.add("a");
                showTestResults(actual, false, testNum, s1, null,
                                "Adding Test for Item Already Found Within Set");
                testNum++;

                // UnsortedSets - Contains()

                actual = s1.contains("Hello");
                showTestResults(actual, false, testNum, s1, null,
                                "Contains Method Test with Value Not Found Within Set.");
                testNum++;

                // Removes() - for UnsortedSets
                actual = s1.remove("");
                showTestResults(actual, false, testNum, s1, null,
                                "Remove Value Not Found within Set");
                testNum++;

                // Size() - For UnSortedSets
                actual = s1.size() == 5;
                showTestResults(actual, true, testNum, s1, null, "Test Size Method");
                testNum++;

                // containsAll() - For UnSortedSets
                ISet<String> s2 = new UnsortedSet<>();
                s2.add("a");
                s2.add("ab");
                s2.add("z");

                actual = s1.containsAll(s2);
                showTestResults(actual, false, testNum, s1, s2,
                                "checks to see if the set1 contains all elements of set2");
                testNum++;

                // Difference() - For UnSortedSets
                s1 = new UnsortedSet<>();
                s1.add("a");
                s1.add("b");
                s1.add("c");
                s1.add("d");

                s2 = new UnsortedSet<>();
                s2.add("b");
                s2.add("c");
                s2.add("d");
                s2.add("e");

                ISet<String>s3 = new UnsortedSet<>();
                s3.add("a");

                ISet<String> s4 = s1.difference(s2);

                actual = s3.equals(s4);
                showTestResults(actual, true, testNum, s3, s4, "Gets the Difference of two Sets");
                testNum++;

                // Union() - For UnsortedSets
                s4 = s1.union(s2);

                s3.add("b");
                s3.add("c");
                s3.add("d");
                s3.add("e");

                actual = s3.equals(s4);
                showTestResults(actual, true, testNum, s3, s4, "Gets the Union of two Sets");
                testNum++;

                // Intersection() - For UnsortedSets
                s3 = s1.intersection(s2);

                s4.remove("a");
                s4.remove("e");

                actual = s3.equals(s4);
                showTestResults(actual, true, testNum, s3, s4, "Gets the Intersection of 2 Sets");
                testNum++;

                // AddAll() - For UnsortedSets
                s3 = new UnsortedSet<>();
                s3.addAll(s1);

                actual = s3.equals(s1);
                showTestResults(actual, true, testNum, s1, s3,
                                "Adds All Value from set 1 to set 3");
                testNum++;

                // Clear() - For UnsortedSets
                s3.clear();

                actual = s3.size() == 0;
                showTestResults(actual, true, testNum, s3, null,
                                "Clears the entry found in the set");
                testNum++;

                // Equals() - For UnsortedSets
                s3 = new UnsortedSet<>();
                s3.addAll(s1);

                actual = s3.equals(s1);
                showTestResults(actual, true, testNum, s1, s3, "Tests the Equal() method");
                testNum++;

                // UnsortedSets - Iterator()
                Iterator<String> it1 = s1.iterator();
                Iterator<String> it2 = s3.iterator();
                while (it1.hasNext() && it2.hasNext()) {
                        it1.next();
                        it2.next();
                }

                actual = !(it1.hasNext() || it2.hasNext());
                showTestResults(actual, true, testNum, s1, s3,
                                "Testing the Iterator Method for Set 1 and Set 2");
                testNum++;

                // Sorted Sets Test Cases

                // SortedSets - Add()
                s1 = new SortedSet<>();

                s1.add("a");
                s1.add("ab");
                s1.add("A");
                s1.add("c");
                s1.add("Ca");

                actual = s1.add("Cab");
                showTestResults(actual, true, testNum, s1, null,
                                "Adding Test for Item Already Found Within Set");
                testNum++;

                // SortedSets - Contains()
                actual = s1.contains("a");
                showTestResults(actual, true, testNum, s1, null,
                                "Contains Method Test with Value Not Found Within Set.");
                testNum++;

                // SortedSets - ContainsAll()
                s2 = new SortedSet<>();
                s2.add("c");
                s2.add("Ca");

                actual = s1.containsAll(s2);
                showTestResults(actual, true, testNum, s1, s2, "ContainsAll Testing.");
                testNum++;

                // SortedSets - Remove()
                actual = s1.remove("ab");
                showTestResults(actual, true, testNum, s1, null, "Removing a value from a set.");
                testNum++;

                // SortedSets - Size()
                actual = s1.size() == 5;
                showTestResults(actual, true, testNum, s1, null, "Size Method Testing.");
                testNum++;
                
                // SortedSets - Equals()
                ISet<Integer> s5 = new SortedSet<>();
                s5.add(1);
                s5.add(2);
                s5.add(3);
                s5.add(4);
                s5.add(5);
                s5.add(6);

                actual = s1.equals(s5);
                showTestResults(actual, false, testNum, s1, null,
                                "Equals method with different type sets.");
                testNum++;

                // SortedSets - Differences()
                s2.addAll(s1);
                actual = s1.difference(s2).size() == 0;
                showTestResults(actual, true, testNum, s1, s2, "Size Method Testing.");
                testNum++;

                // SortedSets - Intersection()
                s3 = s1.intersection(s2);
                actual = s3.equals(s1);
                showTestResults(actual, true, testNum, s1, null, "Same Value Intersection.");
                testNum++;

                // SortedSets - Union()
                s4 = new SortedSet<>();
                s4.add("z");
                s4 = s4.union(s1);

                s3.add("z");

                actual = s4.equals(s3);
                showTestResults(actual, true, testNum, s3, s4, "Union.");
                testNum++;

                // SortedSets - AddAll()
                s4 = new SortedSet<>();
                s4.addAll(s1);

                actual = s4.equals(s1);
                showTestResults(actual, true, testNum, s1, s4, "AddAll Operation.");
                testNum++;

                // SortedSets - Clear()
                s4.clear();

                actual = s4.size() == 0;
                showTestResults(actual, true, testNum, s3, s4, "Clear.");
                testNum++;

                // SortedSets - Iterators()

                s1 = s1.union(s3);

                it1 = s1.iterator();
                it2 = s3.iterator();

                while (it1.hasNext() && it2.hasNext()) {
                        actual = actual && it1.next().equals(it2.next());
                }

                showTestResults(actual, true, testNum, s3, s1, "Iterator.");
                testNum++;

                // SortedSets - Min()
                actual = ((SortedSet<String>) s1).min().equals("A");
                showTestResults(actual, true, testNum, s1, null, "Min.");
                testNum++;

                // SortedSets - Min()
                actual = ((SortedSet<String>) s1).max().equals("z");
                showTestResults(actual, true, testNum, s1, null, "Max.");
                testNum++;

                // Cross Type Equivalence
                s1 
        }

        // print out results of test
        private static <E> void showTestResults(boolean actualResult, boolean expectedResult,
                        int testNumber, ISet<E> set1, ISet<E> set2, String testDescription) {
                if (actualResult == expectedResult) {
                        System.out.println("Passed test " + testNumber);
                } else {
                        System.out.print("Failed test ");
                        System.out.println(testNumber + ": " + testDescription);
                        System.out.println("Expected result: " + expectedResult);
                        System.out.println("Actual result  : " + actualResult);
                        System.out.println("Set 1: " + set1);
                        if (set2 != null) {
                                System.out.println("Set 2: " + set2);
                        }
                }

        }

        /*
         * Method asks user for file and compares run times to add words from file
         * to various sets, including CS314 UnsortedSet and SortedSet and Java's
         * TreeSet and HashSet.
         */
        private static void largeTest() {
                System.out.println();
                System.out.println("Opening Window to select file. "
                                + "You may have to minimize other windows.");
                String text = convertFileToString();
                Scanner keyboard = new Scanner(System.in);
                System.out.println();
                System.out.println("***** CS314 SortedSet: *****");
                processTextCS314Sets(new SortedSet<String>(), text, keyboard);
                System.out.println("****** CS314 UnsortedSet: *****");
                processTextCS314Sets(new UnsortedSet<String>(), text, keyboard);
                System.out.println("***** Java HashSet ******");
                processTextJavaSets(new HashSet<String>(), text, keyboard);
                System.out.println("***** Java TreeSet ******");
                processTextJavaSets(new TreeSet<String>(), text, keyboard);
                keyboard.close();
        }

        /*
         * pre: set != null, text != null Method to add all words in text to the
         * given set. Words are delimited by white space. This version for CS314
         * sets.
         */
        private static void processTextCS314Sets(ISet<String> set, String text, Scanner keyboard) {
                Stopwatch s = new Stopwatch();
                Scanner sc = new Scanner(text);
                int totalWords = 0;
                s.start();
                while (sc.hasNext()) {
                        totalWords++;
                        set.add(sc.next());
                }
                s.stop();

                showResultsAndWords(set, s, totalWords, set.size(), keyboard);
        }

        /*
         * pre: set != null, text != null Method to add all words in text to the
         * given set. Words are delimited by white space. This version for Java
         * Sets.
         */
        private static void processTextJavaSets(Set<String> set, String text,
                        Scanner keyboard) {
                Stopwatch s = new Stopwatch();
                Scanner sc = new Scanner(text);
                int totalWords = 0;
                s.start();
                while (sc.hasNext()) {
                        totalWords++;
                        set.add(sc.next());
                }
                s.stop();
                sc.close();

                showResultsAndWords(set, s, totalWords, set.size(), keyboard);
        }

        /*
         * Show results of add words to given set.
         */
        private static <E> void showResultsAndWords(Iterable<E> set, Stopwatch s, int totalWords,
                        int setSize, Scanner keyboard) {

                System.out.println("Time to add the elements in the text to this set: " + s.toString());
                System.out.println("Total number of words in text including duplicates: " + totalWords);
                System.out.println("Number of distinct words in this text " + setSize);

                System.out.print("Enter y to see the contents of this set: ");
                String response = keyboard.next();

                if (response != null && response.length() > 0
                                && response.substring(0, 1).equalsIgnoreCase("y")) {
                        for (Object o : set) {
                                System.out.println(o);
                        }
                }
                System.out.println();
        }

        /*
         * Ask user to pick a file via a file choosing window and convert that file
         * to a String. Since we are evaluating the file with many sets convert to
         * string once instead of reading through file multiple times.
         */
        private static String convertFileToString() {
                // create a GUI window to pick the text to evaluate
                JFileChooser chooser = new JFileChooser(".");
                StringBuilder text = new StringBuilder();
                int retval = chooser.showOpenDialog(null);

                chooser.grabFocus();

                // read in the file
                if (retval == JFileChooser.APPROVE_OPTION) {
                        File source = chooser.getSelectedFile();
                        Scanner s = null;
                        try {
                                s = new Scanner(new FileReader(source));

                                while (s.hasNextLine()) {
                                        text.append(s.nextLine());
                                        text.append(" ");
                                }

                                s.close();
                        } catch (IOException e) {
                                System.out.println("An error occured while trying to read from the file: " + e);
                        } finally {
                                if (s != null) {
                                        s.close();
                                }
                        }
                }

                return text.toString();
        }
}