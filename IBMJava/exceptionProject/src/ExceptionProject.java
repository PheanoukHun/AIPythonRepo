import java.util.Scanner;

public class ExceptionProject {
    public static void main(String[] args) {
        
        // Initializing Scanner
        Scanner sc = new Scanner(System.in);

        // Initializing a 5 Item string Array
        String strArr[] = new String[5];

        // Initializing Integer
        int strIndex= 0;
        int returnIndex;

        // Initializing String
        String userAction;
        String inputString;

        while (true) {
            // The System Instructions.
            System.out.println("\nPress 1 to Add a String.");
            System.out.println("Press 2 to get String from a Particular Index.");
            System.out.println("Press 3 to get the Length of the String in any Index.");
            System.out.println("Press 4 to get all the Strings in the Array.");
            System.out.println("Pressing Any Other Keys Exits the Program");
            System.out.print("Enter your Character Here: ");

            // Getting User Actions
            userAction = sc.nextLine();

            // Determining the Results of the User Action
            if (userAction.equals("1")) {
                if (strIndex == 5) {
                    System.out.println("There are Already 5 Strings in the Array!");
                } else {
                    System.out.print("Enter the String: ");
                    inputString = sc.nextLine();
                    strArr[strIndex++] = inputString;
                }
            } else if (userAction.equals("2")) {
                System.out.print("Enter the Index You Want to Recieve: ");
                try {
                    returnIndex = Integer.parseInt(sc.nextLine());
                    System.out.println("\n" + strArr[returnIndex] + "\n");
                } catch (NumberFormatException e) {
                    System.out.println("Invalid Input! Please enter a Valid Integer.");
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("Invalid Index! Please enter an index between 0 and " + (strArr.length - 1));
                }
            
            } else if (userAction.equals("3")) {
                System.out.print("Enter the Index You Check the Length of: ");
                try {
                    returnIndex = Integer.parseInt(sc.nextLine());
                    System.out.println("\n" + strArr[returnIndex].length());
                } catch (NumberFormatException e) {
                    System.out.println("Invalid Input! Please enter a Valid Integer.");
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("Invalid Index! Please enter an index between 0 and " + (strArr.length - 1));
                } catch (NullPointerException e) {
                    System.out.println("No string exists at the specified Index!");
                }
            
            } else if (userAction.equals("4")) {
                System.out.println();
                for (int i = 0; i < strArr.length; i++) {
                    if (strArr[i] != null) {
                        System.out.println("Index " + i + ": " + strArr[i]);
                    } else {
                        System.out.println("Index " + i + ": (null)");
                    }
                }
            } else {
                System.out.println();
                sc.close();
                break;
            }
        }
    }
}