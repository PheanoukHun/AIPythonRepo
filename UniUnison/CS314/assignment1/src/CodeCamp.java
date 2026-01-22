/*
 * CodeCamp.java - CS314 Assignment 1
 *
 * By signing my name below, I affirm that this assignment is my own work. I
 * have neither given nor received unauthorized assistance on this assignment.
 *
 *  Name: Pheanouk Hun
 *  Email address: ph23434@my.utexas.edu 
 *  UTEID: ph23434
 */

import java.util.Random;

public class CodeCamp {

    /**
     * Determine the Hamming distance between two arrays of ints. Neither the
     * parameter
     * <tt>aData</tt> or <tt>bData</tt> are altered as a result of this method.<br>
     * 
     * @param aData != null, aData.length == aData.length
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

        if (aData == bData) {
            return 0;
        }

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
     * parameter
     * <tt>aData</tt> or the parameter <tt>bData</tt> are altered as a result of
     * this
     * method.<br>
     * 
     * @param aData != null
     * @param bData != null
     * @return <tt>true</tt> if aData is a permutation of bData, <tt>false</tt>
     *         otherwise
     */
    public static boolean isPermutation(int[] aData, int[] bData) {
        // check preconditions
        if (aData == null || bData == null) {
            throw new IllegalArgumentException("Violation of precondition: "
                    + "isPermutation. neither parameter may equal null.");
        }

        if (aData.length != bData.length) {
            return false;
        }

        int index = 0, alreadyFoundIndex = 0;
        int[] alreadySearchedNums = new int[aData.length];

        while (index < aData.length) {
            boolean alreadyFound = false;

            for (int i = 0; i < alreadyFoundIndex + 1; i++) {
                if (aData[index] == alreadySearchedNums[i]) {
                    alreadyFound = true;
                }
            }

            if (!alreadyFound) {

                alreadySearchedNums[alreadyFoundIndex] = aData[index];

                int aCount = 0, bCount = 0;

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
     * Vowels are
     * defined as <tt>'A', 'a', 'E', 'e', 'I', 'i', 'O', 'o',
     * 'U', and 'u'</tt>. The parameter <tt>arrayOfStrings</tt> is not altered as a
     * result of
     * this method.
     * <p>
     * pre: <tt>arrayOfStrings != null</tt>, <tt>arrayOfStrings.length > 0</tt>,
     * there is an
     * least 1 non null element in arrayOfStrings.
     * <p>
     * post: return the index of the non-null element in arrayOfStrings that has the
     * largest
     * number of characters that are vowels. If there is a tie return the index
     * closest to
     * zero. The empty String, "", has zero vowels. It is possible for the maximum
     * number of
     * vowels to be 0.<br>
     * 
     * @param arrayOfStrings the array to check
     * @return the index of the non-null element in arrayOfStrings that has the
     *         largest number
     *         of vowels.
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

        for (int i = 0; i < arrayOfStrings.length; i++) {

            String word = arrayOfStrings[i];

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
     * Perform an experiment simulating the birthday problem. Pick random birthdays
     * for the
     * given number of people. Return the number of pairs of people that share the
     * same
     * birthday.<br>
     * 
     * @param numPeople     The number of people in the experiment. This value must
     *                      be > 0
     * @param numDaysInYear The number of days in the year for this experiement.
     *                      This value
     *                      must be > 0
     * @return The number of pairs of people that share a birthday after running the
     *         simulation.
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
     * 
     * @param numPeople
     * @param numDaysInYear
     * @param numTries
     * @return
     */
    public static int averageOfSharedBirthdays(int numPeople, int numDaysInYear, int numTries) {
        
        int total = 0;

        for (int i = 0; i < numTries; i++) {
            total += sharedBirthdays(numPeople, numDaysInYear);
        }

        int mean = total / numTries;

        return mean;
    }

    /**
     * Determine if the chess board represented by board is a safe set up.
     * <p>
     * pre: board != null, board.length > 0, board is a square matrix. (In other
     * words all
     * rows in board have board.length columns.), all elements of board == 'q' or
     * '.'. 'q's
     * represent queens, '.'s represent open spaces.<br>
     * <p>
     * post: return true if the configuration of board is safe, that is no queen can
     * attack
     * any other queen on the board. false otherwise. the parameter <tt>board</tt>
     * is not
     * altered as a result of this method.<br>
     * 
     * @param board the chessboard
     * @return true if the configuration of board is safe, that is no queen can
     *         attack any
     *         other queen on the board. false otherwise.
     */
    public static boolean queensAreSafe(char[][] board) {
        char[] validChars = { 'q', '.' };
        // check preconditions
        if (board == null || board.length == 0 || !isSquare(board)
                || !onlyContains(board, validChars)) {
            throw new IllegalArgumentException("Violation of precondition: "
                    + "queensAreSafe. The board may not be null, must be square, "
                    + "and may only contain 'q's and '.'s");
        }

        //
        for (int row = 0; row < board.length; row++) {

            //
            for (int col = 0; col < board[row].length; col++) {

                if (board[row][col] == validChars[0]) {

                    boolean checkRight = checkRight(board, row, col, validChars[0]);
                    boolean checkDown = checkDown(board, col, row, validChars[0]);
                    boolean checkDiags = checkDiagnols(board, row, col, validChars[0]);

                    // IF
                    if (checkRight || checkDown || checkDiags) {
                        return false;
                    }
                }

            }
        }

        return true;
    }

    /**
     * Given a 2D array of ints return the value of the most valuable contiguous sub
     * rectangle
     * in the 2D array. The sub rectangle must be at least 1 by 1.
     * <p>
     * pre: <tt>mat != null, mat.length > 0, mat[0].length > 0,
     * mat</tt> is a rectangular matrix.
     * <p>
     * post: return the value of the most valuable contiguous sub rectangle in
     * <tt>city</tt>.<br>
     * 
     * @param city The 2D array of ints representing the value of each block in a
     *             portion of a
     *             city.
     * @return return the value of the most valuable contiguous sub rectangle in
     *         <tt>city</tt>.
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

    // TODO: Put your birthday problem experiment code here:

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

    private static boolean checkRight(char[][] board, int row, int startCol, char queen) {

        for (int col = startCol + 1; col < board[row].length; col++) {
            if (board[row][col] == queen) {
                return true;
            }
        }

        return false;
    }

    private static boolean checkDown(char[][] board, int col, int startRow, char queen) {

        for (int row = startRow + 1; row < board.length; row++) {
            if (board[row][col] == queen) {
                return true;
            }
        }

        return false;
    }

    private static boolean checkDiagnols(char[][] board, int strRow, int strCol, char queen) {

        int row = strRow + 1;
        int col = strCol + 1;

        while (row < board.length && col < board[row].length) {
            if (board[row][col] == queen) {
                return true;
            }

            row++;
            col++;
        }

        row = strRow + 1;
        col = strCol - 1;

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
