public class StringOps {
    public static void main(String[] args) {
        
        System.out.println();
        
        String s1 = "Hello World!";
        System.out.println("S1: " + s1);

        String s2 = new String("Hello World!");
        System.out.println("S2: " + s2);
    
        String s3 = "Hello World!";
        System.out.println("S3: " + s3);

        System.out.println();

        System.out.println("S1 and S2 Comparison: " + (s1 == s2));
        System.out.println("S2 and S3: " + (s2 == s3));
        System.out.println("S1 and S3: " + (s1 == s3));

        System.out.println();
    }
}