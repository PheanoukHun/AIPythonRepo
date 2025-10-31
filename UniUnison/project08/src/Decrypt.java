import java.util.Scanner;

/**
 * CS312 Assignment 8 - Decrypt
 * 
 * On my honor, <NAME>, this programming assignment is my own work, 
 * I have not shared it with others and I will not share it in the future.
 *
 * Program to decrypt a message that has been encrypted with a substitution cipher.
 * We assume only characters with ASCII codes from 32 to 126 inclusive have been encrypted.
 * 
 * Name:
 * UTEID:
 * 
 */

public class Decrypt {

    // CS312 students, add your constants here: (delete this comment in final version of program)



    public static void main(String[] arg) {
        // CS312 Students, do not create any other Scanners connected to System.in
        Scanner keyboard = new Scanner(System.in);
        String fileName = getFileName(keyboard);
        String encryptedText = DecryptUtilities.convertFileToString(fileName);

        // The other method from DecryptUtilities you will have to use is
        // DecryptUtilities.getDecryptionKey(int[]), but first you need to
        // create an array with the frequency of all the ASCII characters in the
        // encrypted text. Count ALL characters from ASCII code 0 to ASCII code 127, and
        // pass it as a parameter to getDecryptionKey.
        // (delete this comment in final version of program)

        // CS312 students, add you code here. (delete this comment in final version of program)


        keyboard.close();
    }

    // CS312 students, add your methods here: (delete this comment in final version of program)





    // Get the name of file to use. 
    public static String getFileName(Scanner keyboard) {
        System.out.print("Enter the name of the encrypted file: ");
        String fileName = keyboard.nextLine().trim();
        System.out.println();
        return fileName;
    }
}