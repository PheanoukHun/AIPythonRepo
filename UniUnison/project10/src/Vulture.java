import java.awt.Color;

public class Vulture extends Bird {

    private boolean isHungry = true;

    /**
     * A Method that asks the Vulture whether it wants to eat and returns the
     * boolean value of that.
     * 
     * @return - A boolean value that tells whether the Critter wants to eat or not.
     */
    public boolean eat() {

        // IF: the Vulture is Hungry
        if (isHungry) {
            isHungry = false;
            return true;
        }

        return false;
    }

    /**
     * This method takes an opponent string and checks whether the opponent is ant.
     * If the opponent is an Ant, roar; Else pounce. After which, the vulture is
     * hungry again.
     * 
     * @return - An Enum Attack Constant to signify the Attack the Critter Wants to
     *         Do.
     */
    public Attack fight(String opponent) {
        isHungry = true;
        return super.fight(opponent);
    }

    /**
     * This methods returns the color of the Vulture
     * 
     * @return - Returns the BLACK color Object
     */
    public Color getColor() {
        return Color.BLACK;
    }

}
