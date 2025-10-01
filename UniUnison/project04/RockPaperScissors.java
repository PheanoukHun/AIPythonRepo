import java.util.Scanner;

/**
 * CS312 Assignment 4 - Rock, Paper, Scissors
 *
 * On my honor, Pheanouk Hun, this programming assignment is my own work,
 * I have not shared it with others and I will not share it in the future.
 *
 * A program to play Rock Paper Scissors
 *
 * Name: Pheanouk Hun
 * UTEID: ph23434
 *
 */

public class RockPaperScissors {

    /*
     * A program to allow a human player to play rock - paper - scissors
     * against the computer. If args.length != 0 then we assume
     * the first element of args can be converted to an int
     */

    public static final int ROCK = 1;
    public static final int PAPER = 2;
    public static final int SCISSORS = 3;

    public static void main(String[] args) {
        // CS312 Students. Do not change the following line.
        RandomPlayer computerPlayer = buildRandomPlayer(args);

        // CS312 students do no change the following line.
        // Do not create any other Scanners.
        Scanner keyboard = new Scanner(System.in);

        String name = welcome(keyboard);
        int numRounds = askNumRounds(keyboard, name);
        String results = playRounds(keyboard, computerPlayer, name, numRounds);
        printResults(name, numRounds, results);

        keyboard.close();
    }

    /*
     * Build the random player. If args is length 0 then the
     * default RandomPlayer is built that follows a predictable
     * sequence. If args.length > 0 then we assume we can
     * convert the first element to an int and build the
     * RandomPlayer with that initial value.
     */
    public static RandomPlayer buildRandomPlayer(String[] args) {
        if (args.length == 0) {
            return new RandomPlayer();
        } else {
            int seed = Integer.parseInt(args[0]);
            return new RandomPlayer(seed);
        }
    }

    // Purpose: Ask the player for their name and print a welcome message
    // Parameters: console - Scanner object used to read player input
    // Output: Print instructions and welcome messages
    // Return: name - the name entered by the player

    public static String welcome(Scanner console) {

        System.out.println("\nWelcome to ROCK PAPER SCISSORS. I, Computer, will be your opponent.");
        System.out.print("Please type in your name and press return: ");
        String name = console.nextLine();
        System.out.println("\nWelcome " + name + ".");
        return name;
    }

    // Purpose: Ask the player how many rounds they want to play
    // Parameters: console - Scanner object used to read player input
    //             name - the name of the player
    // Output: Print a prompt asking for the number of rounds
    // Return: numRounds - the number of rounds the player chose to play

    public static int askNumRounds(Scanner console, String name) {
        System.out.println("\nAll right " + name + ". How many rounds would you like to play?");
        System.out.print("Enter the number of rounds you want to play and press return: ");
        int numRounds = console.nextInt();
        return numRounds;
    }

    // Purpose: Play the requested number of rounds between the player and computer
    // Parameters: console - Scanner object used to read player input
    //             computer - RandomPlayer object that generates computer choices
    //             name - the name of the player
    //             numRounds - the total number of rounds to play
    // Output: Print the round number and results of each round
    // Return: results - a string with player wins, computer wins, and draws separated by spaces

    public static String playRounds(Scanner console, RandomPlayer computer, String name, int numRounds) {
        int playerScore = 0;
        int computerScore = 0;
        int draws = 0;

        // Loop runs once per round of the game
        for (int i = 1; i <= numRounds; i++) {
            System.out.println("\nRound " + i + ".");
            char roundResult = playRound(console, computer, name);

            // If: player wins the round
            if (roundResult == 'w') {
                playerScore++;
            
            // Else if: computer wins the round
            }else if (roundResult == 'l') {
                computerScore++;
            
            // Else: the round is a draw
            } else {
                draws++;
            }
        }

        String results = playerScore + " " + computerScore + " " + draws;
        return results;
    }

    // Purpose: Play a single round of rock-paper-scissors between the player and computer
    // Parameters: console - Scanner object used to read player input
    //             computer - RandomPlayer object that generates the computer's choice
    //             name - the name of the player
    // Output: Print the player's choice, the computer's choice, and the round outcome
    // Return: result - 'w' if the player wins, 'l' if the computer wins, 'd' if it is a draw

    public static char playRound(Scanner console, RandomPlayer computer, String name) {
        System.out.println(name + ", please enter your choice for this round.");
        System.out.print("1 for ROCK, 2 for PAPER, and 3 for SCISSORS: ");
        int playerChoice = console.nextInt();
        int computerChoice = computer.getComputerChoice();

        String playerStr = numHandToStringHand(playerChoice);
        String computerStr = numHandToStringHand(computerChoice);
        System.out.println("Computer picked " + computerStr + ", " + name + " picked " + playerStr);

        char result = checkPlayerWin(playerChoice, computerChoice);
        return result;
    }

    // Purpose: Convert a numeric choice into the corresponding string representation
    // Parameters: num - the numeric value representing a hand (ROCK, PAPER, SCISSORS)
    // Output: None
    // Return: The string form of the hand ("ROCK", "PAPER", or "SCISSORS")

    public static String numHandToStringHand(int num) {
        
        // If: the number corresponds to ROCK
        if (num == ROCK) {
            return "ROCK";

        // Else if: the number corresponds to SCISSORS
        } else if (num == SCISSORS) {
            return "SCISSORS";

        // Else: the number corresponds to PAPER
        } else {
            return "PAPER";
        }
    }

    // Purpose: Determine the winner of a round of rock-paper-scissors
    // Parameters: playerChoice - the numeric value of the player's hand
    //             computerChoice - the numeric value of the computer's hand
    // Output: Print the round outcome message
    // Return: 'w' if the player wins, 'l' if the computer wins, 'd' if it is a draw

    public static char checkPlayerWin(int playerChoice, int computerChoice) {
        
        // If: both player and computer chose the same hand
        if (playerChoice == computerChoice) {
            System.out.println("\nWe picked the same thing! This round is a draw.");
            return 'd';
        }

        boolean conditionOne = playerChoice == ROCK && computerChoice == SCISSORS;
        boolean conditionTwo = playerChoice == SCISSORS && computerChoice == PAPER;
        boolean conditionThree = playerChoice == PAPER && computerChoice == ROCK;

        // If: the player has a winning hand
        if (conditionOne || conditionTwo || conditionThree) {
            printRoundWinMessage(playerChoice, " You win.");
            return 'w';

        // Else: the computer has a winning hand
        } else {
            printRoundWinMessage(computerChoice, " I win.");
            return 'l';
        }
    }

    // Purpose: Print the message describing how the winning hand defeated the losing hand
    // Parameters: winner - the numeric value of the winning hand
    //             lastPhrase - the phrase appended to the end of the message
    // Output: Print the round outcome explanation
    // Return: None

    public static void printRoundWinMessage(int winner, String lastPhrase) {
        
        // If: The Winning Hand is ROCK
        if (winner == ROCK) {
            System.out.println("\nROCK breaks SCISSORS." + lastPhrase);
        
        // Else if: The Winning Hand is SCISSORS
        } else if (winner == SCISSORS) {
            System.out.println("\nSCISSORS cuts PAPER." + lastPhrase);
        
        // Else: The Winning Hand is PAPER
        } else {
            System.out.println("\nPAPER covers ROCK." + lastPhrase);
        }
    }

    // Purpose: Print the final results of the game including total rounds, wins, losses, and draws
    // Parameters: name - the name of the player
    //             numOfRounds - total number of rounds played
    //             gameResults - string containing the player score, computer score, and draws
    // Output: Print the game summary and declare the overall winner or tie
    // Return: None

    public static void printResults(String name, int numOfRounds, String gameResults) {

        int indexOne = gameResults.indexOf(" ");
        int indexTwo = gameResults.indexOf(" ", indexOne + 1);

        int playerScore = Integer.parseInt(gameResults.substring(0, indexOne));
        int computerScore = Integer.parseInt(gameResults.substring(indexOne + 1, indexTwo));
        int draws = Integer.parseInt(gameResults.substring(indexTwo + 1));

        System.out.println("\nNumber of games of ROCK PAPER SCISSORS: " + numOfRounds);
        System.out.println("Number of times Computer won: " + computerScore);
        System.out.println("Number of times " + name + " won: " + playerScore);
        System.out.println("Number of draws: " + draws);

        // If: player won more rounds than the computer
        if (playerScore > computerScore) {
            System.out.println("You, " + name + ", are the master at ROCK, PAPER, SCISSORS.");

        // Else if: computer won more rounds than the player
        } else if (playerScore < computerScore) {
            System.out.println("I, Computer, am a master at ROCK, PAPER, SCISSORS.");
        
        // Else: player and computer are tied
        } else {
            System.out.println("We are evenly matched.");
        }
    }
}
