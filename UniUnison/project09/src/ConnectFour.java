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
    public static final int DRAW_CONDITION = 2;
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
        System.out.println();

        char[][] board = createBoard();

        boolean[] gameResults = playGame(board, keyboard, names);
        printGameResults(gameResults, names);

        System.out.println("Final Board");
        printBoard(board);

        keyboard.close();
    }

    /**
     * A Method to continually run tne game until either one of the player wins or
     * there is a draw.
     * 
     * @param board    - A 2D Char Array that stores the state of the game with the
     *                 first index value signifying the row value of the board, and
     *                 the second index being the column value of the board. With
     *                 the value signifying the empty token, r being red player, and
     *                 b being blue player.
     * @param keyboard - A Scanner object that takes in input from the keyboard and
     *                 user.
     * @param names    - An array of strings wiht a length of 2 that contains the
     *                 name of the two players.
     * @return - A Boolean Array that contains the result of the game (whether
     *         player one or player two wins, or there is a draw).
     */
    public static boolean[] playGame(char[][] board, Scanner keyboard, String[] names) {

        boolean[] conditions = { false, false, false };
        boolean didPlayerWin = false;
        int counter = 0, chosenColumn;

        // Loop runs once per player turn
        while (!(didPlayerWin || conditions[DRAW_CONDITION])) {
            System.out.println("Current Board");
            printBoard(board);

            int playerTurn = counter % 2;
            chosenColumn = getPlayerResponse(keyboard, names, playerTurn, board);
            updateBoard(board, chosenColumn, playerTurn);

            conditions[RED_PLAYER] = checkWinConditions(board, TOKENS_TYPES[RED_PLAYER]);
            conditions[BLUE_PLAYER] = checkWinConditions(board, TOKENS_TYPES[BLUE_PLAYER]);

            conditions[DRAW_CONDITION] = checkDrawCondition(board);

            counter++;
            didPlayerWin = conditions[RED_PLAYER] || conditions[BLUE_PLAYER];
        }

        return conditions;
    }

    /**
     * This method prints the results of the game.
     * 
     * @param gameResults - A Boolean Array that contains the result of the game
     *                    (whetherplayer one or player two wins, or there is a
     *                    draw).
     * @param names       - An array of strings wiht a length of 2 that contains the
     *                    name of the two players.
     */
    public static void printGameResults(boolean[] gameResults, String[] names) {
        // IF: The RED PLAYER wins, then prints out their names.
        if (gameResults[RED_PLAYER]) {
            System.out.println(names[RED_PLAYER] + " wins!!\n");

            // ELSE IF: The BLUE PLAYER wins, then prints out their name
        } else if (gameResults[BLUE_PLAYER]) {
            System.out.println(names[BLUE_PLAYER] + " wins!!\n");

            // ELSE: It is a draw and prints out the draw message.
        } else {
            System.out.println("The game is a draw.\n");
        }
    }

    /**
     * This method gets the column number of the board in which the player palced
     * their token into.
     * 
     * @param keyboard - A Scanner object that takes in input from the keyboard and
     *                 user.
     * @param names    - A Scanner object that takes in input from the keyboard and
     *                 user.
     * @param turn     - An int value that tells the methods whose turn it is.
     * @param board    - A 2D Char Array that stores the state of the game with the
     *                 first index value signifying the row value of the board, and
     *                 the second index being the column value of the board. With
     *                 the value signifying the empty token, r being red player, and
     *                 b being blue player.
     * @return - The int value that represents the column in which the user wants to
     *         drop their token into.
     */
    public static int getPlayerResponse(Scanner keyboard, String[] names,
            int turn, char[][] board) {

        System.out.println(names[turn] + " it is your turn.");
        System.out.println("Your pieces are the " + TOKENS_TYPES[turn] + "'s.");

        String prompt = names[turn] + ", enter the column to drop your checker: ";

        int column = 0;
        boolean notValidColumn = false;
        boolean isColumnFull = false;

        do {

            System.out.print(prompt);
            column = getInt(keyboard, prompt);
            System.out.println();

            notValidColumn = column < 1 || column > NUM_COLUMNS;

            // IF: The Responds with a valid column number
            if (!notValidColumn) {
                column--;
                isColumnFull = board[0][column] != TOKENS_TYPES[EMPTY];

                // IF: The column the user placed it in is full tell the user that.
                if (isColumnFull) {
                    System.out.print((column + 1) + " is not a legal column.");
                    System.out.println(" That column is full");
                }

                // ELSE: Ask the User to print a valid character.
            } else {
                System.out.println(column + " is not a valid column.");
            }

            // Repeats until the player gives a valid and free column.
        } while (notValidColumn || isColumnFull);

        return column;
    }

    /**
     * A method that creates that creates the board at the start of the game and
     * filling it with the EMPTY TOKEN char value.
     * 
     * @return - A 2D Char Array that stores the state of the game with the
     *         first index value signifying the row value of the board, and
     *         the second index being the column value of the board. With
     *         the value signifying the empty token, r being red player, and
     *         b being blue player.
     */
    public static char[][] createBoard() {

        char[][] board = new char[NUM_ROWS][NUM_COLUMNS];

        // Runs once per row of the board.
        for (int row = 0; row < NUM_ROWS; row++) {

            // Runs once per column of the row.
            for (int col = 0; col < NUM_COLUMNS; col++) {
                board[row][col] = TOKENS_TYPES[EMPTY];
            }
        }

        return board;
    }

    /**
     * This method updates the game board based on which column the user dropped
     * their token.
     * 
     * @param board        - A 2D Char Array that stores the state of the game with
     *                     the first index value signifying the row value of the
     *                     board, and the second index being the column value of the
     *                     board. With the value signifying the empty token, r being
     *                     red player, and b being blue player.
     * @param chosenColumn - The int value that represents the column in which the
     *                     user wants to drop their token into.
     * @param playerTurn   - The int value that represents who turns it is to play
     *                     it meaning, what character to update the board with.
     */
    public static void updateBoard(char[][] board, int chosenColumn, int playerTurn) {

        int currRow = NUM_ROWS - 1;

        // Runs until you get the lowest empty spot in the column.
        while (currRow >= 0 && board[currRow][chosenColumn] != TOKENS_TYPES[EMPTY]) {
            currRow--;
        }

        // Place the player's token in the first empty row found.
        if (currRow >= 0) {
            board[currRow][chosenColumn] = TOKENS_TYPES[playerTurn];
        }
    }

    /**
     * This method prints out the game board after each round.
     * 
     * @param board - A 2D Char Array that stores the state of the game with the
     *              first index value signifying the row value of the board, and the
     *              second index being the column value of the board. With the value
     *              signifying the empty token, r being red player, and b being blue
     *              player.
     */
    public static void printBoard(char[][] board) {

        // Runs once per from 1-7 to signify the column numbers
        for (int col = 1; col <= NUM_COLUMNS; col++) {
            System.out.print(col + " ");
        }

        System.out.println(" column numbers");

        // Runs once per row
        for (int row = 0; row < NUM_ROWS; row++) {

            // Runs once per column and prints out the token at that point with an
            // additional space.
            for (int col = 0; col < NUM_COLUMNS; col++) {
                System.out.print(board[row][col] + " ");
            }

            System.out.println();
        }

        System.out.println();
    }

    /**
     * This method checks whether there a draw in the current round right now.
     * 
     * @param board - A 2D Char Array that stores the state of the game with the
     *              first index value signifying the row value of the board, and
     *              the second index being the column value of the board. With
     *              the value signifying the empty token, r being red player, and
     *              b being blue player.
     * @return - A Boolean Value that tells the program if there is a draw or not.
     */
    public static boolean checkDrawCondition(char[][] board) {

        // Loop runs once to check whether there is any empty spots left.
        for (int col = 0; col < NUM_COLUMNS; col++) {

            // IF: The token given is an empty token, then return false for a draw.
            if (board[0][col] == TOKENS_TYPES[EMPTY]) {
                return false;
            }
        }

        return true;
    }

    /**
     * A method that checks if the player wins during the current round in 4
     * directions.
     * 
     * @param board      - A 2D Char Array that stores the state of the game with
     *                   the first index value signifying the row value of the
     *                   board, and the second index being the column value of the
     *                   board. With the value signifying the empty token, r being
     *                   red player, and b being blue player.
     * @param playerChar - An char value that represents what the method have to
     *                   look for when trying to check for the win condition.
     * @return
     */
    public static boolean checkWinConditions(char[][] board, char playerChar) {

        boolean didWin = false;

        // Runs once per row of the game board.
        for (int row = 0; row < NUM_ROWS; row++) {

            // Runs once per column of the game board.
            for (int col = 0; col < NUM_COLUMNS; col++) {

                // IF: The current character at the current row and column index equals to the
                // player character, check the win conditions.
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

    /**
     * This method checks to see if there is win condition horizontally.
     * 
     * @param board      - A 2D Char Array that stores the state of the game with
     *                   the first index value signifying the row value of the
     *                   board, and the second index being the column value of the
     *                   board. With the value signifying the empty token, r being
     *                   red player, and b being blue player.
     * @param playerChar - An char value that represents what the method have to
     *                   look for when trying to check for the win condition.
     * @param row        - The row index value of the board of the current
     *                   character.
     * @param col        - The column index value of the board of the current
     *                   character.
     * @return - The Boolean value of whether there is a win horizontally from that
     *         position.
     */
    public static boolean checkRight(char[][] board, char playerChar, int row, int col) {

        int horizontalRange = col + WIN_SIZE;

        // IF: It is possible to get 4 character connection.
        if (horizontalRange <= NUM_COLUMNS) {
            int count = 0;

            // Runs for the number of times it takes to win.
            for (int i = 0; i < WIN_SIZE; i++) {

                // IF: the current char is the same as the player character, then increment the
                // counter.
                if (board[row][col + i] == playerChar) {
                    count++;
                }
            }

            // IF: the count variable is the same as the winning number, return true.
            if (count == WIN_SIZE) {
                return true;
            }
        }

        return false;
    }

    /**
     * This method checks to see if there is win condition vertically.
     * 
     * @param board      - A 2D Char Array that stores the state of the game with
     *                   the first index value signifying the row value of the
     *                   board, and the second index being the column value of the
     *                   board. With the value signifying the empty token, r being
     *                   red player, and b being blue player.
     * @param playerChar - An char value that represents what the method have to
     *                   look for when trying to check for the win condition.
     * @param row        - The row index value of the board of the current
     *                   character.
     * @param col        - The column index value of the board of the current
     *                   character.
     * @return - The Boolean value of whether there is a win vertically from that
     *         position.
     */
    public static boolean checkDown(char[][] board, char playerChar, int row, int col) {

        int verticalRange = row + WIN_SIZE;

        // IF: It is possible to get 4 character connection.
        if (verticalRange <= NUM_ROWS) {
            int count = 0;

            // Runs for the number of times it takes to win.
            for (int i = 0; i < WIN_SIZE; i++) {

                // IF: the current char is the same as the player character, then increment the
                // counter.
                if (board[row + i][col] == playerChar) {
                    count++;
                }
            }

            // IF: the count variable is the same as the winning number, return true.
            if (count == WIN_SIZE) {
                return true;
            }
        }

        return false;
    }

    /**
     * This method checks to see if there is win condition right down.
     * 
     * @param board      - A 2D Char Array that stores the state of the game with
     *                   the first index value signifying the row value of the
     *                   board, and the second index being the column value of the
     *                   board. With the value signifying the empty token, r being
     *                   red player, and b being blue player.
     * @param playerChar - An char value that represents what the method have to
     *                   look for when trying to check for the win condition.
     * @param row        - The row index value of the board of the current
     *                   character.
     * @param col        - The column index value of the board of the current
     *                   character.
     * @return - The Boolean value of whether there is a win right down from that
     *         position.
     */
    public static boolean checkDownRight(char[][] board, char playerChar, int row, int col) {

        int horizontalRange = col + WIN_SIZE;
        int verticalRange = row + WIN_SIZE;

        // IF: It is possible to get 4 character connection.
        if (horizontalRange <= NUM_COLUMNS && verticalRange <= NUM_ROWS) {
            int count = 0;

            // Runs for the number of times it takes to win.
            for (int i = 0; i < WIN_SIZE; i++) {

                // IF: the current char is the same as the player character, then increment the
                // counter.
                if (board[row + i][col + i] == playerChar) {
                    count++;
                }
            }

            // IF: the count variable is the same as the winning number, return true.
            if (count == WIN_SIZE) {
                return true;
            }
        }

        return false;
    }

    /**
     * This method checks to see if there is win condition left down.
     * 
     * @param board      - A 2D Char Array that stores the state of the game with
     *                   the first index value signifying the row value of the
     *                   board, and the second index being the column value of the
     *                   board. With the value signifying the empty token, r being
     *                   red player, and b being blue player.
     * @param playerChar - An char value that represents what the method have to
     *                   look for when trying to check for the win condition.
     * @param row        - The row index value of the board of the current
     *                   character.
     * @param col        - The column index value of the board of the current
     *                   character.
     * @return - The Boolean value of whether there is a win left down from that
     *         position.
     */
    public static boolean checkDownLeft(char[][] board, char playerChar, int row, int col) {

        int horizontalRange = col - WIN_SIZE + 1;
        int verticalRange = row + WIN_SIZE;

        // IF: It is possible to get 4 character connection.
        if (horizontalRange >= 0 && verticalRange <= NUM_ROWS) {
            int count = 0;

            // IF: the current char is the same as the player character, then increment the
            // counter.
            for (int i = 0; i < WIN_SIZE; i++) {
                if (board[row + i][col - i] == playerChar) {
                    count++;
                }
            }

            // IF: the count variable is the same as the winning number, return true.
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
