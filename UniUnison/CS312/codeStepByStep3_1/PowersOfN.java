public class PowersOfN {
    public static void main(String[] args) {
        printPowersOfN(4, 3);
        printPowersOfN(5, 6);
        printPowersOfN(-2, 8);
    }

    public static void printPowersOfN(int base, int exponents) {
        int result = 1;
        for (int i = 0; i <= exponents; i++) {
            System.out.print(result + " ");
            result *= base;
        }
        System.out.println();
    }
}
