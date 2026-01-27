public class EggStop {
    
    public static void main(String[] args) {
        hexagon();

        System.out.println();

        hexagon();
        line();

        System.out.println();

        stopSign();
        line();
    }

    public static void hexagon() {
        System.out.println("  ______");
        System.out.println(" /      \\");
        System.out.println("/        \\");
        System.out.println("\\        /");
        System.out.println(" \\______/");
    }

    public static void line() {
        System.out.println("+--------+");
    }

    public static void stopSign() {
        System.out.println("  ______");
        System.out.println(" /      \\");
        System.out.println("/        \\");
        System.out.println("|  STOP  |");
        System.out.println("\\        /");
        System.out.println(" \\______/");
    }
}