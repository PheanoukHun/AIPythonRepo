
/**
 * CS312 Assignment 10 - Critters - Ant
 *
 * On my honor, Pheanouk Hun, this programming assignment is my own work, 
 * I have not shared it with others and I will not share it in the future.
 *
 *  Description: This class creates a critter object modeled after the Ant Animal. 
 *               It has a set movement structure, attack, and eating behavior.
 *               It extends from the Critters class.
 * 
 *  Name: Pheanouk Hun
 *  UT EID: ph23434
 * 
 */

import java.awt.Color;

public class Ant extends Critter {

    private boolean moveSouth;
    private int counter;

    /**
     * A Constructor that Initializes the Ant Critter Object.
     * 
     * @param walkSouth - A boolean variable that decides whether the Ant should
     *                  move North or South.
     */
    public Ant(boolean walkSouth) {
        counter = 0;
        moveSouth = walkSouth;
    }

    /**
     * This method tells the program that the Ant will always Eat.
     * 
     * @return - Always returns true.
     */
    public boolean eat() {
        return true;
    }

    /**
     * This method takes an opponent string and returns the Scratch Attack.
     * 
     * @return - An Enum Attack Constant to signify the Attack the Critter Wants to
     *         Do.
     */
    public Attack fight(String opponent) {
        return Attack.SCRATCH;
    }

    /**
     * This methods returns the color of the Ant
     * 
     * @return - Returns the RED color Object
     */
    public Color getColor() {
        return Color.RED;
    }

    /**
     * This method returns the movement of the Ant based on the current turn and
     * whether it wants to move North or South.
     * 
     * @return - A direction Enum that represents the direction in which the
     *         ant wants to go.
     */
    public Direction getMove() {
        boolean isEven = counter % 2 == 0;
        counter++;

        // IF: the turn is Even and the Ant wants to Go South
        if (moveSouth && isEven) {
            return Direction.SOUTH;

            // ELSE IF: The Ant wants to go North
        } else if (isEven) {
            return Direction.NORTH;
        }

        return Direction.EAST;
    }

    /**
     * This method returns the String Version of the Ant.
     * 
     * @return - A string that is just simply % because it kinda looks like an
     *         Ant.
     */
    public String toString() {
        return "%";
    }
}
