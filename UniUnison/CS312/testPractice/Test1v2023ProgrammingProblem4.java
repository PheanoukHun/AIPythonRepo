public class Test1v2023ProgrammingProblem4 {
    public static void main(String[] args) {
        System.out.println(reverseDigit(1234));
    }

    public static int reverseDigit(int num) {
        int result = 0;
        int numRepetition = (int) (Math.log10(num) + 1);

        for (int i = 0; i < numRepetition; i++) {
            result += num % 10;
            result *= 10;
            num /= 10;
        }
        
        return result;
    }
}
