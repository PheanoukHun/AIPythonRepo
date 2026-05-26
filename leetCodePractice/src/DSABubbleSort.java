import java.util.Arrays;

public class DSABubbleSort {
    public static void main(String[] args) {
        int[] arr = {7, 3, 9, 12, 11};
        int[] results = bubbleSort(arr);
        System.out.println(Arrays.toString(results));
    }   
    
    public static int[] bubbleSort(int[] arr) {

        for (int i = 0; i < arr.length; i++) {
            boolean swapped = false;

            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j+1]) {
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                    swapped = true;
                }
            }

            if (!swapped) {
                break;
            }
        }

        return arr;
    }
}