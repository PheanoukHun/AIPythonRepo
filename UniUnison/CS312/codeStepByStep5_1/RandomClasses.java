import java.util.Random;

public class RandomClasses {
    public static void main(String[] args) {
        generateRandomOver900();
    }

    public static void generateRandomOver900() {
        Random rand = new Random();
        int randomNum;
        do {
            randomNum = rand.nextInt(1000);
            System.out.println("Random number: " + randomNum);
        } while (randomNum < 900);
    }
}