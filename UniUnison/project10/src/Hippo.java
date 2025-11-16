import java.awt.Color;
import java.lang.Math;

public class Hippo extends Critter {
    
    private int hunger;
    private boolean isHungry;
    
    private final Direction[] DIRECTIONS = {
        Direction.NORTH, Direction.SOUTH,
        Direction.EAST, Direction.WEST
    };

    private Direction currDirection;
    private final int DIRECTION_CHANGE = 4;
    
    private int stepCounter = 0;
    
    public Hippo (int hunger) {
        this.hunger = hunger;
        if (hunger > 0) {
            isHungry = true;
        }
    }

    public boolean eat() {
        
        if (hunger > 0) {
            hunger--;
            isHungry = true;
            return isHungry;
        }

        isHungry = false;
        return isHungry;
    }

    public Attack fight(String opp) {
        
        if (isHungry) {
            return Attack.SCRATCH;
        }

        return Attack.POUNCE;
    }

    public Color getColor() {
        
        if (isHungry) {
            return Color.GRAY;
        }

        return Color.WHITE;
    }

    public Direction getMove() {
        
        if (stepCounter == 0) {
            int randomInt = (int) (Math.random() * 4);
            currDirection = DIRECTIONS[randomInt];
            return currDirection;
        }

        stepCounter++;
        if (stepCounter == DIRECTION_CHANGE) {
            stepCounter = 0;
        }
                
        return currDirection;
    }

    public String toString() {
        return ("" + hunger);
    }
}
