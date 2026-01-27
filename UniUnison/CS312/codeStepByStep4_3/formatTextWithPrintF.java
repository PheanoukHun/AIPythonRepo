public class formatTextWithPrintF {
    public static void main(String[] args) {
        printTemps(70, 101, 100, 103, 2, 8);
    }

    public static void printTemps(int dayOneMin, int dayOneMax, int dayTwoMin, int dayTwoMax, int dayThreeMin, int dayThreeMax) {
        double dayOneAverage = (dayOneMax + dayOneMin) / 2.0;
        double dayTwoAverage =  (dayTwoMax + dayTwoMin) / 2.0;
        double dayThreeAverage =  (dayThreeMax + dayThreeMin) / 2.0;

        System.out.printf("%2s%11s%11s%10s\n", "Day", "Min Temp", "Max Temp", "Average");
        System.out.printf("%3d%11d%11d%10.1f\n", 1, dayOneMin, dayOneMax, dayOneAverage);
        System.out.printf("%3d%11d%11d%10.1f\n", 2, dayTwoMin, dayTwoMax, dayTwoAverage);
        System.out.printf("%3d%11d%11d%10.1f\n", 3, dayThreeMin, dayThreeMax, dayThreeAverage);
    }
}
