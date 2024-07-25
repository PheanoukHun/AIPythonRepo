import java.util.ArrayList;

public class Shape {

    private ArrayList<Point> pointList = new ArrayList<>();

    public void addPoint(int x, int y){
        Point newPoint = new Point(x, y);
        pointList.add(newPoint);
    }

    public int getNumPoints(){
        return pointList.size();
    }

    public Point getLastPoint(){
        return pointList.get(pointList.size() - 1);
    }

    public Point[] getPoints(){
        return pointList.toArray(new Point[0]);
    }
}
