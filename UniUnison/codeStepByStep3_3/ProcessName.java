import java.util.Scanner;

public class ProcessName {
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        processName(console);
        console.close();
    }

    public static void processName(Scanner console) {
        System.out.print("Please enter your full name: ");
        String fullName = console.nextLine();
        
        int index = fullName.indexOf(" ");
        String firstName = fullName.substring(0, index);
        String lastName = fullName.substring(index + 1, fullName.length());

        System.out.println("Your name in reverse order is " + lastName + ", " + firstName);
    }
}
