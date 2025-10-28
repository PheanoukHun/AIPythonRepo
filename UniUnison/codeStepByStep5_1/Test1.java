import java.util.Arrays;

public class Test1 {
    public static void main(String[] args) {
        int[] nums = {1, 8, 3, 6};
        mysteryE(nums);
        System.out.println(Arrays.toString(nums));
    }

    public static void mysteryE(int[] nums) {
        for (int i = nums.length - 2; i > 0; i--) {

            System.out.println("Hello World 1");

            if (nums[i + 1] <= nums[i - 1]) {
                System.out.println("Hello World 2");
                nums[i]++;
            }
        }
    }
}
