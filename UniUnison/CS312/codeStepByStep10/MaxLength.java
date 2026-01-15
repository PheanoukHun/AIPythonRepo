import java.util.ArrayList;

public class MaxLength {
    public static void main(String[] args) {
        
    }

    public static int maxLength(ArrayList<String> words) {
        int max = -1;
        
        for (int i = 0; i < words.size(); i++) {
            if (max < words.get(i).length()) {
                max = words.get(i).length();
            }
        }

        return max;
    }
}