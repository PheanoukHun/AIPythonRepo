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

    /**
     * Creates a empty linked list object.
     */
    public LL314() {
        this.makeEmpty();
    }

    /**
     * Return the size of this list (the number of elements).
     * 
     * Big O Notation: O(1)
     * 
     * @return the number of items in this list
     */
    public int size() {
        return this.size;
    }

    /**
     * Add an item to the end of this list.
     * 
     * pre: item != null
     * post: size() = old size() + 1, get(size() - 1) = item
     * 
     * Big O Notation: O(1)
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
     * Big O Notation: O(1)
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
     * Big O Notation: O(1)
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
     * Big O Notation: O(N)
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
            DoubleListNode<E> oldNode = this.getNodeAt(pos);
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
     * Big O Notation: O(1)
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
     * Big O Notation: O(1)
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
     * Big O Notation: O(N)
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
        DoubleListNode<E> node = this.getNodeAt(pos);
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
     * Big O Notation: O(N)
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
     * Big O Notation: O(N)
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

        DoubleListNode<E> node = this.getNodeAt(pos);

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
     * Big O Notation: O(N)
     *
     * @param pos specifies which element to get
     * @return the element at the specified position in the list
     */
    public E get(int pos) {

        // Preconditions
        if (pos < 0 || pos >= this.size) {
            throw new IllegalArgumentException("You Must use a Pos Value "
                    + "between 0 and size - 1");
        }

        return getNodeAt(pos).data;
    }

    /**
     * Return a sublist of elements in this list from start inclusive to stop
     * exclusive. This list is not changed as a result of this call. elements at
     * positions start through stop - 1 in this list.
     * 
     * pre: 0 <= start <= size(), start <= stop <= size()
     * post: return a list whose size is stop - start and contains the
     * 
     * Big O Notation: O(N)
     *
     * @param start index of the first element of the sublist.
     * @param stop  stop - 1 is the index of the last element of the sublist.
     * @return a list with stop - start elements, the elements are from positions
     *         start inclusive to stop exclusive in this list. If start == stop an
     *         empty list is returned.
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

            DoubleListNode<E> node = this.getNodeAt(start);

            for (int i = start; i < stop; i++) {
                result.add(node.data);
                node = node.next;
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
     * Big O Notation: O(N)
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
     * 
     * pre: 0 <= pos < size(), item != null
     * post: return the index of the first element equal to item starting at pos or
     * -1 if item is not present from position pos onward.
     * 
     * Big O Notation: O(N)
     *
     * @param item the element to search for in the list. Item != null
     * @param pos  the position in the list to start searching from
     * @return starting from the specified position return the index of the first
     *         element equal to item or a -1 if item is not present between pos and
     *         the end of the list.
     */
    public int indexOf(E item, int pos) {

        // Precondition
        if (item == null) {
            throw new IllegalArgumentException("The Value of the new Element cannot be null");
        }

        if (pos < 0 || pos >= this.size) {
            throw new IllegalArgumentException("You must give a position value that must "
                    + "between 0 and size.");
        }

        int counter = pos;
        DoubleListNode<E> currNode = getNodeAt(pos);

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
     * 
     * pre: none
     * post: size() = 0
     * 
     * Big O Notation: O(1)
     */
    public void makeEmpty() {
        this.first = null;
        this.last = null;
        this.size = 0;
    }

    /**
     * Return an Iterator for this list.
     * 
     * pre: none
     * post: return an Iterator object for this List
     * 
     * Big O Notation: O(1)
     */
    public Iterator<E> iterator() {
        return new LL314Iterator();
    }

    /**
     * Remove all elements in this list from start inclusive to stop exclusive.
     * 
     * pre: <tt>0 <= start <= size(), start <= stop <= size()
     * post: size() = old size() - (stop - start)
     *
     * Big O Notation: O(N)
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

            // Makes Sure Elements are Actually being removed.
        } else if (start != stop) {

            DoubleListNode<E> startNode = this.getNodeAt(start);
            DoubleListNode<E> endNode = this.getNodeAt(stop - 1);

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

    /**
     * Return a String version of this list enclosed in square brackets, [].
     * Elements are in order based on position in the list with the first
     * element first. Adjacent elements are separated by comma's.
     *
     * Big O Notation: O(N)
     * 
     * @return a String representation of this IList
     */
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

    /**
     * Determine if this IList is equal to other. Two ILists are equal if they
     * contain the same elements in the same order.
     *
     * Big O Notation: O(N)
     * 
     * @param other The other object to compare this IList to
     * @return true if this IList is equal to other, false otherwise
     */
    public boolean equals(Object other) {

        // Check Based on Class
        if (!(other instanceof IList<?>)) {
            return false;
        }

        IList<?> otherList = (IList<?>) other;

        // Check Based on Size
        if (otherList.size() != this.size) {
            return false;
        }

        // Check based on Each Elements Matching
        Iterator<E> thisIt = this.iterator();
        Iterator<?> otherIt = otherList.iterator();

        while (thisIt.hasNext()) {
            if (!thisIt.next().equals(otherIt.next())) {
                return false;
            }
        }

        return true;
    }

    /**
     * Gets the DoubleListNode object found at the position found at the pos index.
     * 
     * Big O Notation: O(N)
     * 
     * @param pos - the position to insert the data at in the list
     * @return - The DoubleListNode object found at the pos index.
     */
    private DoubleListNode<E> getNodeAt(int pos) {

        DoubleListNode<E> node;

        // Search Based which End is Closest to the Node Position
        if (pos < this.size / 2) {
            node = this.first;
            for (int i = 0; i < pos; i++) {
                node = node.next;
            }
        } else {
            node = this.last;
            for (int i = this.size - 1; i > pos; i--) {
                node = node.prev;
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
         * 
         * pre: none
         * post: data = null, next = null, prev = null
         * 
         * O(1)
         */
        public DoubleListNode() {
            this(null, null, null);
        }

        /**
         * Create a DoubleListNode that holds the specified data
         * and refers to the specified next and previous elements.
         * 
         * pre: none
         * post: this.data = data, this.next = next, this.prev = prev
         * 
         * Big O Notation: O(1)
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

    /**
     * This class creates a custom iterator object that can iterates over the LL314
     * Class.
     * 
     */
    private class LL314Iterator implements Iterator<E> {

        private DoubleListNode<E> currNode;
        private DoubleListNode<E> lastNode;
        private boolean hasUsedNext;

        public LL314Iterator() {
            this.currNode = LL314.this.first;
            this.hasUsedNext = false;
        }

        /**
         * Checks to see if the iterator is able to move to another value.
         * 
         * Big O Notation: O(1)
         * 
         * @return - A boolean value stating the fact whether the iterator is able to
         *         move to the next module or not.
         */
        public boolean hasNext() {
            return this.currNode != null;
        }

        /**
         * Moves the iterators to the Next Node. Returns the value of the past node.
         * 
         * Big O Notation: O(1)
         * 
         * @return - The Value of the Node the iterator was at before being moved.
         */
        public E next() {

            if (!this.hasNext()) {
                throw new IllegalStateException("No Such Elements Left to Get.");
            }

            this.lastNode = this.currNode;
            this.currNode = this.currNode.next;
            this.hasUsedNext = true;

            return this.lastNode.data;
        }

        /**
         * This method removes the previous node that the iterator was pointing at.
         * 
         * pre: next() has been called before this.
         * post: the element most recently returned by next() is removed
         * from the list and the size of the list decreases by 1
         * 
         * Big O Notation: O(1)
         */
        public void remove() {

            if (!hasUsedNext) {
                throw new IllegalStateException("You have to use next first before remove.");
            }

            if (this.lastNode.prev == null) {
                LL314.this.first = this.lastNode.next;
                if (LL314.this.first != null) {
                    LL314.this.first.prev = null;
                }
            } else {
                this.lastNode.prev.next = this.lastNode.next;
            }

            if (this.lastNode.next == null) {
                LL314.this.last = this.lastNode.prev;
            } else {
                this.lastNode.next.prev = this.lastNode.prev;
            }

            this.currNode = this.lastNode.next;

            this.hasUsedNext = false;
            LL314.this.size--;
        }
    }
}