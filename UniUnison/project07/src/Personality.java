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

        String[] typeAs = new String[] { "E", "S", "T", "J" };
        String[] typeBs = new String[] { "I", "N", "F", "P" };

        int numOfPersonalityCategories = typeAs.length;

        int[] numAs = new int[numOfPersonalityCategories];
        int[] numBs = new int[numOfPersonalityCategories];
        int[] totalNumQuestions = new int[numOfPersonalityCategories];

        double[] percentA = new double[numOfPersonalityCategories];
        double[] percentB = new double[numOfPersonalityCategories];

        int numUsers = fileScanner.nextInt();
        fileScanner.nextLine();

        for (int i = 0; i < numUsers; i++) {

            String name = fileScanner.nextLine();
            String answers = fileScanner.nextLine();

            analyzePerson(answers, numAs, numBs, percentA, percentB, totalNumQuestions);
            String personality = getPersonality(typeAs, typeBs, percentA, percentB);
            printPersonality(name, personality, percentA, percentB);
            resetArrays(numAs, numBs, percentA, percentB);
        }

        fileScanner.close();
        keyboard.close();
    }

    /**
     * 
     * @param questionAnswers
     * @param numAs
     * @param numBs
     * @param percentA
     * @param percentB
     */
    public static void analyzePerson(String questionAnswers, int[] numAs, int[] numBs,
            double[] percentA, double[] percentB, int[] totalNumQuestions) {

        questionAnswers = questionAnswers.toUpperCase();
        int numQuestions = questionAnswers.length();

        for (int i = 0; i < numQuestions; i++) {
            char answer = questionAnswers.charAt(i);
            int updateLocation = (i % 7 + 1) / 2;
            totalNumQuestions[updateLocation]++;
            updatePersonalityArrays(updateLocation, answer, numAs, numBs);
        }
        updatePercentageArrays(numAs, numBs, percentA, percentB, totalNumQuestions);
    }

    /**
     * 
     * @param updateLocation
     * @param answer
     * @param numAs
     * @param numBs
     */
    public static void updatePersonalityArrays(int updateLocation, char answer,
            int[] numAs, int[] numBs) {
        if (answer == 'A') {
            numAs[updateLocation]++;
        } else if (answer == 'B') {
            numBs[updateLocation]++;
        }
    }

    /**
     * 
     * @param numAs
     * @param numBs
     * @param percentA
     * @param percentB
     */
    public static void updatePercentageArrays(int[] numAs, int[] numBs,
            double[] percentA, double[] percentB, int[] totalNumQuestions) {
        for (int i = 0; i < numAs.length; i++) {
            int denominator = totalNumQuestions[i];

            if (denominator != 0) {
                percentA[i] = Math.round((double) numAs[i] * 100 / denominator);
                percentB[i] = Math.round((double) numBs[i] * 100 / denominator);
            }
        }
    }

    /**
     * 
     * @param typeAs
     * @param typeBs
     * @param percentA
     * @param percentB
     * @return
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
     * 
     * @param personProfile
     * @param numAs
     * @param numBs
     * @param percentA
     * @param percentB
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
     * 
     * @param numAs
     * @param numBs
     * @param percentA
     * @param percentB
     * @param personProfile
     */
    public static void resetArrays(int[] numAs, int[] numBs,
            double[] percentA, double[] percentB) {
        Arrays.fill(numAs, 0);
        Arrays.fill(numBs, 0);
        Arrays.fill(percentA, 0.0);
        Arrays.fill(percentB, 0.0);
    }

    // Method to choose a file.
    public static Scanner getFileScanner(Scanner keyboard){
        Scanner result = null;
        try {
            System.out.print("Enter the name of the file with"
                    + " the personality data: ");
            String fileName = keyboard.nextLine().trim();
            result = new Scanner(new File(fileName));
        } catch(FileNotFoundException e) {
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
