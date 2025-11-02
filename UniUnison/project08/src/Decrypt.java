import java.util.Arrays;
import java.util.Scanner;

/**
 * CS312 Assignment 8 - Decrypt
 * 
 * On my honor, Pheanouk Hun, this programming assignment is my own work,
 * I have not shared it with others and I will not share it in the future.
 *
 * Program to decrypt a message that has been encrypted with a substitution
 * cipher.
 * We assume only characters with ASCII codes from 32 to 126 inclusive have been
 * encrypted.
 * 
 * Name: Pheanouk Hun
 * UTEID: ph23434
 * 
 */

public class Decrypt {

    // Initializing the Constants Values
    public static final int ASCII_LEN = 128;
    public static final int SPACE = 32;
    public static final int TILDE = 126;

    public static void main(String[] arg) {
        // CS312 Students, do not create any other Scanners connected to System.in
        Scanner keyboard = new Scanner(System.in);
        String fileName = getFileName(keyboard);
        String encryptedText = DecryptUtilities.convertFileToString(fileName);

        // Initializing a boolean variable for user swapping condition testing.
        boolean wantToSwap = true;

        // A char array that contains the list of substitution that should be made with
        // the index containing the int version of the encrypted char and the value
        // being the decrypted char.
        int[] charFrequencies = new int[ASCII_LEN];

        // This prints out the Text before it was decrypted.
        System.out.println("The encrypted text is:\n" + encryptedText);

        // Updates the charFrequencies Array to contain the number of frequency a char
        // appears.
        getCharFrequencies(charFrequencies, encryptedText);

        // Get the Key Set of the encrypted text based on the frequency char.
        char[] keySet = DecryptUtilities.getDecryptionKey(charFrequencies);

        // Prints Out the Output
        printFrequency(charFrequencies);
        printEncryptionKeySet(keySet);

        // Loop runs once after checking whether the user wants to swapping characters
        do {

            // Prints out the predicted text based on the current Key Set
            printDecryptedText(encryptedText, keySet);

            // Ask the user if they want to continue switching characters in the key set.
            wantToSwap = switchChars(keyboard, keySet);
        } while (wantToSwap);

        // Print out the Prediction Key set for future use.
        printEncryptionKeySet(keySet);

        // Closing the Scanner
        keyboard.close();
    }

    /**
     * A method that asks whether the user wants to continue to swap characters in
     * order to further decrypt the text. If the user answers yes, the method will
     * ask the user what characters they want to swap.
     * 
     * @param keyboard - A Scanner objects that takes input from the Keyboard and
     *                 User.
     * @param keySet   - A char array that contains the list of substitution that
     *                 should be made with the index containing the int version of
     *                 the encrypted char and the value being the decrypted char.
     * @return - A boolean value stating the decision of the user on whether they
     *         want to continue swapping or not.
     */
    public static boolean switchChars(Scanner keyboard, char[] keySet) {

        // Instructure the User on what to do.
        System.out.println("Do you want to make a change to the key?");
        System.out.print("Enter 'Y' or 'y' to make change: ");

        // Get user input and clean it up
        String userChoice = keyboard.nextLine();
        char cleanedChoice = userChoice.toLowerCase().charAt(0);

        // IF: the user responed y or Y, then switch the keys and return true
        if (cleanedChoice == 'y') {
            switchKeys(keyboard, keySet);
            return true;
        }

        // Returns False
        return false;
    }

    /**
     * This method asks whether the user wants to swap characters in the keySet char
     * array to try to decode the message more. If they do say yes, then asks them
     * what chars do they want to switch around.
     * 
     * @param keyboard - A Scanner objects that takes input from the Keyboard and
     *                 User.
     * @param prompt   - A String containing the question that should be asked to
     *                 the user.
     * @return - A char that ensures the user response is valid.
     */
    public static char getCharForSwitching(Scanner keyboard, String prompt) {

        // Prompts the User and Get their Response
        System.out.print(prompt);
        String userChoice = keyboard.nextLine();

        // Clean user response
        char result = userChoice.charAt(0);

        // Return Value
        return result;
    }

    /**
     * A method that asks the user for two chars that the user wants to swap. Once
     * that happens, the program goes through the char array to find the location of
     * both char and swap the characters around.
     * 
     * @param keyboard - A Scanner objects that takes input from the Keyboard and
     *                 User.
     * @param keySet   - A char array that contains the list of substitution that
     *                 should be made with the index containing the int version of
     *                 the encrypted char and the value being the decrypted char.
     * 
     * @return - void
     */
    public static void switchKeys(Scanner keyboard, char[] keySet) {

        // Ask the user what the first char that they want to choose is.
        String promptOne = "Enter the decrypt character you want to change: ";
        char charOne = getCharForSwitching(keyboard, promptOne);

        // Ask the user what the second char that they want to choose is.
        String promptTwo = "Enter what the character " + charOne + " should decrypt to instead: ";
        char charTwo = getCharForSwitching(keyboard, promptTwo);

        // Signal to the user that they will be swapping the position of the two chars
        // in the keySet char array.
        System.out.println(charOne + "'s will now decrypt to " + charTwo + "'s and vice versa.\n");

        // Loop runs once per char in the keySet char array.
        for (int i = 0; i < keySet.length; i++) {
            char currChar = keySet[i];

            // IF: the current char selected is the same as the first char, then switch its
            // value with that of the second char.
            if (currChar == charOne) {
                keySet[i] = charTwo;

                // ELSE IF: the current selected is the same as the second char as the second
                // char, then switch its value with that of the first char.
            } else if (currChar == charTwo) {
                keySet[i] = charOne;
            }
        }
    }

    /**
     * A method that prints out how many times each chars appears in the text from
     * ASCII value 32-126 (from the Space to the Tilde).
     * 
     * @param charFrequencies - An int array in which it's index value is the int
     *                        representation of the char at the ASCII with the value
     *                        being the number of times that char appears in the
     *                        encrypted text.
     * 
     * @return - void
     */
    public static void printFrequency(int[] charFrequencies) {

        System.out.println("Frequencies of characters.\n" + "Character - Frequency");

        // For each value starting from space to tilde, print out how many times the
        // char appears
        for (int i = SPACE; i <= TILDE; i++) {
            char currChar = (char) i;
            int freq = charFrequencies[i];
            System.out.println(currChar + " - " + freq);
        }
        System.out.println();
    }

    /**
     * This method prints out the encrypted char and potential decrypted counterpart
     * from the ASCII table value of 32-126.
     * 
     * @param keySet - A char array that contains the list of substitution that
     *               should be made with the index containing the int version of
     *               the encrypted char and the value being the decrypted char.
     * 
     * @return - void
     */
    public static void printEncryptionKeySet(char[] keySet) {

        System.out.println("The current version of the key for ASCII characters 32 to 126 is: ");

        // For each value starting from space to tilde, print out the char and its
        // associated decrypted char counterpart.
        for (int i = SPACE; i <= TILDE; i++) {

            char encryptedChar = (char) i;
            char decryptedChar = keySet[i];

            System.out.print("Encrypt character: " + encryptedChar);
            System.out.println(", decrypt character: " + decryptedChar);
        }
        System.out.println();
    }

    /**
     * This method takes in the encryptedTexts and replaces each char with that of
     * its decrypted version found within the char array.
     * 
     * @param encryptedText - A string that contains the encrypted version of the
     *                      file.
     * @param keySet        - A char array that contains the list of substitution
     *                      that should be made with the index containing the int
     *                      version of the encrypted char and the value being the
     *                      decrypted char.
     * 
     * @return - void
     */
    public static void printDecryptedText(String encryptedText, char[] keySet) {

        System.out.println("The current version of the decrypted text is:\n");

        // For the length of the encryptedText string, convert the char at that index
        // into an int, find its decrypted version, and print out the decrypted version.
        for (int i = 0; i < encryptedText.length(); i++) {
            int swappedIndex = (int) encryptedText.charAt(i);
            char newChar = keySet[swappedIndex];
            System.out.print(newChar);
        }

        System.out.println();
    }

    /**
     * This method increments the int at the int value of the char at a specific
     * index for all the characters in the encrypted text. This would update the
     * array.
     * 
     * @param charFrequencies - an int array containing the number of times each
     *                        char appear as the value and the index being the int
     *                        version of the ASCII char value.
     * @param encryptedText   - A string that contains the encrypted version of the
     *                        file.
     * @return - void
     */
    public static void getCharFrequencies(int[] charFrequencies, String encryptedText) {

        // For the length of the encryptedText string, get the char at that index,
        // convert it to an int to index to its ASCII position into the charFrequencies
        // Array, and increment the value at that point.
        for (int i = 0; i < encryptedText.length(); i++) {
            int currCharIndex = (int) encryptedText.charAt(i);
            charFrequencies[currCharIndex]++;
        }
    }

    // Get the name of file to use.
    public static String getFileName(Scanner keyboard) {
        System.out.print("Enter the name of the encrypted file: ");
        String fileName = keyboard.nextLine().trim();
        System.out.println();
        return fileName;
    }
}