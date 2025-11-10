import java.util.Scanner;
import java.util.Arrays;

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
            chosenColumn = getPlayerResponse(keyboard, names, playerTurn);
            updateBoard(board, chosenColumn, playerTurn);

            didRedPlayerWin = checkWinConditions(board, TOKENS_TYPES[RED_PLAYER]);
            didBluePlayerWin = checkWinConditions(board, TOKENS_TYPES[BLUE_PLAYER]);

            didDraw = drawConditions(board);

            counter++;
        }

        keyboard.close();
    }

    /**
     * 
     * @param keyboard
     * @param names
     * @param playerTurn
     * @return
     */
    public static int getPlayerResponse(Scanner keyboard, String[] names, int playerTurn) {

        System.out.println(names[playerTurn] + "it is your turn.");
        System.out.println("Your pieces are the " + TOKENS_TYPES[playerTurn] + "'s.");

        String prompt = names[playerTurn] + ", enter the column to drop your checker: ";
        int playerChoice = getInt(keyboard, prompt);

        return playerChoice;
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

        int currRow = 0;
        while (board[currRow][chosenColumn] == TOKENS_TYPES[EMPTY] && currRow < NUM_ROWS) {
            currRow++;
        }

        board[currRow][chosenColumn] = TOKENS_TYPES[playerTurn];
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

        for (int i = 0; i < NUM_ROWS; i++) {
            System.out.println(board[i].toString());
        }
    }

    /**
     * 
     * @param board
     * @return
     */
    public static boolean drawConditions(char[][] board) {

        for (int row = 0; row < NUM_ROWS; row++) {
            for (int col = 0; col < NUM_COLUMNS; col++) {
                if (board[row][col] == TOKENS_TYPES[EMPTY]) {
                    return false;
                }
            }
        }

        return true;
    }

    public static boolean checkWinConditions(char[][] board, char playerChar) {
        boolean didWin = false;
        didWin = didWin || checkWinHorizontally(board, playerChar);
        didWin = didWin || checkWinDownwards(board, playerChar);
        didWin = didWin || checkDiagonallyLeft(board, playerChar);
        didWin = didWin || checkDiagonallyRight(board, playerChar);
        return didWin;
    }

    public static boolean checkWinHorizontally(char[][] charRow, char playerChar) {
        int verticalRange = NUM_ROWS - WIN_SIZE;
        int[] playerTokenPlaced = new int[WIN_SIZE];

        for (int col = 0; col < NUM_COLUMNS; col++) {
            int count = 0;
            Arrays.fill(playerTokenPlaced, 0);
            for (int row = 0; row <= verticalRange; row++) {
                if (charRow[row][col] == playerChar) {
                    playerTokenPlaced[count]++;
                    count++;
                }
            }

            int totalTokens = getArraySum(playerTokenPlaced, playerChar);
            if (totalTokens == WIN_SIZE) {
                return true;
            }
        }

        return false;
    }

    public static boolean checkWinDownwards(char[][] charRow, char playerChar) {
        int horizontalRange = NUM_COLUMNS - WIN_SIZE;
        int[] playerTokenPlaced = new int[WIN_SIZE];

        for (int row = 0; row < NUM_ROWS; row++) {
            int count = 0;
            Arrays.fill(playerTokenPlaced, 0);
            for (int col = 0; col <= horizontalRange; col++) {
                playerTokenPlaced[count]++;
                count++;
            }

            int totalTokens = getArraySum(playerTokenPlaced, playerChar);
            if (totalTokens == WIN_SIZE) {
                return true;
            }
        }

        return false;
    }

    public static boolean checkDiagonallyRight(char[][] board, char playerChar) {
        
    }

    public static boolean checkDiagonallyLeft(char[][] board, char playerChar) {

    }

    public static int getArraySum(int[] playerTokenPlaced, char playerChar) {
        
        int sum = 0;
        for (int i = 0; i < playerTokenPlaced.length; i++) {
            if (playerTokenPlaced[i] == playerChar) {
                sum++;
            }
        }
        return sum;
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
