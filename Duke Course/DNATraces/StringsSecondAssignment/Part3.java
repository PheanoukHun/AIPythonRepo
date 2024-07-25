package DNATraces.StringsSecondAssignment;

import java.util.ArrayList;
public class Part3 {
    public static void main(String[] args) {
        Part3 pThree = new Part3();
        pThree.testCountGenes();
    }

    public void testCountGenes(){
        String dna = "ATGTAAGATGCCCTAGT";
        if (countGenes(dna) != 2) System.out.println("Error on 2");
        System.out.println("Test Complete");
    }

    public int countGenes(String dna){
        Part1 pOne = new Part1();
        ArrayList<String> genes = new ArrayList<>();
        String results;

        int count = 0;
        int startIndex = 0;

        while (true){
            results = pOne.findGene(dna, startIndex);
            if (results.isEmpty()){
                break;
            } else{
                count ++;
                genes.add(results);
                startIndex = dna.indexOf(results) + results.length();
            }
        }
        
        return count;
    }
}
