import java.io.*;
import java.util.Scanner;

public class PerimeterAssignmentRunner {
    public static void main (String[] args) {
        PerimeterAssignmentRunner pr = new PerimeterAssignmentRunner();
        try{
            pr.testPerimeter();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void testPerimeter() throws Exception {
        
        Shape s = new Shape();

        File file = new File("ShapeFolder/example1.txt");
        Scanner scanner = new Scanner(file);

        while (scanner.hasNext()){
            
            String newLine = scanner.nextLine();
            String noSpace = newLine.replace(" ", "");
            String[] coords = noSpace.split(",");

            int x = Integer.valueOf(coords[0]);
            int y = Integer.valueOf(coords[1]);
            s.addPoint(x, y);
        }
        scanner.close();

        double length = getPerimeter(s);
        System.out.println("\nPerimeter = " + length);

        int numPoints = getNumPoints(s);
        System.out.println("Number of Points = " + numPoints);

        double averageLenght = getAverageLength(s);
        System.out.println("Average Side Length = " + averageLenght);

        double longestSide = getLargestSide(s);
        System.out.println("Largest Side Length = " + longestSide);

        double largestX = getLargestX(s);
        System.out.println("Largest X Value = " + largestX);

        try{
            double largestPerimeter = getLargestPerimeterMultipleFiles();
            System.out.println("Largest Perimeter = " + largestPerimeter);
        } catch (Exception e){
            e.printStackTrace();
        }

        try{
            String largestPerimeterName = getFileWithLargestPerimeter();
            System.out.println("Largest Perimeter Name = " + largestPerimeterName);
        } catch (Exception e){
            e.printStackTrace();
        }

        System.out.println("\nA List of Files in a Directory:");
        printFileNames();
    }

    public double getPerimeter (Shape s) {
        // Start with totalPerim = 0
        double totalPerim = 0.0;
        // Start wth prevPt = the last point
        Point prevPt = s.getLastPoint();
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt
            double currDist = prevPt.getDistance(currPt);
            // Update totalPerim by currDist
            totalPerim = totalPerim + currDist;
            // Update prevPt to be currPt
            prevPt = currPt;
        }
        // totalPerim is the answer
        return totalPerim;
    }

    public int getNumPoints (Shape s) {
        return s.getNumPoints();
    }

    public double getAverageLength(Shape s) {
        double perimeter = getPerimeter(s);
        double average = perimeter / s.getNumPoints();
        return average;
    }

    public double getLargestSide(Shape s) {
        double largestSide = 0;
        Point prevPoint = s.getLastPoint();
        for (Point currPoint : s.getPoints()){
            double distance = currPoint.getDistance(prevPoint);
            if (distance > largestSide){
                largestSide = distance;
            }
            prevPoint = currPoint;
        }
        return largestSide;
    }

    public double getLargestX(Shape s) {
        double largestX = 0;
        for (Point point : s.getPoints()){
            double x = point.getX();
            if (x > largestX){
                largestX = x;
            }
        }
        return largestX;
    }

    public double getLargestPerimeterMultipleFiles() throws Exception {
        
        double largestPerimeter = 0;
        File folder = new File("ShapeFolder");
        for (File file : folder.listFiles()){
            
            Shape s = new Shape();
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()){
                String newLine = scanner.nextLine();
                String noSpace = newLine.replace(" ", "");
                String[] coords = noSpace.split(",");

                int x = Integer.valueOf(coords[0]);
                int y = Integer.valueOf(coords[1]);
                s.addPoint(x, y);
            }
            scanner.close();

            double perimeter = getPerimeter(s);
            if (perimeter > largestPerimeter){
                largestPerimeter = perimeter;
            }
        }

        return largestPerimeter;
    }

    public String getFileWithLargestPerimeter() throws Exception {
        double largestPerimeter = 0;
        String largestPerimeterName = " ";
        File folder = new File("ShapeFolder");
        for (File file : folder.listFiles()){
            
            Shape s = new Shape();
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()){
                String newLine = scanner.nextLine();
                String noSpace = newLine.replace(" ", "");
                String[] coords = noSpace.split(",");

                int x = Integer.valueOf(coords[0]);
                int y = Integer.valueOf(coords[1]);
                s.addPoint(x, y);
            }
            scanner.close();

            double perimeter = getPerimeter(s);
            if (perimeter > largestPerimeter){
                largestPerimeter = perimeter;
                largestPerimeterName = file.getName();
            }
        }

        return largestPerimeterName;
    }

    // This method creates a triangle that you can use to test your other methods
    public void triangle(){
        Shape triangle = new Shape();
        triangle.addPoint(0,0);
        triangle.addPoint(6,0);
        triangle.addPoint(3,6);
        for (Point p : triangle.getPoints()){
            System.out.println(p);
        }
        double peri = getPerimeter(triangle);
        System.out.println("perimeter = " + peri);
    }

    // This method prints names of all files in a chosen folder that you can use to test your other methods
    public void printFileNames() {
        File folder = new File("DatabaseFolder");
        File[] files = folder.listFiles();
        
        if (files != null){
            for (File file : files){
                if (file.isFile()){
                    System.out.println(file);
                }
            }
        }
    }
}
