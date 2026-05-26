public class OperatorsAndArrays {
    public static void main(String[] args) {
        
        // Integer Arrays Initialization
        int[] arr = {9, 5, 6, 7, 89, 10, 19, 21, 31, 52, 45};

        System.out.println();

        // Addition
        System.out.println("Addition: " + (arr[0] + arr[1]));

        // Subtraction
        System.out.println("Subtraction: " + (arr[2] - arr[3]));

        // Multiplication
        System.out.println("Multiplication: " + (arr[4] * arr[5]));

        // Division
        System.out.println("Division: " + (arr[6] / arr[7]));

        // Modulus
        System.out.println("Modulus: " + (arr[8] % arr[9]));

        System.out.println();

        // Loop Greater Than

        for (int i = 0; i < arr.length - 1; i++) {
            String result = (arr[i] > arr[i + 1]) ? "Greater Than" : (arr[i] < arr[i + 1]) ? "Less Than" : "Equal to";
            System.out.println("The number " + arr[i] + " is " + result + " the number " + arr[i+1]);
        }
    }
}