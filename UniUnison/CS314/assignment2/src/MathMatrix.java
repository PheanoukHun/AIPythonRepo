/*
 * MathMatrix.java - CS 314 Assignment 2
 *
 * By signing my name below, I affirm that this assignment is my own work. I
 * have neither given nor received unauthorized assistance on this assignment.
 *
 * Name: Pheanouk Hun
 * Email address: ph23434
 * UTEID: ph23434
 * 
 * Description: The Data Structure Represents the Math Matrix and has a list of operations that
 * could be used on normal matrixes
 */

import java.util.Arrays;

/**
 * A class that models a mathematical matrix.
 */
public class MathMatrix {

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

        for (int row = 0; row < getNumRows(); row++) {
            for (int col = 0; col < getNumColumns(); col++) {
                values[row][col] = mat[row][col];
            }
        }

    }

    /**
     * Create a MathMatrix of the specified size with all cells set to the
     * intialValue.
     *
     * @param numRows    numRows > 0
     * @param numCols    numCols > 0
     * @param initialVal all cells of this Matrix are set to initialVal. Create a
     *                   matrix with numRows rows and numCols columns. All elements
     *                   of this matrix equal initialVal. In other words after this
     *                   method has been called getVal(r,c) = initialVal for all
     *                   valid r and c.
     */
    public MathMatrix(int numRows, int numCols, int initialVal) {

        // Checking Preconditions
        if (numRows <= 0 || numCols <= 0) {
            throw new IllegalArgumentException("Violation of precondition: "
                    + "MathMatrix Constructor neither parameter may equal null, arrays"
                    + " lenght and a rectangular matrix.");
        }

        // Initialing the Variables
        values = new int[numRows][numCols];
        this.numRows = numRows;
        this.numCols = numCols;

        // Setting the Values
        for (int row = 0; row < getNumRows(); row++) {
            for (int col = 0; col < getNumColumns(); col++) {
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
        if (row < 0 || row >= getNumRows() || col < 0 || col >= getNumColumns()) {
            throw new IllegalArgumentException("Violation of precondition: "
                    + "Row is Less than 0, or Greater than the Num of Rows"
                    + " Column is Less than 0 or Greater than the Num of Columns");
        }

        // Returning the Values
        return values[row][col];
    }

    /**
     * Implements MathMatrix addition, (this MathMatrix) + rightHandSide.
     *
     * @param rightHandSide rightHandSide != null,
     *                      rightHandSide.getNumRows() = getNumRows(),
     *                      rightHandSide.getNumColumns() = getNumColumns()
     * @return a new MathMatrix that is the result of adding this Matrix to
     *         rightHandSide. The number of rows in the returned Matrix is equal to
     *         the number of rows in this MathMatrix. The number of columns in the
     *         returned Matrix is equal to the number of columns in this MathMatrix.
     *         This method does not alter the calling object or rightHandSide
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
     * @param rightHandSide rightHandSide != null,
     *                      rightHandSide.getNumRows() = getNumRows(),
     *                      rightHandSide.getNumColumns() = getNumColumns()
     * @return a new MathMatrix that is the result of subtracting rightHandSide
     *         from this MathMatrix. The number of rows in the returned MathMatrix
     *         is equal to the number of rows in this MathMatrix.The number of
     *         columns in the returned MathMatrix is equal to the number of columns
     *         in this MathMatrix. This method does not alter the calling object or
     *         rightHandSide
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
     * @param rightHandSide rightHandSide != null,
     *                      rightHandSide.getNumRows() = getNumColumns()
     * @return a new MathMatrix that is the result of multiplying this
     *         MathMatrix and rightHandSide. The number of rows in the returned
     *         MathMatrix is equal to the number of rows in this MathMatrix. The
     *         number of columns in the returned MathMatrix is equal to the number
     *         of columns in rightHandSide.This method should not alter the calling
     *         object or rightHandSide
     */
    public MathMatrix multiply(MathMatrix rightHandSide) {

        // Checking Proconditions
        if (rightHandSide == null || rightHandSide.getNumRows() != getNumColumns()) {
            throw new IllegalArgumentException("Violation of precondition: "
                    + "rightHandSide may not be null or "
                    + " rightHandSide num of rows must equals current object num of columns");
        }

        // Multiplying the two Matrixes Together
        int[][] results = new int[this.getNumRows()][rightHandSide.getNumColumns()];

        for (int i = 0; i < this.getNumRows(); i++) {

            for (int j = 0; j < rightHandSide.getNumColumns(); j++) {

                int sum = 0;

                for (int k = 0; k < this.getNumColumns(); k++) {
                    sum += this.getVal(i, k) * rightHandSide.getVal(k, j);
                }

                results[i][j] = sum;
            }
        }

        // Returing the New Matriz
        return new MathMatrix(results);
    }

    /**
     * Create and return a new Matrix that is a copy of this matrix, but with
     * all values multiplied by a scale value.
     *
     * @param factor the value to multiply every cell in this Matrix by.
     * @return returns a new Matrix with all elements in this matrix multiplied by
     *         factor. In other words after this method has been called
     *         returned_matrix.getVal(r,c) = original_matrix.getVal(r, c) * factor
     *         for all valid r and c.
     */
    public MathMatrix getScaledMatrix(int factor) {

        // Scaling the Values of the Matrix
        int[][] results = new int[getNumRows()][getNumColumns()];
        for (int row = 0; row < getNumRows(); row++) {
            for (int col = 0; col < getNumColumns(); col++) {
                results[row][col] = getVal(row, col) * factor;
            }
        }

        // Returning the New Matrix
        return new MathMatrix(results);
    }

    /**
     * Get a transpose of this MathMatrix, this Matrix is not changed.
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
        return new MathMatrix(results);
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

        // Checks to see if the two matrixes have the same dimensions
        if (otherMathMatrix.getNumRows() != getNumRows() ||
                otherMathMatrix.getNumColumns() != getNumColumns()) {
            return false;
        }

        // Checks to see if they all have the same value
        for (int row = 0; row < getNumRows(); row++) {
            for (int col = 0; col < getNumColumns(); col++) {
                if (otherMathMatrix.getVal(row, col) != this.getVal(row, col)) {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * Override toString.
     *
     * @return a String with all elements of this MathMatrix. Each row is on a
     *         separate line. Spacing based on longest element in this Matrix.
     */
    public String toString() {

        StringBuilder totalString = new StringBuilder();

        // Getting the Length of the Longest Int Per Column
        int numSpaces = 0;
        for (int row = 0; row < getNumRows(); row++) {
            for (int col = 0; col < getNumColumns(); col++) {
                int currLen = ("" + getVal(row, col)).length();
                if (currLen > numSpaces) {
                    numSpaces = currLen;
                }
            }
        }

        numSpaces++;
        String format = "%" + numSpaces + "d";

        // Building the String
        for (int row = 0; row < getNumRows(); row++) {

            totalString.append("|");
            for (int col = 0; col < getNumColumns(); col++) {
                totalString.append(String.format(format, getVal(row, col)));
            }
            totalString.append("|\n");
        }

        // Returned the String of the Matrix
        return totalString.toString();
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

        // Checking the Preconditions
        if (getNumRows() != getNumColumns()) {
            throw new IllegalArgumentException("Violation of precondition: "
                    + "the number of rows must equal to the number of columns");
        }

        // Returns if it is a 1x1 Matrix
        if (getNumRows() == 1) {
            return true;
        }

        // Checking all Items Below the Diagonals
        for (int row = 0; row < getNumRows(); row++) {
            for (int col = 0; col < row; col++) {
                if (getVal(row, col) != 0) {
                    return false;
                }
            }
        }

        return true;
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

    /**
     * This method runs a Experiment 2 of Assignment 1. From Steps 1-5.
     * It checks how the how increasing the dimension of the Matrix increases the
     * runtime of the experiment with respects to the add method.
     */
    public static void experimentOnePartOne() {

        // Step 1 and Step 4
        final int SIZE = 775;
        final int NUM_REPETITION = 1000;

        // Step 1
        MathMatrix mat1 = createRandomMatrices(SIZE);
        MathMatrix mat2 = createRandomMatrices(SIZE);
        MathMatrix mat3;

        // Step 2
        Stopwatch st = new Stopwatch();

        // Step 3
        st.start();
        mat3 = mat1.add(mat2);
        st.stop();

        System.out.println("\nStep 1-6:\nRuntime Once: " + st.time());

        // Step 5
        st.start();

        for (int i = 0; i < NUM_REPETITION; i++) {
            mat3 = mat1.add(mat2);
        }
        st.stop();

        System.out.println("Runtime x1000: " + st.time() + "\n");
    }

    /**
     * This method runs a Experiment 2 of Assignment 1. From Steps 6-8.
     * It checks how the how increasing the dimension of the Matrix increases the
     * runtime of the experiment with respects to the add method.
     */
    public static void experimentOnePartTwo() {

        // Constants and Variables
        final int SIZE = 775;
        final int NUM_REPETITION = 1000;
        Stopwatch st = new Stopwatch();

        // Step 6
        MathMatrix mat1 = createRandomMatrices(SIZE * 2);
        MathMatrix mat2 = createRandomMatrices(SIZE * 2);
        MathMatrix mat3;

        // Step 7

        st.start();
        mat3 = mat1.add(mat2);
        st.stop();

        System.out.println("Step 7:\nRuntime Once: " + st.time() + "\n");

        // Step 8
        System.out.println("Step 8:\nDimensions: " + mat1.getNumRows() + " x " + mat1.getNumColumns());
        st.start();
        for (int i = 0; i < NUM_REPETITION; i++) {
            mat3 = mat1.add(mat2);
        }
        st.stop();

        System.out.println("Runtime x1000: " + st.time() + "\n");

    }

    /**
     * This method runs a Experiment 2 of Assignment 1. For Steps 9.
     * It checks how the how increasing the dimension of the Matrix increases the
     * runtime of the experiment with respects to the add method.
     */
    public static void experimentOnePartThree() {

        // Constants and Variables
        final int SIZE = 775;
        final int NUM_REPETITION = 1000;
        Stopwatch st = new Stopwatch();

        // Step 9
        MathMatrix mat1 = createRandomMatrices(SIZE * 4);
        MathMatrix mat2 = createRandomMatrices(SIZE * 4);
        MathMatrix mat3;

        st.start();
        mat3 = mat1.add(mat2);
        st.stop();

        System.out.println("Step 9:");
        System.out.println("Dimensions: " + mat1.getNumRows() + " x " + mat1.getNumColumns());
        System.out.println("Runtime Once: " + st.time());

        st.start();
        for (int i = 0; i < NUM_REPETITION; i++) {
            mat3 = mat1.add(mat2);
        }
        st.stop();

        System.out.println("Runtime x100: " + st.time() + "\n");
    }

    /**
     * This method runs a Experiment 2 of Assignment 2. From Steps 1-5.
     * It checks how the how increasing the dimension of the Matrix increases the
     * runtime of the experiment with respects to the multiplication method.
     */
    public static void experimentTwoPartOne() {

        // Step 1 and Step 4
        final int SIZE = 260;
        final int NUM_REPETITION = 100;

        // Step 1
        MathMatrix mat1 = createRandomMatrices(SIZE);
        MathMatrix mat2 = createRandomMatrices(SIZE);
        MathMatrix mat3;

        // Step 2
        Stopwatch st = new Stopwatch();

        // Step 3
        st.start();
        mat3 = mat1.multiply(mat2);
        st.stop();

        System.out.println("\nStep 1-6:\nRuntime Once: " + st.time());

        // Step 5
        st.start();
        for (int i = 0; i < NUM_REPETITION; i++) {
            mat3 = mat1.multiply(mat2);
        }
        st.stop();

        System.out.println("Runtime x100: " + st.time() + "\n");
    }

    /**
     * This method runs a Experiment 2 of Assignment 2. From Steps 6-9.
     * It checks how the how increasing the dimension of the Matrix increases the
     * runtime of the experiment with respects to the multiplication method.
     */
    public static void experimentTwoPartTwo() {

        // Constants and Variables
        Stopwatch st = new Stopwatch();
        final int SIZE = 260;
        final int NUM_REPETITION = 100;

        // Step 6
        MathMatrix mat1 = createRandomMatrices(SIZE * 2);
        MathMatrix mat2 = createRandomMatrices(SIZE * 2);
        MathMatrix mat3;

        // Step 7

        st.start();
        mat3 = mat1.multiply(mat2);
        st.stop();

        System.out.println("Step 7:\nRuntime Once: " + st.time() + "\n");

        // Step 8
        System.out.println("Step 8:\nDimensions: " + mat1.getNumRows() + " x " + mat1.getNumColumns());
        st.start();
        for (int i = 0; i < NUM_REPETITION; i++) {
            mat3 = mat1.multiply(mat2);
        }
        st.stop();

        System.out.println("Runtime x100: " + st.time() + "\n");
    }

    /**
     * This method runs a Experiment 2 of Assignment 2. From Steps 6-9.
     * It checks how the how increasing the dimension of the Matrix increases the
     * runtime of the experiment with respects to the multiplication method.
     */
    public static void experimentTwoPartThree() {
        // Constants and Variables
        Stopwatch st = new Stopwatch();
        final int SIZE = 260;
        final int NUM_REPETITION = 100;

        // Step 6
        MathMatrix mat1 = createRandomMatrices(SIZE * 2);
        MathMatrix mat2 = createRandomMatrices(SIZE * 2);
        MathMatrix mat3;

        // Step 9
        mat1 = createRandomMatrices(SIZE * 4);
        mat2 = createRandomMatrices(SIZE * 4);

        st.start();
        mat3 = mat1.multiply(mat2);
        st.stop();

        System.out.println("Step 9:");
        System.out.println("Dimensions: " + mat1.getNumRows() + " x " + mat1.getNumColumns());
        System.out.println("Runtime Once: " + st.time());

        st.start();
        for (int i = 0; i < NUM_REPETITION; i++) {
            mat3 = mat1.multiply(mat2);
        }
        st.stop();

        System.out.println("Runtime x100: " + st.time() + "\n");
    }

    /**
     * Creates a Random MathMatrix Operations to filled with Random Values.
     * 
     * @param size - The Size of the Square Matrix and the size > 0
     * @return - Returns the Random Valued MathMatrix Object with the dimension
     *         size x size.
     */
    private static MathMatrix createRandomMatrices(int size) {

        // Checking Preconditions
        if (size <= 0) {
            throw new IllegalArgumentException("Violation of precondition: "
                    + "size must be greater than 0.");
        }

        // Creating the Object and Returns it
        int[][] arr = new int[size][size];

        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                arr[row][col] = (int) (Math.random() * size);
            }
        }

        return new MathMatrix(arr);
    }
}
