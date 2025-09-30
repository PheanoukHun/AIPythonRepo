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
    public static final int SCISSORS = 2;
    public static final int PAPER = 3;

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

    public static String welcome(Scanner console) {
        String name;

        System.out.println("Welcome to ROCK PAPER SCISSORS. I, Computer, will be your opponent.");
        System.out.print("Please type in your name and press return: ");
        name = console.nextLine();

        System.out.println();

        System.out.print("Welcome " + name);

        return name;
    }

    public static int askNumRounds(Scanner console, String name) {
        System.out.println("All right " + name + ". How many rounds would you like to play?");
        System.out.print("Enter the number of rounds you want to play and press return: ");
        int numRounds = console.nextInt();
        return numRounds;
    }

    public static String playRounds(Scanner console, RandomPlayer computer, String name, int numRounds) {

        int playerScore = 0;
        int computerScore = 0;
        int draws = 0;

        for (int i = 1; i <= numRounds; i++) {
            System.out.println("Round " + i);
            char roundResult = playRound(console, computer, name);

            if (roundResult == 'w') {
                playerScore++;
            } else if (roundResult == 'l') {
                computerScore++;
            } else {
                draws++;
            }
        }

        String results = playerScore + " " + computerScore + " " + draws;
        return results;
    }

    public static char playRound(Scanner console, RandomPlayer computer, String name) {

        System.out.println(name + ", please enter your choice for this round.");
        System.out.print("1 for ROCK, 2 for PAPER, and 3 for SCISSORS: ");
        int playerChoice = console.nextInt();
        int computerChoice = computer.getComputerChoice();

        String playerStr = numHandToStringHand(playerChoice);
        String computerStr = numHandToStringHand(computerChoice);
        System.out.println("Computer picked " + computerStr + ", Olivia picked " + playerStr);

        char results = checkPlayerWin(playerChoice, computerChoice);
        return results;
    }

    public static String numHandToStringHand(int num) {
        if (num == ROCK) {
            return "ROCK";
        } else if (num == SCISSORS) {
            return "SCISSORS";
        } else {
            return "PAPER";
        }
    }

    public static char checkPlayerWin(int playerChoice, int computerChoice) {
        if (playerChoice == computerChoice) {
            System.out.println("\nWe picked the same thing! This round is a draw.");
            return 'd';
        }

        if (playerChoice == ROCK && computerChoice == SCISSORS) {
            printRoundWinMessage(playerChoice, " You win.\n");
            return 'w';
        } else if (playerChoice == SCISSORS && playerChoice == PAPER) {
            printRoundWinMessage(playerChoice, "You win.\n");
            return 'w';
        } else if (playerChoice == PAPER && playerChoice == ROCK) {
            printRoundWinMessage(playerChoice, "You win.\n");
            return 'w';
        } else {
            printRoundWinMessage(computerChoice, " I win.\n");
            return 'l';
        }
    }

    public static void printRoundWinMessage(int winner, String lastPhrase) {
        if (winner == ROCK) {
            System.out.println("\nROCK breaks SCISSORS." + lastPhrase);
        } else if (winner == SCISSORS) {
            System.out.println("\nSCISSORS cuts PAPER." + lastPhrase);
        } else {
            System.out.println("\nPAPER covers ROCK." + lastPhrase);
        }
    }

    public static void printResults(String name, int numOfRounds, String gameResults) {
        
        int indexOne = gameResults.indexOf(" ");
        int indexTwo = gameResults.substring(indexOne + 1).indexOf(" ");

        int numWins = Integer.parseInt(gameResults.substring(0, indexOne));
        int numLoses = Integer.parseInt(gameResults.substring(indexOne + 1, indexTwo));
        int numDraws = Integer.parseInt(gameResults.substring(indexTwo + 1, gameResults.length() - 1));

        System.out.println("Number of games of ROCK PAPER SCISSORS: " + numOfRounds);
        System.out.println("Number of times Computer won: " + numWins);
        System.out.println("Number of times " + name + " won: " + numLoses);
        System.out.println("Number of draws: " + numDraws);

        if (numWins > numLoses) {
            System.out.println("You, " + name + ", are the master at ROCK, PAPER, SCISSORS.");
        } else if (numLoses > numWins) {
            System.out.println("I, " + name + ", am a master at ROCK, PAPER, SCISSORS.");
        } else {
            System.out.println("We are evenly matched.");
        }
    }
}
