import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/*
 * CS312 Assignment 6 - HomeFieldAdvantage
 * 
 * On my honor, Pheanouk Hun, this programming assignment is my own work, 
 * I have not shared it with others and I will not share it in the future.
 *
 * A program to play determine the extend of home field advantage in sports.
 *
 *  Name: Pheanouk Hun
 *  UTEID: ph23434
 */

//  Data from 14 input files and analysis of Home Field Advantate go here. 

/*
**********   College Football --- 2005   **********

HOME FIELD ADVANTAGE RESULTS

Total number of games: 4,069
Number of games with a home team: 3,955
Percentage of games with a home team: 97.2%
Number of games the home team won: 2,257
Home team win percentage: 57.1%
Home team average margin: 4.24

Do you want to check another data set?
Enter Y or y to analyze another file, anything else to quit: 
Enter the file name: 
**********   College Football --- 2008   **********

HOME FIELD ADVANTAGE RESULTS

Total number of games: 4,702
Number of games with a home team: 4,592
Percentage of games with a home team: 97.7%
Number of games the home team won: 2,617
Home team win percentage: 57.0%
Home team average margin: 4.28

Do you want to check another data set?
Enter Y or y to analyze another file, anything else to quit: 
Enter the file name: 
**********   NCAA Men's Basketball --- 2011 - 2012   **********

HOME FIELD ADVANTAGE RESULTS

Total number of games: 15,842
Number of games with a home team: 14,481
Percentage of games with a home team: 91.4%
Number of games the home team won: 9,178
Home team win percentage: 63.4%
Home team average margin: 5.37

Do you want to check another data set?
Enter Y or y to analyze another file, anything else to quit: 
Enter the file name: 
**********   NCAA Men's Basketball --- 2013 - 2014   **********

HOME FIELD ADVANTAGE RESULTS

Total number of games: 16,219
Number of games with a home team: 14,754
Percentage of games with a home team: 91.0%
Number of games the home team won: 9,276
Home team win percentage: 62.9%
Home team average margin: 5.18

Do you want to check another data set?
Enter Y or y to analyze another file, anything else to quit: 
Enter the file name: 
**********   Major League Baseball --- 2012   **********

HOME FIELD ADVANTAGE RESULTS

Total number of games: 2,467
Number of games with a home team: 2,465
Percentage of games with a home team: 99.9%
Number of games the home team won: 1,312
Home team win percentage: 53.2%
Home team average margin: 0.16

Do you want to check another data set?
Enter Y or y to analyze another file, anything else to quit: 
Enter the file name: 
**********   College Men's Soccer --- 2006   **********

HOME FIELD ADVANTAGE RESULTS

Total number of games: 8,380
Number of games with a home team: 7,373
Percentage of games with a home team: 88.0%
Number of games the home team won: 3,962
Home team win percentage: 53.7%
Home team average margin: 0.51

Do you want to check another data set?
Enter Y or y to analyze another file, anything else to quit: 
Enter the file name: 
**********   NCAA Women's Basketball --- 1999 - 2000   **********

HOME FIELD ADVANTAGE RESULTS

Total number of games: 4,607
Number of games with a home team: 4,345
Percentage of games with a home team: 94.3%
Number of games the home team won: 2,696
Home team win percentage: 62.0%
Home team average margin: 5.60

Do you want to check another data set?
Enter Y or y to analyze another file, anything else to quit: 
Enter the file name: 
**********   NCAA Women's Basketball --- 2004 - 2005   **********

HOME FIELD ADVANTAGE RESULTS

Total number of games: 14,687
Number of games with a home team: 13,261
Percentage of games with a home team: 90.3%
Number of games the home team won: 8,043
Home team win percentage: 60.7%
Home team average margin: 4.95

Do you want to check another data set?
Enter Y or y to analyze another file, anything else to quit: 
Enter the file name: 
**********   NCAA Women's Basketball --- 2011 - 2012   **********

HOME FIELD ADVANTAGE RESULTS

Total number of games: 14
Number of games with a home team: 9
Percentage of games with a home team: 64.3%
Number of games the home team won: 6
Home team win percentage: 66.7%
Home team average margin: 6.00

Do you want to check another data set?
Enter Y or y to analyze another file, anything else to quit: 
Enter the file name: 
**********   NCAA Women's Basketball --- 2011 - 2012   **********

HOME FIELD ADVANTAGE RESULTS

Total number of games: 15,640
Number of games with a home team: 14,303
Percentage of games with a home team: 91.5%
Number of games the home team won: 8,496
Home team win percentage: 59.4%
Home team average margin: 4.41

Do you want to check another data set?
Enter Y or y to analyze another file, anything else to quit: 
Enter the file name: 
**********   NCAA Women's Basketball --- 2012 - 2013   **********

HOME FIELD ADVANTAGE RESULTS

Total number of games: 15,722
Number of games with a home team: 14,341
Percentage of games with a home team: 91.2%
Number of games the home team won: 8,512
Home team win percentage: 59.4%
Home team average margin: 4.23

Do you want to check another data set?
Enter Y or y to analyze another file, anything else to quit: 
Enter the file name: 
**********   NCAA Women's Basketball --- 2013 - 2014   **********

HOME FIELD ADVANTAGE RESULTS

Total number of games: 15,790
Number of games with a home team: 14,305
Percentage of games with a home team: 90.6%
Number of games the home team won: 8,471
Home team win percentage: 59.2%
Home team average margin: 4.24

Do you want to check another data set?
Enter Y or y to analyze another file, anything else to quit: 
Enter the file name: 
**********   NCAA Women's Soccer --- 2010   **********

HOME FIELD ADVANTAGE RESULTS

Total number of games: 10,593
Number of games with a home team: 9,941
Percentage of games with a home team: 93.8%
Number of games the home team won: 5,392
Home team win percentage: 54.2%
Home team average margin: 0.51

Do you want to check another data set?
Enter Y or y to analyze another file, anything else to quit: 
*/

public class HomeField {

    // Ask the user for the name of a data file and process
    // it until they want to quit.
    public static void main(String[] args) throws IOException {
        System.out.println("A program to analyze home field advantage in sports.");
        System.out.println();
        // CS312 students. Do not create any other Scanners connected to System.in.
        // Pass keyboard as a parameter to all the methods that need it.
        Scanner keyboard = new Scanner(System.in);

        boolean tryAgain;

        // Loop runs once per file analysis and runs until player wants to stop
        do {

            // Get a file and analyze it
            File validFile = getValidFile(keyboard);
            analyzeData(validFile);

            // Prompts user to analyze the another data
            tryAgain = continueAnalyzing(keyboard);
        } while (tryAgain);

        // Closes Keyboard Scanner
        keyboard.close();
    }

    // CS312 Students - Add your methods here.

    /**
     * This method uses a scanner to get a valid file from the user.
     * It continues to prompt the user until a an Existing file is entered.
     * 
     * @param keyboard - Scanner object for reading user input.
     * @return An Existing File object with the file the user wants to analyze.
     */
    public static File getValidFile(Scanner keyboard) {
        System.out.print("Enter the file name: ");
        String userFile = keyboard.nextLine();
        File validFile = new File(userFile);

        // Loop runs once per file name entered by the user.
        while (!validFile.exists()) {
            System.out.println("Sorry, that file does not exist");
            System.out.print("Enter the file name: ");
            userFile = keyboard.nextLine();
            validFile = new File(userFile);
        }

        return validFile;
    }

    /**
     * This method analyzes data from the file object and calculates the number of
     * games,
     * number of home games, number of home games won, and other stats.
     * 
     * @param dataFile is File object containing game data to analyze
     * @return - void
     * @throws FileNotFoundException if the file object is not a valid object
     */
    public static void analyzeData(File dataFile) throws FileNotFoundException {
        Scanner fileSc = new Scanner(dataFile);

        String title = fileSc.nextLine();
        String years = fileSc.nextLine();

        title += " --- " + years;

        int numGames = 0, homeGame = 0, homeGamesWon = 0;
        double teamMargin = 0.0, homePercent = 0.0, homeWinRate = 0.0;

        String game;

        // Loop runs once per game record in the file
        while (fileSc.hasNextLine()) {
            game = fileSc.nextLine();
            numGames++;

            // Checks to see if the game is a home game
            if (game.contains("@")) {
                homeGame++;

                int diffScore = determineHomeTeamWin(game);

                // If the Score is greater than 0 than home team won.
                if (diffScore > 0) {
                    homeGamesWon++;
                }

                teamMargin += diffScore;
            }
        }

        // If there are home games, assigns them values.
        if (homeGame != 0) {
            teamMargin /= homeGame;
            homePercent = ((double) homeGame / numGames) * 100;
            homeWinRate = ((double) homeGamesWon / homeGame) * 100;
        }

        printStat(title, numGames, homeGame, homePercent, homeGamesWon, homeWinRate, teamMargin);

        fileSc.close();
    }

    /**
     * Determines the score margin that the home team has that game.
     * If the score is greater than one, the team won. If the score is less than
     * one, the team
     * loss.
     * 
     * @param game - A string that contains the numbers for a single match
     * @return an int value of the differences in score between the home team and
     *         visiting team
     */
    public static int determineHomeTeamWin(String game) {
        int scoreOne = 0, scoreTwo = 0;

        Scanner lineSc = new Scanner(game);
        lineSc.next();

        boolean isTeamOneHome = (lineSc.next()).charAt(0) == '@';

        // Loop runs until an int is detected as the next token
        while (!lineSc.hasNextInt()) {
            lineSc.next();
        }
        scoreOne = lineSc.nextInt();

        // Loop runs until an int is detected as the next token
        while (!lineSc.hasNextInt()) {
            lineSc.next();
        }
        scoreTwo = lineSc.nextInt();

        lineSc.close();

        // If the first team is the home team return the first score minus second score.
        if (isTeamOneHome) {
            return (scoreOne - scoreTwo);
        }

        return (scoreTwo - scoreOne);
    }

    /**
     * Prints the results of the home field advantage analysis.
     * 
     * @param header         - Prints the Header of File
     * @param numGames       - Total Number of Games
     * @param homeGame       - Total Number of Home Games
     * @param homePercent    - Percentage of Games who is a Home Game
     * @param homeGamesWon   - Number of Home Team Wins
     * @param homeWinPercent - Percentage of Home Game Won
     * @param teamMargin     - Average margin for home team wins
     * @return - void
     */
    public static void printStat(String header, int numGames, int homeGame, double homePercent,
            int homeGamesWon, double homeWinPercent, double teamMargin) {
        System.out.println("\n**********   " + header + "   **********");
        System.out.println("\nHOME FIELD ADVANTAGE RESULTS\n");

        System.out.printf("Total number of games: %,d\n", numGames);
        System.out.printf("Number of games with a home team: %,d\n", homeGame);
        System.out.printf("Percentage of games with a home team: %.1f%%\n", homePercent);
        System.out.printf("Number of games the home team won: %,d\n", homeGamesWon);
        System.out.printf("Home team win percentage: %.1f%%\n", homeWinPercent);
        System.out.printf("Home team average margin: %.2f\n", teamMargin);
    }

    /**
     * This method asks whether the user want to continue analyze new files.
     * 
     * @param keyboard - Scanner object for reading user input.
     * @return - A boolean value on whether they want to continue
     */
    public static boolean continueAnalyzing(Scanner keyboard) {

        System.out.println("\nDo you want to check another data set?");
        System.out.print("Enter Y or y to analyze another file, anything else to quit: ");
        String answer = (keyboard.nextLine()).toLowerCase();
        boolean wantContinue = (answer.equals("y") || answer.equals("yes"));

        // Checks to see if the player wants to continue and prints an extra line if
        // continued.
        if (wantContinue) {
            System.out.println();
        }

        return wantContinue;
    }
}