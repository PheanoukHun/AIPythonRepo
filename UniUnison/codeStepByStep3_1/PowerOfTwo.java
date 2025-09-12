public class PowerOfTwo {
    public static void main(String[] args) {
        printPowersOf2(3);
        printPowersOf2(10);
    }

    public static void printPowersOf2(int num) {
        
        int result = 1;
        
        for (int i = 0; i <= num; i++) {
            System.out.print(result + " ");
            result *= 2;
        }

        System.out.println();
    }
}