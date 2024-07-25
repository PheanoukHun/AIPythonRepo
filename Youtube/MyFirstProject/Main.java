import java.util.ArrayList;

public class Main {
    public static void main(String[] args){
        
        Animal a = new Animal();
        String str = "hello world";
        str.indexOf('e');

        str = str.toUpperCase();
        str = end(str);

        Math.random(); // Generates a number between 0 and 1 (random.uniform)
        
        String[] cars = {"Volvo", "BMW", "Ford"};
        System.out.println(cars.length);

        int[][] myNumbers = {{1, 2, 3, 4}, {5, 6, 7}};
        //System.out.println(myNumbers);

        print(str);
        print("\n");
        print(Animal.iAmDog());
        
        ArrayList<Integer> b = new ArrayList<Integer>();

        a.doStuff();

        int x = 10;
        int y = 5;

        if (x == y){
            print("1");
        }
        else if (x < y){
            print("2");
        }
        else {
            print("3");
        }

    }

    private static String end(String s){
        return s + ".";
    }

    public static void print(String s){
        System.out.println(s);
    }
}
