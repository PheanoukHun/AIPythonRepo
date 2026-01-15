public class QuadraticFormula {
    public static void main(String[] args) {
        quadratic(1, -7, 12);
    }

    public static void quadratic(int a, int b, int c) {
        double rootOne = (-b + Math.sqrt(Math.pow(b, 2) - (4 * a * c))) / (2 * a);
        double rootTwo = (-b - Math.sqrt(Math.pow(b, 2) - (4 * a * c))) / (2 * a);
        System.out.println("First root = " + rootOne);
        System.out.println("Second root = " + rootTwo);
    }
}