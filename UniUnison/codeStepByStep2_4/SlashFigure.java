public class SlashFigure {
    public static void main(String[] args) {
        printSlashFigure();
    }

    public static void printSlashFigure() {
        int totalNumberOfSpaces = 22;
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < i; j++) {
                System.out.print("\\\\");
            }

            for (int j = 0; j < (totalNumberOfSpaces - (4 * i)); j++) {
                System.out.print("!");
            }

            for (int j = 0; j < i; j++) {
                System.out.print("//");
            }

            System.out.println();
        }
    }
}
