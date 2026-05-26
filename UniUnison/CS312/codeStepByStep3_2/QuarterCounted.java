public class QuarterCounted {
    public static void main(String[] args) {
        System.out.println(countQuarters(164));
    }

    public static int countQuarters(int cents) {
        return ((cents % 100) / 25);
    }
}