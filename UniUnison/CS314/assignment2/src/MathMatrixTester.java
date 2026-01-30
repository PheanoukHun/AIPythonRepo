/*
 * MathMatrixTester.java - CS 314 Assignment 2
 *
 * By signing my name below, I affirm that this assignment is my own work. I
 * have neither given nor received unauthorized assistance on this assignment.
 *
 * Name: Pheanouk Hun
 * Email address: ph23434@my.utexas.edu
 * UTEID: ph23434
 */

/*
 * Experiment One
 * 
 * Step 1-6:
 * Runtime Once: 0.006188677
 * Runtime x1000: 0.948250579
 * 
 * Step 7:
 * Runtime Once: 0.005700116
 * 
 * Step 8:
 * Dimensions: 1550 x 1550
 * Runtime x1000: 3.305183212
 * 
 * Step 9:
 * Dimensions: 3100 x 3100
 * Runtime Once: 0.011282314
 * Runtime x100: 13.763499999
 * 
 * Experiment Two:
 * 
 * Step 1-6:
 * Runtime Once: 0.029200956
 * Runtime x100: 1.057259393
 * 
 * Step 7:
 * Runtime Once: 0.144385615
 * 
 * Step 8:
 * Dimensions: 520 x 520
 * Runtime x100: 14.085506996
 * 
 * Step 9:
 * Dimensions: 1040 x 1040
 * Runtime Once: 1.144298406
 * Runtime x100: 113.832409113
*/

/*
 * Question 1: I think that it would take 56 Seconds to run the add 1000 times
 * after doubling the side lengths of the matrix.
 * 
 * Question 2: Based on the Analysis of my Code, the Add Operation has a Big O Notation of O(N^2).
 * 
 * Question 3: I think that it would take 904 Seconds to Run the Multiply Operation 100 more times 
 * after doubling the side lengths of the matrix.
 * 
 * Question 4: Based on the Analysis of my Code, the Multiply Operation has a Big O Notation of 
 * O(N^3)
 * 
 * Question 5: The size of the matrix has to be around 28,000 by 28,000
 * 
 * Question 6: Based on the previous answer, the max heap size on my systen is 3.14 GB
 */

/**
 * A class to run tests on the MathMatrix class
 */
public class MathMatrixTester {

    /**
     * main method that runs simple test on the MathMatrix class
     *
     * @param args not used
     */
    public static void main(String[] args) {

        int size = 1;

        System.out.println("\nMy Testcases:");

        // Testcase 1 For GetNumRows
        // Really Small MathMatrix
        MathMatrix mat = new MathMatrix(size, size, 1);
        System.out.println("\nGetNumberRows Test 1:");

        int results = mat.getNumRows();
        int answer = size;
        if (results == answer) {
            System.out.println("\tTest 1 (GetNumberRows) Has Passed.");
        } else {
            System.out.println("*************** TEST FAILED ***************");
        }

        // Testcase 2 For GetNumRows
        // Really Large MathMatrix

        size = 10000000;

        mat = new MathMatrix(10000000, 1, 1);
        System.out.println("\nGetNumberRows Test 2:");

        results = mat.getNumRows();
        answer = size;
        if (results == answer) {
            System.out.println("\tTest 2 (GetNumberRows) Has Passed.");
        } else {
            System.out.println("*************** TEST FAILED ***************");
        }

        // Testcase 1 For GetNumColumns
        // Really Small MathMatrix

        size = 1;

        mat = new MathMatrix(size, size, 1);
        System.out.println("\nGetNumberColumns Test 1:");

        results = mat.getNumColumns();
        answer = size;
        if (results == answer) {
            System.out.println("\tTest 1 (GetNumColumns) Has Passed.");
        } else {
            System.out.println("*************** TEST FAILED ***************");
        }

        // Testcase 2 For GetNumRows
        // Really Large MathMatrix

        size = 10000000;

        mat = new MathMatrix(1, size, 1);
        System.out.println("\nGetNumColumns Test 2:");

        results = mat.getNumColumns();
        answer = size;
        if (results == answer) {
            System.out.println("\tTest 2 (GetNumColumns) Has Passed.");
        } else {
            System.out.println("*************** TEST FAILED ***************");
        }

        // Creating Random MathMatrix for General Use
        size = 1000;
        int[][] randArr = new int[size][size];
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                randArr[row][col] = (int) (Math.random() * size);
            }
        }
        MathMatrix randMat = new MathMatrix(randArr);

        // Testcase 1 For GetVal
        // Value at (0, 0)

        System.out.println("\nGetVal Test 1:");

        results = randMat.getVal(0, 0);
        answer = randArr[0][0];

        if (results == answer) {
            System.out.println("\tTest 1 (GetVal) Has Passed.");
        } else {
            System.out.println("*************** TEST FAILED ***************");
        }

        // Testcase 2 For GetVal
        // Value at (size - 1, size - 1)

        System.out.println("\nGetVal Test 2:");

        results = randMat.getVal(size - 1, size - 1);
        answer = randArr[size - 1][size - 1];

        if (results == answer) {
            System.out.println("\tTest 2 (GetVal) Has Passed.");
        } else {
            System.out.println("*************** TEST FAILED ***************");
        }

        // Testcase 1 For Add
        // One Matrix Value's are all large numbers.

        int[][] a = {
                { 1_000_000_000, 1_000_000_000 },
                { 1_000_000_000, 1_000_000_000 }
        };

        int[][] b = {
                { 1_000_000_000, 1_000_000_000 },
                { 1_000_000_000, 1_000_000_000 }
        };

        int[][] expected = {
                { 2_000_000_000, 2_000_000_000 },
                { 2_000_000_000, 2_000_000_000 }
        };

        MathMatrix m1 = new MathMatrix(a);
        MathMatrix m2 = new MathMatrix(b);
        MathMatrix expectedMathMatrix = new MathMatrix(expected);
        MathMatrix resultMat = m1.add(m2);

        System.out.println("\nAdd Test 1:");
        if (resultMat.equals(expectedMathMatrix)) {
            System.out.println("\tTest 1 (Add) Has Passed.");
        } else {
            System.out.println("*************** TEST FAILED ***************");
        }

        // Testcase 2 For Add
        // One Matrix Value's are all 0s.

        a = new int[][] { { 0, 0 }, { 0, 0 } };

        b = new int[][] {
                { 1_000_000_000, 1_000_000_000 },
                { 1_000_000_000, 1_000_000_000 }
        };

        m1 = new MathMatrix(a);
        m2 = new MathMatrix(b);
        resultMat = m1.add(m2);

        System.out.println("\nAdd Test 2:");
        if (resultMat.equals(m2)) {
            System.out.println("\tTest 2 (Add) Has Passed.");
        } else {
            System.out.println("*************** TEST FAILED ***************");
        }

        // Testcase 1 For Subtract
        // One Matrix Value's are all 0s.

        a = new int[][] {
                { 1_000_000_000, 1_000_000_000 },
                { 1_000_000_000, 1_000_000_000 }
        };

        b = new int[][] { { 0, 0 }, { 0, 0 } };

        m1 = new MathMatrix(a);
        m2 = new MathMatrix(b);
        resultMat = m1.subtract(m2);

        System.out.println("\nSubtract Test 1:");
        if (resultMat.equals(m1)) {
            System.out.println("\tTest 1 (Subtract) Has Passed.");
        } else {
            System.out.println("*************** TEST FAILED ***************");
        }

        // Testcase 2 For Subtract
        // One Matrix Value's are all 0s.

        a = new int[][] {
                { 1_000_000_000, 1_000_000_000 },
                { 1_000_000_000, 1_000_000_000 }
        };

        b = new int[][] {
                { 2_000_000_000, 2_000_000_000 },
                { 2_000_000_000, -1_000_000_000 }
        };

        expected = new int[][] {
                { -1_000_000_000, -1_000_000_000 },
                { -1_000_000_000, 2_000_000_000 }
        };

        m1 = new MathMatrix(a);
        m2 = new MathMatrix(b);
        resultMat = m1.subtract(m2);
        expectedMathMatrix = new MathMatrix(expected);

        System.out.println("\nSubtract Test 2:");
        if (resultMat.equals(expectedMathMatrix)) {
            System.out.println("\tTest 2 (Subtract) Has Passed.");
        } else {
            System.out.println("*************** TEST FAILED ***************");
        }

        // Testcase 1 For Multiply
        a = new int[][] {
            {1, 2, 3, 4}
        }

        b = new int[][] {
            {1}, {2}, {3}, {4}
        };

        expected = new int[][] {{30}};

        m1 = new MathMatrix(a);
        m2 = new MathMatrix(b);
        resultMat 
    }
}
