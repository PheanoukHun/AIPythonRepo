/*
 * SortedSet.java - CS 314 Assignment 7
 *
 * By signing my/our name(s) below, I/we affirm that this assignment is my/our
 * own work. I/we have neither given nor received unauthorized assistance on
 * this assignment.
 *
 * Name 1: Pheanouk Hun
 * Email address 1: ph23434@eid.utexas.edu
 * UTEID 1: ph23434
 */

import java.util.Iterator;
import java.util.ArrayList;

/**
 * In this implementation of the ISet interface the elements in the Set are
 * maintained in ascending order.
 *
 * The data type for E must be a type that implements Comparable.
 *
 * Implement methods that were not implemented in AbstractSet
 * and override methods that can be done more efficiently. An ArrayList must
 * be used as the internal storage container. For methods involving two set,
 * if that method can be done more efficiently if the other set is also a
 * SortedSet, then do so.
 */
public class SortedSet<E extends Comparable<? super E>> extends AbstractSet<E> {

    private ArrayList<E> myCon;

    /**
     * create an empty SortedSet
     */
    public SortedSet() {
        this.myCon = new ArrayList<E>();
    }

    /**
     * create a SortedSet out of an unsorted set. <br>
     * 
     * @param other != null
     */
    public SortedSet(ISet<E> other) {

        // Precondition
        if (other == null) {
            throw new IllegalArgumentException("The Parameter Cannot Equal Null");
        }

        this();
        for (E item : other) {
            this.add(item);
        }
    }

    /**
     * Return the smallest element in this SortedSet.
     * <br>
     * pre: size() != 0
     * 
     * @return the smallest element in this SortedSet.
     */
    public E min() {
        return null;
    }

    /**
     * Return the largest element in this SortedSet.
     * <br>
     * pre: size() != 0
     * 
     * @return the largest element in this SortedSet.
     */
    public E max() {
        return null;
    }

    /**
     * Add an item to this set.
     * item != null
     * 
     * @param item the item to be added to this set. item may not equal null.
     * @return true if this set changed as a result of this operation,
     *         false otherwise.
     */
    public boolean add(E item) {

        // Precondition
        if (item == null) {
            throw new IllegalArgumentException("The Item Parameter cannot be null.");
        }

        if (this.contains(item)) {
            return false;
        }

        for (int i = 0; i < this.size(); i++) {
            if (item.compareTo(myCon.get(i)) < 0) {
                myCon.add(i, item);
                return true;
            }
        }

        myCon.add(item);
        return false;
    }

    public void clear() {
        this.myCon = new ArrayList<E>();
    }

    public boolean equals(Object other) {

        boolean equalLen = super.equals(other);

        if (equalLen) {
            SortedSet<?> otherSet = (SortedSet<?>) other;
            Iterator<?> otherIt = otherSet.iterator();
            Iterator<E> thisIt = this.iterator();
            while (thisIt.hasNext() && otherIt.hasNext()) {
                if (!thisIt.next().equals(otherIt.next())) {
                    return false;
                }
            }
        }

        return equalLen;
    }

    public boolean contains(E item) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'contains'");
    }

    public boolean containsAll(ISet<E> otherSet) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'containsAll'");
    }

    public ISet<E> difference(ISet<E> otherSet) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'difference'");
    }

    @Override
    public ISet<E> intersection(ISet<E> otherSet) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'intersection'");
    }

    @Override
    public Iterator<E> iterator() {
        return new SortedSetIterator();
    }

    @Override
    public boolean remove(E item) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'remove'");
    }

    @Override
    public int size() {
        return this.myCon.size();
    }

    @Override
    public ISet<E> union(ISet<E> otherSet) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'union'");
    }

    private class SortedSetIterator implements Iterator<E> {

        private int nextIndex;
        private boolean removeable;

        public boolean hasNext() {
            return nextIndex < myCon.size();
        }

        public E next() {

            if (!hasNext()) {
                throw new IllegalStateException("There are No Elements Left to Use");
            }

            E result = myCon.get(nextIndex);
            removeable = true;
            nextIndex++;
            return result;
        }

        public void remove() {

            if (!removeable) {
                throw new IllegalStateException("You Cannot Call Remove Twice or "
                        + "Remove Before You Have Called Next for the first time.");
            }

            removeable = false;
            nextIndex--;
            myCon.remove(nextIndex);
        }
    }
}