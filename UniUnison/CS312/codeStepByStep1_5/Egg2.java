public class Egg2 {
    public static void main(String[] args) {
        normalEgg();
        normalEgg();
        bottomHalfEgg();
        eggSplitInTwo();
    }

    public static void normalEgg() {
        System.out.println("  _______");
        System.out.println(" /       \\");
        System.out.println("/         \\");
        System.out.println("\\         /");
        System.out.println(" \\_______/");
        System.out.println("-\"-'-\"-'-\"-");
    }

    public static void bottomHalfEgg() {
        System.out.println("\\         /");
        System.out.println(" \\_______/");
    }

    public static void eggSplitInTwo() {
        System.out.println("  _______");
        System.out.println(" /       \\");
        System.out.println("/         \\");
        System.out.println("-\"-'-\"-'-\"-");
        System.out.println("\\         /");
        System.out.println(" \\_______/");
    }
}
