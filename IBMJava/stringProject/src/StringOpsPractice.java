public class StringOpsPractice {
    public static void main(String[] args) {
        
        // String Literals
        String s1 = "Maple Tree";
        String s2 = "Maple Tree";

        System.out.println();

        System.out.println("S1: " + s1);
        System.out.println("S2: " + s2);

        // Printing
        System.out.println("S1 and S2 Comparisons: " + (s1 == s2));

        // String Objects
        String s3 = new String("Maple Tree");

        // Printing .equals
        System.out.println("S1 and S3 Comparison: " + (s3.equals(s1)));

        // Substrings
        String maple = s1.substring(0, 5);
        System.out.println("S1 Substring from 0 to 5: " + maple);

        String tree = s1.substring(5);
        System.out.println("S1 Substring from 5 to end: " + tree);

        // Concats
        String s4 = maple.concat(tree);
        System.out.println("S1 Part 1 and S1 Part 2 Combined: " + s4);

        // To Lowercase
        String s5 = s4.toLowerCase();
        System.out.println("S1 to Lowercase: " + s5);

        // To Uppercase
        String s6 = s4.toUpperCase();
        System.out.println("S1 to Uppercase: " + s6);

        System.out.println();
    }
}