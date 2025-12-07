import java.lang.Math;

/**
 * CS312 Assignment 11 - Guitar Hero - GuitarHero.java
 *
 * On my honor, Pheanouk Hun, this programming assignment is my own work,
 * I have not shared it with others and I will not share it in the future.
 *
 * Program that allows two people to play Guitar sounds on a keyboard.
 * 
 * Name: Pheanouk Hun
 * UT EID: ph23434
 * 
 */

public class GuitarHero {

    public static String KEYS = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
    public static double CONCERT_A = 440.0;
    public static double BASE_FACTOR = 1.05956;

    public static void main(String[] args) {

        Keyboard keyboard = new Keyboard();
        GuitarString[] strings = createGuitarStrings();

        // The Loop Runs Indefinitely Until the Player Chooses to Exits out of the
        // Program.
        while (true) {

            // IF: A Player did play a Key
            if (keyboard.hasNextKeyPlayed()) {
                pluckString(keyboard, strings);
            }

            StdAudio.play(getTotalSample(strings));

            runTics(strings);
        }

    }

    /**
     * This method creates an Array of GuitarStrings that contains 37 Unique
     * Strings.
     * 
     * @return - A GuitarString array that contains a unique frequency in each
     *         index.
     */
    public static GuitarString[] createGuitarStrings() {

        GuitarString[] strings = new GuitarString[KEYS.length()];

        // Loop Runs Once Per Key on the Keyboard
        for (int i = 0; i < strings.length; i++) {
            int exponentPow = i - 24;
            double frequency = CONCERT_A * Math.pow(BASE_FACTOR, exponentPow);
            strings[i] = new GuitarString(frequency);
        }

        return strings;
    }

    /**
     * This method listens to all keyboard input and plucks the string of the
     * character being chosen if it is in the list of Keys.
     * 
     * @param keyboard - A keyboard object that listens to keyboard input.
     * @param strings  - An Array that contains 37 GuitarStrings with unique
     *                 frequencies.
     */
    public static void pluckString(Keyboard keyboard, GuitarString[] strings) {
        char key = keyboard.nextKeyPlayed();
        int index = KEYS.indexOf(key);

        // IF: The Character Key is Found.
        if (index != -1) {
            strings[index].pluck();
        }
    }

    /**
     * This method gets the total sample of all the Strings.
     * 
     * @param strings - An Array that contains 37 GuitarStrings with unique
     *                frequencies.
     * @return - Returns the total of all the sample values in the GuitarString
     *         Arrays.
     */
    public static double getTotalSample(GuitarString[] strings) {
        double sample = 0.0;

        // Runs once per String in the GuitarString Array
        for (GuitarString string : strings) {
            sample += string.sample();
        }
        return sample;
    }

    /**
     * This method tics all the Strings once in the Strings Arrays Once.
     * 
     * @param strings - An Array that contains 37 GuitarStrings with unique
     *                frequencies.
     */
    public static void runTics(GuitarString[] strings) {

        // Runs once per String in the GuitarString Array.
        for (GuitarString string : strings) {
            string.tic();
        }
    }
}
