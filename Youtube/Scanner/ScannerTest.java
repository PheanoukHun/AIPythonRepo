import java.util.Scanner;

public class ScannerTest {
    public static void main(String[] args){
        Scanner scanInput = new Scanner(System.in);
        System.out.println("What is your age?");

        String myString = scanInput.nextLine();
        scanInput.close();
        System.out.println("You are " + myString + " years old.");
    }
}
