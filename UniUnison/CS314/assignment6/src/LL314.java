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

    private DoubleListNode<E> first;
    private DoubleListNode<E> last;
    private int size;

    public LL314() {
        this.first = null;
        this.last = null;
        this.size = 0;
    }

    /**
     * Add item to the front of the list.
     * 
     * pre: item != null
     * post: size() = old size() + 1, get(0) = item
     *
     * @param item the data to add to the front of this list
     */
    public void addFirst(E item) {

        // Precondition
        if (item == null) {
            throw new IllegalArgumentException("The Value of the new Element cannot be null");
        }

        DoubleListNode<E> newNode = new DoubleListNode<>(null, item, this.first);

        if (this.first != null) {
            this.first.prev = newNode;
        }

        this.size++;
    }

    /**
     * Add item to the end of the list.
     * pre: item != null
     * post: size() = old size() + 1, get(size() -1) = item
     *
     * @param item the data to add to the end of this list
     */
    public void addLast(E item) {

        // Precondition
        if (item == null) {
            throw new IllegalArgumentException("The Value of the new Element cannot be null");
        }

        DoubleListNode<E> newNode = new DoubleListNode<>(this.last, item, null);

        if (this.last != null) {
            this.last.next = newNode;
        }

        this.size++;
    }

    /**
     * Remove and return the first element of this list.
     * 
     * pre: size() > 0
     * post: size() = old size() - 1
     *
     * @return the old first element of this list
     */
    public E removeFirst() {

        // Precondition
        if (this.size == 0) {
            throw new IllegalStateException("You cannot Remove an Item from an Empty List");
        }

        E data = this.first.data;
        this.first = this.first.next;
        this.size--;
        return data;
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

        // Precondition
        if (this.size == 0) {
            throw new IllegalStateException("You cannot Remove an Item from an Empty List");
        }

        E data = this.last.data;
        this.last = this.last.prev;
        this.size--;
        return data;
    }

    /**
     * Add an item to the end of this list.
     * pre: item != null
     * post: size() = old size() + 1, get(size() - 1) = item
     *
     * @param item the data to be added to the end of this list, item != null
     */
    public void add(E item) {

        // Precondition
        if (item == null) {
            throw new IllegalArgumentException("The Value of the new Element cannot be null");
        }

        addLast(item);
    }

    /**
     * Insert an item at a specified position in the list. All elements in the list
     * with a position >= pos have a position = old position + 1
     * 
     * pre: 0 <= pos <= size(), item != null
     * post: size() = old size() + 1, get(pos) = item,
     *
     * @param pos  the position to insert the data at in the list
     * @param item the data to add to the list, item != null
     */
    public void insert(int pos, E item) {

        // Precondition
        if (item == null || pos > this.size || pos < 0) {
            throw new IllegalArgumentException("The Value of the new Element cannot be null"
                    + "or position value must be between 0 and the size of the list");
        }

        if (pos == 0) {
            addFirst(item);
        } else if (pos == this.size) {
            addLast(item);
        } else {
            DoubleListNode<E> oldNode = this.getNodeAtPos(pos);
            DoubleListNode<E> newNode = new DoubleListNode<>(oldNode.prev, item, oldNode);
            oldNode.prev = newNode;
            this.size++;
        }
    }

    /**
     * Change the data at the specified position in the list.
     * The old data at that position is returned.
     * 
     * pre: 0 <= pos < size(), item != null
     * post: get(pos) = item, return the old get(pos)
     *
     * @param pos  the position in the list to overwrite
     * @param item the new item that will overwrite the old item, item != null
     * @return the old data at the specified position
     */
    public E set(int pos, E item) {

        // Precondition
        if (item == null || pos > this.size || pos < 0) {
            throw new IllegalArgumentException("The Value of the new Element cannot be null"
                    + "or position value must be between 0 and the size of the list");
        }

        DoubleListNode<E> node = this.getNodeAtPos(pos);

        E oldData = node.data;
        node.data = item;

        return oldData;
    }

    public E get(int pos) {
        return getNodeAtPos(pos).data;
    }

    /**
     * Remove an element in the list based on position. all elements of list with a
     * position > pos have a position = old position - 1
     * 
     * pre: 0 <= pos < size()
     * post: size() = old size() - 1
     *
     * @param pos the position of the element to remove from the list
     * @return the data at position pos
     */
    public E remove(int pos) {

        // Precondition
        if (this.size == 0) {
            throw new IllegalStateException("You cannot Remove an Item from an Empty List");
        } else if (pos >= this.size || pos < 0) {
            throw new IllegalArgumentException("Position value must be between"
                    + " 0 and the size of the list");
        }

        // Removing from the Front or Back
        if (pos == 0) {
            return this.removeFirst();
        } else if (pos == this.size) {
            return this.removeLast();
        }

        // Removing From the Middle
        DoubleListNode<E> node = this.getNodeAtPos(pos);
        E oldVal = node.data;

        node.prev.next = node.next;
        node.next.prev = node.prev;
        this.size--;

        return oldVal;
    }

    public boolean remove(E obj) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'remove'");
    }

    /**
     * Return a sublist of elements in this list from <tt>start</tt> inclusive to
     * stop exclusive. This list is not changed as a result of this call. elements
     * at positions start through stop - 1 in this list.
     * 
     * pre: <tt>0 <= start <= size(), start <= stop <= size()</tt>
     * post: return a list whose size is stop - start and contains the
     *
     * @param start index of the first element of the sublist.
     * @param stop  stop - 1 is the index of the last element of the sublist.
     * @return a list with <tt>stop - start</tt> elements, the elements are
     *         from positions <tt>start</tt> inclusive to <tt>stop</tt> exclusive in
     *         this list. If start == stop an empty list is returned.
     */
    public IList<E> getSubList(int start, int stop) {

        // Preconditions
        if (start > stop || start < 0 || stop > this.size) {
            throw new IllegalArgumentException("start and stop must satisfy: "
                    + "0 <= start <= size() and start <= stop <= size().");
        }

        // Creating a New Result
        LL314<E> result = new LL314<>();
        
        result.first = this.getNodeAtPos(start);
        result.last = this.getNodeAtPos(stop);
        result.size = stop - start;
        
        return result;
    }

    /**
     * Return the size of this list (the number of elements).
     * 
     * @return the number of items in this list
     */
    public int size() {
        return this.size;
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

    private DoubleListNode<E> getNodeAtPos(int pos) {

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
}