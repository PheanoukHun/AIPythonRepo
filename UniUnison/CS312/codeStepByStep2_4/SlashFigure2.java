public class SlashFigure2 {

    public static final int SIZE = 7;
    public static void main(String[] args) {
        printSlashFigure(SIZE);
    }

    public static void printSlashFigure(int numOfLevels) {
        int totalNumberOfSpaces = 2 + ((numOfLevels - 1) * 4);

        for (int i = 0; i < numOfLevels; i++) {
            for (int j = 0; j < i; j++) {
                System.out.print("\\\\");
            }

            for (int j=0; j < (totalNumberOfSpaces - (4 * i)); j++) {
                System.out.print("!");
            }

            for (int j = 0; j < i; j++) {
                System.out.print("//");
            }

            System.out.println();
        }
    }
}