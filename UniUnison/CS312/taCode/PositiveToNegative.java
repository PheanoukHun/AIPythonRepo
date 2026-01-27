public class PositiveToNegative {
    
    public static final int SIZE = 4;

    public static void main(String[] args) {
        displayFigure();
    }

    public static void displayFigure() {
        for (int i = 0; i < SIZE; i++) {
            int numPlus = SIZE - i;
            int numDash = (2 * SIZE) + i;

            for (int j = 0; j < numPlus; j++) {
                System.out.print("+");
            }

            for (int j = 0; j < numDash; j++) {
                System.out.print("-");
            }

            System.out.println();
        }
    }
}

// Size: 3
// +++------
// ++-------
// +--------

// Size: 4
// ++++--------
// +++---------
// ++----------
// +-----------