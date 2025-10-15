import java.awt.*;

public class ShowDesign {
    public static void main(String[] args) {
        
        DrawingPanel dp = new DrawingPanel(200, 200);
        Graphics gr = dp.getGraphics();

        for (int i = 0; i < 4; i++) {
            gr.drawRect(20 + (i * 20), 20 + (i * 20), 160 - (i * 40), 160 - (i * 40));
        }
    }
}