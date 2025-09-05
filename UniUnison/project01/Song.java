/*
 * CS312 Assignment 1
 *
 * On my honor, Pheanouk Hun, this programming assignment is my own work,
 * I have not shared it with anyone else, and I will not share
 * it in the future.
 *
 * A program to print out the lyrics to the
 * children's song, "There was an old woman".
 *
 * Name: Pheanouk Hun
 * UTEID: ph23434
 *
 */

public class Song {

    // Print out the lyrics of "There was an old woman ... "
    public static void main(String[] args) {
        stanzaOne();
        stanzaTwo();
        stanzaThree();
        stanzaFour();
        stanzaFive();
        stanzaSix();
        stanzaSeven();
        stanzaEight();
    }
    
    // Add your methods here.
    public static void refrain() {
        System.out.println("I don't know why she swallowed that fly,");
        System.out.println("Perhaps she'll die.");
        System.out.println();
    }

    public static void stanzaOne() {
        System.out.println("There was an old woman who swallowed a fly.");
        refrain();
    }
    
    public static void stanzaTwo() {
        System.out.println("There was an old woman who swallowed a spider,");
        System.out.println("That wriggled and iggled and jiggled inside her.");
        System.out.println("She swallowed the spider to catch the fly,");
        refrain();
    }

    public static void stanzaThree() {
        System.out.println("There was an old woman who swallowed a bird,");
        System.out.println("How absurd to swallow a bird.");
        System.out.println("She swallowed the bird to catch the spider,");
        System.out.println("She swallowed the spider to catch the fly,");
        refrain();
    }

    public static void stanzaFour() {
        System.out.println("There was an old woman who swallowed a cat,");
        System.out.println("Imagine that to swallow a cat.");
        System.out.println("She swallowed the cat to catch the bird,");
        System.out.println("She swallowed the bird to catch the spider,");
        System.out.println("She swallowed the spider to catch the fly,");
        refrain();
    }

    public static void stanzaFive() {
        System.out.println("There was an old woman who swallowed a dog,");
        System.out.println("What a hog to swallow a dog.");
        System.out.println("She swallowed the dog to catch the cat,");
        System.out.println("She swallowed the cat to catch the bird,");
        System.out.println("She swallowed the bird to catch the spider,");
        System.out.println("She swallowed the spider to catch the fly,");
        refrain();
    }

    public static void stanzaSix() {
        System.out.println("There was an old woman who swallowed a goat,");
        System.out.println("She just opened her throat to swallow a goat.");
        System.out.println("She swallowed the goat to catch the dog,");
        System.out.println("She swallowed the dog to catch the cat,");
        System.out.println("She swallowed the cat to catch the bird,");
        System.out.println("She swallowed the bird to catch the spider,");
        System.out.println("She swallowed the spider to catch the fly,");
        refrain();
    }

    public static void stanzaSeven() {
        System.out.println("There was an old woman who swallowed a cow,");
        System.out.println("I don't know how she swallowed a cow.");
        System.out.println("She swallowed the cow to catch the goat,");
        System.out.println("She swallowed the goat to catch the dog,");
        System.out.println("She swallowed the dog to catch the cat,");
        System.out.println("She swallowed the cat to catch the bird,");
        System.out.println("She swallowed the bird to catch the spider,");
        System.out.println("She swallowed the spider to catch the fly,");
        refrain();
    }
    
    public  static void stanzaEight() {
        System.out.println("There was an old woman who swallowed a horse,");
        System.out.println("She died of course.");
        }
}