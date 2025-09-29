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

    /* A program to allow a human player to play rock - paper - scissors
     * against the computer. If args.length != 0 then we assume
     * the first element of args can be converted to an int
     */
    public static void main(String[] args) {
        // CS312 Students. Do not change the following line.
        RandomPlayer computerPlayer = buildRandomPlayer(args);

        // CS312 students do no change the following line. 
        // Do not create any other Scanners.
        Scanner keyboard = new Scanner(System.in);

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
        if(args.length == 0) {
            return new RandomPlayer();
        } else {
            int seed = Integer.parseInt(args[0]);
            return new RandomPlayer(seed);
    }
}
