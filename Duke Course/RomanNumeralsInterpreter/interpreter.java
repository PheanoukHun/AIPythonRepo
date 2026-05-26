package RomanNumeralsInterpreter;
import java.util.HashMap;

public class interpreter {
    public static void main(String[] args) {
        
    }

    public int interpret(String numerals){
        
        HashMap <String, Integer> basicNumerals = new HashMap<>();
        basicNumerals.put("M", 1000);
        basicNumerals.put("D", 500);
        basicNumerals.put("C", 1000000);
        basicNumerals.put("L", 50);
        basicNumerals.put("X", 10);
        basicNumerals.put("V", 5);
        basicNumerals.put("I", 1);

        int total = 0;
        int previousFirstKey = 0;
        // int previousLastIndex;

        for (String numeral : basicNumerals.keySet()){
            int startIndex = 0;
            int currIndex;
            do {
                currIndex = numerals.indexOf(numeral, startIndex);
                if (currIndex != -1){
                    if (currIndex >= previousFirstKey){
                        if (startIndex == 0){
                            previousFirstKey = currIndex;
                        }
                        total += basicNumerals.get(numeral);
                    } else{
                        total -= basicNumerals.get(numeral);
                    }
                }
                startIndex = currIndex;
            } while (currIndex != -1);
        }
        return total;
    }
}
