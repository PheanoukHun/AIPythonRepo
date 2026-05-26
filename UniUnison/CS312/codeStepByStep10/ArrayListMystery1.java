import java.util.ArrayList;

public class ArrayListMystery1 {
    public static void mystery1(ArrayList<Integer> list) {
        for (int i = list.size() - 1; i > 0; i--) {
            if (list.get(i) < list.get(i - 1)) {
                int element = list.get(i);
                list.remove(i);
                list.add(0, element);
            }
        }
        System.out.println(list);
    }

    public static void main(String[] args) {
        ArrayList<Integer> nums = new ArrayList<>();
        nums.add(30);
        nums.add(20);
        nums.add(10);
        nums.add(60);
        nums.add(50);
        nums.add(40);
        mystery1(nums);
    }
}
