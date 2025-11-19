import java.awt.Color;

public class Ant extends Critter {
    
    private boolean moveSouth;
    private int counter;
    
    public Ant(boolean walkSouth) {
        counter = 0;
        moveSouth = walkSouth;
    }

    public boolean eat() {
        return true;
    }

    public Attack fight(String opponent) {
        return Attack.SCRATCH;
    }

    public Color getColor() {
        return Color.RED;
    }

    public Direction getMove() {
        boolean isEven = counter % 2 == 0;
        counter++;
        
        if (moveSouth && isEven) {
            return Direction.SOUTH;
        } else if (isEven) {
            return Direction.NORTH;
        }

        return Direction.EAST;
    }

    public String toString() {
        return "%";
    }
}
