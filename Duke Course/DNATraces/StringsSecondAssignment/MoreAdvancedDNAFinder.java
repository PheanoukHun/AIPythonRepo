package DNATraces.StringsSecondAssignment;

import java.util.ArrayList;
public class MoreAdvancedDNAFinder {
    public static void main(String[] args) {
        MoreAdvancedDNAFinder madf = new MoreAdvancedDNAFinder();
        madf.testGetGenes("ATGATCTAATTTATGCTGCAACGGTGAAGA");
        // madf.testFindGenes("");
        // madf.testFindGenes("ATGATCATAAGAAGATAATAGGGCCATGTAA");
    }

    public void testFindGene(){
        String dna = "ATGCCCGGGAAATAACCC";
        String gene = getGene(dna, 0);
        if (! gene.equals("ATGCCCGGGAAATAA")){
            System.out.println("Error");
        }
        System.out.println("Test Finished");
    }

    public void testFindGenes(String dna){
        System.out.println("Testing getGenes on " + dna);
        getGenes(dna);
    }

    public void testFindStopCodon(){
        String dna = "xxxyyyzzzTAAxxxyyyzzzTAAxx";

        int index = findStopCodon(dna, 0, "TAA");
        if (index != 9) System.out.println("Error on 9");

        index = findStopCodon(dna, 9, "TAA");
        if (index != 21) System.out.println("Error on 21");

        index = findStopCodon(dna, 1, "TAA");
        if (index != -1) System.out.println("Error on 26");

        index = findStopCodon(dna, 0, "TAG");
        if (index != -1) System.out.println("Error on 26");

        System.out.println("Tests Finished");
    }

    public String getGene(String dna, int where){
        int startIndex = dna.indexOf("ATG", where);
        
        if (startIndex == -1){
            return "";
        }
        
        int taaIndex = findStopCodon(dna, startIndex, "TAA");
        int tgaIndex = findStopCodon(dna, startIndex, "TGA");
        int tagIndex = findStopCodon(dna, startIndex, "TAG");
        
        int minIndex = 0;

        if (taaIndex == -1 || (tgaIndex != -1 && tgaIndex < taaIndex)){
            minIndex = tgaIndex;
        } else {
            minIndex = taaIndex;
        }

        if (minIndex == -1 || (tagIndex != - 1 && tagIndex < minIndex)){
            minIndex = tagIndex;
        }

        if (minIndex == -1){
            return "";
        }

        return dna.substring(startIndex, minIndex + 3);
    }

    public int findStopCodon(String dnaStr, int startIndex, String stopCodon){
        int currIndex = dnaStr.indexOf(stopCodon, startIndex + 3);
        while (currIndex != -1){
            if ((currIndex - startIndex) % 3 == 0){
                return currIndex;
            }
            else {
                currIndex = dnaStr.indexOf(stopCodon, currIndex + 1);
            }
        }
        return -1;
    }

    public void testGetGenes(String dna){
        System.out.println("Testing getAllGenes on " + dna);
        ArrayList<String> genes = getGenes(dna);
        for (String gene : genes){
            System.out.println(gene);
        }
    }
    
    public ArrayList<String> getGenes(String dna){
        int startIndex = 0;
        ArrayList<String> genes = new ArrayList<>();
        String currGene;
        
        while (true){
            currGene = getGene(dna, startIndex);
            if (currGene.isEmpty()){
                break;
            }

            genes.add(currGene);
            startIndex = dna.indexOf(currGene, startIndex) + currGene.length();
            
        }

        return genes;
    }
}
