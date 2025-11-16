import java.awt.Color;

public class Vulture extends Bird {
    
    private boolean isHungry = true;
    
    public boolean eat() {
        
        if (isHungry) {
            isHungry = false;
            return true;
        }

        return false;
    }

    public Attack fight(String opponent) {
        isHungry = true;
        return super.fight(opponent);
    }

    public Color getColor() {
        return Color.BLACK;
    }

}
