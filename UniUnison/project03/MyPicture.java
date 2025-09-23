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
 * On my honor, Pheanouk Hun, this programming assignment is my own work and I have
 * not shared my solution with any other student in the class.
 *
 * A graphic image of my own design.
 *
 *  email address: ph23434@eid.utexas.edu
 *  UTEID: ph23434
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
		Color BURNT_ORANGE = new Color(191, 87, 0);
		gr.setColor(BURNT_ORANGE);
		
		gr.fillRect(0, 0, WIDTH, HEIGHT);
	}

}
