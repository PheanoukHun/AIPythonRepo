/*
 * LL314.java - CS 314 Assignment 6
 *
 * By signing my name below, I affirm that this assignment is my own work. I
 * have neither given nor received unauthorized assistance on this assignment.
 *
 * Name: Pheanouk Hun
 * Email address: ph23434@eid.utexas.edu
 * UTEID: ph23434
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
     * Return the size of this list (the number of elements).
     * 
     * @return the number of items in this list
     */
    public int size() {
        return this.size;
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

        DoubleListNode<E> newNode;

        // When the LinkedList is Empty
        if (this.first != null) {
            newNode = new DoubleListNode<>(null, item, this.first);
            this.first.prev = newNode;
        } else {
            newNode = new DoubleListNode<>(null, item, null);
            this.last = newNode;
        }

        this.first = newNode;
        this.size++;
    }

    /**
     * Add item to the end of the list.
     * 
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

        DoubleListNode<E> newNode;

        // When are LinkedLists are Empty
        if (this.last != null) {
            newNode = new DoubleListNode<>(this.last, item, null);
            this.last.next = newNode;
        } else {
            newNode = new DoubleListNode<>(null, item, null);
            this.first = newNode;
        }

        this.last = newNode;
        this.size++;
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
            // Insert in front
            addFirst(item);
        } else if (pos == this.size) {
            // Insert at the End
            addLast(item);
        } else {
            // Insert in the Middle
            DoubleListNode<E> oldNode = this.getNodeAtPos(pos);
            DoubleListNode<E> newNode = new DoubleListNode<>(oldNode.prev, item, oldNode);

            oldNode.prev.next = newNode;
            oldNode.prev = newNode;
            this.size++;
        }
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

        if (this.size == 1) {
            this.first = null;
            this.last = null;
        } else {
            this.first = this.first.next;
            this.first.prev = null;
        }

        this.size--;
        return data;
    }

    /**
     * remove and return the last element of this list.
     * 
     * pre: size() > 0
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

        if (this.size == 1) {
            this.first = null;
            this.last = null;
        } else {
            this.last = this.last.prev;
            this.last.next = null;
        }

        this.size--;
        return data;
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
        } else if (pos == this.size - 1) {
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

    /**
     * Remove the first occurrence of obj in this list. Return true if this list
     * changed as a result of this call, false otherwise. If obj is not present the
     * list is not altered in any way.
     * 
     * pre: obj != null
     * post: if obj is in this list the first occurrence has been removed and size()
     * = old size() - 1.
     *
     * @param obj The item to remove from this list. obj != null
     * @return Return true if this list changed as a result of this call, false
     *         otherwise.
     */
    public boolean remove(E obj) {

        // Preconditions
        if (obj == null) {
            throw new IllegalArgumentException("The Value of the new Element cannot be null");
        }

        // Check for Object Existence
        int index = this.indexOf(obj);

        if (index == -1) {
            return false;
        }

        // Remove Object from List
        this.remove(index);
        return true;
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
        if (item == null || pos >= this.size || pos < 0) {
            throw new IllegalArgumentException("The Value of the new Element cannot be null"
                    + "or position value must be between 0 and the size of the list");
        }

        DoubleListNode<E> node = this.getNodeAtPos(pos);

        E oldData = node.data;
        node.data = item;

        return oldData;
    }

    /**
     * Get an element from the list.
     * 
     * pre: 0 <= pos < size()
     * post: return the item at pos
     *
     * @param pos specifies which element to get
     * @return the element at the specified position in the list
     */
    public E get(int pos) {
        return getNodeAtPos(pos).data;
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

        if (start != stop) {
            DoubleListNode<E> node = this.getNodeAtPos(start);
            int counter = start;

            while (counter < stop) {
                result.add(node.data);
                node = node.next;
                counter++;
            }
        }

        return result;
    }

    /**
     * Find the position of an element in the list.
     * 
     * pre: item != null
     * post: return the index of the first element equal to item or -1 if item is
     * not present
     *
     * @param item the element to search for in the list. item != null
     * @return return the index of the first element equal to item or a -1 if
     *         item is not present
     */
    public int indexOf(E item) {

        // Precondition
        if (item == null) {
            throw new IllegalArgumentException("The Value of the new Element cannot be null");
        }

        return this.indexOf(item, 0);
    }

    /**
     * Find the position of an element in the list starting at a specified
     * position.
     * <br>
     * pre: 0 <= pos < size(), item != null
     * <br>
     * post: return the index of the first element equal to item starting
     * at pos or -1 if item is not present from position pos onward.
     *
     * @param item the element to search for in the list. Item != null
     * @param pos  the position in the list to start searching from
     * @return starting from the specified position return the index of the
     *         first element equal to item or a -1 if item is not present between
     *         pos
     *         and the end of the list.
     */
    public int indexOf(E item, int pos) {

        // Precondition
        if (item == null) {
            throw new IllegalArgumentException("The Value of the new Element cannot be null");
        } else if (pos < 0 || pos >= this.size) {
            throw new IllegalArgumentException("You must give a position value that must "
                    + "between 0 and size.");
        }

        int counter = pos;
        DoubleListNode<E> currNode = getNodeAtPos(pos);
        while (currNode != null && !currNode.data.equals(item)) {
            currNode = currNode.next;
            counter++;
        }

        if (currNode != null) {
            return counter;
        }

        return -1;
    }

    /**
     * Transform the list to an empty state.
     * pre: none
     * post: size() = 0
     */
    public void makeEmpty() {
        this.first = null;
        this.last = null;
        this.size = 0;
    }

    public Iterator<E> iterator() {
        return new LL314Iterator();
    }

    /**
     * Remove all elements in this list from start inclusive to stop exclusive.
     * <br>
     * pre: <tt>0 <= start <= size(), start <= stop <= size()</tt>
     * <br>
     * post: <tt>size() = old size() - (stop - start)</tt>
     *
     * @param start position at beginning of range of elements to be removed
     * @param stop  stop - 1 is the position at the end of the range of elements
     *              to be removed
     */
    public void removeRange(int start, int stop) {

        // Preconditions
        if (start < 0 || start > stop || stop > this.size) {
            throw new IllegalArgumentException("You must choose a range between 0 and size()");
        }

        // Removes all values from the Start to Last
        if (start == 0 && stop == this.size) {
            this.makeEmpty();
        } else {
            // Get Node at the Start and End
            DoubleListNode<E> startNode = this.getNodeAtPos(start);
            DoubleListNode<E> endNode = this.getNodeAtPos(stop - 1);

            if (start == 0) {
                this.first = endNode.next;
                this.first.prev = null;
            } else if (stop == this.size) {
                this.last = startNode.prev;
                this.last.next = null;
            } else {
                startNode.prev.next = endNode.next;
                endNode.next.prev = startNode.prev;
            }

            this.size -= (stop - start);
        }
    }

    public String toString() {

        StringBuilder sb = new StringBuilder("[");

        if (this.size != 0) {

            DoubleListNode<E> currNode = this.first;
            sb.append(currNode.data);
            currNode = currNode.next;

            while (currNode != null) {
                sb.append(", ");
                sb.append(currNode.data);
                currNode = currNode.next;
            }
        }

        sb.append("]");
        return sb.toString();
    }

    public boolean equals(Object other) {

        if (!(other instanceof LL314<?>)) {
            return false;
        }

        LL314<E> otherList = (LL314<E>) other;

        if (otherList.size != this.size) {
            return false;
        }

        DoubleListNode<E> thisNodeFirst = this.first;
        DoubleListNode<E> otherNodeFirst = otherList.first;

        while (thisNodeFirst != null && otherNodeFirst != null) {

            if (!thisNodeFirst.data.equals(otherNodeFirst.data)) {
                return false;
            }

            thisNodeFirst = thisNodeFirst.next;
            otherNodeFirst = otherNodeFirst.next;
        }

        return true;
    }

    private DoubleListNode<E> getNodeAtPos(int pos) {

        if (pos == 0) {
            return this.first;
        } else if (pos == this.size - 1) {
            return this.last;
        }

        DoubleListNode<E> node;
        int middleIndex = this.size / 2;

        if (pos < middleIndex) {

            node = this.first;
            int counter = 0;

            while (counter < pos) {
                node = node.next;
                counter++;
            }
        } else {

            node = this.last;
            int counter = this.size - 1;

            while (counter > pos) {
                node = node.prev;
                counter--;
            }
        }

        return node;
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

    private class LL314Iterator implements Iterator<E> {

        private DoubleListNode<E> currNode;
        private DoubleListNode<E> lastNode;
        private boolean hasUsedNext;

        public LL314Iterator() {
            this.currNode = LL314.this.first;
        }

        public boolean hasNext() {
            return this.currNode != null;
        }

        public E next() {

            if (!this.hasNext()) {
                throw new IllegalStateException("No Such Elements Left to Get.");
            }

            this.lastNode = this.currNode;
            this.currNode = this.currNode.next;
            this.hasUsedNext = true;

            return this.lastNode.data;
        }

        public void remove() {

            if (!hasUsedNext) {
                throw new IllegalStateException("You have to use next first before remove.");
            }

            if (this.lastNode.prev == null) {
                LL314.this.first = this.lastNode.next;   
            } else {
                this.lastNode.prev.next = this.lastNode.next;
            }

            if (this.currNode == null) {
                LL314.this.last = this.lastNode.prev;
            } else {
                this.currNode = this.lastNode.prev;
            }

            this.hasUsedNext = false;
            LL314.this.size--;
        }
    }
}