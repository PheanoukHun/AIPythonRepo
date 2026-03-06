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

                // UnsortedSet Tests
                

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