import java.awt.*;

public class ShowDesign2 {

    public static final int WIDTH = 300;
    public static final int HEIGHT = 100;

    public static void main(String[] args) {
        DrawingPanel dp = new DrawingPanel(WIDTH, HEIGHT);
        Graphics gr = dp.getGraphics();

        showDesign(gr, WIDTH, HEIGHT);
    }

    public static void showDesign(Graphics gr, int width, int height) {
        final int RECT_COUNT = 4;
        final int GAP = 20;

        // Determine the maximum rectangle size that fits the window
        int maxWidth = width - GAP;
        int maxHeight = height - GAP;

        // Draw the rectangles centered in the panel
        for (int i = 0; i < RECT_COUNT; i++) {
            int rectWidth = maxWidth - i * 2 * GAP;
            int rectHeight = maxHeight - i * 2 * GAP;

            int x = (width - rectWidth) / 2;
            int y = (height - rectHeight) / 2;

            gr.drawRect(x, y, rectWidth, rectHeight);
        }
    }
}
