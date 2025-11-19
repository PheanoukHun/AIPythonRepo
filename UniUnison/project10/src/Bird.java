import java.awt.Color;

public class Bird extends Critter {

    private int moveCount = 0;

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
        int currentTurn = ((moveCount - 1) / 3) % 4;
        moveCount++;
        return CLOCKWISE_MOVEMENT[currentTurn];
    }

    public String toString() {
        int currentTurn = ((moveCount - 1) / 3) % 4;
        return SYMBOL_DIRECTION[currentTurn];
    }
}
