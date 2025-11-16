import java.util.Arrays;

// CSE 142 Critters
// Authors: Marty Stepp, Stuart Reges, Mike Scott
//
// A small testing program with a main method to test your animals.
// Most of your testing should be done in CritterMain.java, but this smaller
// file can help you to test simpler behavior or see a bit about how
// the CritterMain uses your animals.
// This program does NOT test all aspects of your animals' behavior; you should
// perform your own testing to make sure your animals work properly.
//
// If you have not finished all of the animals yet, you can comment out the
// code for the animals you have not written.
//
// YOU DON'T NEED TO EDIT THIS FILE FOR YOUR ASSIGNMENT (but you may if you like).
//

public class MiniMain {
    public static void main(String[] args) {
        test1();
        test2();
        birdToStringTest();
    }

    // tests toString of Bird
    private static void birdToStringTest() {
        String expected = "^^^^>>>VVV<<<^^^>>>VVV<<<^";
        String actual = "";
        Bird b = new Bird();
        actual += b.toString();
        final int LIMIT = expected.length() - 1;
        for (int i = 0; i < LIMIT; i++) {
            b.getMove();
            actual += b.toString();
        }
        System.out.println("Expected String from Bird movement: " + expected);
        System.out.println("Actual String from Bird movement  : " + actual);
        if (actual.equals(expected)) {
            System.out.println("passed Bird movement and toString test");
        } else {
            System.out.println("FAILED Bird movement and toString test");
        }
    }

    // Small, very simple test (Ant only).
    public static void test1() {
        System.out.println("Test 1 (Ant):");

        // create an Ant and move it 10 times
        final int MOVES = 10;
        Ant animal = new Ant(false);
        System.out.println("Creating an Ant and moving it " + MOVES + " times.");
        System.out.print(animal.toString() + " ");
        Critter.Direction[] expectedMoves = new Critter.Direction[MOVES];
        for (int i = 0; i < MOVES; i += 2) {
            expectedMoves[i] = Critter.Direction.NORTH;
            expectedMoves[i + 1] = Critter.Direction.EAST;
        }
        Critter.Direction[] actualMoves = new Critter.Direction[MOVES];
        for (int i = 0; i < MOVES; i++) {
            Critter.Direction move = animal.getMove();
            actualMoves[i] = move;
            System.out.print(move + " " + animal.toString() + " ");
        }
        System.out.println();
        if (Arrays.equals(actualMoves, expectedMoves)) {
            System.out.println("Passed Ant Move Test");
        } else {
            System.out.println("FAILED Ant Move Test");
            System.out.println("Expected moves: " + Arrays.toString(expectedMoves));
            System.out.println("Actual moves  : " + Arrays.toString(actualMoves));
        }
        System.out.println();
    }



    // A bigger test (move several animals).  You can un-comment this
    // test (delete the /* and * /) once you implement the rest of the animals.
    // You'll also need to un-comment the call to test2 up in main.

    public static void test2() {
        System.out.println("Test 2 (all animals):");

        // Ant
        Ant ant1 = new Ant(true);
        Ant ant2 = new Ant(false);
        moves(ant1, 5);
        moves(ant2, 10);
        System.out.println();

        // Bird
        Bird bird1 = new Bird();
        Bird bird2 = new Bird();
        moves(bird1, 17);
        moves(bird2, 14);
        System.out.println();

        // Hippo (movement is random so may not match perfectly)
        Hippo hippo1 = new Hippo(4);
        Hippo hippo2 = new Hippo(0);
        System.out.println("Hippo 1 moving 8 times: ");
        moves(hippo1, 8);
        System.out.println("Hippo 2 moving 12 times: ");
        moves(hippo2, 12);
        System.out.println("Hippo 2 moving another 30 times: ");
        moves(hippo2, 30);
        System.out.println("Hippo 1 eating 6 times: ");
        eating(hippo1, 6);
        System.out.println();

        // Vulture
        Vulture vulture1 = new Vulture();
        Vulture vulture2 = new Vulture();
        System.out.println("Vulture 1 moving 13 times: ");
        moves(vulture1, 13);
        System.out.println("Vulture 2 moving 8 times: ");
        moves(vulture2, 8);
        System.out.println();
    }

    // Moves the given animal the given number of times and prints which
    // way the animal wanted to move each time.
    public static void moves(Critter critter, int times) {
        System.out.print(critter.getClass().getName() + " moving " + times + " times: ");
        System.out.print(critter.toString() + " ");
        for (int i = 1; i <= times; i++) {
            Critter.Direction move = critter.getMove();
            System.out.print(move + " ");
        }
        System.out.println(critter.toString());
    }

    // Asks the given animal if he wants to eat the given number of times
    // and prints whether the animal wanted to eat each time.
    public static void eating(Critter critter, int times) {
        System.out.print(critter.getClass().getName() + " eating " + times + " times: ");
        System.out.print(critter.toString() + " ");
        for (int i = 1; i <= times; i++) {
            boolean ate = critter.eat();
            System.out.print(ate + " " + critter.toString() + " ");
        }
        System.out.println();
    }
}