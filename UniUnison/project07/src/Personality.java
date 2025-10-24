/*
 * CS312 Assignment 7 - Personality Quiz
 * 
 * On my honor, <NAME>, this programming assignment is my own work, 
 * I have not shared it with others and I will not share it in the future.
 *
 * A program to read a file with raw data from a Keirsey personality test
 * and print out the results.
 *
 *  Name:
 *  UTEID:
 *  
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Personality {
    
    // CS312 students - Add class constants here
    
    // The main method to process the data from the personality tests
    public static void main(String[] args) {
        
        Scanner keyboard = new Scanner(System.in);
        Scanner fileScanner = getFileScanner(keyboard);

        // CS312 students - Add code and methods calls here

        fileScanner.close();
        keyboard.close();
    }

    
    // CS312 - Add methods here
    
    
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
