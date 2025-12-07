import java.lang.Math;

/**
 * CS312 Assignment 11 - Guitar Hero - GuitarString.java
 *
 * On my honor, Pheanouk Hun, this programming assignment is my own work,
 * I have not shared it with others and I will not share it in the future.
 *
 * Program that allows two people to play Guitar sounds on a keyboard.
 * 
 * Name: Pheanouk Hun
 * UT EID: ph23434
 * 
 * Description: This Class creates a GuitarString object that contains
 * implements all the funciton of the Guitar String such as plucking, sampling,
 * and tics.
 */

public class GuitarString {

    private final int SAMPLING_RATE = 44100;
    private final double ENERGY_DECAY_FACTOR = 0.994;
    private RingBuffer string;
    private int numOfTicks = 0;

    /**
     * This method takes in a frequency and creates a GuitarString object
     * 
     * @param frequency - The frequency in which the string vibrates at.
     */
    public GuitarString(double frequency) {
        int desiredCapacity = (int) Math.ceil(SAMPLING_RATE / frequency);;
        string = new RingBuffer(desiredCapacity);

        // Loop to Fill the Queue
        for (int i = 0; i < desiredCapacity; i++) {
            string.enqueue(0.0);
        }
    }

    /**
     * Creates a String object initialized with the provided array and enqueueing
     * it.
     * 
     * @param init - An Double Array that contains all the values in the double
     *             array.
     */
    public GuitarString(double[] init) {
        string = new RingBuffer(init.length);

        // Loop to Fill the Queue
        for (int i = 0; i < init.length; i++) {
            string.enqueue(init[i]);
        }
    }

    /**
     * Replaces the buffer contents with random noise. THis simulates plucking the
     * string.
     */
    public void pluck() {

        // Loop runs once per Entry
        for (int i = 0; i < string.size(); i++) {
            string.dequeue();
            string.enqueue(Math.random() - 0.5);
        }
    }

    /**
     * Advances the simulation by one. Applies the Karplus-String update step.
     */
    public void tic() {
        numOfTicks++;
        double first = string.dequeue();
        double newFirst = string.peek();
        double newValue = ((first + newFirst) / 2) * ENERGY_DECAY_FACTOR;
        string.enqueue(newValue);

    }

    /**
     * Return the first element in the array as the sample of the string.
     * 
     * @return - Returns a double value containing the first element in the queue.
     */
    public double sample() {
        return string.peek();
    }

    /**
     * Returns the number of tics that have occurred.
     * 
     * @return - Returns an int that represents the number of times the string has
     *         been ticked.
     */
    public int time() {
        return numOfTicks;
    }

}
