public class BadNews {
    public static final int MAX_ODD = 21;

    public static void writeOdds(int num) {
        // print each odd number

        int biggestNumber = 0;
        for (int count = 1; count <= (num - 2); count += 2) {
            System.out.print(count + " ");

            if (biggestNumber < count) {
                biggestNumber = count;
            }
        }

        // print the last odd number
        System.out.println(biggestNumber + 2);
    }

    public static void main(String[] args) {
        // write all odds up to 21
        writeOdds(MAX_ODD);

        // now, write all odds up to 11
        writeOdds(11);
    }
}