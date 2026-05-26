import java.util.Scanner;
import java.io.*;

public class ScannerParse {
    public static void main(String[] args) throws Exception{
        File file = new File("C:\\Users\\hunto\\Documents\\Java\\Youtube\\Scanner\\lose.txt");
        Scanner sc = new Scanner(file);
        while (sc.hasNext()){
            String line = sc.nextLine();
            System.out.println(line);
        }
        sc.close();
    }
}
