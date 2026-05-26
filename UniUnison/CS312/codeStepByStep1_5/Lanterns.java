public class Lanterns {
    public static void main(String[] args) {
        noTopPyramid();
        System.out.println();

        noTopPyramid();
        laternMiddle();
        noTopPyramid();

        System.out.println();
        noTopPyramid();
        laternBottom();

    }

    public static void noTopPyramid() {
        System.out.println("    *****");
        System.out.println("  *********");
        System.out.println("*************");
    }

    public static void laternMiddle() {
        twoStarsAndSixVerticals();
        System.out.println("*************");
    }

    public static void fourStars() {
        System.out.println("    *****");
    }

    public static void twoStarsAndSixVerticals() {
        System.out.println("* | | | | | *");
    }

    public static void laternBottom() {
        fourStars();
        twoStarsAndSixVerticals();
        twoStarsAndSixVerticals();
        fourStars();
        fourStars();
    }
}
