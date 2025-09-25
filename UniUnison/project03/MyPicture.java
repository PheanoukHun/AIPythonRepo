import java.awt.Graphics;
import java.awt.Color;
import java.io.IOException;

/**
 * CS312 Assignment 3.
 *
 * Replace Pheanouk Hun with your name, stating on your honor you completed this
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

	public static final int WIDTH = 1350; // you can change this value
	public static final int HEIGHT = 750; // you can change this value

	public static final Color WHITE_COLOR = new Color(243, 243, 243);
	public static final Color RED_COLOR = new Color(226, 30, 42);
	public static final Color BLUE_COLOR = new Color(36, 61, 151);
	public static final Color BLACK_COLOR = new Color(0, 0, 0);

	public static final int PIXEL = 50;

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
	// Purpose: Draws Both Half of the Flags
	// Parameters: gr - graphics object
	// Output: Draws the American and Cambodian Flags
	// Return: none
	private static void drawPicture(Graphics gr) {
		gr.setColor(BLACK_COLOR);
		gr.fillRect(0, 0, WIDTH, HEIGHT);

		drawAmericanSide(gr);
		drawCambodianSide(gr);
	}

	// Purpose: This method draws the American Side of the Function
	// Parameters: gr - graphics object
	// Output: Draws the American Side of the Flags
	// Return: none
	public static void drawAmericanSide(Graphics gr) {
		drawStripes(gr);
		drawBlueSection(gr);
		drawStarSection(gr); 
	}

	// Purpose: This method draws the Blue Square of the American Flag
	// Parameters: gr - graphics object
	// Output: Draws the Blue Square in the Top Left Portion of the Flag.
	// Return: none
	public static void drawBlueSection(Graphics gr) {
		int blueSectionX = PIXEL;
		int blueSectionY = PIXEL;
		int blueSectionDimension = 7 * PIXEL;

		gr.setColor(BLUE_COLOR);
		gr.fillRect(blueSectionX, blueSectionY, blueSectionDimension, blueSectionDimension);
	}

	// Purpose: This Method draws the Stars in the Top-Left portion of the American Flag
	// Parameters: gr - graphics object
	// Output: This method draws 13 boxes to the Top-Left Corner of the American Flag
	// Return: none
	public static void drawStarSection(Graphics gr) {

		int stepSize = 2 * PIXEL;

		int startingX = 2 * PIXEL;
		int startingY = 2 * PIXEL;
		drawOddStars(gr, startingX, startingY, stepSize);

		startingX = 3 * PIXEL;
		startingY = 3 * PIXEL;
		drawEvenStars(gr, startingX, startingY, stepSize);
	}

	// Purpose: This method draws the Odd numbered stars in the Top-Left Portion of the American Flag
	// Parameters: gr - graphics object
	// Output: This method draws 9 Stars to the Flag in the Top-Left Corner of the American Flag.
	// Return: none
	public static void drawOddStars(Graphics gr, int startingX, int startingY, int stepSize) {
		int numOddRows = 3;
		int numOddStars = 3;

		gr.setColor(WHITE_COLOR);

		int currX = startingX;
		int currY = startingY;

		// Outer Loop Runs Once per Row of Stars
		for (int i = 0; i < numOddRows; i++) {

			// Inner Loop runs once per Stars Being Print
			for (int j = 0; j < numOddStars; j++) {
				gr.fillRect(currX, currY, PIXEL, PIXEL);
				currX += stepSize;
			}
			currY += stepSize;
			currX = startingX;
		}
	}

	// Purpose: This method draws the Even numbered stars in the Top-Left Portion of the American Flag
	// Parameters: gr - graphics object
	// Output: This method draws 4 Stars to the Flag in the Top-Left Corner of the American Flag.
	// Return: none
	public static void drawEvenStars(Graphics gr, int startingX, int startingY, int stepSize) {
		int numEvenRows = 2;
		int numEvenStars = 2;

		gr.setColor(WHITE_COLOR);

		int currX = startingX;
		int currY = startingY;
		
		// Outer Loop Runs Once per Row of Stars
		for (int i = 0; i < numEvenRows; i++) {

			// Inner Loop runs once per Stars Being Print
			for (int j = 0; j < numEvenStars; j++) {
				gr.fillRect(currX, currY, PIXEL, PIXEL);
				currX += stepSize;
			}

			currY += stepSize;
			currX = startingX;
		}
	}

	// Purpose: This method draws the 13 White and Red Strips in the American Flag
	// Parameters: gr - graphics object
	// Output: This method draws 13 Stripe to the American Flag.
	// Return: none
	public static void drawStripes(Graphics gr) {
		int yValue = PIXEL;
		int separation = 2 * PIXEL;
		int numRedStripes = 7;
		int numWhiteStripes = 6;
		int stripeWidth = PIXEL * 12;

		gr.setColor(RED_COLOR);

		// The Loop Runs Once per red stripe Shown
		for (int i = 0; i < numRedStripes; i++) {
			gr.fillRect(PIXEL, yValue, stripeWidth, PIXEL);
			yValue += separation;
		}

		yValue = 2 * PIXEL;

		gr.setColor(WHITE_COLOR);

		// The Loop Runs Once per White Stripe Shown
		for (int i = 0; i < numWhiteStripes; i++) {
			gr.fillRect(PIXEL, yValue, stripeWidth, PIXEL);
			yValue += separation;
		}
	}

	// Purpose: This method draws the Cambodian Flag
	// Parameters: gr - graphics object
	// Output: This method draws half the Cambodian Flag on the Right side of the Screen
	// Return: none
	public static void drawCambodianSide(Graphics gr) {
		int flagStartX = 14 * PIXEL;
		drawCambodianBackground(gr, flagStartX);
		drawAngkorWat(gr, flagStartX);
	}

	// Purpose: This method draws the Background Cambodian Flag
	// Parameters: gr - graphics object
	// Output: This method draws the 2 Blue Banners and Red background of the Flag.
	// Return: none
	public static void drawCambodianBackground(Graphics gr, int startingX) {
		int stripeWidth = 12 * PIXEL;
		int blueStripeHeight = 2 * PIXEL;

		int blueStripeTwoY = 12 * PIXEL;

		int redStripeY = 3 * PIXEL;
		int redStripeHeight = PIXEL * 9;

		gr.setColor(BLUE_COLOR);
		gr.fillRect(startingX, PIXEL, stripeWidth, blueStripeHeight);
		gr.fillRect(startingX, blueStripeTwoY, stripeWidth, blueStripeHeight);

		gr.setColor(RED_COLOR);
		gr.fillRect(startingX, redStripeY, stripeWidth, redStripeHeight);
	}

	// Purpose: Draws the Angkor Wat structure in the center of the Cambodian flag.
	// Parameters: gr - graphics object, startingX - x coordinate of the left side of Angkor Wat
	// Output: Draws the base rectangle, staircase, and towers of Angkor Wat
	// Return: none
	public static void drawAngkorWat(Graphics gr, int startingX) {
		
		int middleSectionY = 7 * PIXEL;
		int middleBaseWidth = 8 * PIXEL;
		int middleBaseHeight = 4 * PIXEL;

		gr.setColor(WHITE_COLOR);
		gr.fillRect(startingX, middleSectionY, middleBaseWidth, middleBaseHeight);

		int staircaseX = startingX + middleBaseWidth;
		int staircaseY = middleSectionY + PIXEL;
		drawStaircase(gr, staircaseX, staircaseY);

		drawTowers(gr, startingX);
	}

	// Purpose: Draws a staircase structure next to the Angkor Wat base on the Cambodian flag.
	// Parameters: gr - graphics object
	//             startingX - x coordinate of the left side of the staircase
	//             startingY - y coordinate of the top of the staircase
	// Output: Draws three levels of the staircase, each level wider than the one above.
	// Return: none

	public static void drawStaircase(Graphics gr, int startingX, int startingY) {
		
		int levels = 3;
		gr.setColor(WHITE_COLOR);

		// Loop runs once per staircase level
		for (int i = 1; i <= levels; i++) {
			int currX = startingX;

			// Loop runs once per block in the current level
			for (int j = 0; j < i; j++) {
				gr.fillRect(currX, startingY, PIXEL, PIXEL);
				currX += PIXEL;
			}
			startingY += PIXEL;
		}
	}

	// Purpose: Draws the two central towers of Angkor Wat on the Cambodian flag.
	// Parameters: gr - graphics object
	//             startingX - x coordinate of the left side of the temple base
	// Output: Draws two towers, each with a rectangular base and a single block top.
	// Return: none

	public static void drawTowers(Graphics gr, int startingX) {
		gr.setColor(WHITE_COLOR);

		int towerOneX = startingX + PIXEL;
		int towerOneWallY = 5 * PIXEL;
		int towerWidth = 3 * PIXEL;
		int towerOneWallHeight = 2 * PIXEL;	
		gr.fillRect(towerOneX, towerOneWallY, towerWidth, towerOneWallHeight);

		int towerOneTopX = towerOneX + PIXEL;
		int towerOneTopY = towerOneWallY - PIXEL;
		gr.fillRect(towerOneTopX, towerOneTopY, PIXEL, PIXEL);

		int towerTwoX = towerOneX + (4 * PIXEL);
		int towerTwoWallY = towerOneWallY + PIXEL;
		gr.fillRect(towerTwoX, towerTwoWallY, towerWidth, PIXEL);

		int towerTwoTopX = towerTwoX + PIXEL;
		int towerTwoTopY = towerTwoWallY - PIXEL;
		gr.fillRect(towerTwoTopX, towerTwoTopY, PIXEL, PIXEL);
		
	}
}
