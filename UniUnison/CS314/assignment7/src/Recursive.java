/*
 * Recursive.java - CS 314 Assignment 7
 *
 * By signing my/our name(s) below, I/we affirm that this assignment is my/our
 * own work. I/we have neither given nor received unauthorized assistance on
 * this assignment.
 *
 * Name 1: 
 * Email address 1:
 * UTEID 1:
 *
 * Name 2:
 * Email address 2:
 * UTEID 2:
 */

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Various recursive methods to be implemented.
 */
public class Recursive {

    /**
     * Problem 1: Returns the number of elements in data that are followed
     * directly by value that is double that element.
     * pre: data != null
     * post: return the number of elements in data that are followed
     * immediately by double the value
     *
     * @param data The array to search.
     * @return The number of elements in data that are followed immediately by
     *         a value that is double the element.
     */
    public static int nextIsDouble(int[] data) {

        // Precondition
        if (data == null) {
            throw new IllegalArgumentException("Failed precondition: "
                    + "revString. parameter may not be null.");
        }

        if (data.length < 2) {
            return 0;
        }

        return twoIntsAreDouble(0, 1, data);
    }

    /**
     * 
     * @param start
     * @param end
     * @param data
     * @return
     */
    private static int twoIntsAreDouble(int start, int end, int[] data) {

        int doubled = (data[start] * 2 == data[end]) ? 1 : 0;

        if (end == data.length - 1) {
            return doubled;
        }

        return doubled + twoIntsAreDouble(start + 1, end + 1, data);
    }

    /**
     * Problem 2: Find all combinations of mnemonics for the given number.
     * pre: number != null, number.length() > 0, all characters in number are
     * digits
     *
     * @param number The number to find mnemonics for
     * @return The list of all possible mnemonics for the given number
     */
    public static ArrayList<String> listMnemonics(String number) {
        if (number == null || number.length() == 0 || !allDigits(number)) {
            throw new IllegalArgumentException("Failed precondition: "
                    + "listMnemonics");
        }

        ArrayList<String> results = new ArrayList<>();
        findWordMnemonic(number, "", results);
        return results;
    }

    /**
     * 
     * @param number
     * @param currWord
     * @param words
     */
    private static void findWordMnemonic(String number, String currWord,
            ArrayList<String> words) {
        if (number.length() == 0) {
            words.add(currWord);
        } else {
            String letters = digitLetters(number.charAt(0));
            for (int i = 0; i < letters.length(); i++) {
                findWordMnemonic(number.substring(1), currWord + letters.charAt(i), words);
            }
        }
    }

    /*
     * Static code blocks are run once when this class is loaded
     * Create an unmodifiable list to use with the phone mnemonics method,
     * Used by method digitLetters
     */
    private static final List<String> LETTERS_FOR_NUMBER;
    static {
        String[] letters = { "0", "1", "ABC",
                "DEF", "GHI", "JKL", "MNO", "PQRS", "TUV", "WXYZ" };
        ArrayList<String> lettersAsList = new ArrayList<>();
        for (String s : letters) {
            lettersAsList.add(s);
        }
        LETTERS_FOR_NUMBER = Collections.unmodifiableList(lettersAsList);
    }

    /**
     * Helper method for Problem 2: Phone Mnemonics
     * pre: ch is a digit '0' through '9'
     * post: return the characters associated with this digit on a phone keypad
     */
    private static String digitLetters(char ch) {
        if (ch < '0' || ch > '9') {
            throw new IllegalArgumentException("parameter "
                    + "ch must be a digit, 0 to 9. Given value = " + ch);
        }
        int index = ch - '0';
        return LETTERS_FOR_NUMBER.get(index);
    }

    /**
     * Helper method for Problem 2: Phone Mnemonics
     * pre: s != null
     * post: return true if every character in s is a digit ('0' through '9')
     *
     * CS314 students, you should not have to call this method.
     */
    private static boolean allDigits(String s) {
        if (s == null) {
            throw new IllegalArgumentException("Failed precondition: "
                    + "allDigits. String s cannot be null.");
        }
        boolean allDigits = true;
        int i = 0;
        while (i < s.length() && allDigits) {
            allDigits = s.charAt(i) >= '0' && s.charAt(i) <= '9';
            i++;
        }
        return allDigits;
    }

    /**
     * Problem 3: Draw a Sierpinski Carpet.
     *
     * @param size  the size in pixels of the window
     * @param limit the smallest size of a square in the carpet.
     */
    public static void drawCarpet(int size, int limit) {
        DrawingPanel p = new DrawingPanel(size, size);
        Graphics g = p.getGraphics();
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, size, size);
        g.setColor(Color.WHITE);
        drawSquares(g, size, limit, 0, 0);
    }

    /**
     * Helper method for Problem 3: Sierpinski Carpet
     * Draw the individual squares of the carpet.
     * 
     * @param g The Graphics object to use to fill rectangles
     * @param size the size of the current square
     * @param limit the smallest allowable size of squares
     * @param x the x coordinate of the upper left corner of the current square
     * @param y the y coordinate of the upper left corner of the current square
     */
    private static void drawSquares(Graphics g, int size, int limit, double x, double y) {

        // Recursive Case
        if (limit <= size) {

            final int NUM_SQUARES = 3;
            int newSize = size / NUM_SQUARES;

            // Draw Center Square
            g.fillRect((int) (x + newSize), (int) (y + newSize), newSize, newSize);

            // Recursively Create More of the Same Pattern
            for (int row = 0; row < NUM_SQUARES; row++) {
                for (int col = 0; col < NUM_SQUARES; col++) {
                    if (!(row == 1 && col == 1)) {
                        int newX = (int) (x + (newSize * row));
                        int newY = (int) (y + (newSize * col));
                        drawSquares(g, newSize, limit, newX, newY);
                    }
                }

            }
        }
    }

    /**
     * Problem 4: Determine if water at a given point can flow off the map
     * pre: map != null, map.length > 0, map is a rectangular matrix,
     * 0 <= row < map.length, 0 <= col < map[0].length
     * post: return true if a drop of water starting at the location specified
     * by row, column can reach the edge of the map, false otherwise
     *
     * @param map The elevations of a section of a map.
     * @param row The starting row of a drop of water.
     * @param col The starting column of a drop of water.
     * @return true if a drop of water starting at the location specified by
     *         row, column can reach the edge of the map, false otherwise
     */
    public static boolean canFlowOffMap(int[][] map, int row, int col) {

        // Precondition
        if (map == null || map.length == 0 || !isRectangular(map) || !inbounds(row, col, map)) {
            throw new IllegalArgumentException("Failed precondition: " + "canFlowOffMap");
        }

        int[][] directions = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

        for (int[] direction : directions) {

            int newRow = row + direction[0];
            int newCol = col + direction[1];

            // Base Case
            if (newRow < 0 || newRow >= map.length || newCol < 0 || newCol >= map[0].length) {
                return true;
            }

            // Recursive Case Across all 4 Directions
            if (map[newRow][newCol] < map[row][col]) {
                if (canFlowOffMap(map, newRow, newCol)) {
                    return true;
                }
            }
        }

        return false;
    }

    /*
     * Helper method for Problem 4: Flowing Water
     * pre: mat != null,
     *
     * CS314 students, you should not have to call this method.
     */
    private static boolean inbounds(int r, int c, int[][] mat) {
        if (mat == null) {
            throw new IllegalArgumentException("Failed precondition: "
                    + "inbounds. The 2d array mat may not be null.");
        }
        return r >= 0 && r < mat.length && mat[r] != null
                && c >= 0 && c < mat[r].length;
    }

    /*
     * Helper method for Problem 4: Flowing Water
     * pre: mat != null, mat.length > 0
     * post: return true if mat is rectangular
     *
     * CS314 students, you should not have to call this method.
     */
    private static boolean isRectangular(int[][] mat) {
        if (mat == null || mat.length == 0) {
            throw new IllegalArgumentException("Failed precondition: "
                    + "inbounds. The 2d array mat may not be null "
                    + "and must have at least 1 row.");
        }
        boolean correct = true;
        final int numCols = mat[0].length;
        int row = 0;
        while (correct && row < mat.length) {
            correct = (mat[row] != null) && (mat[row].length == numCols);
            row++;
        }
        return correct;
    }

    /**
     * Problem 5: Find the minimum difference possible between teams based on
     * ability scores. The number of teams may be greater than 2. The goal is
     * to minimize the difference between the team with the maximum total
     * ability and the team with the minimum total ability.
     * pre: numTeams >= 2, abilities != null, abilities.length >= numTeams
     * post: return the minimum possible difference between the team with the
     * maximum total ability and the team with the minimum total ability.
     *
     * @param numTeams  the number of teams to form
     * @param abilities the ability scores of the people to distribute
     * @return return the minimum possible difference between the team with the
     *         maximum total ability and the team with the minimum total ability.
     *         The
     *         return value will be greater than or equal to 0.
     */
    public static int minDifference(int numTeams, int[] abilities) {
        int[] teams = new int[numTeams];
        int[] teamsAdded = new int[numTeams];
        return minDiffHelper(0, abilities, teams, teamsAdded);
    }

    private static int minDiffHelper(int index, int[] skillLvls, int[] teams, int[] teamsAdded) {

        // Base Case
        if (index == skillLvls.length) {

            int min = teams[0];
            int max = teams[0];

            for (int i = 0; i < teams.length; i++) {
                
                if (teamsAdded[i] == 0) {
                    return Integer.MAX_VALUE;
                }

                min = Math.min(teams[i], min);
                max = Math.max(teams[i], max);
            }

            for (int sum : teams) {

                // Invalid Sum
                if (sum == 0) {
                    return Integer.MAX_VALUE;
                }

                min = Math.min(sum, min);
                max = Math.max(sum, max);
            }

            return max - min;
        }

        // Recursive Case
        int best = Integer.MAX_VALUE;

        for (int i = 0; i < teams.length; i++) {

            // Selecting
            teams[i] += skillLvls[index];
            teamsAdded[i]++;
            int result = minDiffHelper(index + 1, skillLvls, teams, teamsAdded);

            // Exploring
            if (result != Integer.MAX_VALUE) {
                best = Math.min(best, result);
            }

            // Deselecting
            teams[i] -= skillLvls[index];
            teamsAdded[i]--;
        }

        return best;
    }
}