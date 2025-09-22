import java.awt.Graphics;
import java.awt.Color;
import java.io.IOException;

/*
 * CS312 Assignment 3 - Scintillation Grid
 *
 * On my honor, Pheanouk Hun, this programming assignment is my own work,
 * I have not shared it with others and I will not share it in the future.
 *
 * A program to print out various scintillation grids and a student designed drawing.
 *
 *  Name: Pheanouk Hun
 *  UTEID: ph23434
 */

public class ScintillationGrid {

    // Main method that creates the DrawingPanel with scintillation grids.
    public static void main(String[] args) {

        final int WIDTH = 950;
        final int HEIGHT = 650;

        DrawingPanel dp = new DrawingPanel(WIDTH, HEIGHT);
        Graphics gr = dp.getGraphics();
        
        // CS312 Students add you four methods calls to draw the four
        // required scintillation grids here.

        dp.setBackground(Color.CYAN);
        drawScintillationGrid(gr, 0, 0, 348, 75, 3, 16);
        drawScintillationGrid(gr, 400, 50, 422, 50, 6, 12);
        drawScintillationGrid(gr, 50, 400, 220, 100, 1, 20);
        drawScintillationGrid(gr, 500, 500, 148, 15,7, 4);
        
        // CS312 students, do not alter the following line of code.
        saveDrawingPanel(dp, "grid.png");
    }

    
    // Save the current drawing panel to the given file.
    // CS312 Students, do not alter this method.
    public static void saveDrawingPanel(DrawingPanel dp, String fileName) {
        try {
            dp.save(fileName);
        } catch (IOException e) {
            System.out.println("Unable to save DrawingPanel");
        }
    }

    public static void drawScintillationGrid(Graphics gr, int x, int y, int sizeOfLarge, int sizeOfSmall, int numberOfLines, int thicknessOfLines) {

        gr.setColor(Color.BLACK);
        gr.fillRect(x, y, sizeOfLarge, sizeOfLarge);

        int initialVerticalX = x + sizeOfSmall;
        int initialHorizontalY = y + sizeOfSmall;
        int stepSize = sizeOfSmall + thicknessOfLines;

        int extraDot = (int) (thicknessOfLines * 1.4);
        int dotDiameter = thicknessOfLines + extraDot;

        int intialDotX = initialVerticalX - extraDot / 2;
        int intialDotY = initialHorizontalY - extraDot / 2;

        drawVerticalLines(gr, initialVerticalX, y, thicknessOfLines, sizeOfLarge, numberOfLines, stepSize);
        drawHorizontalLines(gr, x, initialHorizontalY, thicknessOfLines, sizeOfLarge, numberOfLines, stepSize);
        drawDots(gr, intialDotX, intialDotY, dotDiameter, numberOfLines, stepSize);
    }

    public static void drawVerticalLines(Graphics gr, int x, int y, int thicknessOfLines, int lineLength, int numberOfLines, int steps) {
        gr.setColor(Color.GRAY);
        for (int i = 0; i < numberOfLines; i++) {
            gr.fillRect(x, y, thicknessOfLines, lineLength);
            x += steps;
        }
    }

    public static void drawHorizontalLines(Graphics gr, int x, int y, int thicknessOfLines, int lineLength, int numberOfLines, int steps) {
        gr.setColor(Color.GRAY);
        for (int i = 0; i < numberOfLines; i++) {
            gr.fillRect(x, y, lineLength, thicknessOfLines);
            y += steps;
        }
    }

    public static void drawDots(Graphics gr, int x, int y, int dotDiameter, int numberOfLines, int steps) {
        
        int initialX = x;
        
        gr.setColor(Color.LIGHT_GRAY);

        for (int i = 0; i < numberOfLines; i++) {
            
            for (int j = 0; j < numberOfLines; j++) {
                gr.fillOval(x, y, dotDiameter, dotDiameter);
                x += steps;
            }
            x = initialX;
            y += steps;
        }
    }
}

