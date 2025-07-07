// Import Scanner to Read User Input
import java.util.Scanner;

public class AreaCalculator {

    // Private Variable
    private static float pi = 3.142f;

    // Main Method
    public static void main(String[] args) {
        System.out.println("Welcome to the Area Calculator");
        while (true) { 
            
            // Printing Intro Message
            System.out.println("\n Enter 1 to find the Area of: ") ;
            System.out.println(" 1. Circle");
            System.out.println(" 2. Square");
            System.out.println(" 3. Rectangle");

            // Creating the Scanner Class
            Scanner sc = new Scanner(System.in);
            int choice = Integer.parseInt(sc.nextLine());
            
            switch (choice) {
                case 1: {
                    System.out.println("Enter the Radius of the Circle: ");
                    float radius = Float.parseFloat(sc.nextLine());
                    System.out.println("The Area of the Circle with the radius " + radius + " is: " + circle(radius));
                    break;
                }
                case 2: {
                    System.out.println("Enter the Length of the Square: ");
                    float length = Float.parseFloat(sc.nextLine());
                    System.out.println("The Area of the Square with the length " + length + " is: " + square(length));
                    break;
                }
                case 3: {
                    System.out.println("Enter the Width of the Rectangle: ");
                    float width = Float.parseFloat(sc.nextLine());

                    System.out.println("Enter the Height of the Rectangle: ");
                    float height = Float.parseFloat(sc.nextLine());

                    System.out.println("The Area of the Circle with the width " + width + "and height " + height + " is: " + rectangle(width, height));
                    break;
                }
                default: {
                    System.out.println("Invalid Choice");
                    break;
                }
            }
        }
    }

    // Determine the Area of Circle
    private static float circle(float radius) {
        return pi * radius * radius;
    }

    // Determine the Area of a Square
    private static float square(float length) {
        return length * length;
    }

    // Determines the Area of a Rectangle
    private static float rectangle(float length, float width) {
        return length * width;
    }
}
