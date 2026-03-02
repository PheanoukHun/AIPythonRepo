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

    @Override
    public boolean add(E item) {
        
        if (this.contains(item)) {
        }
        
        myCon.add(item);
        return true;
    }

    @Override
    public void clear() {
        myCon = new ArrayList<>();
    }

    @Override
    public boolean containsAll(ISet<E> otherSet) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'containsAll'");
    }

    @Override
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
        return new UnsortedSetIterator();
    }

    @Override
    public boolean remove(E item) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'remove'");
    }

    @Override
    public int size() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'size'");
    }

    @Override
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