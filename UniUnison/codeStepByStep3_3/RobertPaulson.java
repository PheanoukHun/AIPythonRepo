import java.util.Scanner;

public class RobertPaulson {
    public static void main(String[] args) {
        printRobertPaulson();
    }

    public static void printRobertPaulson() {
        Scanner console = new Scanner(System.in);

        System.out.print("What is your phrase? ");
        String repeatedPhrase = console.nextLine();

        System.out.print("How many times should I repeat it? ");
        int numbersRepeated = console.nextInt();

        for (int i = 0; i < numbersRepeated; i++) {
            System.out.println(repeatedPhrase);
        }

        console.close();
    }
}
