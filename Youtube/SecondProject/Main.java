public class Main{
    static String alphabet = "abcdefghijklmnopqrstuvwxyz";
    
    public static void main(String[] args){
        System.out.printf("%2.3f",121.3121);
        String message = "Hello World";
        int numMoved = 3;
        String encodedMessage = encode(message, numMoved);
        print(encodedMessage);

        print(" ");

        String decodedMessage = decode(encodedMessage, numMoved);
        print(decodedMessage);
    }

    private static void print(String s){
        System.out.println(s);
    }

    private static String encode(String s, int numMoved){
        s = s.toLowerCase();
        char[] charArray = s.toCharArray();
        StringBuilder encoded = new StringBuilder();

        for (char c : charArray){
            if (Character.isLetter(c)){
                int index = alphabet.indexOf(c);
                if (index != -1){ //Checks if the letter is found in the alphabet.
                    int newIndex = (index + numMoved) % alphabet.length();
                    if (newIndex < 0) { //Works if the newIndex is a negative number.
                        newIndex += alphabet.length();
                    }
                    encoded.append(alphabet.charAt(newIndex));
                }
            } else {
                encoded.append(c);
            }
        }
    return encoded.toString();
    }

    private static String decode(String s, int numMoved){
        char[] charArray = s.toCharArray();
        StringBuilder decoded = new StringBuilder();

        for(char c: charArray){
            if (Character.isLetter(c)){
                int index = alphabet.indexOf(c);
                if (index != -1){
                    int newIndex = (index - numMoved) % alphabet.length();
                    if (newIndex < 0){
                        newIndex += alphabet.length();
                    }
                    decoded.append(alphabet.charAt(newIndex));
                }
            } else {
                decoded.append(c);
            }
        }
    return decoded.toString();
    }
}