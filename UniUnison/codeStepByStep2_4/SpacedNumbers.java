/*
    1
   2
  3
 4
5
 */

public class SpacedNumbers {
    public static void main(String[] args) {
        printSpacedNumbers(5);
    }

    public static void printSpacedNumbers (int levels) {
        for (int i = 1; i <= levels; i++) {
            for (int j = 0; j < (levels - i); j++) {
                System.out.print(" ");
            }

            System.out.print(i);
            System.out.println();
        }
    }
}