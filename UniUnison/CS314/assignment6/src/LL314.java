/*
 * LL314.java - CS 314 Assignment 6
 *
 * By signing my name below, I affirm that this assignment is my own work. I
 * have neither given nor received unauthorized assistance on this assignment.
 *
 * Name:
 * Email address:
 * UTEID:
 */

import java.util.Iterator;

public class LL314<E> implements IList<E> {

    // TODO: Add instance variables here.

    // TODO: Add constructors here.

    // TODO: Add methods here.

    /**
     * Add item to the front of the list.
     * <br>
     * pre: item != null
     * <br>
     * post: size() = old size() + 1, get(0) = item
     *
     * @param item the data to add to the front of this list
     */
    public void addFirst(E item) {
    }

    /**
     * Add item to the end of the list.
     * <br>
     * pre: item != null
     * <br>
     * post: size() = old size() + 1, get(size() -1) = item
     *
     * @param item the data to add to the end of this list
     */
    public void addLast(E item) {
    }

    /**
     * Remove and return the first element of this list.
     * <br>
     * pre: size() > 0
     * <br>
     * post: size() = old size() - 1
     *
     * @return the old first element of this list
     */
    public E removeFirst() {
        return null;
    }

    /**
     * remove and return the last element of this list.
     * <br>
     * pre: size() > 0
     * <br>
     * post: size() = old size() - 1
     *
     * @return the old last element of this list
     */
    public E removeLast() {
        return null;
    }

    /**
     * A class that represents a node to be used in a linked list.
     * These nodes are doubly linked.
     *
     * @author Mike Scott
     * @version July 25, 2005
     */

    private static class DoubleListNode<E> {

        // the data to store in this node
        private E data;

        // the link to the next node (presumably in a list)
        private DoubleListNode<E> next;
`
        // the reference to the previous node (presumably in a list)
        private DoubleListNode<E> prev;

        /**
         * default constructor.
         * <br>
         * pre: none
         * <br>
         * post: data = null, next = null, prev = null
         * <br>
         * O(1)
         */
        public DoubleListNode() {
            this(null, null, null);
        }

        /**
         * Create a DoubleListNode that holds the specified data
         * and refers to the specified next and previous elements.
         * <br>
         * pre: none
         * <br>
         * post: this.data = data, this.next = next, this.prev = prev
         * <br>
         * O(1)
         *
         * @param prev the previous node
         * @param data the data this DoubleListNode should hold
         * @param next the next node
         */
        public DoubleListNode(DoubleListNode<E> prev, E data, DoubleListNode<E> next) {
            this.prev = prev;
            this.data = data;
            this.next = next;
        }
    }

    public void add(E item) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'add'");

        DoubleListNode<E> newNode = new DoubleListNode<>(this.last);
    }

    public void insert(int pos, E item) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'insert'");
    }

    public E set(int pos, E item) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'set'");
    }

    public E get(int pos) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'get'");
    }

    public E remove(int pos) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'remove'");
    }

    public boolean remove(E obj) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'remove'");
    }

    public IList<E> getSubList(int start, int stop) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getSubList'");
    }

    public int size() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'size'");
    }

    public int indexOf(E item) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'indexOf'");
    }

    public int indexOf(E item, int pos) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'indexOf'");
    }

    public void makeEmpty() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'makeEmpty'");
    }

    public Iterator<E> iterator() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'iterator'");
    }

    public void removeRange(int start, int stop) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'removeRange'");
    }
}