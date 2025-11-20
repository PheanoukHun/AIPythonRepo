import java.awt.Color;
import java.lang.Math;

public class Hippo extends Critter {

    private int hunger;

    private final Direction[] DIRECTIONS = {
            Direction.NORTH, Direction.SOUTH,
            Direction.EAST, Direction.WEST
    };

    private Direction currDirection;
    private final int DIRECTION_CHANGE = 5;

    private int stepCounter = 0;

    /**
     * A constructor for the Hippo class that takes in the hunger level of hippo and
     * assigns it a direction.
     * 
     * @param hunger - An int value that represents how hungry the hippo is.
     */
    public Hippo(int hunger) {
        this.hunger = hunger;
        resetDirection();
    }

    /**
     * A Method that asks the Hippo is hungry, and if it is subtract one from the
     * hunger level and return true, else returns false.
     * 
     * @return - A boolean value that tells whether the Critter wants to eat or not.
     */
    public boolean eat() {

        // IF: Hunger level is greater than 0
        if (hunger > 0) {
            hunger--;
            return true;
        }

        return false;
    }

    /**
     * This method takes an opponent string and Scratches if the Hippo is still
     * hungry, but pounces otherwise.
     * 
     * @return - An Enum Attack Constant to signify the Attack the Critter Wants to
     *         Do.
     */
    public Attack fight(String opp) {

        // IF: Hunger is greater than 0.
        if (hunger > 0) {
            return Attack.SCRATCH;
        }

        return Attack.POUNCE;
    }

    /**
     * This methods returns the color of the Hippo based on its hunger level. If it
     * is still hungry, return gray. Otherwise return white.
     * 
     * @return - Returns the color Object depending on the Hippo's hunger level.
     */
    public Color getColor() {

        // IF: Hunger is greater than 0.
        if (hunger > 0) {
            return Color.GRAY;
        }

        return Color.WHITE;
    }

    /**
     * This method returns the movement of the Hippo. It moves in the same direction
     * 5 times and gets a new direction.
     * 
     * @return - A direction Enum that represents the direction in which the
     *         ant wants to go.
     */
    public Direction getMove() {

        // IF: It is time to change Direction (After moving in the same direction 5
        // times).
        if (stepCounter == DIRECTION_CHANGE) {
            resetDirection();
        }

        stepCounter++;
        return currDirection;
    }

    /**
     * A private method that resets the randomNumberCounter and Counter fields and
     * get the longhorn to move to a new direction.
     */
    private void resetDirection() {

        stepCounter = 0;
        int randomInt = (int) (Math.random() * DIRECTIONS.length);
        Direction next = DIRECTIONS[randomInt];

        // Loop Runs until a next direction is assigned.
        while (next == currDirection) {
            randomInt = (int) (Math.random() * DIRECTIONS.length);
            next = DIRECTIONS[randomInt];
        }

        currDirection = next;
    }

    /**
     * This method returns the current symbol of the hippo based on its
     * hunger-level.
     * 
     * @return - A string that signifies that object is a Hippo.
     */
    public String toString() {
        return ("" + hunger);
    }
}
