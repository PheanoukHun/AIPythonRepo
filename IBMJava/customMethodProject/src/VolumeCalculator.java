// Importing the Scanner Library
import java.util.Scanner;

public class VolumeCalculator {

    // Private Class Variable
    private static float pi = 3.142f;

    public static void main(String[] args) {
        
        // Message Introduction
        System.out.println("\nWelcome to the Volume Calculator!");
        Scanner sc = new Scanner(System.in);
        int choice;

        while (true) {

            // Showing Choices
            System.out.println("Enter a Number to find the Volume of: ");
            System.out.println(" 1. Sphere");
            System.out.println(" 2. Cube");
            System.out.println(" 3. Rectangular Prism");
            System.out.print("Put your Input Here: ");

            // Reading User Input
            choice = Integer.parseInt(sc.nextLine());

            System.out.println();

            // Deciding Outputs

            switch (choice) {
                
                // Sphere Decision
                case 1: {
                    System.out.print("Enter the Radius of the Sphere: ");
                    float radius = Float.parseFloat(sc.nextLine());
                    System.out.println("\nThe Volume of a Sphere with a radius of " + radius + " is: " + sphere(radius));
                    break;
                }

                // Cube Decision
                case 2: {
                    System.out.print("Enter the Length of the Cube: ");
                    float length = Float.parseFloat(sc.nextLine());
                    System.out.println("\nThe Volume of a Cube with a length of " + length + " is: " + cube(length));
                    break;
                }

                // Rectangular Prism Decision
                case 3: {
                    System.out.print("Enter the Width of the Rectangular Prism: ");
                    float width = Float.parseFloat(sc.nextLine());

                    System.out.print("Enter the Length of the Rectangular Prism: ");
                    float length = Float.parseFloat(sc.nextLine());

                    System.out.print("Enter the Height of the Rectangular Prism: ");
                    float height = Float.parseFloat(sc.nextLine());

                    System.out.println("\nThe Volume of a Rectangular Prism with a length of " + length + ", width of " + width + ", and height of " + height + " is: " + rectPrism(width, height, length)); 
                    break;
                }

                // Not a Choice
                default: {
                    System.out.println("Invalid Choice");
                    break;
                }
            }

            System.out.println();
        }
    }

    private static float sphere(float radius) {
        return (4 * radius * radius * radius * pi / 3);
    }

    private static float cube(float length) {
        return length * length * length;
    }

    private static float rectPrism(float width, float height, float length) {
        return length * width * height;
    }
}