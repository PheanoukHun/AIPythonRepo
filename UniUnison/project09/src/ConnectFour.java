import java.util.Scanner;

/**
 * CS312 Assignment 9 - Connect Four
 *
 * On my honor, Pheanouk Hun, this programming assignment is my own work,
 * I have not shared it with others and I will not share it in the future.
 *
 * Program that allows two people to play Connect Four.
 * 
 * Name: Pheanouk Hun
 * UTEID: ph23434
 * 
 */

public class ConnectFour {

    public static final int NUM_COLUMNS = 7;
    public static final int NUM_ROWS = 6;

    public static final int RED_PLAYER = 0;
    public static final int BLUE_PLAYER = 1;
    public static final int NUM_PLAYERS = 2;
    public static final int EMPTY = 2;

    public static final char[] TOKENS_TYPES = { 'r', 'b', '.' };

    public static int WIN_SIZE = 4;

    public static void main(String[] args) {
        intro();

        String[] names = new String[NUM_PLAYERS];

        Scanner keyboard = new Scanner(System.in);

        System.out.print("Player 1 enter your name: ");
        names[RED_PLAYER] = keyboard.nextLine();

        System.out.print("\nPlayer 2 enter your name: ");
        names[BLUE_PLAYER] = keyboard.nextLine();

        char[][] board = createBoard();

        boolean didRedPlayerWin = false;
        boolean didBluePlayerWin = false;
        boolean didDraw = false;

        int counter = 0;
        int chosenColumn;
        while (!(didRedPlayerWin || didBluePlayerWin || didDraw)) {

            System.out.println("\nCurrent Board");
            printBoard(board);

            int playerTurn = counter % 2;
            chosenColumn = getPlayerResponse(keyboard, names, playerTurn, board);
            updateBoard(board, chosenColumn, playerTurn);

            didRedPlayerWin = checkWinConditions(board, TOKENS_TYPES[RED_PLAYER]);
            didBluePlayerWin = checkWinConditions(board, TOKENS_TYPES[BLUE_PLAYER]);

            didDraw = drawConditions(board);

            counter++;
        }

        if (didRedPlayerWin) {
            System.out.println(names[RED_PLAYER] + " wins!!\n");
        } else if (didBluePlayerWin) {
            System.out.println(names[BLUE_PLAYER] + " wins!!\n");
        } else {
            System.out.println("The game is a draw.\n");
        }

        System.out.println("Final Board");
        printBoard(board);

        keyboard.close();
    }

    /**
     * 
     * @param keyboard
     * @param names
     * @param turn
     * @return
     */
    public static int getPlayerResponse(Scanner keyboard, String[] names,
            int turn, char[][] board) {

        System.out.println(names[turn] + " it is your turn.");
        System.out.println("Your pieces are the " + TOKENS_TYPES[turn] + "'s.");

        String prompt = names[turn] + ", enter the column to drop your checker: ";

        int column = 0;
        boolean isNotValidAnswer = true;
        boolean isColumnFull = false;

        while (isNotValidAnswer || isColumnFull) {

            System.out.print(prompt);
            column = getInt(keyboard, prompt);
            column--;

            isNotValidAnswer = column < 0 || column >= NUM_COLUMNS;
            if (!isNotValidAnswer)
                isColumnFull = board[0][column] != TOKENS_TYPES[EMPTY];
        }

        return column;
    }

    /**
     * 
     * @return
     */
    public static char[][] createBoard() {

        char[][] board = new char[NUM_ROWS][NUM_COLUMNS];

        for (int row = 0; row < NUM_ROWS; row++) {
            for (int col = 0; col < NUM_COLUMNS; col++) {
                board[row][col] = TOKENS_TYPES[EMPTY];
            }
        }

        return board;
    }

    /**
     * 
     * @param board
     * @param chosenColumn
     * @param playerTurn
     */
    public static void updateBoard(char[][] board, int chosenColumn, int playerTurn) {

        int currRow = NUM_ROWS - 1;
        while (currRow >= 0 && board[currRow][chosenColumn] != TOKENS_TYPES[EMPTY]) {
            currRow--;
        }

        if (currRow >= 0) {
            board[currRow][chosenColumn] = TOKENS_TYPES[playerTurn];
        }
    }

    /**
     * 
     * @param board
     */
    public static void printBoard(char[][] board) {

        for (int col = 1; col <= NUM_COLUMNS; col++) {
            System.out.print(col + " ");
        }

        System.out.println(" column numbers");

        for (int row = 0; row < NUM_ROWS; row++) {
            for (int col = 0; col < NUM_COLUMNS; col++) {
                System.out.print(board[row][col] + " ");
            }
            System.out.println();
        }
    }

    /**
     * 
     * @param board
     * @return
     */
    public static boolean drawConditions(char[][] board) {

        for (int col = 0; col < NUM_COLUMNS; col++) {
            if (board[0][col] == TOKENS_TYPES[EMPTY]) {
                return false;
            }
        }

        return true;
    }

    public static boolean checkWinConditions(char[][] board, char playerChar) {

        boolean didWin = false;

        for (int row = 0; row < NUM_ROWS; row++) {
            for (int col = 0; col < NUM_COLUMNS; col++) {
                if (board[row][col] == playerChar) {
                    didWin = didWin || checkRight(board, playerChar, row, col);
                    didWin = didWin || checkDown(board, playerChar, row, col);
                    didWin = didWin || checkDownRight(board, playerChar, row, col);
                    didWin = didWin || checkDownLeft(board, playerChar, row, col);
                }
            }
        }

        return didWin;
    }

    public static boolean checkRight(char[][] board, char playerChar, int row, int col) {

        int horizontalRange = col + WIN_SIZE;
        if (horizontalRange <= NUM_COLUMNS) {
            int count = 0;

            for (int i = 0; i < WIN_SIZE; i++) {
                if (board[row][col + i] == playerChar) {
                    count++;
                }
            }

            if (count == WIN_SIZE) {
                return true;
            }
        }

        return false;
    }

    public static boolean checkDown(char[][] board, char playerChar, int row, int col) {

        int verticalRange = row + WIN_SIZE;
        if (verticalRange <= NUM_ROWS) {
            int count = 0;

            for (int i = 0; i < WIN_SIZE; i++) {
                if (board[row + i][col] == playerChar) {
                    count++;
                }
            }

            if (count == WIN_SIZE) {
                return true;
            }
        }

        return false;
    }

    public static boolean checkDownRight(char[][] board, char playerChar, int row, int col) {

        int horizontalRange = col + WIN_SIZE;
        int verticalRange = row + WIN_SIZE;
        if (horizontalRange <= NUM_COLUMNS && verticalRange <= NUM_ROWS) {
            int count = 0;

            for (int i = 0; i < WIN_SIZE; i++) {
                if (board[row + i][col + i] == playerChar) {
                    count++;
                }
            }

            if (count == WIN_SIZE) {
                return true;
            }
        }

        return false;
    }

    public static boolean checkDownLeft(char[][] board, char playerChar, int row, int col) {
        
        int horizontalRange = col - WIN_SIZE + 1;
        int verticalRange = row + WIN_SIZE;
        if (horizontalRange >= 0 && verticalRange <= NUM_ROWS) {
            int count = 0;

            for (int i = 0; i < WIN_SIZE; i++) {
                if (board[row + i][col - i] == playerChar) {
                    count++;
                }
            }

            if (count == WIN_SIZE) {
                return true;
            }
        }

        return false;
    }

    // show the intro
    public static void intro() {
        System.out.println("This program allows two people to play the");
        System.out.println("game of Connect four. Each player takes turns");
        System.out.println("dropping a checker in one of the open columns");
        System.out.println("on the board. The columns are numbered 1 to 7.");
        System.out.println("The first player to get four checkers in a row");
        System.out.println("horizontally, vertically, or diagonally wins");
        System.out.println("the game. If no player gets fours in a row and");
        System.out.println("and all spots are taken the game is a draw.");
        System.out.println("Player one's checkers will appear as r's and");
        System.out.println("player two's checkers will appear as b's.");
        System.out.println("Open spaces on the board will appear as .'s.\n");
    }

    // Prompt the user for an int. The String prompt will
    // be printed out. I expect key is connected to System.in.
    public static int getInt(Scanner keyboard, String prompt) {
        while (!keyboard.hasNextInt()) {
            String notAnInt = keyboard.nextLine();
            System.out.println("\n" + notAnInt + " is not an integer.");
            System.out.print(prompt);
        }
        int result = keyboard.nextInt();
        keyboard.nextLine();
        return result;
    }
}
