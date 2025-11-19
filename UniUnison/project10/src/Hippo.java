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
    
    public Hippo (int hunger) {
        this.hunger = hunger;
        resetDirection();
    }

    public boolean eat() {
        
        if (hunger > 0) {
            hunger--;
            return true;
        }

        return false;
    }

    public Attack fight(String opp) {
        
        if (hunger > 0) {
            return Attack.SCRATCH;
        }

        return Attack.POUNCE;
    }

    public Color getColor() {
        
        if (hunger > 0) {
            return Color.GRAY;
        }

        return Color.WHITE;
    }

    public Direction getMove() {
        
        if (stepCounter == DIRECTION_CHANGE) {
            resetDirection();
        }

        stepCounter++;
        return currDirection;
    }

    private void resetDirection() {

        stepCounter = 0;
        int randomInt = (int) (Math.random() * DIRECTIONS.length);
        Direction next = DIRECTIONS[randomInt];

        while (next == currDirection) {
            randomInt = (int) (Math.random() * DIRECTIONS.length);
            next = DIRECTIONS[randomInt];
        }

        currDirection = next;
    }

    public String toString() {
        return ("" + hunger);
    }
}
