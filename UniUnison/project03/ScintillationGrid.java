import java.awt.Graphics;
import java.awt.Color;
import java.io.IOException;

 /*
 * CS312 Assignment 3 - Scintillation Grid
 * 
 * On my honor, <NAME>, this programming assignment is my own work, 
 * I have not shared it with others and I will not share it in the future.
 *
 * A program to print out various scintillation grids and a student designed drawing. 
 *
 *  Name:
 *  UTEID:
 */

public class ScintillationGrid {

    // Main method that creates the DrawingPanel with scintillation grids.
    public static void main(String[] args) {

        final int WIDTH = 950;
        final int HEIGHT = 650;
        DrawingPanel dp = new DrawingPanel(WIDTH, HEIGHT);
        
        // CS312 Students add you four methods calls to draw the four
        // required scintillation grids here.

        
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
}

