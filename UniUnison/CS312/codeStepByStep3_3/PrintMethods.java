public class PrintMethods {
    public static void main(String[] args) {
        vertical("Hello World");
        reverse("Hello World");
    }

    public static void vertical(String word) {
        for (int i = 0; i < word.length(); i++) {
            System.out.println(word.charAt(i));
        }
        System.out.println();
    }

    public static void reverse(String word) {
        for (int i = word.length() - 1; i >= 0; i--) {
            System.out.print(word.charAt(i));
        }

        System.out.println();
    }
}
