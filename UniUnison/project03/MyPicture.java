import java.awt.Graphics;
import java.awt.Color;
import java.io.IOException;

/**
 * CS312 Assignment 3.
 *
 * Replace <NAME> with your name, stating on your honor you completed this
 * assignment on your own and that you didn't share your code with other
 * students.
 * 
 * On my honor, <NAME>, this programming assignment is my own work and I have
 * not shared my solution with any other student in the class.
 *
 * A graphic image of my own design.
 *
 *  email address:
 *  UTEID:
 */

public class MyPicture {

	public static final int WIDTH = 150; // you can change this value
	public static final int HEIGHT = 100; // you can change this value

    // DO NOT ALTER any code in the main() method. 
	public static void main(String[] args) {

		DrawingPanel dp = new DrawingPanel(WIDTH, HEIGHT);
		Graphics gr = dp.getGraphics();

		drawPicture(gr);
		
		try {
			dp.save("picture.png");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	// This is an example graphic. Delete the body of this method and write your own.
	// Do not alter the method header.
	private static void drawPicture(Graphics gr) {
		Color blue = new Color(0, 87, 183);
		gr.setColor(blue);
		gr.fillRect(0, 0, WIDTH, HEIGHT / 2);
		Color yellow = new Color(255, 215, 0);
		gr.setColor(yellow);
		gr.fillRect(0, HEIGHT / 2, WIDTH, HEIGHT / 2);
	}

}
