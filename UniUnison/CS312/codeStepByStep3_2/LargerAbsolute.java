public class LargerAbsolute {
    public static void main(String[] args) {
        System.out.println(largerAbsVal(11, 2));
    }

    public static int largerAbsVal(int valOne, int valTwo) {
        return Math.max(Math.abs(valOne), Math.abs(valTwo));
    }
}
