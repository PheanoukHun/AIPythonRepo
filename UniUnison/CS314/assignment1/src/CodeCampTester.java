/*
 * CodeCampTester.java - CS314 Assignment 1
 *
 * By signing my name below, I affirm that this assignment is my own work. I
 * have neither given nor received unauthorized assistance on this assignment.
 *
 *  Name: Pheanouk Hun
 *  Email address: ph23434@my.utexas.edu
 *  UTEID: ph23434
 * 
 * Description: This programs run test cases for the CodeCamp.java program.
 */

import java.util.Random;

/*
 * Problem 4, Part 2, Scenario 1:
 * 50 People Shares a Birthday.
 * 
 * Problem 4, Part 2, Scenario 2:
 * 45 People Shares a Birthday.
 * 
 * Problem 4, Part 2, Scenario 3: 
 * I think that we would need to get around 20 people to get at least 50 percent
 * chance of people having a shared birthday in a 365 day year.
 * /

/* 
 * Problem 4, Part 3, Scenario 4:
 * | Num people | Num of experiments with at least one shared pair| Percentage |
 * ------------------------------------------------------------------------------
 * | 2            | 138                                           |      0.28 |
 * | 3            | 379                                           |      0.76 |
 * | 4            | 843                                           |      1.69 |
 * | 5            | 1407                                          |      2.81 |
 * | 6            | 2015                                          |      4.03 |
 * | 7            | 2776                                          |      5.55 |
 * | 8            | 3733                                          |      7.47 |
 * | 9            | 4701                                          |      9.40 |
 * | 10           | 5760                                          |     11.52 |
 * | 11           | 7047                                          |     14.09 |
 * | 12           | 8384                                          |     16.77 |
 * | 13           | 9916                                          |     19.83 |
 * | 14           | 10985                                         |     21.97 |
 * | 15           | 12708                                         |     25.42 |
 * | 16           | 14356                                         |     28.71 |
 * | 17           | 15695                                         |     31.39 |
 * | 18           | 17286                                         |     34.57 |
 * | 19           | 18959                                         |     37.92 |
 * | 20           | 20398                                         |     40.80 |
 * | 21           | 22120                                         |     44.24 |
 * | 22           | 23875                                         |     47.75 |
 * | 23           | 25461                                         |     50.92 |
 * | 24           | 26948                                         |     53.90 |
 * | 25           | 28424                                         |     56.85 |
 * | 26           | 29962                                         |     59.92 |
 * | 27           | 31339                                         |     62.68 |
 * | 28           | 32915                                         |     65.83 |
 * | 29           | 33897                                         |     67.79 |
 * | 30           | 35454                                         |     70.91 |
 * | 31           | 36669                                         |     73.34 |
 * | 32           | 37666                                         |     75.33 |
 * | 33           | 38772                                         |     77.54 |
 * | 34           | 39774                                         |     79.55 |
 * | 35           | 40588                                         |     81.18 |
 * | 36           | 41652                                         |     83.30 |
 * | 37           | 42483                                         |     84.97 |
 * | 38           | 43119                                         |     86.24 |
 * | 39           | 43913                                         |     87.83 |
 * | 40           | 44491                                         |     88.98 |
 * | 41           | 45109                                         |     90.22 |
 * | 42           | 45725                                         |     91.45 |
 * | 43           | 46125                                         |     92.25 |
 * | 44           | 46674                                         |     93.35 |
 * | 45           | 47103                                         |     94.21 |
 * | 46           | 47368                                         |     94.74 |
 * | 47           | 47751                                         |     95.50 |
 * | 48           | 47963                                         |     95.93 |
 * | 49           | 48190                                         |     96.38 |
 * | 50           | 48454                                         |     96.91 |
 * | 51           | 48784                                         |     97.57 |
 * | 52           | 48874                                         |     97.75 |
 * | 53           | 49098                                         |     98.20 |
 * | 54           | 49149                                         |     98.30 |
 * | 55           | 49351                                         |     98.70 |
 * | 56           | 49400                                         |     98.80 |
 * | 57           | 49475                                         |     98.95 |
 * | 58           | 49587                                         |     99.17 |
 * | 59           | 49666                                         |     99.33 |
 * | 60           | 49693                                         |     99.39 |
 * | 61           | 49756                                         |     99.51 |
 * | 62           | 49782                                         |     99.56 |
 * | 63           | 49829                                         |     99.66 |
 * | 64           | 49866                                         |     99.73 |
 * | 65           | 49881                                         |     99.76 |
 * | 66           | 49917                                         |     99.83 |
 * | 67           | 49925                                         |     99.85 |
 * | 68           | 49942                                         |     99.88 |
 * | 69           | 49941                                         |     99.88 |
 * | 70           | 49958                                         |     99.92 |
 * | 71           | 49971                                         |     99.94 |
 * | 72           | 49975                                         |     99.95 |
 * | 73           | 49974                                         |     99.95 |
 * | 74           | 49983                                         |     99.97 |
 * | 75           | 49987                                         |     99.97 |
 * | 76           | 49989                                         |     99.98 |
 * | 77           | 49989                                         |     99.98 |
 * | 78           | 49991                                         |     99.98 |
 * | 79           | 49994                                         |     99.99 |
 * | 80           | 49995                                         |     99.99 |
 * | 81           | 49997                                         |     99.99 |
 * | 82           | 49997                                         |     99.99 |
 * | 83           | 49997                                         |     99.99 |
 * | 84           | 50000                                         |    100.00 |
 * | 85           | 50000                                         |    100.00 |
 * | 86           | 49999                                         |    100.00 |
 * | 87           | 49997                                         |     99.99 |
 * | 88           | 49998                                         |    100.00 |
 * | 89           | 50000                                         |    100.00 |
 * | 90           | 49998                                         |    100.00 |
 * | 91           | 50000                                         |    100.00 |
 * | 92           | 50000                                         |    100.00 |
 * | 93           | 50000                                         |    100.00 |
 * | 94           | 50000                                         |    100.00 |
 * | 95           | 50000                                         |    100.00 |
 * | 96           | 50000                                         |    100.00 |
 * | 97           | 50000                                         |    100.00 |
 * | 98           | 50000                                         |    100.00 |
 * | 99           | 50000                                         |    100.00 |
 * | 100          | 50000                                         |    100.00 |
 * 
 */

 /*
 * Problem 4, Part 3, Scenario 5:
 * It took 23 People in a to meet in a room so that there is a 50 percent chance that at least one
 * pair of people will share a birthday. It is took a greater amount of people than I thought,
 * since I thought it would only take 20 people for the chances to reach 20 percent but it was
 * only 41 percent of the time.
 */
public class CodeCampTester {

    public static void main(String[] args) {

        // CodeCamp.sharedBirthdayExperiments();
        // Creating a Random Object For Later Uses
        Random randomObjt = new Random();

        // Testcase 1 For Hamming Distance
        // Large Empty Arrays, No Differences
        int size = 100000000;

        int[] arr1 = new int[size];
        int[] arr2 = new int[size];

        int results = CodeCamp.hammingDistance(arr1, arr2);
        System.out.println("\nMy Tests; Hamming Distance Test 1:");

        if (results == 0) {
            System.out.println("\tTest 1 (Hamming Distance) Has Passed.");
        } else {
            System.out.println("*************** TEST FAILED ***************");
        }

        // Testcase 2 For Hamming Distance
        // Large Random Array Test
        size = 10000000;
        arr1 = new int[size];
        arr2 = new int[size];
        int diff = 0, maxVal = 1000;

        for (int i = 0; i < arr1.length; i++) {
            arr1[i] = randomObjt.nextInt(maxVal);
            arr2[i] = randomObjt.nextInt(maxVal);
            if (arr1[i] != arr2[i]) {
                diff++;
            }
        }

        results = CodeCamp.hammingDistance(arr1, arr2);
        System.out.println("\nMy Tests; Hamming Distance Test 2:");

        if (results == diff) {
            System.out.println("\tTest 2 (Hamming Distance) Has Passed.");
        } else {
            System.out.println("*************** TEST FAILED ***************");
        }

        // Testcase 1 For isPermutation
        // Reversed Arrays, Same Values, Large Array
        System.out.println("\nMy Tests; Is Permutation Test 1:");

        size = 100000;
        arr1 = new int[size];
        arr2 = new int[size];

        for (int i = 0; i < size; i++) {
            arr1[i] = i;
            arr2[size - i - 1] = i;
        }

        boolean isTheSame = CodeCamp.isPermutation(arr1, arr2);
        if (isTheSame) {
            System.out.println("\tTest 1 (is Permutation) Has Passed.");
        } else {
            System.out.println("*************** TEST FAILED ***************");
        }

        // Testcase 2 For isPermutation
        // Ordered Array with the same value Except the last element, Large Array
        System.out.println("\nMy Tests; Is Permutation Test 2:");

        size = 100000;
        maxVal = 25;

        arr1 = new int[size];
        arr2 = new int[size];

        for (int i = 0; i < size; i++) {
            arr1[i] = i;
            arr2[i] = i;
        }

        arr2[size - 1] = randomObjt.nextInt(maxVal);

        isTheSame = CodeCamp.isPermutation(arr1, arr2);
        if (!isTheSame) {
            System.out.println("\tTest 2 (is Permutation) Has Passed.");
        } else {
            System.out.println("*************** TEST FAILED ***************");
        }

        // Testcase 1 For Most Vowels Method
        // Mixed Characters (Including Numbers and Symbols)
        // along with lower case letters and null values, and empty strings.
        System.out.println("\nMy Tests; Most Vowels Test 1:");

        String[] wordsTest1 = {null, "11111aIOaaa444dddd__89", "-112oiughJK", ""};

        results = CodeCamp.mostVowels(wordsTest1);

        if (results == 1) {
            System.out.println("\tTest 1 (Most Vowels) Has Passed.");
        } else {
            System.out.println("*************** TEST FAILED ***************");
            System.out.println(results);
        }

        // Testcase 2 For MostVowels
        // Really long strings
        System.out.println("\nMy Tests; Most Vowels Test 2:");

        wordsTest1 = new String[]{"                                                   iou  ",
            "aoooaoaoaooaoaoaoaooaoaoaoaoaoaoaooaoaoaoaoaoaoaoaooaoaoaoaoaoaoaoiiiiiiii", "aa",
            "bbseafdfasd", "fdjsakfdjklsda", "fdsafebagb", "fafeafdljobajdf"};

        results = CodeCamp.mostVowels(wordsTest1);
        if (results == 1) {
            System.out.println("\tTest 2 (Most Vowels) Has Passed.");
        } else {
            System.out.println("*************** TEST FAILED ***************");
            System.out.println(results);
        }

        // Testcase 1 For SharedBirthday
        // Large Number of People and A Large Number of Days
        // Stress Test
        System.out.println("\nMy Tests; Shared Birthday Test 1:");

        int numPeople = 100000;
        int days = 36500;

        results = CodeCamp.sharedBirthdays(numPeople, days);
        if (results > 0) {
            System.out.println("\tTest 2 (Shared Birthday) Has Passed. "
                    + "Number of Shared Birthdays: " + results);
        } else {
            System.out.println("*************** TEST FAILED ***************");
        }

        // Testcase 2 For SharedBirthday
        // Large Number of People and Only 2 Days, leads to get a really large number
        System.out.println("\nMy Tests; Shared Birthday Test 2:");

        numPeople = 50000;
        days = 2;

        results = CodeCamp.sharedBirthdays(numPeople, days);
        if (results > 0) {
            System.out.println("\tTest 2 (Shared Birthday) Has Passed. "
                    + "Number of Shared Birthdays: " + results);
        } else {
            System.out.println("*************** TEST FAILED ***************");
            System.out.println(results);
        }

        // Testcase 1 For Queens Are Safe
        // A Board where the queens are not on the same diagnols nor the same column or row.
        System.out.println("\nMy Tests; Queens Are Safe Test 1:");

        char[][] board = new char[][]{
            {'.', '.', 'q', '.'},
            {'q', '.', '.', '.'},
            {'.', '.', '.', 'q'},
            {'.', 'q', '.', '.'}
        };

        boolean safe = CodeCamp.queensAreSafe(board);
        if (safe) {
            System.out.println("\tTest 1 (QueensAreSafe) Has Passed.");
        } else {
            System.out.println("*************** TEST FAILED ***************");
            System.out.println(results);
        }

        // Testcase 2 For Queens Are Safe
        // Empty Large Chess Board, No Queens.
        System.out.println("\nMy Tests; Queens Are Safe Test 2:");

        size = 100;
        board = new char[size][size];
        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                board[row][col] = '.';
            }
        }

        safe = CodeCamp.queensAreSafe(board);
        if (safe) {
            System.out.println("\tTest 2 (QueensAreSafe) Has Passed.");
        } else {
            System.out.println("*************** TEST FAILED ***************");
            System.out.println(results);
        }

        // Testcase 3 For Queens Are Safe
        // A Chess Board Full of Queens
        System.out.println("\nMy Tests; Queens Are Safe Test 3:");

        board = new char[][]{
            {'q', 'q', 'q', 'q'},
            {'q', 'q', 'q', 'q'},
            {'q', 'q', 'q', 'q'},
            {'q', 'q', 'q', 'q'}
        };

        safe = CodeCamp.queensAreSafe(board);
        if (!safe) {
            System.out.println("\tTest 3 (QueensAreSafe) Has Passed.");
        } else {
            System.out.println("*************** TEST FAILED ***************");
            System.out.println(results);
        }

        // Testcase 1 For Most Valuable Subplot
        // A Plot Full of Zero Except for One Index where it has the value of 1
        System.out.println("\nMy Tests; Most Valuable Subplot Test 1:");

        size = 50;
        int[][] city = new int[size][size];
        city[size - 1][size - 2] = 1;

        results = CodeCamp.getValueOfMostValuablePlot(city);
        if (results == 1) {
            System.out.println("\tTest 1 (GetValueOfMostValuablePlot) Has Passed.");
        } else {
            System.out.println("*************** TEST FAILED ***************");
            System.out.println(results);
        }

        // Testcase 2 For Most Valuable Subplot
        // Really Negative Plots except for two spots with a really positive plots.
        System.out.println("\nMy Tests; Most Valuable Subplot Test 2:");

        size = 75;
        city = new int[size][size];

        for (int row = 0; row < size; row++) {
            for (int col = 0; col < size; col++) {
                city[row][col] = randomObjt.nextInt(-10, -1);
            }
        }

        city[size / 2][size / 2] = 15000;
        city[size - 1][size - 1] = 10000;

        results = CodeCamp.getValueOfMostValuablePlot(city);
        if (results > 0) {
            System.out.println("\tTest 2 (GetValueOfMostValuablePlot) Has Passed."
                    + "Value of Most Valuable Subplot: " + results);
        } else {
            System.out.println("*************** TEST FAILED ***************");
            System.out.println(results);
        }

    }
}
