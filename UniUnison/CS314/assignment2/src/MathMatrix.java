/*
 * MathMatrix.java - CS 314 Assignment 2
 *
 * By signing my name below, I affirm that this assignment is my own work. I
 * have neither given nor received unauthorized assistance on this assignment.
 *
 * Name: Pheanouk Hun
 * Email address: ph23434
 * UTEID: ph23434
 */

import java.util.Arrays;

/**
 * A class that models a mathematical matrix.
 */
public class MathMatrix {

    // TODO: INCLUDE INSTANCE VARIABLES HERE AND DELETE COMMENT

    private int numRows, numCols;
    private int[][] values;

    /**
     * Create a MathMatrix with cells equal to the values in mat. A "deep" copy
     * of mat is made. Changes to mat after this constructor do not affect this
     * Matrix and changes to this MathMatrix do not affect mat
     *
     * @param mat mat !=null, mat.length > 0, mat[0].length > 0, mat is a
     *            rectangular matrix
     */
    public MathMatrix(int[][] mat) {

        // Checking Preconditions
        if (mat == null || mat.length == 0 || mat[0].length == 0 || !rectangularMatrix(mat)) {
            throw new IllegalArgumentException("Violation of precondition: "
                    + "MathMatrix Constructor neither parameter may equal null, arrays"
                    + " lenght and a rectangular matrix.");
        }

        values = new int[mat.length][mat[0].length];
        numRows = mat.length;
        numCols = mat[0].length;

        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numRows; col++) {
                values[row][col] = mat[row][col];
            }
        }

    }

    /**
     * Create a MathMatrix of the specified size with all cells set to the
     * intialValue.
     *
     * pre: numRows > 0, numCols > 0
     *
     * post: create a matrix with numRows rows and numCols columns. All elements
     * of this matrix equal initialVal. In other words after this method has
     * been called getVal(r,c) = initialVal for all valid r and c.
     *
     * @param numRows    numRows > 0
     * @param numCols    numCols > 0
     * @param initialVal all cells of this Matrix are set to initialVal
     */
    public MathMatrix(int numRows, int numCols, int initialVal) {

        // Checking Preconditions
        if (numRows <= 0 || numCols <= 0) {
            throw new IllegalArgumentException("Violation of precondition: "
                    + "MathMatrix Constructor neither parameter may equal null, arrays"
                    + " lenght and a rectangular matrix.");
        }

        values = new int[numRows][numCols];
        this.numRows = numRows;
        this.numCols = numCols;

        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                values[row][col] = initialVal;
            }
        }
    }

    /**
     * Get the number of rows.
     *
     * @return the number of rows in this MathMatrix
     */
    public int getNumRows() {
        return numRows;
    }

    /**
     * Get the number of columns.
     *
     * @return the number of columns in this MathMatrix
     */
    public int getNumColumns() {
        return numCols;
    }

    /**
     * Gets the Value from the matrix at the position given.
     *
     * @param row - 0 <= row < getNumRows()
     * @param col - 0 <= col < getNumColumns()
     * @return - the value at the specified position
     */
    public int getVal(int row, int col) {

        // Checking Preconditions
        if (row < 0 || col < 0 || col >= getNumColumns() || row >= getNumRows()) {
            throw new IllegalArgumentException("Violation of precondition: "
                    + "Row is Less than 0, or Greater than the Num of Rows"
                    + " Column is Less than 0 or Greater than the Num of Columns");
        }

        return values[row][col];
    }

    /**
     * Implements MathMatrix addition, (this MathMatrix) + rightHandSide.
     *
     * pre: rightHandSide != null, rightHandSide.getNumRows() = getNumRows(),
     * rightHandSide.getNumColumns() = getNumColumns()
     *
     * post: This method does not alter the calling object or rightHandSide
     *
     * @param rightHandSide rightHandSide.getNumRows() = getNumRows(),
     *                      rightHandSide.getNumColumns() = getNumColumns()
     * @return a new MathMatrix that is the result of adding this Matrix to
     *         rightHandSide. The number of rows in the returned Matrix is equal to
     *         the number of rows in this MathMatrix. The number of columns in the
     *         returned Matrix is equal to the number of columns in this MathMatrix.
     */
    public MathMatrix add(MathMatrix rightHandSide) {

        // Checking Proconditions
        if (rightHandSide == null || rightHandSide.getNumRows() != getNumRows() ||
                rightHandSide.getNumColumns() != getNumColumns()) {
            throw new IllegalArgumentException("Violation of precondition: "
                    + "rightHandSide may not be null or "
                    + " rightHandSide num of rows must equals current object num of rows"
                    + " rightHandSide num of columns must equals current object num of columns");
        }

        // Adding the Values together.
        int[][] results = new int[getNumRows()][getNumColumns()];

        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                results[row][col] = this.getVal(row, col) + rightHandSide.getVal(row, col);
            }
        }

        // Returning the New Matrix
        return (new MathMatrix(results));
    }

    /**
     * Implements MathMatrix subtraction, (this MathMatrix) - rightHandSide.
     *
     * pre: rightHandSide != null, rightHandSide.getNumRows() = getNumRows(),
     * rightHandSide.getNumColumns() = getNumColumns()
     *
     * post: This method does not alter the calling object or rightHandSide
     *
     * @param rightHandSide rightHandSide.getNumRows() = getNumRows(),
     *                      rightHandSide.getNumColumns() = getNumColumns()
     * @return a new MathMatrix that is the result of subtracting rightHandSide
     *         from this MathMatrix. The number of rows in the returned MathMatrix
     *         is equal to the number of rows in this MathMatrix.The number of
     *         columns in the returned MathMatrix is equal to the number of columns
     *         in this MathMatrix.
     */
    public MathMatrix subtract(MathMatrix rightHandSide) {

        // Checking Proconditions
        if (rightHandSide == null || rightHandSide.getNumRows() != getNumRows() ||
                rightHandSide.getNumColumns() != getNumColumns()) {
            throw new IllegalArgumentException("Violation of precondition: "
                    + "rightHandSide may not be null or "
                    + " rightHandSide num of rows must equals current object num of rows"
                    + " rightHandSide num of columns must equals current object num of columns");
        }

        // Subtracting the Values From The Object
        int[][] results = new int[getNumRows()][getNumColumns()];

        for (int row = 0; row < getNumRows(); row++) {
            for (int col = 0; col < getNumColumns(); col++) {
                results[row][col] = this.getVal(row, col) - rightHandSide.getVal(row, col);
            }
        }

        // Returning the New Matrix
        return (new MathMatrix(results));
    }

    /**
     * Implements matrix multiplication, (this MathMatrix) * rightHandSide.
     *
     * pre: rightHandSide != null, rightHandSide.getNumRows() = getNumColumns()
     *
     * post: This method should not alter the calling object or rightHandSide
     *
     * @param rightHandSide rightHandSide.getNumRows() = getNumColumns()
     * @return a new MathMatrix that is the result of multiplying this
     *         MathMatrix and rightHandSide. The number of rows in the returned
     *         MathMatrix is equal to the number of rows in this MathMatrix. The
     *         number of columns in the returned MathMatrix is equal to the number
     *         of columns in rightHandSide.
     */
    public MathMatrix multiply(MathMatrix rightHandSide) {

        // Checking Proconditions
        if (rightHandSide == null || rightHandSide.getNumRows() != getNumColumns()) {
            throw new IllegalArgumentException("Violation of precondition: "
                    + "rightHandSide may not be null or "
                    + " rightHandSide num of rows must equals current object num of columns");
        }

        // TODO: Implement the Multiply Method

        return null;
    }

    /**
     * Create and return a new Matrix that is a copy of this matrix, but with
     * all values multiplied by a scale value.
     *
     * pre: none
     *
     * post: returns a new Matrix with all elements in this matrix multiplied by
     * factor. In other words after this method has been called
     * returned_matrix.getVal(r,c) = original_matrix.getVal(r, c) * factor for
     * all valid r and c.
     *
     * @param factor the value to multiply every cell in this Matrix by.
     * @return a MathMatrix that is a copy of this MathMatrix, but with all
     *         values in the result multiplied by factor.
     */
    public MathMatrix getScaledMatrix(int factor) {

        // Scaling the Values of the Matrix
        int[][] results = new int[getNumRows()][getNumColumns()];
        for (int row = 0; row < getNumRows(); row++) {
            for (int col = 0; col < getNumColumns(); col++) {
                results[row][col] = this.getVal(row, col) * factor;
            }
        }

        // Returning the New Matrix
        return (new MathMatrix(results));
    }

    /**
     * Get a transpose of this MathMatrix, this Matrix is not changed.
     *
     * pre: none
     *
     * @return a transpose of this MathMatrix
     */
    public MathMatrix getTranspose() {

        // Transposing the Values of the Matrix
        int[][] results = new int[getNumColumns()][getNumRows()];
        for (int row = 0; row < getNumRows(); row++) {
            for (int col = 0; col < getNumColumns(); col++) {
                results[col][row] = this.getVal(row, col);
            }
        }

        // Returning the New Matrix
        return (new MathMatrix(results));
    }

    /**
     * Override equals.
     *
     * @return true if rightHandSide is the same size as this MathMatrix and all
     *         values in the two MathMatrix objects are the same, false otherwise
     */
    public boolean equals(Object rightHandSide) {
        /*
         * CS 314 Students: The following is standard equals method code.
         * We will learn about in the coming weeks.
         *
         * We use getClass instead of instanceof because we only want a
         * MathMatrix to equal another MathMatrix as opposed to any possible
         * sub classes. We would use instance of if we were implementing am
         * interface and wanted to equal other objects that are instances of
         * that interface but not necessarily MathMatrix objects.
         */

        if (rightHandSide == null || this.getClass() != rightHandSide.getClass()) {
            return false;
        }
        // Now, we know rightHandSide refers to a non-null MathMatrix object,
        // so safe to cast.
        MathMatrix otherMathMatrix = (MathMatrix) rightHandSide;
        // Now, we can access the private instance variables of otherMathMatrix
        // and/or call MathMatrix methods on otherMathMatrix.

        return true; // TODO: ALTER AS NEEDED AND DELETE THIS COMMENT
    }

    /**
     * Override toString.
     *
     * @return a String with all elements of this MathMatrix. Each row is on a
     *         separate line. Spacing based on longest element in this Matrix.
     */
    public String toString() {
        return "";
    }

    /**
     * Return true if this MathMatrix is upper triangular. To be upper
     * triangular all elements below the main diagonal must be 0.
     *
     * pre: this is a square matrix. getNumRows() == getNumColumns()
     *
     * @return true if this MathMatrix is upper triangular, false otherwise.
     */
    public boolean isUpperTriangular() {
        return false;
    }

    /**
     * Ensure mat is a rectangular matrix. Method is public so client classes
     * can also use it.
     *
     * @param mat mat != null, mat must have at least one row, there must be at
     *            least one column in the first row, and all rows in mat must have
     *            the same
     *            number of columns.
     * @return true if mat is rectangular, false otherwise.
     */
    public static boolean rectangularMatrix(int[][] mat) {
        if (mat == null || mat.length == 0) {
            throw new IllegalArgumentException("argument mat may not be null and must "
                    + " have at least one row. mat = " + Arrays.toString(mat));
        } else if (mat[0] == null) {
            throw new IllegalArgumentException("argument may not have null rows. "
                    + "mat = " + Arrays.toString(mat));
        } else if (mat[0].length == 0) {
            throw new IllegalArgumentException("argument mat must have at least "
                    + "one column per row.");
        }
        boolean isRectangular = true;
        int row = 1;
        final int COLUMNS = mat[0].length;
        while (isRectangular && row < mat.length) {
            // Odd to put this here. Maybe do a two pass approach?
            if (mat[row] == null) {
                throw new IllegalArgumentException("argument may not have null rows. "
                        + "mat = " + Arrays.toString(mat));
            }
            isRectangular = (mat[row].length == COLUMNS);
            row++;
        }
        return isRectangular;
    }

}
