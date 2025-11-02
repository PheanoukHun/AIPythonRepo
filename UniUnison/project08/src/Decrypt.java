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

        boolean wantToSwap = true;

        System.out.println("The encrypted text is:\n" + encryptedText);

        int[] charFrequencies = new int[ASCII_LEN];
        getCharFrequencies(charFrequencies, encryptedText);

        char[] keySet = DecryptUtilities.getDecryptionKey(charFrequencies);

        printFrequency(charFrequencies);
        printEncryptionKeySet(keySet);

        while (wantToSwap) {

            wantToSwap = getWantToSwap(keyboard);

            if (wantToSwap) {
                switchKeys(keyboard, keySet);
            }
        }

        printEncryptionKeySet(keySet);
        keyboard.close();
    }

    /**
     * 
     * @param keyboard
     * @param prompt
     * @return
     */
    public static char getCharForSwitching(Scanner keyboard, String prompt) {
        System.out.print(prompt);
        String userChoice = keyboard.nextLine();
        return (userChoice.charAt(0));
    }

    /**
     * 
     * @param keyboard
     * @param keySet
     */
    public static void switchKeys(Scanner keyboard, char[] keySet) {

        String promptOne = "Enter the decrypt character you want to change: ";
        char oldChar = getCharForSwitching(keyboard, promptOne);

        String promptTwo = "Enter what the character " + oldChar + " should decrypt to instead: ";
        char newChar = getCharForSwitching(keyboard, promptTwo);

        System.out.println(oldChar + "'s will now decrypt to " + newChar + "'s and vice versa.\n");

        for (int i = 0; i < keySet.length; i++) {
            char currChar = keySet[i];
            if (currChar == oldChar) {
                keySet[i] = newChar;
            } else if (currChar == newChar) {
                keySet[i] = oldChar;
            }
        }
    }

    /**
     * 
     * @param charFrequencies
     */
    public static void printFrequency(int[] charFrequencies) {

        System.out.println("Frequencies of characters.\n" + "Character - Frequency");

        for (int i = SPACE; i <= TILDE; i++) {
            char currChar = (char) i;
            int freq = charFrequencies[i];
            System.out.println(currChar + " - " + freq);
        }

        System.out.println();
    }

    /**
     * 
     * @param keySet
     */
    public static void printEncryptionKeySet(char[] keySet) {

        System.out.println("The current version of the key for ASCII characters 32 to 126 is: ");

        for (int i = SPACE; i <= TILDE; i++) {

            char encryptedChar = (char) i;
            char decryptedChar = keySet[i];

            System.out.print("Encrypt character: " + encryptedChar);
            System.out.println(", decrypt character: " + decryptedChar);
        }

        System.out.println();
    }

    /**
     * 
     * @param encryptedText
     * @param keySet
     */
    public static void printDecryptedText(String encryptedText, char[] keySet) {

        System.out.println("The current version of the decrypted text is:\n");

        for (int i = 0; i < encryptedText.length(); i++) {
            int swappedIndex = (int) encryptedText.charAt(i);
            char newChar = keySet[swappedIndex];
            System.out.print(newChar);
        }

        System.out.println();
    }

    /**
     * 
     * @param keyboard
     * @return
     */
    public static boolean getWantToSwap(Scanner keyboard) {
        System.out.println("Do you want to make a change to the key?");
        System.out.print("Enter 'Y' or 'y' to make change: ");

        String userChoice = keyboard.nextLine();
        char cleanedChoice = userChoice.toLowerCase().charAt(0);
        return (cleanedChoice == 'y');
    }

    /**
     * 
     * @param charFrequencies
     * @param encryptedText
     */
    public static void getCharFrequencies(int[] charFrequencies, String encryptedText) {
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