public class Point {
    private int x;
    private int y;

    public Point() {
        x = 0;
        y = 0;
    }

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int quadrant() {
        if (x == 0 || y == 0) {
            return 0;
        } else if (x > 0 && y > 0) {
            return 1;
        } else if (y < 0 && x < 0) {
            return 3;
        } else if (y > 0) {
            return 2;
        } else {
            return 4;
        }
    }

    public void flip() {
        int x_val = this.x * -1;
        this.x = this.y * -1;
        this.y = x_val;
    }

    public int manhattanDistance(Point other) {
        int x_change = Math.abs(Math.abs(this.x) - Math.abs(other.x));
        int y_change = Math.abs(Math.abs(this.y) - Math.abs(other.y));
        return (x_change + y_change);
    }

    public boolean isVertical(Point other) {
        return (this.x == other.x);
    }

    public double slope(Point other) {
        int x_change = other.x - this.x;
        int y_change = other.y - this.y;

        if (x_change == 0) {
            throw new IllegalArgumentException();
        }

        return (((double) y_change) / x_change);
    }

    public boolean isCollinear(Point p1, Point p2) {
        
        // Vertical Line
        if (p1.x == p2.x && p2.x == this.x)
            return true;

        if (p1.y == p2.y && p2.y == this.y)
            return true;

        // Same Slope
        double slope1 = ((this.y - p1.y) * 10000) / (this.x - p1.x);
        double slope2 = ((this.y - p2.y) * 10000) / (this.x - p2.x);
        if (((int) slope1) == ((int) slope2))
            return true;

        return false;
    }
}