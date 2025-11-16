import java.awt.Color;

public class Bird extends Critter {

    private int moveCounter = 0;
    private int stringCounter = 0;

    private int currentDirectionInt = 0;

    private final Direction[] CLOCKWISE_MOVEMENT = {
            Direction.NORTH, Direction.EAST,
            Direction.SOUTH, Direction.WEST
    };

    private final String[] SYMBOL_DIRECTION = { "^", ">", "V", "<" };

    public Attack fight(String opponent) {

        if (opponent.equals("%")) {
            return Attack.ROAR;
        }

        return Attack.POUNCE;
    }

    public Color getColor() {
        return Color.BLUE;
    }

    public Direction getMove() {
        int currentTurn = (moveCounter % 12) / 3;
        moveCounter++;
        return CLOCKWISE_MOVEMENT[currentTurn];
    }

    public String toString() {
        int currentTurn = (stringCounter % 12) / 3;
        stringCounter++;
        return SYMBOL_DIRECTION[currentTurn];
    }
}
