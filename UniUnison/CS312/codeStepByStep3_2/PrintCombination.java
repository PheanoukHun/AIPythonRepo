public class PrintCombination {
    public static void main(String[] args) {
        printSquare(0, 3);
        System.out.println();
        printSquare(5, 5);
        System.out.println();
        printSquare(3, 9);
    }

    public static void printSquare(int min, int max) {
        
        int count = 0;
        for (int i = min; i <= max; i++) {
            
            for (int j = i; j <= max; j++) {
                System.out.print(j);
            }

            for (int j = min; j < count + min; j++) {
                System.out.print(j);
            }

            count++;
            System.out.println();
        }
    }
}
