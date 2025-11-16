import java.awt.Color;

public class Ant extends Critter {
    
    private boolean moveSouth;
    private int counter = -1;
    
    public Ant(boolean walkSouth) {
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
        counter++;
        boolean isEven = counter % 2 == 0;
        
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
