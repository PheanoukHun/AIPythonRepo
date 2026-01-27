public class DataTypes{
    public static void main(String[] args) {
        
        byte b = 125;
        print("Byte Value: " + b);

        short points = 6000;
        print("Short Value: " + points);

        int population = 400000;
        print("Integer Value: " + population);

        long ageInMilliseconds = 788923800000l;
        print("Long Value: " + ageInMilliseconds);

        char keyboardInput = 'a';
        print("The Keyboard input is " + keyboardInput);

        boolean isLabCompleted = false;
        print("The lab is completed " + isLabCompleted);

        float houseValue = 400000.58f;
        print("The value of the house is $" + houseValue);

        double pi = 3.14159265359;
        print("The value of pi is " + pi);
    }

    private static void print(String s) {
        System.out.println(s);
    }
}