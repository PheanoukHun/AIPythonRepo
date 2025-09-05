public class Animal {
    private String name;
    private String food;
    
    public Animal(String name) { this.name = name; }

    public void setFood(String food) {this.food = food;}

    public String getFood() { return this.food; }

    public String sound() { return null; }

    public String toString() { return this.name + " says " + this.sound() + " and eats " + this.food; }

}