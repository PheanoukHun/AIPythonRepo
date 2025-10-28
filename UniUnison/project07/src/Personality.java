/*
 * CS312 Assignment 7 - Personality Quiz
 * 
 * On my honor, Pheanouk Hun, this programming assignment is my own work, 
 * I have not shared it with others and I will not share it in the future.
 *
 * A program to read a file with raw data from a Keirsey personality test
 * and print out the results.
 *
 *  Name: Pheanouk Hun
 *  UTEID: ph23434
 *  
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.Scanner;
import java.util.Arrays;

public class Personality {

    // CS312 students - Add class constants here

    // The main method to process the data from the personality tests
    public static void main(String[] args) {

        Scanner keyboard = new Scanner(System.in);
        Scanner fileScanner = getFileScanner(keyboard);

        // CS312 students - Add code and methods calls here

        // Creates a set of String Arrays that contains all the personality categories.
        String[] typeAs = new String[] { "E", "S", "T", "J" };
        String[] typeBs = new String[] { "I", "N", "F", "P" };

        // Get the Number of Personality Categories
        int numCategories = typeAs.length;

        // Creates a String Array that contains two elements: Name and Answers
        String[] profile = new String[2];
        int name = 0, answers = 1;

        // Create the Arrays that Stores the Answers of Questions
        int[] numAs = new int[numCategories];
        int[] numBs = new int[numCategories];

        // Create the Percentages Arrays
        double[] percentA = new double[numCategories];
        double[] percentB = new double[numCategories];

        // Gets the number of People in the Input File.
        int numUsers = fileScanner.nextInt();
        fileScanner.nextLine();

        // Loops runs for once per person in the input file
        for (int i = 0; i < numUsers; i++) {

            getPerson(fileScanner, profile);

            updatePersonalityArrays(profile[answers], numAs, numBs, percentA, percentB);
            String personalityType = getPersonality(typeAs, typeBs, percentA, percentB);

            printPersonality(profile[name], personalityType, percentA, percentB);
            resetArrays(numAs, numBs, percentA, percentB);
        }

        // Closes the File Scanner and Keyboard Scanner
        fileScanner.close();
        keyboard.close();
    }

    /**
     * Reads in the Name and Answers of a person's entry and writes it to the
     * profile string array
     * 
     * @param fileSc  - A Scanner Object that reads the input files.
     * @param profile - A String array object that is used to store the name and the
     *                answers of the person.
     * 
     * @return void
     */
    public static void getPerson(Scanner fileSc, String[] profile) {
        profile[0] = fileSc.nextLine();
        profile[1] = fileSc.nextLine();
    }

    /**
     * This method reads all the answers from the answers String and updates the num
     * arrays and double arrays.
     * 
     * @param answers  - A string that contains a list of all the answers to the
     *                 quiz questions. Ex: ABBAABB...
     * @param numAs    - An int array containing the number of 'A's or 'a's
     *                 answered per category.
     * @param numBs    - An int array containing the number of 'B's or 'b's
     *                 answered per category.
     * @param percentA - A double array containing the percentage of 'A's or
     *                 'a's of all the questions answered
     * @param percentB - A double array containing the percentage of 'B's or
     *                 'b's of all the questions answered
     * 
     * @return void
     */
    public static void updatePersonalityArrays(String answers, int[] numAs, int[] numBs,
            double[] percentA, double[] percentB) {

        // Convert answers variable to uppercase.
        answers = answers.toUpperCase();

        // Loops runs for once per answer in the answers variable
        for (int i = 0; i < answers.length(); i++) {

            // Get the Answer and the Location to Check the answer.
            char answer = answers.charAt(i);
            int updateLocation = (i % 7 + 1) / 2;

            // Updates the Num Arrays
            updateNumArrays(updateLocation, answer, numAs, numBs);
        }

        // Updates the Percentage Arrays
        updatePercentageArrays(numAs, numBs, percentA, percentB);
    }

    /**
     * The method increments either the 'A's or 'B's arrays at point updateLocation
     * based on the char answer.
     * 
     * @param updateLocation - The int index value where the answer counting arrays
     *                       should be updated at.
     * @param answer         - A char value that the person answered, used to
     *                       compare the whether to add to the 'A's or 'B's
     *                       category.
     * @param numAs          - An int array containing the number of 'A's or 'a's
     *                       answered per category.
     * @param numBs          - An int array containing the number of 'B's or 'b's
     *                       answered per category.
     * 
     * @return void
     */
    public static void updateNumArrays(int updateLocation, char answer, int[] numAs,
            int[] numBs) {
        
        // IF: the char answer equals A, increase the A Num Array at the Location.
        if (answer == 'A') {
            numAs[updateLocation]++;

        // ELSE IF: the char answer equals B, increase the B Num Array at the Location.
        } else if (answer == 'B') {
            numBs[updateLocation]++;
        }
    }

    /**
     * This method updates the percenntage arrays based on the number arrays.
     * Ex: [9, 15, 15, 15] && [1, 5, 5, 5] => [90.0, 75.0, 75.0, 75.0] && [10.0,
     * 25.0, 25.0, 25.0]
     * 
     * @param numAs    - An int array containing the number of 'A's or 'a's answered
     *                 per category.
     * @param numBs    - An int array containing the number of 'B's or 'b's answered
     *                 per category.
     * @param percentA - A double array containing the percentage of 'A's or 'a's of
     *                 all the questions answered
     * @param percentB - A double array containing the percentage of 'B's or 'b's of
     *                 all the questions answered
     * 
     * @return void
     */
    public static void updatePercentageArrays(int[] numAs, int[] numBs,
            double[] percentA, double[] percentB) {

        // 
        for (int i = 0; i < numAs.length; i++) {
            int denominator = numAs[i] + numBs[i];

            if (denominator != 0) {
                percentA[i] = Math.round((double) numAs[i] * 100 / denominator);
                percentB[i] = Math.round((double) numBs[i] * 100 / denominator);
            }
        }
    }

    /**
     * This method analyzes the results of the two percentage arrays to determine
     * the categories that each user gets sorted into.
     * 
     * Ex: percentA: [10, 35, 35, 30] & percentB: [90, 65, 65, 70] == "INTP"
     * 
     * @param typeAs   - A String Array that contains the Categories that would be
     *                 chosen if a majority 'A' was chosen
     * @param typeBs   - A String Array that contains the Categories that would be
     *                 chosen if a majority 'B' was chosen
     * @param percentA - A double array containing the percentage of 'A's or 'a's of
     *                 all the questions answered
     * @param percentB - A double array containing the percentage of 'B's or 'b's of
     *                 all the questions answered
     * 
     * @return A string value that shows the person's personality. Ex: INTP
     */
    public static String getPersonality(String[] typeAs, String[] typeBs, double[] percentA,
            double[] percentB) {
        String personality = "";

        double noAnswers = 0.0;
        double threshold = 50.0;

        for (int i = 0; i < percentA.length; i++) {
            if (percentA[i] == noAnswers && percentB[i] == noAnswers) {
                personality += "-";
            } else if (percentA[i] == percentB[i]) {
                personality += "X";
            } else if (percentA[i] > threshold) {
                personality += typeAs[i];
            } else {
                personality += typeBs[i];
            }
        }

        return personality;
    }

    /**
     * This method prints one formatted person personality breakdown by categories.
     * Ex: Betty Boop: 75 65 10 85 = INTP
     * 
     * @param personality - A string containing the personality of the person. Ex:
     *                    INTP
     * @param numAs       - An int array containing the number of 'A's or 'a's
     *                    answered per category.
     * @param numBs       - An int array containing the number of 'B's or 'b's
     *                    answered per category.
     * @param percentA    - A double array containing the percentage of 'A's or 'a's
     *                    of all the questions answered.
     * @param percentB    - A double array containing the percentage of 'B's or 'b's
     *                    of all the questions answered.
     * 
     * @return void
     */
    public static void printPersonality(String name, String personality, double[] percentA,
            double[] percentB) {
        System.out.printf("%30s:", name);

        for (int i = 0; i < percentA.length; i++) {
            if (percentA[i] == 0.0 && percentB[i] == 0.0) {
                System.out.printf("%11s", "NO ANSWERS");
            } else {
                System.out.printf("%11.0f", percentB[i]);
            }
        }

        System.out.println(" = " + personality);
    }

    /**
     * This method wipes all the data from the previous person off the arrays.
     * 
     * @param numAs    - An int array containing the number of 'A's or 'a's answered
     *                 per category.
     * @param numBs    - An int array containing the number of 'B's or 'b's answered
     *                 per category.
     * @param percentA - A double array containing the percentage of 'A's or 'a's of
     *                 all the questions answered.
     * @param percentB - A double array containing the percentage of 'B's or 'b's of
     *                 all the questions answered.
     * 
     * @return void
     */
    public static void resetArrays(int[] numAs, int[] numBs,
            double[] percentA, double[] percentB) {
        Arrays.fill(numAs, 0);
        Arrays.fill(numBs, 0);
        Arrays.fill(percentA, 0.0);
        Arrays.fill(percentB, 0.0);
    }

    // Method to choose a file.
    public static Scanner getFileScanner(Scanner keyboard) {
        Scanner result = null;
        try {
            System.out.print("Enter the name of the file with"
                    + " the personality data: ");
            String fileName = keyboard.nextLine().trim();
            result = new Scanner(new File(fileName));
        } catch (FileNotFoundException e) {
            System.out.println("Problem creating Scanner: " + e);
            System.out.println("Creating Scanner hooked up to default data "
                    + e);
            String defaultData = "1\nDEFAULT DATA\n"
                    + "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA"
                    + "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA";
            result = new Scanner(defaultData);
        }
        return result;
    }
}
