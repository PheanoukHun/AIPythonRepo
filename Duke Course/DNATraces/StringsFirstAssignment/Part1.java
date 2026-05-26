public class Part1{
    public static void main(String[] args){
        Part1 gf = new Part1();
        gf.testFindGeneSimple();
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

        int startIndex = dna.indexOf("ATG");
        int endIndex = dna.indexOf("TAA", startIndex + 3);

        if (startIndex == -1 || endIndex == -1){
            return "N/A";
        }
        
        String gene = dna.substring(startIndex, endIndex + 3);

        if (gene.length() % 3 != 0){
            return "N/A";
        }

        return gene;
    }
}
