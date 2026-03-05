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
     * create a SortedSet out of an unsorted set.
     * 
     * @param other != null
     */
    public SortedSet(ISet<E> other) {

        // Precondition
        if (other == null) {
            throw new IllegalArgumentException("The Parameter Cannot Equal Null");
        }

        this();

        if (other instanceof SortedSet<?>) {
            SortedSet<E> otherSet = (SortedSet<E>) other;
            for (E item : otherSet.myCon) {
                this.myCon.add(item);
            }
        } else {
            for (E item : other) {
                this.add(item);
            }
        }
    }

    /**
     * Return the smallest element in this SortedSet.
     * 
     * pre: size() != 0
     * 
     * @return the smallest element in this SortedSet.
     */
    public E min() {
        // Precondition
        if (this.size() == 0) {
            throw new IllegalArgumentException("Theres is no elements in this Set.");
        }

        return this.myCon.get(0);
    }

    /**
     * Return the largest element in this SortedSet.
     * 
     * pre: size() != 0
     * 
     * @return the largest element in this SortedSet.
     */
    public E max() {

        // Precondition
        if (this.size() == 0) {
            throw new IllegalArgumentException("Theres is no elements in this Set.");
        }

        return this.myCon.get(this.size() - 1);
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

        int left = 0;
        int right = this.size() - 1;

        while (left <= right) {
            int mid = left + ((right - left) / 2);
            int comparedVal = item.compareTo(this.myCon.get(mid));
            if (comparedVal == 0) {
                return false;
            } else if (comparedVal < 0) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        this.myCon.add(left, item);
        return true;
    }

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

        if (otherSet instanceof SortedSet<?>) {

            SortedSet<E> otherSorted = (SortedSet<E>) otherSet;
            Iterator<E> otherIt = otherSorted.iterator();

            Iterator<E> thisIt = this.iterator();

            int oldSize = this.size();
            final boolean IS_UNION = true;
            this.myCon = mergeArrays(thisIt, otherIt, IS_UNION);

            return oldSize != this.size();
        } else {
            return super.addAll(otherSet);
        }
    }

    /**
     * Determine if item is in this set. Using Binary Search
     * 
     * @param item element whose presence is being tested. item != null
     *             Item may not equal null.
     * @return true if this set contains the specified item, false otherwise.
     */
    public boolean contains(E item) {

        // Precondition
        if (item == null) {
            throw new IllegalArgumentException("The Parameter cannot be Null.");
        }

        return binarySearch(item) != -1;
    }

    /**
     * Make this set empty.
     */
    public void clear() {
        this.myCon.clear();
    }

    /**
     * Create a new set that is the difference of this set and otherSet. Return an
     * ISet of elements that are in this Set but not in otherSet. Also called the
     * relative complement. Example: If ISet A contains [X, Y, Z] and ISet B
     * contains [W, Z]then A.difference(B) would return an ISet with elements [X, Y]
     * while B.difference(A) would return an ISet with elements [W]. Neither this
     * set or otherSet are altered as a result of this operation.
     * 
     * @param otherSet != null
     * @return a set that is the difference of this set and otherSet
     */
    public ISet<E> difference(ISet<E> otherSet) {

        // Precondition
        if (otherSet == null) {
            throw new IllegalArgumentException("The Parameter Cannot Equal Null");
        }

        SortedSet<E> diffSet = new SortedSet<>();

        if (!(otherSet instanceof SortedSet<?>)) {
            for (E val : this) {
                if (!otherSet.contains(val)) {
                    diffSet.add(val);
                }
            }
        } else {

            SortedSet<E> otherSorted = (SortedSet<E>) otherSet;
            Iterator<E> otherIt = otherSorted.iterator();

            Iterator<E> thisIt = this.iterator();

            E thisCurrVal = getSafeIteratorNext(thisIt);
            E otherCurrVal = getSafeIteratorNext(otherIt);

            while (thisCurrVal != null && otherCurrVal != null) {
                int comparedVal = thisCurrVal.compareTo(otherCurrVal);
                if (comparedVal < 0) {
                    diffSet.add(thisCurrVal);
                    thisCurrVal = getSafeIteratorNext(thisIt);
                } else if (comparedVal > 0) {
                    otherCurrVal = getSafeIteratorNext(otherIt);
                } else {
                    thisCurrVal = getSafeIteratorNext(thisIt);
                    otherCurrVal = getSafeIteratorNext(otherIt);
                }
            }

            while (thisCurrVal != null) {
                diffSet.add(thisCurrVal);
                thisCurrVal = getSafeIteratorNext(thisIt);
            }
        }

        return diffSet;
    }

    /**
     * Determine if this set is equal to other.
     * Two sets are equal if they have exactly the same elements.
     * The order of the elements does not matter.
     * pre: none
     * 
     * @param other the object to compare to this set
     * @return true if other is a Set and has the same elements as this set
     */
    public boolean equals(Object other) {

        if (this == other) {
            return true;
        }

        if (!(other instanceof ISet<?>)) {
            return false;
        }

        ISet<?> otherSet = (ISet<?>) other;

        if (otherSet.size() != this.size()) {
            return false;
        }

        if (otherSet instanceof SortedSet<?>) {
            Iterator<E> thisIt = this.iterator();
            Iterator<?> otherIt = otherSet.iterator();

            while (thisIt.hasNext()) {
                if (!thisIt.next().equals(otherIt.next())) {
                    return false;
                }
            }

            return true;
        }

        return super.equals(other);
    }

    /**
     * create a new set that is the intersection of this set and otherSet.
     * Neither this set or otherSet are altered as a result of this operation.
     * 
     * @param otherSet != null
     * @return a set that is the intersection of this set and otherSet
     */
    public ISet<E> intersection(ISet<E> otherSet) {

        // Precondition
        if (otherSet == null) {
            throw new IllegalArgumentException("The Item Parameter cannot be null.");
        }

        SortedSet<E> result = new SortedSet<>();
        SortedSet<E> otherSortedSet;
        
        if (!(otherSet instanceof SortedSet<?>)) {
            otherSortedSet = new SortedSet<>(otherSet);
        } else {
            otherSortedSet = (SortedSet<E>) otherSet;
        }

        Iterator<E> thisIt = this.iterator();
        Iterator<E> otherIt = otherSortedSet.iterator();

        final boolean IS_UNION = false;

        result.myCon = mergeArrays(thisIt, otherIt, IS_UNION);
        return result;
    }

    /**
     * Return an Iterator object for the elements of this set.
     * 
     * @return an Iterator object for the elements of this set
     */
    public Iterator<E> iterator() {
        return this.myCon.iterator();
    }

    public boolean remove(E item) {
        // Precondition
        if (item == null) {
            throw new IllegalArgumentException("The Item Parameter cannot be null.");
        }

        int valIndex = this.binarySearch(item);
        if (valIndex != -1) {
            myCon.remove(valIndex);
            return true;
        }

        return false;
    }

    /**
     * Return the number of elements of this set.
     * 
     * @return the number of items in this set
     */
    public int size() {
        return this.myCon.size();
    }

    /**
     * Create a new set that is the union of this set and otherSet. Neither this set
     * or otherSet are altered as a result of this operation.
     * 
     * @param otherSet != null
     * @return - returns a set that is the union of this set and otherSet.
     */
    public ISet<E> union(ISet<E> otherSet) {

        // Precondition
        if (otherSet == null) {
            throw new IllegalArgumentException("You cannot use null as the parameter otherSet");
        }

        SortedSet<E> result = new SortedSet<E>();

        result.addAll(this);
        result.addAll(otherSet);

        return result;
    }

    /**
     * 
     * @param thisIt
     * @param otherIt
     * @return
     */
    private ArrayList<E> mergeArrays(Iterator<E> thisIt, Iterator<E> otherIt, boolean isUnion) {

        ArrayList<E> results = new ArrayList<>();

        E thisCurrVal = getSafeIteratorNext(thisIt);
        E otherCurrVal = getSafeIteratorNext(otherIt);

        while (thisCurrVal != null && otherCurrVal != null) {

            int comparedVal = thisCurrVal.compareTo(otherCurrVal);

            if (comparedVal == 0) {
                results.add(thisCurrVal);
                thisCurrVal = getSafeIteratorNext(thisIt);
                otherCurrVal = getSafeIteratorNext(otherIt);
            } else if (comparedVal > 0) {
                if (isUnion) { results.add(otherCurrVal); }
                otherCurrVal = getSafeIteratorNext(otherIt);
            } else {
                if (isUnion) { results.add(thisCurrVal); }
                thisCurrVal = getSafeIteratorNext(thisIt);
            }
        }

        while (thisCurrVal != null && isUnion) {
            results.add(thisCurrVal);
            thisCurrVal = getSafeIteratorNext(thisIt);
        }

        while (otherCurrVal != null && isUnion) {
            results.add(otherCurrVal);
            otherCurrVal = getSafeIteratorNext(otherIt);
        }

        return results;
    }

    /**
     * 
     * @param val
     * @return
     */
    private int binarySearch(E val) {

        if (this.size() == 0) {
            return -1;
        }

        int start = 0;
        int end = this.size() - 1;

        while (start <= end) {
            int mid = start + ((end - start) / 2);

            if (this.myCon.get(mid).equals(val)) {
                return mid;
            } else if (this.myCon.get(mid).compareTo(val) < 0) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }

        return -1;
    }

    private E getSafeIteratorNext(Iterator<E> it) {

        if (it.hasNext()) {
            return it.next();
        }

        return null;
    }
}