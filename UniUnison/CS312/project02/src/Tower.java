/*
 * CS312 Assignment 2 - UT Tower
 * 
 * On my honor, Pheanouk Hun, this programming assignment is my own work, 
 * I have not shared it with others and I will not share it in the future.
 *
 *  A program to print out the UT Tower in ASCII art form.
 *
 *  Name: Pheanouk Hun
 *  UTEID: ph23434
 */

public class Tower {

    // CS312 students, DO NOT ALTER THE FOLLOWING LINE except for the
    // value of the literal int.

    // You may change the literal int assigned to SIZE to any value from 2 to 100.
    public static final int SIZE = 3;

    public static void main(String[] args) {
        towerTop();
        towerMiddle();
        printSlopedBase();
        printFoundation();
    }

    // Purpose: Prints the spaces before each line at the top portion of the tower
    // Output: Prints leading spaces on the console before the tower’s top lines
    public static void printTopIndent() {
        int characterLens = SIZE * 10 + 3;
        int numOfChars = SIZE * 2 - 1;

        int numOfSpacesBeforeHashtags = (characterLens - numOfChars) / 2;

        // This Loops Prints all the Spaces
        for (int i = 0; i < numOfSpacesBeforeHashtags; i++) {
            System.out.print(" ");
        }
    }

    // Purpose: Prints the top section of the tower
    // Output: Prints the top portion of the tower to the console
    public static void towerTop() {
        int numOfLines = SIZE * 2;
        int numOfCharactersPerLine = SIZE * 2 - 1;
        int numOfPipeLines = numOfLines - 2;

        printTopIndent();
        for (int i = 0; i < numOfCharactersPerLine; i++) {
            System.out.print("#");
        }

        System.out.println();

        for (int i = 0; i < numOfPipeLines; i++) {
            printTopIndent();
            for (int j = 0; j < numOfCharactersPerLine; j++) {
                System.out.print("|");
            }
            System.out.println();
        }

        printTopIndent();
        for (int i = 0; i < numOfCharactersPerLine; i++) {
            System.out.print("#");
        }
        
        System.out.println();
    }

    // Purpose: Prints the spaces before each line in the middle section of the tower
    // Output: Prints leading spaces on the console before each line in the tower’s middle section
    public static void printMiddleIndent() {
        int numOfSpaces = SIZE * 4;

        // This Loops Prints all the Spaces
        for (int i = 0; i < numOfSpaces; i++) {
            System.out.print(" ");
        }
    }

    // Purpose: Prints the dashed lines in the middle section of the tower
    // Output: Prints dashes on the console for each line in the tower’s middle section
    public static void printDashedLines() {
        int numOfCharsPerLine = (SIZE * 2) + 3;
        printMiddleIndent();

        // This prints all the ~~~~~~~ Lines for the Midsection of the Tower
        for (int j = 0; j < numOfCharsPerLine; j++) {
            System.out.print("~");
        }
        System.out.println();
    }

    // Purpose: Prints the |-O-O-| section of the tower
    // Output: Prints the pattern "|-O-O-|" on the console for each line in this section
    public static void printTowerFloor() {
        System.out.print("|-");

        // This pritns out the O-O-O- Seciton of the Midsection of the Tower
        for (int i = 0; i < SIZE; i++) {
            System.out.print("O-");
        }

        System.out.println("|");
    }

    // Purpose: Prints the middle section of the tower
    // Output: Prints the middle portion of the tower to the console
    public static void towerMiddle() {
        
        int numOfLinesInTheCenterForTheTwoLines = SIZE * SIZE; 

        printDashedLines();

        // This prints out the two lines of the Tower Mid Sections At Once
        for (int i = 0; i < numOfLinesInTheCenterForTheTwoLines; i++) {
            printMiddleIndent();
            printTowerFloor();
            printDashedLines();
        }
    }

    // Purpose: Prints the sloped base of the tower
    // Output: Prints the base portion of the tower to the console
    public static void printSlopedBase() {

        int numOfLines = (SIZE / 2) + 1;
        int numOfTotalChars = (SIZE * 10) + 3;

        // This Loops prints out the Entire Sloped Base of the Tower
        for (int i = 0; i < numOfLines; i++) {

            int numOfSpaces = ((numOfLines - i) - 1) * 3;

            // This prints out the space in front of the The sloped base
            for (int j = 0; j < numOfSpaces; j++) {
                System.out.print(" ");
            }
            
            int numOfCharsPerFloor = numOfTotalChars - (2 * numOfSpaces);
            int numOfDoubleAndSingleQuotesRepetitions = (numOfCharsPerFloor - 3) / 2;

            System.out.print("/\"");

            // This prints '"'"'" portion of the Sloped base that changes based on the SIZE
            for (int j = 0; j < numOfDoubleAndSingleQuotesRepetitions; j++) {
                System.out.print("'\"");
            }
            System.out.println("\\");
        }
    }

    // Purpose: Prints the foundation of the tower
    // Output: Prints the bottom foundation lines of the tower to the console
    public static void printFoundation() {
        int numOfTotalChars = (SIZE * 10) + 3;
        int numOfOsAndQuotesRepetitions = (numOfTotalChars - 3) / 2;

        // This prints out the number of Lines in the Base of the Tower
        for (int i = 0; i < SIZE; i++) {
            System.out.print("/\"");

            // This prints out the "O"O"O" section of the Floor's Base
            for (int j = 0; j < numOfOsAndQuotesRepetitions; j++) {
                System.out.print("O\"");
            }

            System.out.println("\\");
        }
    }
}