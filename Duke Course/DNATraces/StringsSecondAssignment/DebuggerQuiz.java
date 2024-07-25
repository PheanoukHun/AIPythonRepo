package DNATraces.StringsSecondAssignment;

public class DebuggerQuiz {
    public static void main(String[] args) {
        DebuggerQuiz debug = new DebuggerQuiz();
        debug.test();
    }

    public void test() {
        findAbc("abcdefabcghi");
        findAbc("abcd");
        findAbc("abcdabc");
    }

    public void findAbc(String input) {
        int index = input.indexOf("abc");
        while (true) {
            if (index == -1 || index + 4 > input.length()) {
                break;
            }
            System.out.println((index + 1) + ", " + (index + 4));
            String found = input.substring(index+1, index+4);
            System.out.println(found);
            index = input.indexOf("abc", index+4);
        }
    }
}
