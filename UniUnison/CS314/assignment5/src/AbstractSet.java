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
     * Determine if item is in this set.
     * @param item element whose presence is being tested. item != null
     * Item may not equal null.
     * @return true if this set contains the specified item, false otherwise.
     */
    public boolean contains(E item) {
        
        //Precondition
        if (item == null) {
            throw new IllegalArgumentException("The Parameter cannot be Null.");
        }

        Iterator<E> it = this.iterator();
        while (it.hasNext()) {
            if (item.equals(it.next())) {
                return true;
            }
        }

        return false;
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
     * Determine if this set is equal to other.
     * Two sets are equal if they have exactly the same elements.
     * The order of the elements does not matter.
     * <br>pre: none
     * @param other the object to compare to this set
     * @return true if other is a Set and has the same elements as this set
     */
    public boolean equals(Object other) {
        
        if (this == other) {
            return true;
        }
        
        if (other == null || this.getClass() != other.getClass()) {
            return false;
        }

        ISet<?> otherSet = (ISet<?>) other;
        return this.size() == otherSet.size();
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