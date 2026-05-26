package DNATraces.StringsThirdAssignment;

public class Part2 {
    public static void main(String[] args) {
        Part2 run = new Part2();
        run.testRatioFinder();
    }

    public void testRatioFinder(){

        String dna = "ATGCCATAG";
        String[] find = {"C", "G"};

        float results = ratioFinder(dna, find);
        float expected = (4.0f / 9.0f);
        
        System.out.println("\nTest Started");
        if (results != expected){
            System.out.println("Error");
            System.out.println("Expected: " + expected + ", Results: " + results);
        }
        System.out.println("Test Complete");
    }

    public float ratioFinder(String word, String[] find){
        int index = 0;
        int count = 0;

        for (String x : find){
            while (true){
                index = word.indexOf(x, index);
                if (index != -1){
                    count ++;
                    index = index + x.length();
                } else {
                    break;
                }
            }
        }

        float ratio = (float) count / word.length();
        return ratio;
    }
}
