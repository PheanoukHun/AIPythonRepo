import java.io.*;
import java.util.Scanner;

public class Perimeter {

    public static void main(String[] args){
        Perimeter pr = new Perimeter();
        try {
            pr.testPerimeter();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void testPerimeter() throws Exception{
        // Creating the Shape Object
        Shape s = new Shape();

        // Creating the File and Scanner object
        File file = new File("ShapeFolder/example3.txt");
        Scanner scanner = new Scanner(file);

        // Scanning the Document and Adding the Points
        while (scanner.hasNext()){
            String coord = scanner.nextLine();
            coord = coord.replace(" ", "");
            String[] myArray = coord.split(",");
            int x = Integer.valueOf(myArray[0]);
            int y = Integer.valueOf(myArray[1]);
            s.addPoint(x, y);
        }
        scanner.close();

        // Testing the Perimeter Calculator Function
        double resultPerim = calculatePerimeter(s);
        System.out.println("Perimeter : " + resultPerim);
    }

    public double calculatePerimeter(Shape s){
        double totalPerim = 0; // Starts the Sum of the Perimeter as 0.
        
        // Setting the previous point as the Last Point
        Point[] pointList = s.getPoints();
        Point prevPoint = pointList[pointList.length - 1];

        // For each point currPoint in the shape
        for (Point currPoint : pointList){
            double distance = currPoint.getDistance(prevPoint);
            totalPerim += distance;
            prevPoint = currPoint;
        }

        return totalPerim;
    }
}