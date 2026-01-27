public class PracticeArrProblemOne {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        
        System.out.println();

        int j = 0;
        do {
            System.out.println(arr[j]);
            j++;
        } while (j < arr.length);
        
        System.out.println();

        for (int i : arr) {
            System.out.println(i);
        }
    
        System.out.println();
    }
}