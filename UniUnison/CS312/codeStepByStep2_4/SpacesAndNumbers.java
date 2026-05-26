public class SpacesAndNumbers {
    public static void main(String[] args) {
        printSpacesAndNumbers();
    }

    public static void printSpacesAndNumbers() {
        int levelsAndSpaces = 5;
        for (int i = 1; i <= levelsAndSpaces; i++) {
            
            for (int j = 0; j < (levelsAndSpaces - i); j++) {
                System.out.print(" ");
            }

            for (int j = 0; j < i; j++) {
                System.out.print(i);
            }

            System.out.println();
        }
    }
}
