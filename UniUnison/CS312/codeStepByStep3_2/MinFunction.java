
public class MinFunction {
    public static void main(String[] args) {
        System.out.println(min(3, -2, 7));
    }

    public static int min(int one, int two, int three) {
        int smaller = Math.min(one, two);
        return Math.min(smaller, three);
    }
}
