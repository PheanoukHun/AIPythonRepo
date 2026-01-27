public class Displacement {
    public static void main(String[] args) {
        double s_0 = 12.0;
        double v_0  = 3.5;
        double a = 9.8;
        double t = 10;

        double s = s_0 + v_0 * t + 0.5 * a * Math.pow(t, 2);
        System.out.println(s);
    }
}
