import java.util.Scanner;

/*
What is your name? Fifty Cent
Fifty Fifty, bo-Bifty
Banana-fana fo-Fifty
Fee-fi-mo-Mifty
FIFTY!

Cent Cent, bo-Bent
Banana-fana fo-Fent
Fee-fi-mo-Ment
CENT! 
 */

public class TheNameGame {
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        getRhymn(console);
        console.close();
    }

    public static String getFirstName(String fullName) {
        int spaceIndex = fullName.indexOf(" ");
        return fullName.substring(0, spaceIndex);
    }

    public static String getLastName(String fullName) {
        int spaceIndex = fullName.indexOf(" ");
        return fullName.substring(spaceIndex + 1, fullName.length());
    }

    public static void getRhymn(Scanner console) {
        System.out.print("What is your name? ");
        String fullName = console.nextLine();

        String firstName = getFirstName(fullName);
        String lastName = getLastName(fullName);

        printRhymnScheme(firstName);
        System.out.println();
        printRhymnScheme(lastName);
    }

    public static void printRhymnScheme(String word) {
        System.out.println(word + " " + word + ", bo-B" + word.substring(1));
        System.out.println("Banana-fana fo-F" + word.substring(1));
        System.out.println("Fee-fi-mo-M" + word.substring(1));
        System.out.println(word.toUpperCase() + "!");
    }
}
