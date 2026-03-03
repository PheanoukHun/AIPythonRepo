/*
 * UnsortedSet.java - CS 314 Assignment 7
 *
 * By signing my/our name(s) below, I/we affirm that this assignment is my/our
 * own work. I/we have neither given nor received unauthorized assistance on
 * this assignment.
 *
 * Name 1:
 * Email address 1:
 * UTEID 1:
 *
 * Name 2:
 * Email address 2:
 * UTEID 2:
 */

import java.util.Iterator;
import java.util.ArrayList;

/**
 * A simple implementation of an ISet.
 * Elements are not in any particular order.
 * Students are to implement methods that were not implemented in AbstractSet
 * and override methods that can be done more efficiently.
 * An ArrayList must be used as the internal storage container.
 *
 */
public class UnsortedSet<E> extends AbstractSet<E> {

    private ArrayList<E> myCon;

    public UnsortedSet() {
        myCon = new ArrayList<>();
    }

    public UnsortedSet(ISet<E> other) {

        if (other == null) {
            throw new IllegalArgumentException("The Parameter Cannot Equal Null");
        }

        this();

        for (E item : other) {
            this.add(item);
        }
    }

    public boolean add(E item) {

        // Precondition
        if (item == null) {
            throw new IllegalArgumentException("The Item Parameter cannot be null.");
        }

        if (this.contains(item)) {
            return false;
        }

        myCon.add(item);
        return true;
    }

    public void clear() {
        myCon = new ArrayList<>();
    }

    public ISet<E> difference(ISet<E> otherSet) {

        ISet<E> results = new UnsortedSet<>();
        Iterator<E> it = this.iterator();

        while (it.hasNext()) {
            E currVal = it.next();
            if (!otherSet.contains(currVal)) {
                results.add(currVal);
            }
        }

        return results;
    }

    public Iterator<E> iterator() {
        return new UnsortedSetIterator();
    }

    public boolean remove(E item) {
        // Precondition
        if (item == null) {
            throw new IllegalArgumentException("The Item Parameter cannot be null.");
        }

        if (this.contains(item)) {
            myCon.remove(item);
            return true;
        }

        return false;
    }

    public int size() {
        return this.myCon.size();
    }

    public ISet<E> union(ISet<E> otherSet) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'union'");
    }

    private class UnsortedSetIterator implements Iterator<E> {

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