import java.util.Random;

public class MyTesting {
    public static void main(String[] args) {

        // Hamming Distance Test 1 (Long Array, no Differences)
        hammingDistanceTestOne();

        // Hamming Distance Test 2 (Long Array, All Different)
        hammingDistanceTestTwo();

        // Hamming Distance Test 3 (Long Array, Same Array)
        hammingDistanceThree();
    }

    public static void hammingDistanceTestOne() {
        int[] arr1 = new int[10000000];
        int[] arr2 = new int[10000000];
        
        Random randomObjt = new Random();
        
        for (int i = 0; i < arr1.length; i++) {
            
            int randomInt = randomObjt.nextInt(1000);
            
            arr1[i] = randomInt;
            arr2[i] = randomInt;
        }

        System.out.println(CodeCamp.hammingDistance(arr1, arr2));
    }

    public static void hammingDistanceTestTwo() {
        int[] arr1 = new int[10000000];
        int[] arr2 = new int[10000000];
        
        Random randomObjt = new Random();
        
        for (int i = 0; i < arr1.length; i++) {
            arr1[i] = randomObjt.nextInt(1000);
            arr2[i] = randomObjt.nextInt(1000);
        }

        System.out.println(CodeCamp.hammingDistance(arr1, arr2));
    }

    public static void hammingDistanceThree() {
        int[] arr1 = new int[1000000];
        Random randomObjt = new Random();

        for (int i = 0; i < arr1.length; i++) {
            arr1[i] = randomObjt.nextInt();
        }

        System.out.println(CodeCamp.hammingDistance(arr1, arr1));
    }
}