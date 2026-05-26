import java.awt.*;

public class Squares {
    public static void main(String[] args) {
        DrawingPanel dp = new DrawingPanel(300, 200);
        Graphics gr = dp.getGraphics();
        dp.setBackground(Color.CYAN);
        drawTheSquares(gr, 4, 50, 50, 20);

    }

    public static void drawTheSquares(Graphics gr, int numSquares, int x, int y, int step) {
        
        gr.setColor(Color.RED);
        for (int i = 0; i <= numSquares; i++) {
            gr.drawRect(x, y, i * step, i * step);
        }

        int xValue = (numSquares * step) + x;
        int yValue = (numSquares * step) + y;

        gr.drawLine(x, y, xValue, yValue);
    }
}
