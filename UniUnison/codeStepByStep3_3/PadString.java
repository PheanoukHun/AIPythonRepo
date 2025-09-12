public class PadString {
    public static void main(String[] args) {
        System.out.println(padString("hello", 8));
    }

    public static String padString(String word, int stringLength) {
        String newString = "";

        for (int i = 0; i < stringLength - word.length(); i++) {
            newString += " ";
        }

        newString += word;
        return newString;
    }
}
