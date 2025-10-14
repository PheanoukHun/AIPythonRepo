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
 *  
 */

//  Data from 14 input files and analysis of Home Field Advantate go here. 

public class HomeField {

    // Ask the user for the name of a data file and process
    // it until they want to quit.
    public static void main(String[] args) throws IOException {
        System.out.println("A program to analyze home field advantage in sports.");
        System.out.println();
        // CS312 students. Do not create any other Scanners connected to System.in.
        // Pass keyboard as a parameter to all the methods that need it.
        Scanner keyboard = new Scanner(System.in);

        String again;

        do {
            File validFile = getValidFile(keyboard);
            analyzeData(validFile);

            System.out.println("Do you want to check another data set?");
            System.out.print("Enter Y or y to analyze another file, anything else to quit: ");
            again = (keyboard.nextLine()).toLowerCase();
            System.out.println();
        } while (again.equals("y") || again.equals("yes"));

        keyboard.close();
    }

    // CS312 Students - Add your methods here.

    /**
     * This functions uses a keyboard scanner to get a valid file from the user.
     * 
     * @param keyboard - Scanner object for reading user input.
     * @return A valid input File object with the file the user wants to analyze.
     */
    public static File getValidFile(Scanner keyboard) {
        System.out.print("Enter the file name: ");
        String userFile = keyboard.nextLine();
        File validFile = new File(userFile);

        // This loop runs until a valid file name is entered into the Scanner.
        while (!validFile.exists()) {
            System.out.println("Sorry, that file does not exist");
            System.out.print("Enter the file name: ");
            userFile = keyboard.nextLine();
            validFile = new File(userFile);
        }

        return validFile;
    }

    /**
     * 
     * @param dataFile
     */
    public static void analyzeData(File dataFile) throws FileNotFoundException {
        Scanner fileSc = new Scanner(dataFile);

        String title = fileSc.nextLine();
        String years = fileSc.nextLine();

        int numGames = 0, homeGame = 0, homeGameWon = 0;
        double homeMargin = 0.0, homePercent, homeWinsPercent;

        String game;
        while (fileSc.hasNextLine()) {
            game = fileSc.nextLine();
            numGames++;
            if (game.contains("@")) {
                homeGame++;

                int marginScore = determineHomeTeamWin(game);
                if (marginScore > 0) {
                    homeGameWon++;
                }

                homeMargin += marginScore;
            }
        }

        fileSc.close();

        homeMargin /= homeGame;
        homePercent = ((double) homeGame / numGames) * 100;
        homeWinsPercent = ((double) homeGameWon / homeGame) * 100;

        printResults(title, years, numGames, homeGame, homePercent, homeGameWon, homeWinsPercent, homeMargin);
    }

    /**
     * 
     * @param game
     * @return
     */
    public static int determineHomeTeamWin(String game) {
        int scoreOne = 0, scoreTwo = 0;

        Scanner lineSc = new Scanner(game);
        lineSc.next();

        boolean isTeamOneHome = (lineSc.next()).charAt(0) == '@';

        while (!lineSc.hasNextInt()) {
            lineSc.next();
        }
        scoreOne = lineSc.nextInt();

        while (!lineSc.hasNextInt()) {
            lineSc.next();
        }
        scoreTwo = lineSc.nextInt();

        lineSc.close();

        if (isTeamOneHome) {
            return (scoreOne - scoreTwo);
        }

        return (scoreTwo - scoreOne);
    }

    /**
     * 
     * @param results
     * @return - void
     */
    public static void printResults(String title, String years, int numGames, int homeGame,
            double homePercent, int homeGameWon, double homeWinPercent, double homeMargin) {
        System.out.println("\n**********   " + title + " --- " + years + "   **********");

        System.out.println("\nHOME FIELD ADVANTAGE RESULTS\n");
        System.out.printf("%s%,d\n", "Total number of games: ", numGames);
        System.out.printf("%s%,d\n", "Number of games with a home team: ", homeGame);

        System.out.printf("%s%.1f%%\n", "Percentage of games with a home team: ",
                homePercent);

        System.out.printf("%s%,d\n", "Number of games the home team won: ", homeGameWon);
        System.out.printf("%s%.1f%%\n", "Home team win percentage: ", homeWinPercent);

        System.out.printf("%s%.2f\n\n", "Home team average margin: ", homeMargin);
    }
}
