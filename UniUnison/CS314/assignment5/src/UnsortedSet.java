/*
 * UnsortedSet.java - CS 314 Assignment 7
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
 * A simple implementation of an ISet.
 * Elements are not in any particular order.
 * Students are to implement methods that were not implemented in AbstractSet
 * and override methods that can be done more efficiently.
 * An ArrayList must be used as the internal storage container.
 *
 */
public class UnsortedSet<E> extends AbstractSet<E> {

    private ArrayList<E> myCon;

    /**
     * Create an empty SortedSet
     * 
     * Big O Notation: O(1)
     */
    public UnsortedSet() {
        this.myCon = new ArrayList<>();
    }

    /**
     * Add an item to this set.
     * 
     * Big O Notation: O(N)
     * 
     * @param item the item to be added to this set. item may not equal null.
     * @return true if this set changed as a result of this operation, false
     *         otherwise.
     */
    public boolean add(E item) {

        // Precondition
        if (item == null) {
            throw new IllegalArgumentException("The Item Parameter cannot be null.");
        }

        // Is it already Inside?
        if (this.contains(item)) {
            return false;
        }

        myCon.add(item);
        return true;
    }

    /**
     * Make this set empty.
     * 
     * Big O Notation: O(1)
     */
    public void clear() {
        this.myCon = new ArrayList<>();
    }

    /**
     * Return an Iterator object for the elements of this set.
     * 
     * Big O Notation: O(1)
     * 
     * @return an Iterator object for the elements of this set
     */
    public Iterator<E> iterator() {
        return this.myCon.iterator();
    }

    /**
     * Return the number of elements of this set.
     * 
     * Big O Notation: O(1)
     * 
     * @return the number of items in this set
     */
    public int size() {
        return this.myCon.size();
    }

    /**
     * Create a new set that is the difference of this set and otherSet. Return an
     * ISet of elements that are in this Set but not in otherSet. Also called the
     * relative complement. Example: If ISet A contains [X, Y, Z] and ISet B
     * contains [W, Z]then A.difference(B) would return an ISet with elements [X, Y]
     * while B.difference(A) would return an ISet with elements [W]. Neither this
     * set or otherSet are altered as a result of this operation.
     * 
     * Big O Notation: O(N^2)
     * 
     * @param otherSet != null
     * @return a set that is the difference of this set and otherSet
     */
    public ISet<E> difference(ISet<E> otherSet) {

        ISet<E> results = new UnsortedSet<>();

        // Add all items not found in otherSet
        for (E item : this) {
            if (!otherSet.contains(item)) {
                results.add(item);
            }
        }

        return results;
    }

    /**
     * Create a new set that is the union of this set and otherSet. Neither this set
     * or otherSet are altered as a result of this operation.
     * 
     * Big O Notation: O(N^2)
     * 
     * @param otherSet != null
     * @return - returns a set that is the union of this set and otherSet.
     */
    public ISet<E> union(ISet<E> otherSet) {

        UnsortedSet<E> results = new UnsortedSet<>();

        results.addAll(this);
        results.addAll(otherSet);

        return results;
    }
}