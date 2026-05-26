import java.util.Scanner;

public class SumNumbers {
    public static void main(String[] args) {
        printSumNumbers();
    }

    public static void printSumNumbers() {
        Scanner console = new Scanner(System.in);
        
        System.out.print("low? ");
        int low = console.nextInt();

        System.out.print("high? ");
        int high = console.nextInt();

        int sum = 0;
        for (int i = low; i <= high; i++) {
            sum += i;
        }

        System.out.println("sum = " + sum);
        console.close();
    }
}
