public class FindGeneSimpleAndTest{
    
    public static void main(String[] args){
        FindGeneSimpleAndTest gs = new FindGeneSimpleAndTest();
        gs.testFindGeneSimple();
    }
    
    public void testFindGeneSimple(){
        String dna = "AATGCGTAATATGGT";
        System.out.println("\nThe DNA Strand is " + dna);
        String gene = findGeneSimple(dna);
        System.out.println("The gene is " + gene);

        dna = "AATGCTAGGGTAATATGGT";
        System.out.println("\nThe DNA Strand is " + dna);
        gene = findGeneSimple(dna);
        System.out.println("The gene is " + gene);

        dna = "ATCCTATGCTTCGGCTGCTCTAATATGGT";
        System.out.println("\nThe DNA Strand is " + dna);
        gene = findGeneSimple(dna);
        System.out.println("The gene is " + gene);

        dna = "ATGTAA";
        System.out.println("\nThe DNA Strand is " + dna);
        gene = findGeneSimple(dna);
        System.out.println("The gene is " + gene);

        dna = "TTATAA";
        System.out.println("\nThe DNA Strand is " + dna);
        gene = findGeneSimple(dna);
        System.out.println("The gene is " + gene);

        dna = "CGATGGTTTG";
        System.out.println("\nThe DNA Strand is " + dna);
        gene = findGeneSimple(dna);
        System.out.println("The gene is " + gene);
    }

    public String findGeneSimple(String dna){
        String gene = "";

        int startingIndex = dna.indexOf("ATG");
        if (startingIndex == -1){
            return " ";
        }
        int endingIndex = dna.indexOf("TAA", startingIndex + 3);
        if (endingIndex == -1){
            return " ";
        }

        gene = dna.substring(startingIndex, endingIndex + 3);
        
        return gene;
    }
}
