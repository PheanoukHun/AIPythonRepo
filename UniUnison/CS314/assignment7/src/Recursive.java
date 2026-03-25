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
     * a value that is double the element.
     */
    public static int nextIsDouble(int[] data) {
        if (data == null) {
            throw new IllegalArgumentException("Failed precondition: "
                    + "revString. parameter may not be null.");
        }
        return 0; // TODO: Change as necessary
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
        if (number == null ||  number.length() == 0 || !allDigits(number)) {
            throw new IllegalArgumentException("Failed precondition: "
                    + "listMnemonics");
        }

        return null; // TODO: Change as necessary
    }

    /*
     * Static code blocks are run once when this class is loaded
     * Create an unmodifiable list to use with the phone mnemonics method,
     * Used by method digitLetters
     */
    private static final List<String> LETTERS_FOR_NUMBER;
    static {
        String[] letters = {"0", "1", "ABC",
                "DEF", "GHI", "JKL", "MNO", "PQRS", "TUV", "WXYZ"};
        ArrayList<String> lettersAsList = new ArrayList<>();
        for (String s : letters) {
            lettersAsList.add(s);
        }
        LETTERS_FOR_NUMBER = Collections.unmodifiableList(lettersAsList);
    }

    /*
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


    /*
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
     * @param size the size in pixels of the window
     * @param limit the smallest size of a square in the carpet.
     */
    public static void drawCarpet(int size, int limit) {
        DrawingPanel p = new DrawingPanel(size, size);
        Graphics g = p.getGraphics();
        g.setColor(Color.BLACK);
        g.fillRect(0,0,size,size);
        g.setColor(Color.WHITE);
        drawSquares(g, size, limit, 0, 0);
    }

    /*
     * Helper method for Problem 3: Sierpinski Carpet
     * Draw the individual squares of the carpet.
     *
     * @param g The Graphics object to use to fill rectangles
     * @param size the size of the current square
     * @param limit the smallest allowable size of squares
     * @param x the x coordinate of the upper left corner of the current square
     * @param y the y coordinate of the upper left corner of the current square
     */
    private static void drawSquares(Graphics g, int size, int limit,
                                    double x, double y) {
        // TODO: Complete this method
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
     * row, column can reach the edge of the map, false otherwise
     */
    public static boolean canFlowOffMap(int[][] map, int row, int col) {
        if (map == null || map.length == 0 || !isRectangular(map)
                || !inbounds(row, col, map)) {
            throw new IllegalArgumentException("Failed precondition: "
                    + "canFlowOffMap");
        }
        return true; // TODO: Change as necessary
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
     * @param numTeams the number of teams to form
     * @param abilities the ability scores of the people to distribute
     * @return return the minimum possible difference between the team with the
     * maximum total ability and the team with the minimum total ability. The
     * return value will be greater than or equal to 0.
     */
    public static int minDifference(int numTeams, int[] abilities) {
        return -1; // TODO: Change as necessary
    }
}