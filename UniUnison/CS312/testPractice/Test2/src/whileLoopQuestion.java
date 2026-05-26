public class whileLoopQuestion {
    public static void main(String[] args) {
        System.out.println(largerDigits(172, 312));
    }

    public static int largerDigits(int a, int b) {
        int c = 0;
        int power = 0;
        while (a != 0 && b != 0) {
            int aDigit = a % 10;
            int bDigit = b % 10;

            if (aDigit > bDigit) {
                c += aDigit * Math.pow(10, power);
            } else {
                c += bDigit * Math.pow(10, power);;
            }

            power++;
            a /= 10;
            b /= 10;
        }

        return c;
    }
}
