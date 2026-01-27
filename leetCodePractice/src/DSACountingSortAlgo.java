import java.util.Arrays;

public class DSACountingSortAlgo {
    public static void main(String[] args) {
        int[] unsorted = {4, 2, 2, 6, 3, 3, 1, 6, 5, 2, 3};
        int[] sorted = countingSort(unsorted);
        System.out.println(Arrays.toString(sorted));
    }

    public static int[] countingSort(int[] arr) {
        
        if (arr.length < 2) {
            return arr;
        } 
        
        int maxVal = Integer.MIN_VALUE;
        int minVal = Integer.MAX_VALUE;

        for (int num : arr) {
            if (num < minVal) {
                minVal = num;
            } 
            
            if (num > maxVal) {
                maxVal = num;
            }
        }

        int[] frqArr = new int[maxVal - minVal + 1];        

        for (int num : arr) {
            frqArr[num - minVal]++;
        }

        int[] result = new int[arr.length];
        
        int count = 0;
        for (int i = 0; i < frqArr.length; i++) {
            while (frqArr[i] > 0) {
                result[count] = i + minVal;
                count++;
                frqArr[i]--;
            }
        }

        return result;
    }
}
