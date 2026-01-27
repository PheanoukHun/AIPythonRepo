/*
 * CodeCamp.java - CS314 Assignment 1
 *
 * By signing my name below, I affirm that this assignment is my own work. I
 * have neither given nor received unauthorized assistance on this assignment.
 *
 *  Name: Pheanouk Hun
 *  Email address: ph23434@my.utexas.edu 
 *  UTEID: ph23434
 * 
 * Description: 
 * - Problem 1: Calculates the Hamming Distance Between Two Objects.
 * - Problem 2: Checks Wheter two arrays are Permuations of Each Other.
 * - Problem 3: Returns the index of the word with the most vowels inside the word,
 *              perferring the index closest to 0.
 * - Problem 4: Runs an Experiment calculating the chances that you will
 *              find someone with the same birthday.
 * - Problem 5: Checks to see if the queens on the chessboards are safe.
 * - Problem 6: Calculates the subplot of an array with the most value.
 */

import java.util.Random;

public class CodeCamp {

    /**
     * Determine the Hamming distance between two arrays of ints. Neither the
     * parameter aData or bData are altered as a result of this method
     *
     * @param aData != null, aData.length == bData.length
     * @param bData != null
     * @return the Hamming Distance between the two arrays of ints.
     */
    public static int hammingDistance(int[] aData, int[] bData) {

        // check preconditions
        if (aData == null || bData == null || aData.length != bData.length) {
            throw new IllegalArgumentException("Violation of precondition: "
                    + "hammingDistance. neither parameter may equal null, arrays"
                    + " must be equal length.");
        }

        // Check to See if Both Arrays Are the Same Length
        if (aData == bData) {
            return 0;
        }

        // Check the Differences between the Two Arrays
        int diffCounter = 0;

        for (int i = 0; i < aData.length; i++) {
            if (aData[i] != bData[i]) {
                diffCounter++;
            }
        }

        return diffCounter;
    }

    /**
     * Determine if one array of ints is a permutation of another. Neither the
     * parameter aData or the parameter bData are altered as a result of this
     * method
     *
     * @param aData != null
     * @param bData != null
     * @return true if aData is a permutation of bData, false otherwise
     */
    public static boolean isPermutation(int[] aData, int[] bData) {

        // check preconditions
        if (aData == null || bData == null) {
            throw new IllegalArgumentException("Violation of precondition: "
                    + "isPermutation. neither parameter may equal null.");
        }

        // Check to See if Both Arrays Are the Same Length
        if (aData.length != bData.length) {
            return false;
        }

        int index = 0, alreadyFoundIndex = 0;
        int[] alreadySearchedNums = new int[aData.length];

        while (index < aData.length) {
            boolean alreadyFound = false;

            // Check to See if We Already Searched For this Number Before
            for (int i = 0; i < alreadyFoundIndex + 1; i++) {
                alreadyFound = (aData[index] == alreadySearchedNums[i]);
            }

            // Check Number of Occurrence of the Number
            if (!alreadyFound) {

                int aCount = 0, bCount = 0;
                alreadySearchedNums[alreadyFoundIndex] = aData[index];

                for (int i = 0; i < aData.length; i++) {
                    if (aData[index] == aData[i]) {
                        aCount++;
                    }

                    if (aData[index] == bData[i]) {
                        bCount++;
                    }
                }

                if (aCount != bCount) {
                    return false;
                }

                alreadyFoundIndex++;
            }

            index++;
        }

        return true;
    }

    /**
     * Determine the index of the String that has the largest number of vowels.
     * Vowels are defined as 'A', 'a', 'E', 'e', 'I', 'i', 'O', 'o', 'U', and
     * 'u'. The parameter arrayOfStrings is not altered as a result of this
     * method.
     *
     * @param arrayOfStrings the array to check, arrayOfStrings != null,
     * arrayOfStrings.length > 0, there is an least 1 non null element in
     * arrayOfStrings.
     *
     * @return return the index of the non-null element in arrayOfStrings that
     * has the largest number of characters that are vowels. If there is a tie
     * return the index closest to zero. The empty String, "", has zero vowels.
     * It is possible for the maximum number of vowels to be 0.
     */
    public static int mostVowels(String[] arrayOfStrings) {

        // check preconditions
        if (arrayOfStrings == null || arrayOfStrings.length == 0
                || !atLeastOneNonNull(arrayOfStrings)) {
            throw new IllegalArgumentException("Violation of precondition: "
                    + "mostVowels. parameter may not equal null and must contain "
                    + "at least one none null value.");
        }

        int mostVowelsIndex = 0;
        int numVowels = -1;
        String vowels = "AaEeIiOoUu";

        // Finding out how many vowels Each Word from the Lists Would Have
        for (int i = 0; i < arrayOfStrings.length; i++) {

            String word = arrayOfStrings[i];

            // Check Each Individual Word
            if (word != null) {

                int currNumVowels = 0;

                for (int j = 0; j < word.length(); j++) {
                    if (vowels.indexOf(word.charAt(j)) != -1) {
                        currNumVowels++;
                    }
                }

                if (currNumVowels > numVowels) {
                    numVowels = currNumVowels;
                    mostVowelsIndex = i;
                }
            }

        }

        return mostVowelsIndex;
    }

    /**
     * Perform an experiment simulating the birthday problem. Pick random
     * birthdays for the given number of people. Return the number of pairs of
     * people that share the same birthday.
     *
     * @param numPeople The number of people in the experiment. This value must
     * be > 0
     * @param numDaysInYear The number of days in the year for this experiement.
     * This value must be > 0
     *
     * @return The number of pairs of people that share a birthday after running
     * the simulation.
     */
    public static int sharedBirthdays(int numPeople, int numDaysInYear) {

        // check preconditions
        if (numPeople <= 0 || numDaysInYear <= 0) {
            throw new IllegalArgumentException("Violation of precondition: "
                    + "sharedBirthdays. both parameters must be greater than 0. " + "numPeople: "
                    + numPeople + ", numDaysInYear: " + numDaysInYear);
        }

        // Generating the Random Birthdays
        Random random = new Random();

        int sharedBirthdays = 0;
        int[] birthdays = new int[numPeople];

        for (int i = 0; i < numPeople; i++) {
            birthdays[i] = random.nextInt(numDaysInYear);
        }

        // Checking to See if any Birthdays are Shared
        for (int i = 0; i < numPeople; i++) {
            for (int j = i + 1; j < numPeople; j++) {
                if (birthdays[i] == birthdays[j]) {
                    sharedBirthdays++;
                }
            }
        }

        return sharedBirthdays;
    }

    /**
     * Determine if the chess board represented by board is a safe set up.
     *
     * @param board the chessboard; board != null, board.length > 0, board is a
     * square matrix. (In other words all rows in board have board.length
     * columns.), all elements of board == 'q' or '.'. 'q's represent queens,
     * '.'s represent open spaces.
     *
     * @return - true if the configuration of board is safe, that is no queen
     * can attack any other queen on the board. false otherwise. The parameter
     * board is not altered as a result of this method.
     */
    public static boolean queensAreSafe(char[][] board) {
        char[] validChars = {'q', '.'};

        // check preconditions
        if (board == null || board.length == 0 || !isSquare(board)
                || !onlyContains(board, validChars)) {
            throw new IllegalArgumentException("Violation of precondition: "
                    + "queensAreSafe. The board may not be null, must be square, "
                    + "and may only contain 'q's and '.'s");
        }

        // Checking Every Cell on the Board to See There is Queen
        for (int row = 0; row < board.length; row++) {

            for (int col = 0; col < board[row].length; col++) {

                // Check to See if the Queen in this Square is Safe
                if (board[row][col] == validChars[0]) {

                    boolean checkRight = checkRight(board, row, col, validChars[0]);
                    boolean checkDown = checkDown(board, col, row, validChars[0]);
                    boolean checkDiags = checkDiagnols(board, row, col, validChars[0]);

                    if (checkRight || checkDown || checkDiags) {
                        return false;
                    }
                }

            }
        }

        return true;
    }

    /**
     * Given a 2D array of ints return the value of the most valuable contiguous
     * sub rectangle in the 2D array. The sub rectangle must be at least 1 by 1.
     *
     * @param city The 2D array of ints representing the value of each block in
     * a portion of a city. mat != null, mat.length > 0, mat[0].length > 0, mat
     * is a rectangular matrix.
     *
     * @return return the value of the most valuable contiguous sub rectangle in
     * city.
     */
    public static int getValueOfMostValuablePlot(int[][] city) {

        // check preconditions
        if (city == null || city.length == 0 || city[0].length == 0 || !isRectangular(city)) {
            throw new IllegalArgumentException("Violation of precondition: "
                    + "getValueOfMostValuablePlot. The parameter may not be null,"
                    + " must value at least one row and at least"
                    + " one column, and must be rectangular.");
        }

        int largestSum = city[0][0];

        // Creating Every Possible Subplot and Checking the Value to see
        // if it is greater than the elargest sum.
        for (int top = 0; top < city.length; top++) {
            for (int bottom = top; bottom < city.length; bottom++) {
                for (int left = 0; left < city[top].length; left++) {
                    for (int right = left; right < city[top].length; right++) {

                        int currSum = 0;

                        for (int row = top; row <= bottom; row++) {
                            for (int col = left; col <= right; col++) {
                                currSum += city[row][col];
                            }
                        }

                        if (currSum > largestSum) {
                            largestSum = currSum;
                        }
                    }
                }
            }
        }

        return largestSum;
    }

    /**
     * Perform an experiment simulating the birthday problem. Pick random
     * birthdays for the given number of people. Return the number of pairs of
     * people that share the same birthday. Then get the Average Value of Shared
     * Birthday Experiment ran on the numTries parameter.
     *
     * @param numPeople - The number of people in the experiment. This value
     * must be > 0
     * @param numDaysInYear - The number of days in the year for this
     * experiement. This value must be > 0.
     * @param numTries - The number of tries the experiment will be ran. The
     * value must be > 0.
     *
     * @return - Getting the Average Value of the Experiment.
     */
    public static int averageOfSharedBirthdays(int numPeople, int numDaysInYear, int numTries) {

        // Checking the Procondition
        if (numTries <= 0) {
            throw new IllegalArgumentException("Violation of precondition: "
                    + "numTries value is less than 0.");
        }

        // Getting the Average Value
        int total = 0;

        for (int i = 0; i < numTries; i++) {
            total += sharedBirthdays(numPeople, numDaysInYear);
        }

        int mean = total / numTries;

        return mean;
    }

    /**
     * This Method runs a set of from Part 2 of Problem Number 4 and Prints the
     * Results to the Console.
     */
    public static void sharedBirthdayExperiments() {

        int numPeople = 182, numDays = 365, numTries = 1000000;

        // Scenario 1: 182 People and 365 Days And Print the Results
        System.out.println("\nProblem 4, Part 2, Scenario 1:");
        System.out.println("How many pairs of people Share the Same Birthday "
                + "(182 People, 365 Days):");

        System.out.println(CodeCamp.sharedBirthdays(numPeople, numDays)
                + " People Shares a Birthday.");

        // Scenario 2: Average of 1,000,000 tries of Scenario One
        System.out.println("\nProblem 4, Part 2, Scenario 2:");
        System.out.println("How many pairs of people Share the Same Birthday"
                + " (182 People, 365 Days):");
        System.out.println(CodeCamp.averageOfSharedBirthdays(numPeople, numDays, numTries)
                + " People Shares a Birthday.");

        // Scenario 4: Running the Experiment 50,000 times from 2 to 100
        System.out.println("| Num people | Num of experiments with at least one shared pair"
                + "| Percentage |");
        System.out.println("---------------------------------------------"
                + "---------------------------------");

        numTries = 50000;

        // Creates a Table Showing the Climbing Rate of Shared Birthday Pairs
        for (int i = 2; i <= 100; i++) {
            int count = 0;

            for (int j = 0; j < numTries; j++) {
                if (CodeCamp.sharedBirthdays(i, numDays) > 0) {
                    count++;
                }
            }

            double percent = (((double) count) / numTries) * 100;
            System.out.printf("| %-12s | %-45d | %9.2f |\n", i, count, percent);
        }
    }

    /*
     * pre: arrayOfStrings != null post: return true if at least one element in
     * arrayOfStrings
     * is not null, otherwise return false.
     */
    private static boolean atLeastOneNonNull(String[] arrayOfStrings) {
        // check precondition
        if (arrayOfStrings == null) {
            throw new IllegalArgumentException("Violation of precondition: "
                    + "atLeastOneNonNull. parameter may not equal null.");
        }
        boolean foundNonNull = false;
        int i = 0;
        while (!foundNonNull && i < arrayOfStrings.length) {
            foundNonNull = arrayOfStrings[i] != null;
            i++;
        }
        return foundNonNull;
    }

    /*
     * pre: mat != null post: return true if mat is a square matrix, false otherwise
     */
    private static boolean isSquare(char[][] mat) {
        if (mat == null) {
            throw new IllegalArgumentException(
                    "Violation of precondition: " + "isSquare. Parameter may not be null.");
        }
        final int numRows = mat.length;
        int row = 0;
        boolean isSquare = true;
        while (isSquare && row < numRows) {
            isSquare = (mat[row] != null) && (mat[row].length == numRows);
            row++;
        }
        return isSquare;
    }

    /*
     * pre: mat != null, valid != null post: return true if all elements in mat are
     * one of the
     * characters in valid
     */
    private static boolean onlyContains(char[][] mat, char[] valid) {
        // check preconditions
        if (mat == null || valid == null) {
            throw new IllegalArgumentException(
                    "Violation of precondition: " + "onlyContains. Parameters may not be null.");
        }
        String validChars = new String(valid);
        int row = 0;
        boolean onlyContainsValidChars = true;
        while (onlyContainsValidChars && row < mat.length) {
            int col = 0;
            while (onlyContainsValidChars && col < mat[row].length) {
                int indexOfChar = validChars.indexOf(mat[row][col]);
                onlyContainsValidChars = indexOfChar != -1;
                col++;
            }
            row++;
        }
        return onlyContainsValidChars;
    }

    /*
     * pre: mat != null, mat.length > 0 post: return true if mat is rectangular
     */
    private static boolean isRectangular(int[][] mat) {
        // check preconditions
        if (mat == null || mat.length == 0) {
            throw new IllegalArgumentException("Violation of precondition: "
                    + "isRectangular. Parameter may not be null and must contain"
                    + " at least one row.");
        }
        boolean correct = mat[0] != null;
        int row = 0;
        while (correct && row < mat.length) {
            correct = (mat[row] != null) && (mat[row].length == mat[0].length);
            row++;
        }
        return correct;
    }

    /**
     * Check to See if There is another queen to the right of the current spot.
     *
     * @param board - the chessboard; board != null, board.length > 0, board is
     * a square matrix. (In other words all rows in board have board.length
     * columns.), all elements of board == 'q' or '.'. 'q's represent queens,
     * '.'s represent open spaces.
     * @param row - An int value telling what row to look at.
     * @param startCol - The starting column to start the search
     * @param queen - 'q'; the marker that is used to check if there is another
     * queen in the same row.
     *
     * @return a true or false value depending on whether another queen is
     * found.
     */
    private static boolean checkRight(char[][] board, int row, int startCol, char queen) {

        for (int col = startCol + 1; col < board[row].length; col++) {
            if (board[row][col] == queen) {
                return true;
            }
        }

        return false;
    }

    /**
     * Check to See if There is another queen below the current spot.
     *
     * @param board - the chessboard; board != null, board.length > 0, board is
     * a square matrix. (In other words all rows in board have board.length
     * columns.), all elements of board == 'q' or '.'. 'q's represent queens,
     * '.'s represent open spaces.
     * @param col - An int value telling what col to look at.
     * @param startrow - The starting row to start the search
     * @param queen - 'q'; the marker that is used to check if there is another
     * queen in the same col.
     *
     * @return a true or false value depending on whether another queen is
     * found.
     */
    private static boolean checkDown(char[][] board, int col, int startRow, char queen) {

        for (int row = startRow + 1; row < board.length; row++) {
            if (board[row][col] == queen) {
                return true;
            }
        }

        return false;
    }

    /**
     * Check to See if there is another queen diagnol to the current spot.
     *
     * @param board - the chessboard; board != null, board.length > 0, board is
     * a square matrix. (In other words all rows in board have board.length
     * columns.), all elements of board == 'q' or '.'. 'q's represent queens,
     * '.'s represent open spaces.
     * @param strRow - The starting row to start the search
     * @param strCol - The starting column to start the search
     * @param queen - 'q'; the marker that is used to check if there is another
     * queen in the same col.
     *
     * @return a true or false value depending on whether another queen is
     * found.
     */
    private static boolean checkDiagnols(char[][] board, int strRow, int strCol, char queen) {

        int row = strRow + 1;
        int col = strCol + 1;

        // Checking the Diagnol Facing Down Right
        while (row < board.length && col < board[row].length) {
            if (board[row][col] == queen) {
                return true;
            }

            row++;
            col++;
        }

        row = strRow + 1;
        col = strCol - 1;

        // Checking the Diagnol Facing Down Left
        while (row < board.length && col >= 0) {
            if (board[row][col] == queen) {
                return true;
            }

            row++;
            col--;
        }

        return false;
    }

    // make constructor private so no instances of CodeCamp can not be created
    private CodeCamp() {

    }
}
