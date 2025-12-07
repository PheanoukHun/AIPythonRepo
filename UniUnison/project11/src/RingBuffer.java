import java.util.NoSuchElementException;

/**
 * CS312 Assignment 11 - Guitar Hero - RingBuffer.java
 *
 * On my honor, Pheanouk Hun, this programming assignment is my own work,
 * I have not shared it with others and I will not share it in the future.
 *
 * Program that allows two people to play Guitar sounds on a keyboard.
 * 
 * Name: Pheanouk Hun
 * UT EID: ph23434
 * 
 * Description:
 */

public class RingBuffer {

    private double[] buffer;

    private int size;
    private int capacity;

    private int first;
    private int last;

    /**
     * Creates the Ringbuffer Object that contains a queue and can loop around.
     * 
     * @param capacity - The max size of the Double Array.
     */
    public RingBuffer(int capacity) {

        size = 0;
        first = 0;
        last = 0;
        this.capacity = capacity;

        buffer = new double[capacity];

    }

    /**
     * Return the current size of the array.
     * 
     * @return - Return the size value.
     */
    public int size() {
        return size;
    }

    /**
     * Returns the boolean value based on whether the buffer is empty or not.
     * 
     * @return - Returns the boolean based on whether the buffer is empty or not.
     */
    public boolean isEmpty() {
        return (size == 0);
    }

    /**
     * Returns the boolean value based on whether the buffer is full or not.
     * 
     * @return - Returns the boolean based on whether the buffer is full or not.
     */
    public boolean isFull() {
        return (size == capacity);
    }

    /**
     * Adds an element to the buffer/queue at the end.
     * 
     * @param x - The value that is going to be added to the Queue.
     */
    public void enqueue(double x) {

        // IF: the Queue is Full.
        if (isFull()) {
            String prompt = "Cannot call enqueue on a full RingBuffer.";
            throw new IllegalStateException(prompt);
        }

        buffer[last] = x;
        last = (last + 1) % capacity;
        size++;
    }

    /**
     * This method removes the first item from the queue and returns its value
     * 
     * @return - Returns the value of the element that was just removed.
     */
    public double dequeue() {

        checkAndRaiseEmpty("dequeue");

        double selectedItem = buffer[first];
        first = (first + 1) % capacity;
        size--;

        return selectedItem;
    }

    /**
     * This method returns the first queue if it is not empty.
     * 
     * @return - A double that represents the first item in the queue.
     */
    public double peek() {
        checkAndRaiseEmpty("peek");
        return buffer[first];
    }

    /**
     * This method handles errors for empty buffer operations.
     * 
     * @param issue - The String of the method that flagged the problem
     */
    private void checkAndRaiseEmpty(String issue) {

        // IF: the Queue is Empty.
        if (isEmpty()) {
            String prompt = "Cannot call " + issue + " on an empty RingBuffer.";
            throw new NoSuchElementException(prompt);
        }
    }

    /**
     * This method prints out all the elements in the queue.
     * 
     * @return - Returns a String version of the Buffer.
     */
    public String toString() {

        String output = "[";

        // IF: the Queue is not Empty.
        if (!isEmpty()) {
            output += "" + buffer[first];

            // Loop runs once per element in the Queue
            for (int i = 1; i < size; i++) {
                int index = (first + i) % capacity;
                output += ", " + buffer[index];
            }

        }

        output += "]";

        return output;
    }
}
