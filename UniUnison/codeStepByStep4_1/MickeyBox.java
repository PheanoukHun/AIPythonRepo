import java.awt.*;

public class MickeyBox {
    public static void main(String[] args) {

        final int WIDTH = 220;
        final int HEIGHT = 150;

        DrawingPanel dp = new DrawingPanel(WIDTH, HEIGHT);
        
        dp.setBackground(Color.YELLOW);
        Graphics gr = dp.getGraphics();
        drawMickeyBox(gr);
    }

    public static void drawMickeyBox(Graphics gr) {

        gr.setColor(Color.BLUE);
        gr.fillOval(50, 25, 40, 40);
        gr.fillOval(130, 25, 40, 40);

        gr.setColor(Color.RED);
        gr.fillRect(70, 45, 80, 80);

        gr.setColor(Color.BLACK);
        gr.drawLine(70, 85, 150, 85);
    }

    public static void drawMickeyBox2(Graphics gr, int x, int y) {
        
        gr.setColor(Color.BLUE);
        gr.fillOval(x, y, 40, 40);
        gr.fillOval(x + 80, y, 40, 40);

        gr.setColor(Color.RED);
        gr.fillRect(x + 20, y + 20, 80, 80);

        gr.setColor(Color.BLACK);
        gr.drawLine(x + 20, y + 60, x + 100, y + 60);
    }
}
