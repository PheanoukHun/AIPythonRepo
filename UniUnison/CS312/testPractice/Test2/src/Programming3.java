public class Programming3 {

    /**
     * Rearranges the given array so that all even numbers come before odd numbers.
     *
     * @param nums the input array of integers
     */
    public static void evenBeforeOdd(int[] nums) {
        int left = 0;
        int right = nums.length - 1;

        while (left < right) {
            // Move left index forward while number is even
            while (left < right && nums[left] % 2 == 0) {
                left++;
            }

            // Move right index backward while number is odd
            while (left < right && nums[right] % 2 == 1) {
                right--;
            }

            // Swap odd (on left) with even (on right)
            if (left < right) {
                int temp = nums[left];
                nums[left] = nums[right];
                nums[right] = temp;
            }
        }
    }

    // Example test
    public static void main(String[] args) {
        int[] nums = {3, 8, 5, 12, 7, 4, 10};
        evenBeforeOdd(nums);

        for (int num : nums) {
            System.out.print(num + " ");
        }
    }
}