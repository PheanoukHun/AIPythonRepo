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

    // Purpose: Draws a Scintillation Grid of Various Sizes Depending on the Given Variables
    // Parameters: gr - graphics object, x - left corner of the grid, y - top corner of the grid,
    //             gridSize - Width of the Actual Size of the Grid, smallSize - Size of Small Box,
    //             numLines - Number of Lines in the Grid, lineWidth - Width of Each Line
    // Output: Draws a Scintillation Grid with the given variables
    // Return: none
    public static void drawScintillationGrid(Graphics gr, int x, int y, int gridSize,
                                                int smallSize, int numLines, int lineWidth) {
        int firstLineX = x + smallSize;
        int firstLineY = y + smallSize;
        int stepSize = smallSize + lineWidth;

        gr.setColor(Color.BLACK);
        gr.fillRect(x, y, gridSize, gridSize);

        drawVerticalLines(gr, firstLineX, y, lineWidth, gridSize, numLines, stepSize);
        drawHorizontalLines(gr, x, firstLineY, lineWidth, gridSize, numLines, stepSize);
        drawDots(gr, firstLineX, firstLineY, lineWidth, numLines, stepSize);
    }

    // Purpose: Draw vertical gray lines for one grid
    // Parameters: gr - graphics object, x - starting x coordinate, y - top y coordinate,
    //             lineWidth - width of each vertical line, lineLenght - length of each line,
    //             numLines - number of lines to draw, steps - distance between each line
    // Output: Draws vertical rectangles on the grid
    // Return: none
    public static void drawVerticalLines(Graphics gr, int x, int y, int lineWidth,
                                            int lineLength, int numLines, int steps) {

        gr.setColor(Color.GRAY);

        // Loop runs once per vertical line
        for (int i = 0; i < numLines; i++) {
            gr.fillRect(x, y, lineWidth, lineLength);
            x += steps;
        }
    }

    // Purpose: Draw Horizontal gray lines for one grid
    // Parameters: gr - graphics object, x - x coordinate, y - starting y coordinate,
    //             lineWidth - width of each vertical line, lineLenght - length of each line,
    //             numLines - how many lines to draw, steps - distance between each line
    // Output: Draws horizontal rectangles on the grid
    // Return: none
    public static void drawHorizontalLines(Graphics gr, int x, int y, int lineWidth,
                                            int lineLenght, int numLines, int steps) {

        gr.setColor(Color.GRAY);

        // Loop runs once per Horizontal Line
        for (int i = 0; i < numLines; i++) {
            gr.fillRect(x, y, lineLenght, lineWidth);
            y += steps;
        }
    }

    // Purpose: Draws a Grid of Dots in the Intersection of the Vertical and Horizontal Lines
    // Parameters: gr - graphics object, x - starting x coordinate, y - starting y coordinate,
    //             lineWidth - Width of the Lines used to determin dot size,
    //             numDotsLinePerLine - The Number of Dots Per Line and number of Lines,
    //             steps - distance between each dots up and down
    // Output: Draws a grid of with numDotsLine by numDotsLine as the Dimensions
    // Return: none
    public static void drawDots(Graphics gr, int x, int y, int lineWidth,
                                    int numDotsLinePerLine, int steps) {

        final int MIN_EXTRA_DOT = 4;
        final double DOT_SCALE = 0.4;

        int dotPadding = Math.max((int) (lineWidth * DOT_SCALE), MIN_EXTRA_DOT);
        int dotDiameter = lineWidth + dotPadding;
        int initialDotX = x - dotPadding / 2;
        int initialDotY = y - dotPadding / 2;

        gr.setColor(Color.LIGHT_GRAY);

        // Outer Loop Runs Once per Row of Dots
        for (int i = 0; i < numDotsLinePerLine; i++) {
            
            int currX = initialDotX;

            // Inner Loop runs once per Dot in the Row
            for (int j = 0; j < numDotsLinePerLine; j++) {
                gr.fillOval(currX, initialDotY, dotDiameter, dotDiameter);
                currX += steps;
            }

            initialDotY += steps;
        }
    }
}

