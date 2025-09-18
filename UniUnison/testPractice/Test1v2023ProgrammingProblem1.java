public class Test1v2023ProgrammingProblem1 {
    public static void main(String[] args) {
        printFibs(5);
    }

    public static void printFibs(int n) {
        int a = 0;
        int b = 1;
        System.out.print(a + " " + b + " ");
        for (int i = 2; i < n; i++) {
            int sum = a + b;
            System.out.print(sum + " ");
            a = b;
            b = sum;
        }
    }
}
