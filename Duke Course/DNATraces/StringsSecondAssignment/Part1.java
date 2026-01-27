package DNATraces.StringsSecondAssignment;

public class Part1 {
    public static void main(String[] args) {
        Part1 assignment = new Part1();
        assignment.testFindStopCodon();
        assignment.testFindGene();
    }

    public void testFindStopCodon(){
        String dna = "xxxyyyzzzTAAxxxyyyzzzTAAxx";

        int index = findStopCodon(dna, 0, "TAA");
        if (index != 9) System.out.println("Error on 9");

        index = findStopCodon(dna, 9, "TAA");
        if (index != 21) System.out.println("Error on 21");

        index = findStopCodon(dna, 1, "TAA");
        if (index != -1) System.out.println("Error on -1");

        index = findStopCodon(dna, 0, "TAG");
        if (index != -1) System.out.println("Error on -1");

        System.out.println("Tests Finished");
    }

    public void testFindGene(){
        String dna = "ATGCCCGGGAAATAGCCC";
        String gene = findGene(dna, 0);
        if (! gene.equals("ATGCCCGGGAAATAG")){
            System.out.println("Error");
        }
        System.out.println("Test Finished");
    }

    public int findStopCodon(String dna, int startIndex, String stopCodon){
        
        int currentIndex = dna.indexOf(stopCodon, startIndex + 3);
        while (currentIndex != -1){
            if ((currentIndex - startIndex) % 3 == 0){
                return currentIndex;
            } else {
                currentIndex = dna.indexOf(stopCodon, currentIndex + 1);
            }
        }
        return -1;
    }

    public String findGene(String dna, int where){
        int startIndex = dna.indexOf("ATG", where);
        
        if (startIndex == -1){
            return "";
        }
        
        int[] stopCodonIndexes = {
            findStopCodon(dna, startIndex, "TAA"),
            findStopCodon(dna, startIndex, "TAG"),
            findStopCodon(dna, startIndex, "TGA")
        };

        int min = dna.length() + 1;
        for (int index : stopCodonIndexes){
            if (index < min && index != -1){
                min = index;
            }
        }

        if (min == dna.length() + 1){
            return "";
        } else {
            return dna.substring(startIndex, min + 3);
        }

    }
}
