public class Part2 {
    public static void main(String[] args){
        Part2 gs = new Part2();
        gs.testFindGeneSimple();
    }

    public void testFindGeneSimple(){
        String dna = "AATGCGTAATATGGT";
        System.out.println("\nThe DNA Strand is " + dna);
        String gene = findGeneSimple(dna, "ATG", "TAA");
        System.out.println("The gene is " + gene);

        dna = "AATGCTAGGGTAATATGGT";
        System.out.println("\nThe DNA Strand is " + dna);
        gene = findGeneSimple(dna, "ATG", "TAA");
        System.out.println("The gene is " + gene);

        dna = "ATGGGTTAAGTC";
        System.out.println("\nThe DNA Strand is " + dna);
        gene = findGeneSimple(dna, "ATG", "TAA");
        System.out.println("The gene is " + gene);

        dna = "ATGTAA";
        System.out.println("\nThe DNA Strand is " + dna);
        gene = findGeneSimple(dna, "ATG", "TAA");
        System.out.println("The gene is " + gene);

        dna = "TTATAA";
        System.out.println("\nThe DNA Strand is " + dna);
        gene = findGeneSimple(dna, "ATG", "TAA");
        System.out.println("The gene is " + gene);

        dna = "CGATGGTTTG";
        System.out.println("\nThe DNA Strand is " + dna);
        gene = findGeneSimple(dna, "ATG", "TAA");
        System.out.println("The gene is " + gene);

        dna = "gatgctataat";
        System.out.println("\nThe DNA Strand is " + dna);
        gene = findGeneSimple(dna, "ATG", "TAA");
        System.out.println("The gene is " + gene);
    }

    public String findGeneSimple(String dna, String startCodon, String endCodon){

        if (dna.equals(dna.toLowerCase())){
            startCodon = startCodon.toLowerCase();
            endCodon = endCodon.toLowerCase();
        }

        int startIndex = dna.indexOf(startCodon);
        int endIndex = dna.indexOf(endCodon, startIndex + 3);

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

