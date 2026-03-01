/*
 * AbstractSet.java - CS 314 Assignment 7
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

/**
 * Students are to complete this class.
 * Students should implement as many methods as they can using the Iterator
 * from the iterator method and the other methods.
 */
public abstract class AbstractSet<E> implements ISet<E> {

    /*
     * DELETE THIS COMMENT FROM YOUR SUBMISSION.
     *
     * RECALL:
     *
     * NO INSTANCE VARIABLES ALLOWED.
     *
     * NO DIRECT REFERENCE TO UnsortedSet OR SortedSet ALLOWED.
     * (In other words the data types UnsortedSet and SortedSet
     * will not appear anywhere in this class.)
     *
     * NO DIRECT REFERENCES to ArrayList or other Java Collections.
     *
     * NO METHODS ADDED other than those in ISet and Object.
     */

    /**
     * A union operation. Add all items of otherSet that
     * are not already present in this set to this set.
     * 
     * @param otherSet != null
     * @return true if this set changed as a result of this operation,
     *         false otherwise.
     */
    public boolean addAll(ISet<E> otherSet) {

        // Precondition
        if (otherSet == null) {
            throw new IllegalArgumentException("The Parameter Other Set cannot be Null.");
        }

        boolean changed = false;
        Iterator<E> it = this.iterator();

        while (it.hasNext()) {
            changed = changed || add(it.next());
        }

        return changed;
    }

    /**
     * Determine if all of the elements of otherSet are in this set.
     *  
     * @param otherSet != null
     * @return true if this set contains all of the elements in otherSet,
     *         false otherwise.
     */
    public boolean containsAll(ISet<E> otherSet) {

        // Precondition
        if (otherSet == null) {
            throw new IllegalArgumentException("The Parameter Other Set cannot be Null.");
        }

        boolean same = true;
        Iterator<E> it = this.iterator();

        while (it.hasNext()) {
            same = same && contains(it.next());
        }

        return same;
    }

    /**
     * Return a String version of this set.
     * Format is (e1, e2, ... en)
     * 
     * @return A String version of this set.
     */
    public String toString() {
        StringBuilder result = new StringBuilder();
        String seperator = ", ";
        result.append("(");

        Iterator<E> it = this.iterator();
        while (it.hasNext()) {
            result.append(it.next());
            result.append(seperator);
        }
        // get rid of extra separator
        if (this.size() > 0) {
            result.setLength(result.length() - seperator.length());
        }

        result.append(")");
        return result.toString();
    }
}