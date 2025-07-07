public class StringOps2 {
    public static void main(String[] args) {
        
        System.out.println();
        
        String s1 = "The quick brown fox jumped over the lazy dog.";
        System.out.println("S1 Length: " + s1.length());

        char[] strArr = s1.toCharArray();
        System.out.println("S1 Char Array: " + strArr.length);

        System.out.println(strArr);

        System.out.println("The First Char of the String is " + strArr[0]);
        System.out.println("The Last Char of the String is " + strArr[strArr.length - 1]);

        System.out.println("The Index of the First T is " + s1.indexOf('T'));
        System.out.println("The Index of the First g is " + s1.indexOf('g'));

        System.out.println();
    }
}