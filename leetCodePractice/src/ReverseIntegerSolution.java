public class ReverseIntegerSolution {
    public static void main(String[] args) {
        System.out.println(reverse(-1534236469));
    }

    public static int reverse(int x) {

        if (x < 0) {
            return -reverse(-x);
        } else if (x < 10) {
            return x;
        }

        int result = 0, digit = 0;
        while (x >= 1) {

            digit = x % 10;
            x /= 10;

            if (result > Integer.MAX_VALUE / 10 || (result == Integer.MAX_VALUE / 10 && digit > 7)) {
                return 0;
            }

            result = (result * 10) + digit;
        }

        if (result > Integer.MAX_VALUE - 1) {
            return 0;
        }

        return result;
    }
}