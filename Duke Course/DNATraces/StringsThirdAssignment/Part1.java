package DNATraces.StringsThirdAssignment;
import java.util.ArrayList;
import DNATraces.StringsSecondAssignment.*;

public class Part1 {
    public static void main(String[] args) {
        
    }

    public ArrayList<String> getAllGenes(String dna){
        ArrayList<String> genes = new ArrayList<>();
        MoreAdvancedDNAFinder allFunct = new MoreAdvancedDNAFinder();
        int startIndex = 0;
        String gene;

        while(true){
            gene = allFunct.getGene(dna, startIndex);
            if (gene.isEmpty()){
                break;
            } else {
                genes.add(gene);
                startIndex = dna.indexOf(gene) + gene.length();
            }
        }

        return genes;
    }
}
