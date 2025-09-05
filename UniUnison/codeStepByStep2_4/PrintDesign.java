/*
 * -----1-----
 * ----333----
 * ---55555---
 * --7777777--
 * -999999999-
 */

public class PrintDesign {
    public static void main(String[] args) {
        
        System.out.println();
        printDesign();
        System.out.println();
    }

    public static void printDesign () {
        int numOfLevels = 5;
        int numOfSpaces = ((numOfLevels * 2) - 1) + 2;

        for (int i = 1; i <= (numOfSpaces - 2); i += 2) {
            int numOfDashes = (numOfSpaces - i) / 2;
            
            for (int j = 0; j < numOfDashes; j++) {
                System.out.print("-");
            }

            for (int j = 0; j < i; j++) {
                System.out.print(i);
            }

            for (int j = 0; j < numOfDashes; j++) {
                System.out.print("-");
            }

            System.out.println();
        }
    }
}