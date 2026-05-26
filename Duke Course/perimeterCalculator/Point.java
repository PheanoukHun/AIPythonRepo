public class Point {

    private int x;
    private int y;

    Point(int xVal, int yVal){
        x = xVal;
        y = yVal;
    }

    public void changeCoords(int xValue, int yValue){
        x = xValue;
        y = yValue;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public double getDistance(Point otherPoint){
        
        double distance;
        int xDiff = x - otherPoint.getX();
        int yDiff = y - otherPoint.getY();

        double squaredDistance = Math.pow(xDiff, 2) + Math.pow(yDiff, 2);
        distance = Math.sqrt(squaredDistance);
        
        return distance;
    }
}
