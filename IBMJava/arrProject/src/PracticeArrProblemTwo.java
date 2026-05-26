public class PracticeArrProblemTwo {
    public static void main(String[] args) {
        
        String s;
        
        try {
            s = args[0];
        } catch (Exception e) {
            s = "Hello World";
        }

        System.out.println();
        for (char i : s.toCharArray()) {
            System.out.println(i);
        }
        System.out.println();
    }
}