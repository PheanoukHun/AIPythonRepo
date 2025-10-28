import java.awt.*;

public class ShowDesign2 {
	public static void main(String[] args) {
        DrawingPanel dp = new DrawingPanel(900, 300);
        Graphics gr = dp.getGraphics();
        drawRectangles(gr, 5, 900, 300);
    }

    public static void drawRectangles(Graphics gr, int numOfRect, int width, int height) {
        gr.setColor(Color.BLACK);
        
        int verticalStep = height / (numOfRect * 2);
        int horizontalStep = width / (numOfRect * 2);

        for (int i = 1; i < numOfRect; i++) {
            gr.drawRect(i * verticalStep, i * horizontalStep, width - (2 * i * horizontalStep), height - (2 * i * verticalStep));
        }
    }
}