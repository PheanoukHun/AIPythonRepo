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

    private final String[] BIRD_SYMBOLS = { "^", ">", "V", "<" };

    public Longhorn() {
        wantMate = true;
        getNewDirection();
    }

    public boolean eat() {

        for (Direction direction : DIRECTIONS) {
            String currentNeighbor = getNeighbor(direction);
            if (!currentNeighbor.equals(" ") && !currentNeighbor.equals("%")) {
                return false;
            }
        }

        return true;
    }

    /**
     * A method that takes an opponent string that checks what kind of critter it is
     * and return one of the most optimal attack against the opponent critter.
     * 
     * @param opponent - A string that has one a length of one that represents the
     *                 opponent the critter has to fight against.
     * 
     * @return - A Enum Attack Constant to signify the Attack the Critter Wants to
     *         Do.
     */
    public Attack fight(String opponent) {

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

    public Color getColor() {
        return Color.ORANGE;
    }

    public Direction getMove() {

        for (Direction direction : DIRECTIONS) {
            String currrentNeighbor = getNeighbor(direction);

            boolean isFoodNear = currrentNeighbor.equals(".");

            boolean nearMate = currrentNeighbor.equals("Y");

            if (nearMate && wantMate) {
                wantMate = false;
                getNewDirection();
                return direction;
            } else if (isFoodNear) {
                return direction;
            }
        }

        counter++;
        if (counter == randNumMoves) {
            getNewDirection();
        }

        return currDirection;
    }

    private void getNewDirection() {

        randNumMoves = (int) (Math.random() * 4) + 3;
        counter = 0;

        int randomNum = (int) (Math.random() * DIRECTIONS.length);
        Direction next = DIRECTIONS[randomNum];

        while (next == currDirection) {
            randomNum = (int) (Math.random() * DIRECTIONS.length);
            next = DIRECTIONS[randomNum];
        }

        currDirection = next;
    }

    public String toString() {
        return "Y";
    }
}
