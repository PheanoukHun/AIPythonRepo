public class methodProblems {
    public static void main(String[] args) {
        System.out.println(countE("Nulle"));
    }

    public static int countE(String s) {
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            if (Character.toLowerCase(s.charAt(i)) == 'e') {
                count++;
            }
        }

        return count;
    }
}
