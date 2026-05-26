public class StringOps3 {
    public static void main(String[] args) {
        
        print("\n");

        // Initializing the Strings
        String s1 = "Washington";
        String s2 = new String("Washington");
        String s3 = "WASHINGTON";
        
        // Print Equality
        print("Equality Check Between S1 and S2: " + (s1.equals(s2)));
        print("Equality Check Between S1 and S3: " + s1.equals(s3));
        print("Equality Check Between S1 and S3 Ignoring Case: " + s1.equalsIgnoreCase(s3));

        // Lowercase String Literals
        print("Lowercase S1: " + s1.toLowerCase());
        print("Uppercase S3: " + s3.toLowerCase());

        // Equals Cases
        print("S1 and S3 Lowercase Equality Check: " + s1.toLowerCase().equals(s3.toLowerCase()));

        // Uppercase String Literals
        print("Uppercase S1: " + s1.toUpperCase());
        print("Uppercase S3: " + s3.toUpperCase());

        String s4 = "50F1A";
        print("S4 in Lowercase: " + s4.toLowerCase());

        String regexStr = "^W.*";
        print("S1 Matches Regex ^W.*: " + s1.matches(regexStr));
        print("S3 Matches Regex ^W.*: " + s3.matches(regexStr));

        String s5 = "        Washington           ";
        print("Equality checks S3 and S5: " + s3.equals(s5));
        s5 = s5.strip();
        print("Equality checks after Stripping S3 and S5: " + s3.equals(s5));

        print("\n");
    }

    private static void print(String s) {
        System.out.println(s);
    }
}