import java.awt.*;

public class MickeyBox {
    public static void main(String[] args) {

        final int WIDTH = 220;
        final int HEIGHT = 150;

        DrawingPanel dp = new DrawingPanel(WIDTH, HEIGHT);
        drawMickeyBox(dp);
    }

    public static void drawMickeyBox(DrawingPanel dp) {
        Graphics gr = dp.getGraphics();
        dp.setBackground(Color.YELLOW);

        gr.setColor(Color.BLUE);
        gr.fillOval(50, 25, 40, 40);
        gr.fillOval(130, 25, 40, 40);

        gr.setColor(Color.RED);
        gr.fillRect(70, 45, 80, 80);

        gr.setColor(Color.BLACK);
        gr.drawLine(70, 85, 150, 85);
    }
}
