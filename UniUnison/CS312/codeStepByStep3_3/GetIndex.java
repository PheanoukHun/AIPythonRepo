public class GetIndex {
    public static void main(String[] args) {
        printIndex("ZELDA");
    }

    public static void printIndex(String str) {
        int stringLength = str.length();
        for (int i = 1; i <= stringLength; i++) {
            char currChar = str.charAt(i - 1);
            int currNumber = stringLength - i;
            System.out.print(currChar + "" + currNumber);
        }
        System.out.println();
    }
}
