import java.awt.*;

public class Face2 {
    public static void main(String[] args) {
        final int WIDTH = 520;
        final int HEIGHT = 180;

        DrawingPanel dp = new DrawingPanel(WIDTH, HEIGHT);
        Graphics gr = dp.getGraphics();

        drawFace2(gr, 5, 10, 30, 100);
    }

    public static void drawFace2(Graphics gr, int numFaces, int x, int y, int step) {
        for (int i = 0; i < numFaces; i++) {
            gr.setColor(Color.BLACK);
            gr.drawOval(x + step * i, y , step, step);

            gr.setColor(Color.BLUE);
            gr.fillOval((x + 20) + step * i, y + 30, 20, 20);
            gr.fillOval((x + 60) + step * i, y + 30, 20, 20);

            gr.setColor(Color.RED);
            gr.drawLine((x + 30) + step * i, y + 70, (x + 70) + step * i, y + 70);
        }
    }
}
