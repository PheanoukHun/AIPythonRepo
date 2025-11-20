import java.lang.Math;
import java.awt.Color;

public class Longhorn extends Critter {

    private final Direction[] DIRECTIONS = {
            Direction.NORTH, Direction.EAST,
            Direction.SOUTH, Direction.WEST
    };

    private Direction currDirection;

    private int randNumMoves;
    private int counter;

    private boolean wantMate;
    private boolean hungry;

    private final String[] BIRD_SYMBOLS = { "^", ">", "V", "<" };

    /**
     * A Constructor that Initializes the Longhorn Critter Object.
     */
    public Longhorn() {
        wantMate = true;
        hungry = true;
        getNewDirection();
    }

    /**
     * A Method that asks the Longhorn whether it wants to eat and it decides based
     * on the neighbor is.
     * 
     * @return - A boolean value that tells whether the Critter wants to eat or not.
     */
    public boolean eat() {

        // Rns once per direction to check.
        for (Direction direction : DIRECTIONS) {
            String currentNeighbor = getNeighbor(direction);

            // IF: the Neighbor is not Empty and not an Ant, don't eat
            if (!currentNeighbor.equals(" ") && currentNeighbor.equals("%")) {
                return false;
            }
        }

        hungry = false;
        return true;
    }

    /**
     * A method that takes an opponent string that checks what kind of critter it is
     * and return one of the most optimal attack against the opponent critter.
     * 
     * @param opponent - A string that has one a length of one that represents the
     *                 opponent the critter has to fight against.
     * 
     * @return - An Enum Attack Constant to signify the Attack the Critter Wants to
     *         Do.
     */
    public Attack fight(String opponent) {

        hungry = true;

        // IF: Opponent is an Ant
        if (opponent.equals("%")) {
            return Attack.ROAR;
        }

        // Runs once per Bird Symbol
        for (String symbol : BIRD_SYMBOLS) {

            // IF: Opponent is a Bird or Vulture
            if (opponent.equals(symbol)) {
                return Attack.SCRATCH;
            }
        }

        // IF: Opponent is a non-Hungry Hippo
        if (opponent.equals("0")) {
            return Attack.SCRATCH;
        }

        // IF: Opponent is a Hungry Hippo
        if (Character.isDigit(opponent.charAt(0))) {
            return Attack.ROAR;
        }

        return Attack.POUNCE;
    }

    /**
     * A method used to return the color of the Critter
     * 
     * @return - Returns the Color Orange.
     */
    public Color getColor() {
        return Color.ORANGE;
    }

    /**
     * This method gets the direction in which the Longhorn wants to move based on
     * whether the Longhorn is hungry or wants to mate.
     * 
     * @return - A direction Enum that represents the direction in which the
     *         longhorn wants to go.
     */
    public Direction getMove() {

        // Runs Once Per Direction in the Directions Array
        for (Direction direction : DIRECTIONS) {
            String currrentNeighbor = getNeighbor(direction);

            boolean isFoodNear = currrentNeighbor.equals(".");

            boolean nearMate = currrentNeighbor.equals("Y");

            // IF: There is a Mate Nearby and Longhorn Wants to mate
            if (nearMate && wantMate) {
                getNewDirection();
                return direction;

                // ELSE IF: There is food nearby and Longhorn is Hungry.
            } else if (isFoodNear && hungry) {
                getNewDirection();
                return direction;
            }
        }

        counter++;

        // IF: It is time to get a new direction
        if (counter == randNumMoves) {
            getNewDirection();
        }

        return currDirection;
    }

    /**
     * A private method that resets the randomNumberCounter and Counter fields and
     * get the longhorn to move to a new direction.
     */
    private void getNewDirection() {

        randNumMoves = (int) (Math.random() * 4) + 3;
        counter = 0;

        int randomNum = (int) (Math.random() * DIRECTIONS.length);
        Direction next = DIRECTIONS[randomNum];

        // Loop Runs until a next direction is assigned
        while (next == currDirection) {
            randomNum = (int) (Math.random() * DIRECTIONS.length);
            next = DIRECTIONS[randomNum];
        }

        currDirection = next;
    }

    /**
     * This method returns the String Version of the Object
     * 
     * @return - A string that is just simply Y because it kinda looks like a
     *         longhorn.
     */
    public String toString() {
        return "Y";
    }

    /**
     * This method signifies the end of mating and sets the behavioral fields of
     * longhorn.
     */
    public void mateEnd() {
        hungry = true;
        wantMate = false;
    }
}
