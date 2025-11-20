import java.awt.Color;

public class Bird extends Critter {

    private int moveCount = 0;

    private final Direction[] CLOCKWISE_MOVEMENT = {
            Direction.NORTH, Direction.EAST,
            Direction.SOUTH, Direction.WEST
    };

    private final String[] SYMBOL_DIRECTION = { "^", ">", "V", "<" };

    /**
     * This method takes an opponent string and checks whether the opponent is ant.
     * If the opponent is an Ant, roar; Else pounce.
     * 
     * @return - An Enum Attack Constant to signify the Attack the Critter Wants to
     *         Do.
     */
    public Attack fight(String opponent) {

        // IF: the opponent is an Ant, ROAR
        if (opponent.equals("%")) {
            return Attack.ROAR;
        }

        return Attack.POUNCE;
    }

    /**
     * This methods returns the color of the Bird
     * 
     * @return - Returns the BLUE color Object
     */
    public Color getColor() {
        return Color.BLUE;
    }

    /**
     * This method returns the movement of the Bird based on the current turn.
     * 
     * @return - A direction Enum that represents the direction in which the
     *         ant wants to go.
     */
    public Direction getMove() {
        int currentTurn = (moveCount / 3) % 4;
        moveCount++;
        return CLOCKWISE_MOVEMENT[currentTurn];
    }

    /**
     * This method returns the current version of the bird symbol based on the
     * counter variable.
     * 
     * @return - A string that signifies that object is a bird.
     */
    public String toString() {
        int currentTurn = ((moveCount - 1) / 3) % 4;
        return SYMBOL_DIRECTION[currentTurn];
    }
}
