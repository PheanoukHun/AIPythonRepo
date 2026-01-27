public class DataDoubleToInt {
    public static void main(String[] args) {
        double areaCircle = 78.53981634d;
        print("Area of Circle of 5 cm radius is " + areaCircle);
        print("Area of Circle of 5 cm radius is " + (int)areaCircle);
    }

    private static void print(String s) {
        System.out.println(s);
    }
}