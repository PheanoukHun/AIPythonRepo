package DNATraces.StringsSecondAssignment;

public class Part2 {
    public static void main(String[] args) {
        Part2 assignment = new Part2();
        assignment.testHowMany();
    }

    public void testHowMany(){
        if (howMany("AA", "ATAAAA") != 2) System.out.println("Error on 2");
        if (howMany("GAA", "ATGAACGAATTGAATC") != 3) System.out.println("Error on 3");
        System.out.println("Tests Finished.");
    }

    public int howMany(String a, String b){
        int count = 0;
        int startIndex = 0;
        int index;

        while (true){

            index = b.indexOf(a, startIndex);
            if (index == -1) {
                break;
            }

            count++;
            startIndex = index + a.length();
        }

        return count;
    }
}
